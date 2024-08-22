package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

// TODO ��ũŸ�� ������ GET��Ŀ� ���� ���� �ʿ�
// 		���1. �α��� ��������, ������ ��ũ�� �Ķ���Ϳ� �α��� ������ ���� ������ ��
//		���2. readUser.jsp���� ������ �Ѿ�� �� POST ������� �����ؼ� GET�� �� ���ƹ�����
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
