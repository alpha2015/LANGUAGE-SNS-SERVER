package com.alpha.languageexchange.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.alpha.languageexchange.model.User;

@Repository
public class UserDao extends JdbcDaoSupport {
	@Resource
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public void createUser(User user) {
		String sql = "INSERT INTO USERS VALUES(?,?,?,?,?,?,?,null,null,null,default, default)";
		getJdbcTemplate().update(sql, user.getUserImage(), user.getUserEmail(), user.getUserName(),
				user.getUserPassword(), user.getUserGender(), user.getUserAge(), user.getoAuth());
	}

	public User findUserByUserEmail(String userEmail) {
		String sql = "SELECT * FROM USERS WHERE userEmail=?";

		try {
			return getJdbcTemplate().queryForObject(
					sql,
					(rs, rowNum) -> new User(rs.getString("userImage"), rs.getString("userEmail"), rs
							.getString("userName"), rs.getString("userPassword"), rs.getString("userGender"), rs
							.getInt("userAge"), rs.getString("oAuth"), rs.getString("userNative"), rs
							.getString("userPracticing"), rs.getString("userIntro"), rs.getString("userCreateDate"), rs
							.getString("userUpdateDate")), userEmail);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public User userCheckForSignUp(String userEmail) {
		String sql = "SELECT * FROM USERS WHERE userEmail=? and oAuth IS NULL";

		try {
			return getJdbcTemplate().queryForObject(
					sql,
					(rs, rowNum) -> new User(rs.getString("userImage"), rs.getString("userEmail"), rs
							.getString("userName"), rs.getString("userPassword"), rs.getString("userGender"), rs
							.getInt("userAge"), rs.getString("oAuth"), rs.getString("userNative"), rs
							.getString("userPracticing"), rs.getString("userIntro"), rs.getString("userCreateDate"), rs
							.getString("userUpdateDate")), userEmail);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updateUser(User user) {
		String sql;
		if (user.getoAuth() == null) {
			sql = "UPDATE USERS SET userImage = ?, userName = ?, userPassword = ?, userGender = ?, userAge = ?, userIntro = ?, userUpdateDate = DEFAULT WHERE userEmail = ? and oAuth IS NULL";
			getJdbcTemplate().update(sql, user.getUserImage(), user.getUserName(), user.getUserPassword(),
					user.getUserGender(), user.getUserAge(), user.getUserIntro(), user.getUserEmail());
		} else {
			sql = "UPDATE USERS SET userImage = ?, userName = ?, userPassword = ?, userGender = ?, userAge = ?, userIntro = ?, userUpdateDate = DEFAULT WHERE userEmail = ? and oAuth = ?";
			getJdbcTemplate().update(sql, user.getUserImage(), user.getUserName(), user.getUserPassword(),
					user.getUserGender(), user.getUserAge(), user.getUserIntro(), user.getUserEmail(), user.getoAuth());
		}
	}

	public List<User> readTimeline() {
		String sql = "SELECT * FROM USERS WHERE userIntro IS NOT NULL ORDER BY userUpdateDate DESC LIMIT 20;";

		return getJdbcTemplate().query(
				sql,
				(rs, rowNum) -> new User(rs.getString("userImage"), rs.getString("userEmail"),
						rs.getString("userName"), rs.getString("userGender"), rs.getInt("userAge"), rs.getString("oAuth"), rs
								.getString("userNative"), rs.getString("userPracticing"), rs.getString("userIntro"), rs
								.getString("userUpdateDate")));
	}

	public User login(User user) {
		String sql;
		if (user.getoAuth() == null || user.getoAuth().equals("")) {
			sql = "SELECT * FROM USERS WHERE userEmail= ? and userPassword = ? and oAuth IS NULL;";
			try {
				return getJdbcTemplate().queryForObject(
						sql,
						(rs, rowNum) -> new User(rs.getString("userImage"), rs.getString("userEmail"), rs
								.getString("userName"), rs.getString("userPassword"), rs.getString("userGender"), rs
								.getInt("userAge"), rs.getString("oAuth"), rs.getString("userNative"), rs
								.getString("userPracticing"), rs.getString("userIntro"),
								rs.getString("userCreateDate"), rs.getString("userUpdateDate")), user.getUserEmail(),
						user.getUserPassword());
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
		} else {
			sql = "SELECT * FROM USERS WHERE userEmail= ? and userPassword IS NULL and oAuth = ?;";
			try {
				return getJdbcTemplate().queryForObject(
						sql,
						(rs, rowNum) -> new User(rs.getString("userImage"), rs.getString("userEmail"), rs
								.getString("userName"), rs.getString("userPassword"), rs.getString("userGender"), rs
								.getInt("userAge"), rs.getString("oAuth"), rs.getString("userNative"), rs
								.getString("userPracticing"), rs.getString("userIntro"),
								rs.getString("userCreateDate"), rs.getString("userUpdateDate")), user.getUserEmail(),
						user.getoAuth());
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
		}
	}

	public List<User> search(String query) {
		String keyword = "userName like \"%" + query + "%\" OR userEmail like \"%" + query + "%\"";
		String sql = "SELECT * FROM USERS WHERE (" + keyword + ") ORDER BY userUpdateDate DESC LIMIT 20;";
		return getJdbcTemplate().query(
				sql,
				(rs, rowNum) -> new User(rs.getString("userImage"), rs.getString("userEmail"),
						rs.getString("userName"), rs.getString("userGender"), rs.getInt("userAge"), rs.getString("oAuth"), rs
								.getString("userNative"), rs.getString("userPracticing"), rs.getString("userIntro"), rs
								.getString("userUpdateDate")));
	}

	public List<User> match(User user) {
		String sql = "SELECT * FROM USERS WHERE userNative = ? AND userPracticing = ? ORDER BY userUpdateDate DESC LIMIT 20;";
		return getJdbcTemplate().query(
				sql,
				(rs, rowNum) -> new User(rs.getString("userImage"), rs.getString("userEmail"),
						rs.getString("userName"), rs.getString("userGender"), rs.getInt("userAge"), rs
								.getString("userNative"), rs.getString("userPracticing"), rs.getString("userIntro"), rs.getString("oAuth"), rs
								.getString("userUpdateDate")), user.getUserPracticing(), user.getUserNative());
	}

	public void updateLanguage(User user) {
		// TODO Auto-generated method stub
		String sql;
		if (user.getoAuth() == null) {
			sql = "UPDATE USERS SET userNative = ?, userPracticing = ? WHERE userEmail = ? and oAuth IS NULL";
			getJdbcTemplate().update(sql, user.getUserNative(), user.getUserPracticing(), user.getUserEmail());
		} else {
			sql = "UPDATE USERS SET userNative = ?, userPracticing = ? WHERE userEmail = ? and oAuth = ?";
			getJdbcTemplate().update(sql, user.getUserNative(), user.getUserPracticing(), user.getUserEmail(), user.getoAuth());
		}
		
	}
}
