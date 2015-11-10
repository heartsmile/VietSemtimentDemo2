package spring.fb.demo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.fb.demo.dto.PageInfo;
import spring.fb.demo.dto.PostData;
import app.server.handling.ServerInterf;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.restfb.types.User;
import com.restfb.types.Page;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private static final int MAX_POST_LIMITED = 50;

	private FacebookClient facebookClient23;
	private ServerInterf server;
	private Registry myRegis;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome home!");
		try {
			if (myRegis == null) {
				myRegis = LocateRegistry.getRegistry("127.0.0.1");
			}
			if (server == null) {
				server = (ServerInterf) myRegis.lookup("server");
			}
			if (server != null) {
				System.out.println("qtran: " + server.hello());
				System.out
						.println("This is a demo: "
								+ server.runAnalyzeSentiment(
										"Hôm nay anh không vui và cũng không hạnh phúc tí nào em à!",
										true));
			} else {
				return "error";
			}
		} catch (RemoteException e) {
			server = null;
			logger.info("(RemoteException) Failed to find server.");
			return "error";
		} catch (NotBoundException e) {
			server = null;
			logger.info("(NotBoundException) Failed to find server.");
			return "error";
		}
		return "home";
	}

	@RequestMapping(value = "/submitAccessToken", method = RequestMethod.POST)
	public String ProcessAccessToken(Model model, HttpServletRequest req) {
		logger.info("Process form");

		Connection<Post> listPosts;

		String userAT = req.getParameter("userAccessToken");
		String pageID = req.getParameter("pageID");

		facebookClient23 = new DefaultFacebookClient(userAT,
				Version.VERSION_2_3);

		if (pageID == null || pageID == "") {

			// get user's feed
			listPosts = facebookClient23.fetchConnection("me/feed", Post.class,
					Parameter.with("limit", MAX_POST_LIMITED));
			Connection<User> user = facebookClient23.fetchConnection("me",
					User.class, Parameter.with("fields", "id,name"));
			if (user != null && user.getData().size() > 0) {
				String userName = user.getData().get(0).getFirstName() + " "
						+ user.getData().get(0).getLastName();
				req.setAttribute("userID", userName);
			}
		} else {

			// get page's feed
			listPosts = facebookClient23.fetchConnection(pageID + "/feed",
					Post.class, Parameter.with("limit", MAX_POST_LIMITED));
			/*
			 * PageInfo page = facebookClient23.fetchObject("485290711519463",
			 * PageInfo.class, Parameter.with(null, null)); if(page != null){
			 * req.setAttribute("userID", page.getPageName()); }
			 */
		}

		List<PostData> listPostDatas = new ArrayList<>();
		PostData postData;

		for (Post post : listPosts.getData()) {
			postData = new PostData();
			postData.setCaption(post.getCaption());
			postData.setComments(post.getComments());
			postData.setCreatedTime(post.getCreatedTime());
			postData.setDescription(post.getDescription());
			postData.setLikes(post.getLikes());
			postData.setLikesCount(post.getLikesCount());
			postData.setMessage(post.getMessage());
			postData.setPostID(post.getId());
			postData.setStory(post.getStory());
			postData.setUpdatedTime(post.getUpdatedTime());
			try {
				StringBuffer bufferItem = new StringBuffer();
				
				bufferItem.append(post.getDescription() != null ? post.getDescription() : "");
				bufferItem.append(post.getMessage() != null ? post.getMessage() : "");
				bufferItem.append(post.getCaption() != null ? post.getCaption() : "");
				bufferItem.append(post.getStory() != null ? post.getStory() : "");

				// analyze all comments
				if (post.getCommentsCount() > 0) {
					
					for (Comment cmt : post.getComments().getData()) {
						bufferItem.append(". ");
						bufferItem.append(cmt.getMessage());
						if (cmt.getCommentCount() > 0) {
							for (Comment subCmt : cmt.getComments().getData()) {
								bufferItem.append(". ");
								bufferItem.append(subCmt.getMessage());
							}
						}
					}
				}
				postData.setSentimentScore(postData.getSentimentScore()
						+ server.runAnalyzeSentiment(bufferItem.toString(),
								true));
			} catch (RemoteException e) {
				e.printStackTrace();
				return "error";
			}

			listPostDatas.add(postData);
		}

		model.addAttribute("listPostDatas", listPostDatas);

		return "home";
	}

}
