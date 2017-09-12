package com.tangzq.response;

public class Result<T> {
	int status;
	String msg;
	T data;

	public Result() {
		super();
	}

	public Result(T data) {
		super();
		this.status = 0;
		this.msg = "success";
		this.data = data;
	}

	public Result(int status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> Result<T> ok(T data) {
		return new Result<T>(data);
	}

	public static <T> Result<T> fail(T data) {
		return new Result<T>(-1, "fail", data);
	}

	@Override
	public String toString() {
		return "Result{" +
				"status=" + status +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
}
