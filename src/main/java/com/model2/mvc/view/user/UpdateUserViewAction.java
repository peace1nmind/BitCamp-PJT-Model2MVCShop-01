package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

// TODO 링크타서 들어오는 GET방식에 대한 대응 필요
// 		방법1. 로그인 상태인지, 들어오는 링크의 파라미터와 로그인 상태의 값과 같은지 비교
//		방법2. readUser.jsp에서 수정이 넘어올 때 POST 방식으로 오게해서 GET을 다 막아버리기
public class UpdateUserViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		String userId=request.getParameter("userId");
		
		UserService service=new UserServiceImpl();
		UserVO userVO=service.getUser(userId);
		
		request.setAttribute("userVO", userVO);
		
		return "forward:/user/updateUser.jsp";
	}
}
