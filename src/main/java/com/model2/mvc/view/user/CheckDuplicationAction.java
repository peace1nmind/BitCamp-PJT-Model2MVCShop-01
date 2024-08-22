package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class CheckDuplicationAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String userId=request.getParameter("userId");
		
		UserService service=new UserServiceImpl();
		boolean result=service.checkDuplication(userId);
		
		// TODO Q.참조값이 변경되지 않기 위해서 새로 인스턴스를 생성해서 세션에 추가?
		request.setAttribute("result",new Boolean(result) );
		request.setAttribute("userId", userId);
		
		return "forward:/user/checkDuplication.jsp";
	}
}