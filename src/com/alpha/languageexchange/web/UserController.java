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
			@RequestParam String userPassword, @RequestParam String userGender, @RequestParam int userAge,
			@RequestParam String oAuth) throws IOException {
		if (oAuth.equals(""))
			oAuth = null;
		userDao.createUser(new User(userEmail, userName, userPassword, userGender, userAge, oAuth));
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		out.print("success");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	protected void update(HttpSession session, HttpServletResponse resp, @RequestParam String userEmail,
			@RequestParam String userName, @RequestParam String userPassword, @RequestParam String userGender,
			@RequestParam int userAge, @RequestParam String userIntro,
			@RequestParam("profileImage") MultipartFile profileImage) throws IOException {
		String rootPath = session.getServletContext().getRealPath("/");
		User user = new User();
		user.setUserEmail(userEmail);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setUserGender(userGender);
		user.setUserAge(userAge);
		user.setUserIntro(userIntro);

		String path = rootPath + "img/profile/" + userEmail;
		profileImage.transferTo(new File(path));
		user.setUserImage(path);

		userDao.updateUser(user);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		out.print("success");
	}

	@RequestMapping("/timeline")
	protected ResponseEntity<Object> timeline(HttpServletResponse resp) throws IOException {
		List<User> users = userDao.readTimeline();
		return JSONResponseUtil.getJSONResponse(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected ResponseEntity<Object> login(HttpServletResponse resp, @RequestParam String userEmail,
			@RequestParam String userPassword, @RequestParam String oAuth) throws IOException {
		if (oAuth.equals(""))
			oAuth = null;
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
}
