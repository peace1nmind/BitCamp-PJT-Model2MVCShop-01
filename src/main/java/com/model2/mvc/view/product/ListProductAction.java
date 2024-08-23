package com.model2.mvc.view.product;
// W D 

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request, HttpServletResponse response) 
							throws Exception {
		
		System.out.println("\tnew ListProductAction().execute(request, response)");
		
		SearchVO searchVO = new SearchVO();
		
//		int page = 1;
		if (request.getParameter("page") != null) {
			int page = Integer.parseInt(request.getParameter("page"));
			System.out.println("\tpage= "+page);
			searchVO.setPage(page);
		}
		
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		searchVO.setPageUnit(pageUnit);
		
		ProductService service = new ProductServiceImpl();
		System.out.println("\tsearchVO= "+searchVO);
		HashMap<String, Object> map = service.getProductList(searchVO);
		
		System.out.println("\tmap= "+map);
		request.setAttribute("map", map);
//		System.out.println("\tsearchVO= "+searchVO);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/product/listProduct.jsp";
	}
}
// class end
