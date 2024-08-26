package com.model2.mvc.view.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

// TODO	Q.request 파라미터 값은 어디서 오는가?
//		A.listUser.jsp에서 보냄
public class ListUserAction extends Action {

	@Override
	public String execute(HttpServletRequest request,HttpServletResponse response) 
							throws Exception {
		SearchVO searchVO=new SearchVO(getServletContext());
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		UserService service=new UserServiceImpl();
		HashMap<String,Object> map=service.getUserList(searchVO);

		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/user/listUser.jsp";
	}
}