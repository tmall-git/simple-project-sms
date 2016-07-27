package com.ruanwei.tool;

import java.io.Serializable;

public class SmsResult implements Serializable{

	private static final long serialVersionUID = 1L;

	private boolean success;
	private String msg;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
