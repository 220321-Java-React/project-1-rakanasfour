package com.revature.models;

public class RequestDTO {
	int reimb_id;
	int reimb_amount;
	String reimb_submitted;
	
	public RequestDTO(int reimb_id, int reimb_amount, String reimb_submitted) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
	}

	public RequestDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
	
}