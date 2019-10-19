package com.tiktop.exception;

//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.tiktop.model.ErrorHandle;
//
//@ControllerAdvice
//public class AllException {
//
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleAllException(Exception ex) {
//		ModelAndView mav = new ModelAndView("admin/error");
//		ErrorHandle error = new ErrorHandle("Can't Delete! The one you deleted is used another form!!!");
//		mav.addObject("error", error);
//		return mav;
//	}
//}
