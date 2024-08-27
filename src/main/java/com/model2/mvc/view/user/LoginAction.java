package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;


public class LoginAction extends Action{

	@Override
	public String execute(	HttpServletRequest request, HttpServletResponse response) 
							throws Exception {
		
		System.out.println("\nLoginAction");
		
		UserVO userVO=new UserVO();
		userVO.setUserId(request.getParameter("userId"));
		System.out.println("\tuserId= "+request.getParameter("userId"));
		userVO.setPassword(request.getParameter("password"));
		System.out.println("\tpassword= "+request.getParameter("password"));
		
		UserService service=new UserServiceImpl();
		UserVO dbVO=service.loginUser(userVO);
		
		if (dbVO == null) {
			System.out.println("\t로그인 실패");
			
		}
		
		else { 
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(60 * 60);		// 세션 1시간 설정
			session.setAttribute("user", dbVO);
		}
		
		return "redirect:/index.jsp";
	}
}