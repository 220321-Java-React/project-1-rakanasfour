package com.revature.models;

public class RequestDTO {
	int reimb_id;
	int reimb_amount;
	String reimb_submitted;
	
	int  user;
	
	int  status;
	

	int   type;


	public RequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RequestDTO(int reimb_id, int reimb_amount, String reimb_submitted, int user, int status, int type) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.user = user;
		this.status = status;
		this.type = type;
	}


	public RequestDTO(int reimb_amount, String reimb_submitted, int user, int status, int type) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.user = user;
		this.status = status;
		this.type = type;
	}


	@Override
	public String toString() {
		return "RequestDTO [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
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


	public int getUser() {
		return user;
	}


	public void setUser(int user) {
		this.user = user;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	
	
	
}