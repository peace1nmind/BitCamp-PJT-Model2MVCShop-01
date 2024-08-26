package com.model2.mvc.service.user.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;


// 회원이 사용하는 Dao
public class UserServiceImpl implements UserService{
	
	private UserDAO userDAO;
	
	public UserServiceImpl() {
		userDAO=new UserDAO();
	}

	public void addUser(UserVO userVO) throws Exception {
		userDAO.insertUser(userVO);
	}

	public UserVO loginUser(UserVO userVO) throws Exception {
			UserVO dbUser=userDAO.findUser(userVO.getUserId());
			
			// TODO DB에 userVO.getUserId()가 없을 때의 행동처리 필요
			
			if (dbUser==null) {
				System.out.println("해당하는 아이디가 없습니다.");
				
			} else if (! dbUser.getPassword().equals(userVO.getPassword())) {
				throw new Exception("로그인에 실패했습니다.");
				
			}
				
			return dbUser;
	}

	public UserVO getUser(String userId) throws Exception {
		return userDAO.findUser(userId);
	}

	public HashMap<String,Object> getUserList(SearchVO searchVO) throws Exception {
		return userDAO.getUserList(searchVO);
	}

	public void updateUser(UserVO userVO) throws Exception {
		userDAO.updateUser(userVO);
	}
	
	// 아이디 중복검사 - 없으면 true 있으면 false
	public boolean checkDuplication(String userId) throws Exception {
		
		boolean result=true;
		UserVO userVO=userDAO.findUser(userId);
		if(userVO != null) {
			result=false;
		}
		return result;
	}
}