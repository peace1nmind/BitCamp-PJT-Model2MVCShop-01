package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

// TODO 본인 아이디이거나 관리자인지 확인하여 보여주는 방어 필요
//		본인이 아니어도 URL을 통해서 남의 정보 볼 수 있음
//		체크할 내용 : 로그인 상태, 로그인 아이디와 파라미터 아이디가 같은지 OR 관리자인지
public class GetUserAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		/*
		
		// 로그인한 유저
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		// 로그인 상태에 대한 방어
		// 로그인 상태가 아니면 로그인 화면으로
		if (user==null) {
			return "redirect:/user/loginView.jsp";
		}
		
		// TODO 로그인 상태 OK에 대한 방어 추가 필요
		
		*/
		
		String userId=request.getParameter("userId");
		
		UserService service=new UserServiceImpl();
		UserVO userVO=service.getUser(userId);
		
		request.setAttribute("userVO", userVO);

		return "forward:/user/readUser.jsp";
	}
}