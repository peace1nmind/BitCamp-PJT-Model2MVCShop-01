package com.model2.mvc.service.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.dao.AbstractDAO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;


// 유저들이 Dao에서 하는 일을 모듈화
// INSERT, SELECT, UPDATE
public class UserDAO extends AbstractDAO {
	
	// Constructor
	public UserDAO(){
		System.out.println("\ncom.model2.mvc.service.user.dao.UserDAO");
	}

	
	// Method
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
		
		close(con, stmt);
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
		
		close(con, stmt, rs);

		return userVO;
	}

	
	public HashMap<String,Object> getUserList(SearchVO searchVO) throws Exception {
		
		System.out.println("UserDAO().getUserList(searchVO)");
		
		Connection con = connect();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<UserVO> list = new ArrayList<UserVO>();
		
		String sql = "SELECT * FROM "
					+ "(SELECT ROWNUM rn, users.* FROM users ";
		
		String searchConditon = searchVO.getSearchCondition();
		String searchKeyword = searchVO.getSearchKeyword();
		String condition = "user_id";
		
		System.out.println("\tsearchConditon= "+searchConditon);
		
		if (!(searchKeyword == null || searchKeyword.trim().equals(""))) {
			
			switch ((searchConditon==null)? "0" : searchConditon) {
			case "0":	// 회원아이디로 검색
				sql += String.format("WHERE %s LIKE \'%%%s%%\' ", condition, searchKeyword);
				break;
				
			case "1":	// 회원명으로 검색
				condition = "user_name";
				sql += String.format("WHERE %s LIKE \'%%%s%%\' ", condition, searchKeyword);
				break;
			}
		}
		
		sql+= ") ";
		
		System.out.println("\tSQL= "+sql);
		
		try {
			stmt = con.prepareStatement(sql, 
										ResultSet.TYPE_SCROLL_INSENSITIVE, 
										ResultSet.CONCUR_UPDATABLE);
			
			rs = stmt.executeQuery();
			System.out.println("\tstmt.executeQuery()");
			
			rs.last();
			int total = rs.getRow();
			System.out.println("\ttotalRow= "+total);
			map.put("count", new Integer(total));
			
			// 1차로 검색 조건의 총 개수를 구하고 닫고 원하는 페이지의 위치를 다시 구함
			close(stmt, rs);
			
			sql += "WHERE rn BETWEEN ? AND ? ORDER BY user_id ";
			System.out.println("\tSQL= "+sql);
			
			stmt = con.prepareStatement(sql);
			
			int startRow = (total==0)? 0 :(searchVO.getPage() - 1) * searchVO.getPageUnit() + 1;
			int endRow = searchVO.getPage() * searchVO.getPageUnit();
			
			if (endRow > total) {
				endRow = total;
				
			}
			
			stmt.setInt(1, startRow);
			stmt.setInt(2, endRow);
			System.out.println("\tstartRow= "+startRow+", endRow= "+endRow);
			
			rs = stmt.executeQuery();
			System.out.println("\tstmt.executeQuery()");
			
			while (rs.next()) {
				UserVO userVO = new UserVO();
				userVO.setUserId(rs.getString("USER_ID"));
				userVO.setUserName(rs.getString("USER_NAME"));
				userVO.setPassword(rs.getString("PASSWORD"));
				userVO.setRole(rs.getString("ROLE"));
				userVO.setSsn(rs.getString("SSN"));
				userVO.setPhone(rs.getString("CELL_PHONE"));
				userVO.setAddr(rs.getString("ADDR"));
				userVO.setEmail(rs.getString("EMAIL"));
				userVO.setRegDate(rs.getDate("REG_DATE"));
				
				System.out.println(userVO);
				
				list.add(userVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt, rs);
			
		}
		
		System.out.println("\tlist.size() : "+ list.size());
		// 페이지에 해당하는 UserVO들을 map에 ArrayList타입으로 넣음
		map.put("list", list);
		System.out.println("\tmap().size() : "+ map.size());
		
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
		
		close(con, stmt);
	}
	
	
}