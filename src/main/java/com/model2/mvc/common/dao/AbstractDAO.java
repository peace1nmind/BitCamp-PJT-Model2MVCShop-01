package com.model2.mvc.common.dao;
// W D 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model2.mvc.common.util.DBUtil;

public abstract class AbstractDAO {

	// Field

	// Constructor
	public AbstractDAO() {
		System.out.println("\ncom.model2.mvc.common.dao\nAbstractDAO");
	}

	// Method
	public Connection connect() {
		
		System.out.println("AbstractDAO().connect()");
		
		Connection con = null;
		
		try {
			con = DBUtil.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public void close(Connection con, PreparedStatement stmt) {
		
		System.out.println("AbstractDAO().close(con, stmt)");
		
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void close(Connection con, PreparedStatement stmt, ResultSet rs) {
		
		System.out.println("AbstractDAO().close(con, stmt, rs)");
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void close(PreparedStatement stmt, ResultSet rs) {
		
		System.out.println("AbstractDAO().close(con, stmt, rs)");
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/* 전체 ROW의 개수 반환하는 함수 DAO에서 LIST 얻을 때 MAP에 넣던 기능을 분리 */
	public int getCount(String table){
		
		System.out.println("AbstractDAO().getCount(table)");
		
		int total = 0;
		
		Connection con = connect();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM "+table;
		System.out.println("\tSQL= "+sql);
		
		try {
			stmt = con.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			System.out.println("\tstmt.executeQuery()");

			rs.next();
			total = rs.getInt("COUNT(*)");
			System.out.println("\ttotal= "+total);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt, rs);
			
		}
		
		return total;
	}

}
// class end
