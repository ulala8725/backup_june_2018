package com.study.spring.util;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
//	@ExceptionHandler (Exception.class)
//	public String errorHandler(Model model, Exception ex) {
//		model.addAttribute("message", "エラー発生");
//		model.addAttribute("ex", ex);
//		return "kadai/error";
//	}

}
