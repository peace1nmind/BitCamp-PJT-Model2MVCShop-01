package com.model2.mvc.view.purchase;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;

public class ListPurchaseHistoryAction extends Action {

	// Field

	// Constructor
	public ListPurchaseHistoryAction() {
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("\n>> ListPurchaseHistoryAction");
		
		request.setAttribute("test", "테스트값");
		
		return "forward:/purchase/listPurchaseHistory.jsp";
	}

}
// class end
