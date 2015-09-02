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

import com.alpha.languageexchange.dao.MessageDao;
import com.alpha.languageexchange.model.Message;
import com.alpha.languageexchange.model.User;
import com.alpha.languageexchange.util.JSONResponseUtil;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Resource
	private MessageDao messageDao;

	@RequestMapping(method = RequestMethod.POST)
	protected void create(HttpServletResponse resp, @RequestParam String senderEmail,
			@RequestParam(required = false) String senderOAuth, @RequestParam String senderName, @RequestParam String receiverEmail,
			@RequestParam(required = false) String receiverOAuth, String message) throws IOException {
		System.out.println("message create start");
		User sender = new User();
		User receiver = new User();
		sender.setUserEmail(senderEmail);
		sender.setoAuth(senderOAuth);
		sender.setUserName(senderName);
		receiver.setUserEmail(receiverEmail);
		receiver.setoAuth(receiverOAuth);

		messageDao.create(sender, receiver, message);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("success");
	}

	@RequestMapping(value = "/list")
	protected ResponseEntity<Object> read(HttpServletResponse resp, @RequestParam String receiverEmail,
			@RequestParam(required = false) String receiverOAuth) throws IOException {
		System.out.println("read message start");
		User receiver = new User();
		receiver.setUserEmail(receiverEmail);
		receiver.setoAuth(receiverOAuth);
		System.out.println(receiverEmail);
		if(receiverOAuth != null) System.out.println(receiverOAuth);
		List<Message> messages = messageDao.read(receiver);
		System.out.println("" + messages);
		return JSONResponseUtil.getJSONResponse(messages, HttpStatus.OK);
	}
}
