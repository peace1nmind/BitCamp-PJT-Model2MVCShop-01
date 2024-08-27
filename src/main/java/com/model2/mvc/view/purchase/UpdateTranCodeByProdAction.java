package com.model2.mvc.view.purchase;
// W D 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.dao.AbstractDAO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.dao.ProductDAO;

public class UpdateTranCodeByProdAction extends Action {

	// Field
	
	
	// Constructor
	public UpdateTranCodeByProdAction() {
	}
	
	
	// Method
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AbstractDAO dao = new ProductDAO();
		dao.getCount("product");
		
		
		return "redirect:/listProduct.do?menu=manage";
	}

}
// class end
