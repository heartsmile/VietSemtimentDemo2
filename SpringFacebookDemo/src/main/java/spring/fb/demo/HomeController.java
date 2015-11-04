package spring.fb.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.fb.demo.dto.PostData;
import spring.fb.demo.spellchecker.Checker;
import spring.fb.demo.vietsentiment.VietSentiData;
import vn.hus.nlp.tokenizer.VietTokenizer;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Comment;
import com.restfb.types.Post;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	private FacebookClient facebookClient23;
	private VietTokenizer tokenizer = new VietTokenizer();
	private Checker check = new Checker();

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome home!");

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
		
		//Viet Sentiment
		VietSentiData.init();
		
		if (pageID == null || pageID == "") {
			
			//get user's feed
			listPosts = facebookClient23.fetchConnection("me/feed", Post.class,
					Parameter.with("limit", 100));
		} else {
			
			//get page's feed
			listPosts = facebookClient23.fetchConnection(pageID + "/feed",
					Post.class, Parameter.with("limit", 100));

		}
		/*if (listPosts.getData().get(1).getComments() != null) {
			for (Comment comment : listPosts.getData().get(1).getComments()
					.getData()) {
				System.out.println(comment.getMessage());
			}
		}*/

		for(Post post : listPosts.getData()){
			if(post.getCommentsCount() > 0){
				
			}
		}
		
		model.addAttribute("listPosts", listPosts.getData());

		return "home";
	}

}
