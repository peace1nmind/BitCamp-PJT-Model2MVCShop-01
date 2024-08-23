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
		System.out.println("\n/com.model2.mvc.common.dao.AbstractDAO");
	}

	// Method
	public Connection connect() {
		
		System.out.println("\tnew AbstractDAO().connect()");
		
		Connection con = null;
		
		try {
			con = DBUtil.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public void close(Connection con, PreparedStatement stmt) {
		
		System.out.println("\tnew AbstractDAO().close(con, stmt)");
		
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
		
		System.out.println("\tnew AbstractDAO().close(con, stmt, rs)");
		
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
		
		System.out.println("\tnew AbstractDAO().close(con, stmt, rs)");
		
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

}
// class end
