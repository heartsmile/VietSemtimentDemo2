package spring.fb.demo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.fb.demo.services.VietSentiService;
import app.server.handling.ServerInterf;

import com.restfb.FacebookClient;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	private FacebookClient facebookClient23;
	
	//@Autowired
	private VietSentiService vsService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome home!");
		ServerInterf server;
		Registry myRegis;
		try {
			myRegis = LocateRegistry.getRegistry("127.0.0.1");
			server = (ServerInterf) myRegis.lookup("server");
		} catch (RemoteException e) {
			server = null;
		} catch (NotBoundException e) {
			server = null;
		}
		
		if(server != null){
			try {
				System.out.println("qtran: " + server.hello());
				
				System.out.println("This is a demo: " + server.runAnalyze("Hôm nay anh không vui và cũng không hạnh phúc tí nào em à!"));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		//vsService = new VietSentiService();
		return "home";
	}

	@RequestMapping(value = "/submitAccessToken", method = RequestMethod.POST)
	public String ProcessAccessToken(Model model, HttpServletRequest req) {
		logger.info("Process form");
		
		//vsService = new VietSentiService();
		//vsService.SentimentDemo();

		/*Connection<Post> listPosts;

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
		
		
		

		for(Post post : listPosts.getData()){
			if(post.getCommentsCount() > 0){
				
			}
		}
		
		model.addAttribute("listPosts", listPosts.getData());*/

		return "home";
	}

}
