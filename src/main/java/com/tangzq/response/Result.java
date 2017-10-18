package com.tangzq.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
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
