package com.test.elo7.spacial.probes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Result {

	@JsonInclude(Include.NON_NULL)
	private Object result;

	@JsonInclude(Include.NON_NULL)
	private List<String> errors;

	public Result(Object result) {
		this.result = result;
	}

	public Result(List<String> errors) {
		this.errors = errors;
	}

	public Object getResult() {
		return result;
	}

	public List<String> getErrors() {
		return errors;
	}
}
