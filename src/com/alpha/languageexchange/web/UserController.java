package com.alpha.languageexchange.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alpha.languageexchange.dao.UserDao;
import com.alpha.languageexchange.model.User;
import com.alpha.languageexchange.util.JSONResponseUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserDao userDao;

	@RequestMapping(method = RequestMethod.POST)
	protected void create(HttpServletResponse resp, @RequestParam String userEmail,
			@RequestParam String userName, @RequestParam String userPassword, @RequestParam String userGender,
			@RequestParam int userAge, @RequestParam String oAuth) throws IOException {
		if(oAuth.equals(""))
			oAuth = null;
		userDao.createUser(new User(userEmail, userName, userPassword, userGender, userAge, oAuth));
		System.out.println("userEmail : " + userEmail);
		System.out.println("userName : " + userName);
		System.out.println("userPassword : " + userPassword);
		System.out.println("userGender : " + userGender);
		System.out.println("userAge : " + userAge);
		System.out.println("oAuth : " + oAuth);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		resp.setContentType("application/json;charset=UTF-8");

		PrintWriter out = resp.getWriter();

		out.print("success");
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	protected void update(HttpServletResponse resp, @RequestParam String userEmail,
			@RequestParam String userName, @RequestParam String userPassword, @RequestParam String userGender,
			@RequestParam int userAge, @RequestParam String userIntro) throws IOException {
		User user = new User();
		user.setUserEmail(userEmail);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setUserGender(userGender);
		user.setUserAge(userAge);
		user.setUserIntro(userIntro);
		
		userDao.updateUser(user);
		
		System.out.println("userEmail : " + userEmail);
		System.out.println("userName : " + userName);
		System.out.println("userPassword : " + userPassword);
		System.out.println("userGender : " + userGender);
		System.out.println("userAge : " + userAge);
		System.out.println("userIntro : " + userIntro);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		resp.setContentType("application/json;charset=UTF-8");

		PrintWriter out = resp.getWriter();

		out.print("success");
	}
	
	@RequestMapping("/timeline")
	protected ResponseEntity<Object> timeline(HttpServletResponse resp) throws IOException {
		List<User> users = userDao.readTimeline();
		// User user = userDao.findUserByUserEmail(userEmail);
		// resp.setContentType("application/json;charset=UTF-8");
		System.out.println(users);
//		PrintWriter out = resp.getWriter();
//		Gson gson = new Gson();
		// if (user != null) {
		// out.print(gson.toJson(user));
		// }
		// else{
		// out.print(gson.toJson("fail"));
		// }

//		out.print(gson.toJson("fail"));
		return JSONResponseUtil.getJSONResponse(users, HttpStatus.OK);
	}

	@RequestMapping("/login")
	protected void login(HttpServletResponse resp, @RequestParam String userEmail) throws IOException {
		// User user = userDao.findUserByUserEmail(userEmail);
		// resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		// if (user != null) {
		// out.print(gson.toJson(user));
		// }
		// else{
		// out.print(gson.toJson("fail"));
		// }

		out.print(gson.toJson("fail"));
	}
}
