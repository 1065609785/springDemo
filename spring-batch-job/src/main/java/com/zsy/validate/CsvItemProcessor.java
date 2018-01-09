package com.zsy.validate;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.google.gson.Gson;
import com.zsy.domain.Emp;

public class CsvItemProcessor extends ValidatingItemProcessor<Emp> {
	@Override
	public Emp process(Emp emp) throws ValidationException {
		Gson gson = new Gson();
		System.err.println(gson.toJson(emp));
		return super.process(emp);
	}
}
