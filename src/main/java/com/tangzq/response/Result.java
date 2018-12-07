package com.tangzq.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
public class Result<T> {
	boolean status;
	String msg;
	T data;

	public Result() {
		super();
	}

	public Result(T data) {
		super();
		this.status = Boolean.TRUE;
		this.msg = "success";
		this.data = data;
	}

	public Result(boolean status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static <T> Result<T> ok(String msg) {
		return new Result<T>(Boolean.TRUE,msg,null);
	}

	public static <T> Result<T> ok(String msg, T data) {
		return new Result<T>(Boolean.TRUE,msg,data);
	}

	public static <T> Result<T> fail(String msg) {
		return new Result<T>(Boolean.FALSE, msg, null);
	}

	public static <T> Result<T> fail(String msg, T data) {
		return new Result<T>(Boolean.FALSE, msg, data);
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
