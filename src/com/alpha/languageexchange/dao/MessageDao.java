package com.alpha.languageexchange.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.alpha.languageexchange.model.Message;
import com.alpha.languageexchange.model.User;

@Repository
public class MessageDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public void create(User sender, User receiver, String message) {
		String sql = "INSERT INTO MESSAGE (senderEmail, senderOAuth, senderName, receiverEmail, receiverOAuth, message, sendTime) VALUES(?,?,?,?,?,?,default)";
		getJdbcTemplate().update(sql, sender.getUserEmail(), sender.getoAuth(), sender.getUserName(),
				receiver.getUserEmail(), receiver.getoAuth(), message);
	}

	public List<Message> read(User receiver) {
		// TODO 메시지 개수 확장
		if (receiver.getoAuth() == null) {
			String sql = "SELECT * FROM MESSAGE WHERE receiverEmail = ? AND receiverOauth IS NULL ORDER BY sendTime DESC LIMIT 20;";
			return getJdbcTemplate().query(
					sql,
					(rs, rowNum) -> new Message(rs.getInt("messageId"), rs.getString("senderEmail"), rs
							.getString("senderOAuth"), rs.getString("senderName"), rs.getString("message"), rs
							.getString("sendTime")), receiver.getUserEmail());
		} else {
			String sql = "SELECT * FROM MESSAGE WHERE receiverEmail = ? AND receiverOauth = ? ORDER BY sendTime DESC LIMIT 20;";
			return getJdbcTemplate().query(
					sql,
					(rs, rowNum) -> new Message(rs.getInt("messageId"), rs.getString("senderEmail"), rs
							.getString("senderOAuth"), rs.getString("senderName"), rs.getString("message"), rs
							.getString("sendTime")), receiver.getUserEmail(), receiver.getoAuth());
		}
	}
}
