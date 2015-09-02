package com.alpha.languageexchange.model;

public class Message {
	private int messageId;
    private String senderEmail;
    private String senderOAuth;
    private String senderName;
    private String message;
    private String sendTime;
    
	public Message() {
		this(0, null, null, null, null, null);
	}

	public Message(int messageId, String senderEmail, String senderOAuth, String senderName, String message,
			String sendTime) {
		this.messageId = messageId;
		this.senderEmail = senderEmail;
		this.senderOAuth = senderOAuth;
		this.senderName = senderName;
		this.message = message;
		this.sendTime = sendTime;
	}

	public int getMessageId() {
		return messageId;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public String getSenderOAuth() {
		return senderOAuth;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getMessage() {
		return message;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public void setSenderOAuth(String senderOAuth) {
		this.senderOAuth = senderOAuth;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + messageId;
		result = prime * result + ((sendTime == null) ? 0 : sendTime.hashCode());
		result = prime * result + ((senderEmail == null) ? 0 : senderEmail.hashCode());
		result = prime * result + ((senderName == null) ? 0 : senderName.hashCode());
		result = prime * result + ((senderOAuth == null) ? 0 : senderOAuth.hashCode());
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
		Message other = (Message) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageId != other.messageId)
			return false;
		if (sendTime == null) {
			if (other.sendTime != null)
				return false;
		} else if (!sendTime.equals(other.sendTime))
			return false;
		if (senderEmail == null) {
			if (other.senderEmail != null)
				return false;
		} else if (!senderEmail.equals(other.senderEmail))
			return false;
		if (senderName == null) {
			if (other.senderName != null)
				return false;
		} else if (!senderName.equals(other.senderName))
			return false;
		if (senderOAuth == null) {
			if (other.senderOAuth != null)
				return false;
		} else if (!senderOAuth.equals(other.senderOAuth))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", senderEmail=" + senderEmail + ", senderOAuth=" + senderOAuth
				+ ", senderName=" + senderName + ", message=" + message + ", sendTime=" + sendTime + "]";
	}
}
