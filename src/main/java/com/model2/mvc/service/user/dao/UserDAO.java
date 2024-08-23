package com.model2.mvc.service.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.user.vo.UserVO;


// �������� Dao���� �ϴ� ���� ���ȭ
// INSERT, SELECT, UPDATE
public class UserDAO {
	
	public UserDAO(){
		System.out.println("\ncom.model2.mvc.service.user.dao.UserDAO");
	}

	public void insertUser(UserVO userVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into USERS values (?,?,?,'user',?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userVO.getUserId());
		stmt.setString(2, userVO.getUserName());
		stmt.setString(3, userVO.getPassword());
		// role default= user
		stmt.setString(4, userVO.getSsn());
		stmt.setString(5, userVO.getPhone());
		stmt.setString(6, userVO.getAddr());
		stmt.setString(7, userVO.getEmail());
		// regDate default= SYSDATE
		stmt.executeUpdate();
		
		con.close();
	}

	public UserVO findUser(String userId) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "select * from USERS where USER_ID=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userId);

		ResultSet rs = stmt.executeQuery();

		UserVO userVO = null;
		while (rs.next()) {
			userVO = new UserVO();
			userVO.setUserId(rs.getString("USER_ID"));
			userVO.setUserName(rs.getString("USER_NAME"));
			userVO.setPassword(rs.getString("PASSWORD"));
			userVO.setRole(rs.getString("ROLE"));
			userVO.setSsn(rs.getString("SSN"));
			userVO.setPhone(rs.getString("CELL_PHONE"));
			userVO.setAddr(rs.getString("ADDR"));
			userVO.setEmail(rs.getString("EMAIL"));
			userVO.setRegDate(rs.getDate("REG_DATE"));
		}
		
		con.close();

		return userVO;
	}

	public HashMap<String,Object> getUserList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM users ";
		if (searchVO.getSearchCondition() != null) {
			
			// listUser.jsp���� ȸ�����̵�� �˻��� ���
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE user_id='" + searchVO.getSearchKeyword()
						+ "'";
				
			// listUser.jsp���� ȸ���̸����� �˻��� ���
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE user_name='" + searchVO.getSearchKeyword()
						+ "'";
			}
		}
		sql += " ORDER BY user_id";

		PreparedStatement stmt = con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();
		
		// �˻��� ��� ���ڵ��� ����
		rs.last();
		int total = rs.getRow();
		System.out.println("�ο��� ��:" + total);
		
		// map�� "count"�� �߰� : ��ü ���ڵ��� ��
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));
		
		// Ŀ���� page, pageUnit�� �̿��Ͽ� �ش� �������� ��������� ������ ù��° ���ڵ�� �̵�
		rs.absolute((searchVO.getPage() -1 ) * searchVO.getPageUnit() +1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		// �������� �ش��ϴ� UserVO�� ���� ArrayList
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				UserVO vo = new UserVO();
				vo.setUserId(rs.getString("USER_ID"));
				vo.setUserName(rs.getString("USER_NAME"));
				vo.setPassword(rs.getString("PASSWORD"));
				vo.setRole(rs.getString("ROLE"));
				vo.setSsn(rs.getString("SSN"));
				vo.setPhone(rs.getString("CELL_PHONE"));
				vo.setAddr(rs.getString("ADDR"));
				vo.setEmail(rs.getString("EMAIL"));
				vo.setRegDate(rs.getDate("REG_DATE"));

				list.add(vo);
				
				if (!rs.next())
					break;
			}
		}
		
		System.out.println("list.size() : "+ list.size());
		// �������� �ش��ϴ� UserVO���� map�� ArrayListŸ������ ����
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());
		
		// DB �ݱ�
		rs.close();
		stmt.close();
		con.close();
			
		return map;
	}

	public void updateUser(UserVO userVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update USERS set USER_NAME=?,CELL_PHONE=?,ADDR=?,EMAIL=? where USER_ID=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userVO.getUserName());
		stmt.setString(2, userVO.getPhone());
		stmt.setString(3, userVO.getAddr());
		stmt.setString(4, userVO.getEmail());
		stmt.setString(5, userVO.getUserId());
		stmt.executeUpdate();
		
		con.close();
	}
}