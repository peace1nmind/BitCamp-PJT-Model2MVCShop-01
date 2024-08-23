package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

// TODO ���� ���̵��̰ų� ���������� Ȯ���Ͽ� �����ִ� ��� �ʿ�
//		������ �ƴϾ URL�� ���ؼ� ���� ���� �� �� ����
//		üũ�� ���� : �α��� ����, �α��� ���̵�� �Ķ���� ���̵� ������ OR ����������
public class GetUserAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		/*
		
		// �α����� ����
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		// �α��� ���¿� ���� ���
		// �α��� ���°� �ƴϸ� �α��� ȭ������
		if (user==null) {
			return "redirect:/user/loginView.jsp";
		}
		
		// TODO �α��� ���� OK�� ���� ��� �߰� �ʿ�
		
		*/
		
		String userId=request.getParameter("userId");
		
		UserService service=new UserServiceImpl();
		UserVO userVO=service.getUser(userId);
		
		request.setAttribute("userVO", userVO);

		return "forward:/user/readUser.jsp";
	}
}