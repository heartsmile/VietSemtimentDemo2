package spring.fb.demo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.fb.demo.services.VietSentiService;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Post;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	private FacebookClient facebookClient23;
	
	private VietSentiService vsService;

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

		if (pageID == null || pageID == "") {
			
			//get user's feed
			listPosts = facebookClient23.fetchConnection("me/feed", Post.class,
					Parameter.with("limit", 100));
		} else {
			
			//get page's feed
			listPosts = facebookClient23.fetchConnection(pageID + "/feed",
					Post.class, Parameter.with("limit", 100));

		}
		
		vsService = new VietSentiService();
		vsService.SentimentDemo();
		

		for(Post post : listPosts.getData()){
			if(post.getCommentsCount() > 0){
				
			}
		}
		
		model.addAttribute("listPosts", listPosts.getData());

		return "home";
	}

}
