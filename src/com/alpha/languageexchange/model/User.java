package com.alpha.languageexchange.model;

public class User {
	private String userImage;
	private String userEmail;
	private String userName;
	private String userPassword;
	private String userGender;
	private int userAge;
	private String oAuth;
	private String userNative;
	private String userPracticing;
	private String userIntro;
	private String userCreateDate;
	private String userUpdateDate;

	public User() {
		this(null, null, null, null, null, 0, null, null, null, null, null, null);
	}
	
	// login
	public User(String userEmail, String userPassword, String oAuth) {
		this(null, userEmail, null, userPassword, null, 0, oAuth, null, null, null, null, null);
	}

	// timeline user list
	public User(String userImage, String userEmail, String userName, String userGender, int userAge, String oAuth, String userNative,
			String userPracticing, String userIntro, String userUpdateDate) {
		this(userImage, userEmail, userName, null, userGender, userAge, oAuth, userNative, userPracticing, userIntro, null,
				userUpdateDate);
	}

	public User(String userEmail, String userName, String userPassword, String userGender, int userAge, String oAuth) {
		this(null, userEmail, userName, userPassword, userGender, userAge, oAuth, null, null, null, null, null);
	}

	public User(String userEmail, String userName, String userPassword, String userGender,
			int userAge, String oAuth, String userNative, String userPracticing, String userIntro,
			String userCreateDate, String userUpdateDate) {
		this(null, userEmail, userName, userPassword, userGender, userAge, oAuth, userNative, userPracticing, userIntro, userCreateDate, userUpdateDate);
	}
	
	public User(String userImage, String userEmail, String userName, String userPassword, String userGender,
			int userAge, String oAuth, String userNative, String userPracticing, String userIntro,
			String userCreateDate, String userUpdateDate) {
		super();
		this.userImage = userImage;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userGender = userGender;
		this.userAge = userAge;
		this.oAuth = oAuth;
		this.userNative = userNative;
		this.userPracticing = userPracticing;
		this.userIntro = userIntro;
		this.userCreateDate = userCreateDate;
		this.userUpdateDate = userUpdateDate;
	}

	public String getUserImage() {
		return userImage;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserGender() {
		return userGender;
	}

	public int getUserAge() {
		return userAge;
	}

	public String getoAuth() {
		return oAuth;
	}

	public String getUserNative() {
		return userNative;
	}

	public String getUserPracticing() {
		return userPracticing;
	}

	public String getUserIntro() {
		return userIntro;
	}

	public String getUserCreateDate() {
		return userCreateDate;
	}

	public String getUserUpdateDate() {
		return userUpdateDate;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public void setoAuth(String oAuth) {
		this.oAuth = oAuth;
	}

	public void setUserNative(String userNative) {
		this.userNative = userNative;
	}

	public void setUserPracticing(String userPracticing) {
		this.userPracticing = userPracticing;
	}

	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}

	public void setUserCreateDate(String userCreateDate) {
		this.userCreateDate = userCreateDate;
	}

	public void setUserUpdateDate(String userUpdateDate) {
		this.userUpdateDate = userUpdateDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oAuth == null) ? 0 : oAuth.hashCode());
		result = prime * result + userAge;
		result = prime * result + ((userCreateDate == null) ? 0 : userCreateDate.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userGender == null) ? 0 : userGender.hashCode());
		result = prime * result + ((userImage == null) ? 0 : userImage.hashCode());
		result = prime * result + ((userIntro == null) ? 0 : userIntro.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userNative == null) ? 0 : userNative.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		result = prime * result + ((userPracticing == null) ? 0 : userPracticing.hashCode());
		result = prime * result + ((userUpdateDate == null) ? 0 : userUpdateDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (oAuth == null) {
			if (other.oAuth != null)
				return false;
		} else if (!oAuth.equals(other.oAuth))
			return false;
		if (userAge != other.userAge)
			return false;
		if (userCreateDate == null) {
			if (other.userCreateDate != null)
				return false;
		} else if (!userCreateDate.equals(other.userCreateDate))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userGender == null) {
			if (other.userGender != null)
				return false;
		} else if (!userGender.equals(other.userGender))
			return false;
		if (userImage == null) {
			if (other.userImage != null)
				return false;
		} else if (!userImage.equals(other.userImage))
			return false;
		if (userIntro == null) {
			if (other.userIntro != null)
				return false;
		} else if (!userIntro.equals(other.userIntro))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userNative == null) {
			if (other.userNative != null)
				return false;
		} else if (!userNative.equals(other.userNative))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		if (userPracticing == null) {
			if (other.userPracticing != null)
				return false;
		} else if (!userPracticing.equals(other.userPracticing))
			return false;
		if (userUpdateDate == null) {
			if (other.userUpdateDate != null)
				return false;
		} else if (!userUpdateDate.equals(other.userUpdateDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userImage=" + userImage + ", userEmail=" + userEmail + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userGender=" + userGender + ", userAge=" + userAge + ", oAuth="
				+ oAuth + ", userNative=" + userNative + ", userPracticing=" + userPracticing + ", userIntro="
				+ userIntro + ", userCreateDate=" + userCreateDate + ", userUpdateDate=" + userUpdateDate + "]";
	}
}
