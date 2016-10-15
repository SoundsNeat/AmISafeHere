package edu.csupomona.cs480.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.util.MathArrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;


/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;
	@Autowired
	private Random random;

	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs480/ping
	 */
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK";
	}

	/**
	 * @author Wai Phyo (William)
	 *
	 * Simple function to return random number
	 * @return random number
	 */
	@RequestMapping(value = "/SoundsNeat/Random", method = RequestMethod.GET)
	int getRandomNumber(){
		return random.nextInt();
	}
	/**
	 * @author Ben Chin
	 *
	 * A simple function to return the danger level of a given location
	 * @param String location
	 * @return danger level (0 to 4 inclusive)
	 */
	@RequestMapping(value = "/SoundsNeat/Danger/{location}", method = RequestMethod.GET)
	int getDangerLevel(@PathVariable("location") String location){
		return random.nextInt(5);
	}

	/**
	 * @author Jonathan Fetzer
	 *
	 * A simple function to compare relative danger level of two locations
	 * @param String cityA, cityB
	 * @return int to indicate whether cityA danger > cityB danger
	 */
	@RequestMapping(value = "/SoundsNeat/Danger/{cityA, cityB}", method = RequestMethod.GET)
	int compare(@PathVariable("cityA") String cityA, @PathVariable("cityB") String cityB){
		int cityADanger = getDangerLevel(cityA);
		int cityBDanger = getDangerLevel(cityB);
		if(cityADanger > cityBDanger) {
			return  1;
		} else if(cityADanger < cityBDanger) {
			return  -1;
		} else return 0;
	}
	
	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs480/user/user101
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs480/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setMajor(major);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

	@RequestMapping(value = "/480/manga", method = RequestMethod.GET)
	String readKingDomManga() throws IOException {
		Document doc = Jsoup.connect("http://www.mangareader.net/kingdom/1").get();
		String title = doc.title();
		return title;
	}

    /**
     * @author Connor
     *
     * Simple function that uses Commons Math3
     * finds the total distance between all the elements in two arrays
     */
    @RequestMapping(value = "/480/M_A_T_H_B_O_Y_S", method = RequestMethod.GET)
    int commonsMath() {
        int [] point1 = {12,18,12,50,7};
        int [] point2 = {50,25,12,13,8};
        return MathArrays.distance1(point1, point2);
    }


}
