package com.alpha.languageexchange.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.languageexchange.dao.UserDao;
import com.alpha.languageexchange.model.User;
import com.alpha.languageexchange.util.JSONResponseUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserDao userDao;

	@RequestMapping(method = RequestMethod.POST)
	protected void create(HttpServletResponse resp, @RequestParam String userEmail, @RequestParam String userName,
			@RequestParam(required = false) String userPassword, @RequestParam String userGender,
			@RequestParam int userAge, @RequestParam(required = false) String oAuth) throws IOException {
		User user = new User(userEmail, userName, userPassword, userGender, userAge, oAuth);
		if (user.getoAuth() == null){
//			user.setUserImage("http://10.0.3.2:8080/img/profile/default_profile_image.png");
			user.setUserImage("http://125.209.198.129/img/profile/default_profile_image.png");
		}
		else if (user.getoAuth().equals("facebook")){
//			user.setUserImage("http://10.0.3.2:8080/img/profile_facebook/default_profile_image.png");
			user.setUserImage("http://125.209.198.129/img/profile_facebook/default_profile_image.png");
		}else{
			user.setUserImage("http://125.209.198.129/img/profile/default_profile_image.png");
		}
		userDao.createUser(user);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("success");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	protected void update(HttpSession session, HttpServletResponse resp, @RequestParam String userEmail,
			@RequestParam String userName, @RequestParam(required = false) String userPassword,
			@RequestParam String userGender, @RequestParam int userAge,
			@RequestParam(required = false) String userIntro, @RequestParam(required = false) String oAuth,
			@RequestParam(value = "userImage", required = false) MultipartFile userImage) throws IOException {
		String rootPath = session.getServletContext().getRealPath("/");
		User user = new User();
		user.setUserEmail(userEmail);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setUserGender(userGender);
		user.setUserAge(userAge);
		user.setUserIntro(userIntro);
		user.setoAuth(oAuth);

		String path = null;
		String returnPath = null;
		if (user.getoAuth() == null || user.getoAuth().equals("")) {
			path = rootPath + "img/profile/" + user.getUserEmail() + ".png";
//			returnPath = "http://10.0.3.2:8080/img/profile/" + user.getUserEmail() + ".png";
			returnPath = "http://125.209.198.129/img/profile/" + user.getUserEmail() + ".png";
		}

		if (user.getoAuth() != null && user.getoAuth().equals("facebook")) {
			path = rootPath + "img/profile_facebook/" + user.getUserEmail() + ".png";
//			returnPath = "http://10.0.3.2:8080/img/profile_facebook/" + user.getUserEmail() + ".png";
			returnPath = "http://125.209.198.129/img/profile_facebook/" + user.getUserEmail() + ".png";
		}
		userImage.transferTo(new File(path));
		user.setUserImage(returnPath);
		userDao.updateUser(user);

		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		out.print(returnPath);
		// TODO
		// return JSONResponseUtil.getJSONResponse(user, HttpStatus.OK);
	}

	@RequestMapping("/timeline")
	protected ResponseEntity<Object> timeline(HttpServletResponse resp) throws IOException {
		List<User> users = userDao.readTimeline();
		return JSONResponseUtil.getJSONResponse(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected ResponseEntity<Object> login(HttpServletResponse resp, @RequestParam String userEmail,
			@RequestParam(required = false) String userPassword, @RequestParam(required = false) String oAuth)
			throws IOException {
		User user = userDao.login(new User(userEmail, userPassword, oAuth));
		// login 실패
		if (user == null)
			return JSONResponseUtil.getJSONResponse(user, HttpStatus.UNAUTHORIZED);
		return JSONResponseUtil.getJSONResponse(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	protected ResponseEntity<Object> search(HttpServletResponse resp, @RequestParam String query) throws IOException {
		List<User> users = userDao.search(query);
		return JSONResponseUtil.getJSONResponse(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/match", method = RequestMethod.POST)
	protected ResponseEntity<Object> match(HttpServletResponse resp, @RequestParam(required = false) String userEmail,
			@RequestParam(required = false) String oAuth, @RequestParam String userNative,
			@RequestParam String userPracticing) throws IOException {
		User user = new User();
		user.setUserEmail(userEmail);
		user.setoAuth(oAuth);
		user.setUserNative(userNative);
		user.setUserPracticing(userPracticing);
		userDao.updateLanguage(user);
		List<User> users = userDao.match(user);
		return JSONResponseUtil.getJSONResponse(users, HttpStatus.OK);
	}

	@RequestMapping("/check")
	protected void userCheck(HttpServletResponse resp, @RequestParam String userEmail) throws IOException {
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		if (userDao.findUserByUserEmail(userEmail) == null) {
			out.print("success");
		} else {
			out.print("fail");
		}
	}
}
