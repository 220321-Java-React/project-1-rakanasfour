package com.revature.models;

public class Request {
	
	private int reimb_id;
	private int reimb_amount;
	
	private String reimb_submitted;
	
	private User user;
	
	private Status status;
	

	private Type type;


	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Request(int reimb_id, int reimb_amount, String reimb_submitted, User user, Status status, Type type) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.user = user;
		this.status = status;
		this.type = type;
	}


	public Request(int reimb_amount, String reimb_submitted, User user, Status status, Type type) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.user = user;
		this.status = status;
		this.type = type;
	}


	@Override
	public String toString() {
		return "Request [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
				+ reimb_submitted + ", user=" + user + ", status=" + status + ", type=" + type + "]";
	}


	public int getReimb_id() {
		return reimb_id;
	}


	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}


	public int getReimb_amount() {
		return reimb_amount;
	}


	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
	}


	public String getReimb_submitted() {
		return reimb_submitted;
	}


	public void setReimb_submitted(String reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}

}
