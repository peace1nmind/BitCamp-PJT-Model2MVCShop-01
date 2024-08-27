package com.model2.mvc.view;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;

public class HomeAction extends Action {

	// Field

	// Constructor
	public HomeAction() {
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		System.out.println("\n>> HomeAction");
		
		return "redirect:/home.jsp";
	}

}
// class end
