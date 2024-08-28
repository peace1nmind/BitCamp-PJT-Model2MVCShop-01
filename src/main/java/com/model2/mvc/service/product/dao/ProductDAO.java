package com.model2.mvc.service.product.dao;
// W D 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.dao.AbstractDAO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.TranCodeMapper;

public class ProductDAO extends AbstractDAO {

	// Field
	
	
	// Constructor
	public ProductDAO() {
		System.out.println("\ncom.model2.mvc.service.product.dao.ProductDAO");
	}
	
	
	// Method
	// 상품정보 조회를 위한 DBMS
	public ProductVO findProduct(int prodNo) {
		
		System.out.println("ProductDAO().findProduct(prodNo)");
		System.out.println("\tprodNo= "+prodNo);
		
		Connection con = connect();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ProductVO productVO = new ProductVO();
		
		String sql = "SELECT * FROM product WHERE prod_no = ?";
		System.out.println("\tSQL= "+sql);
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, prodNo);
			
			rs = stmt.executeQuery();
			System.out.println("\tstmt.executeQuery()");
			
			if (rs.next()) {
				productVO.setProdNo(prodNo);
				productVO.setProdName(rs.getString("prod_name"));
				productVO.setProdDetail(rs.getString("prod_detail"));
				productVO.setManuDate(rs.getString("manufacture_day"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setFileName(rs.getString("image_file"));
				productVO.setRegDate(rs.getDate("reg_date"));
				productVO.setProTranCode(rs.getString("pro_tran_code"));
				
				System.out.println("\t찾은 productVO= "+productVO);
				
			} else {
				System.out.println("\tprodNo= "+prodNo+" 인 상품은 등록되어있지 않습니다.");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e2) {
			e2.printStackTrace();
			
		} finally {
			close(con, stmt, rs);
			
		}
		
		return productVO;
	}
	
	// 최근 등록된 상품정보 조회를 위한 DBMS
	public ProductVO findProduct() {
		
		System.out.println("ProductDAO().findProduct()");
		
		Connection con = connect();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ProductVO productVO = new ProductVO();
		
		String sql = "SELECT * FROM product ORDER BY prod_no";
		System.out.println("\tSQL= "+sql);
		
		try {
			stmt = con.prepareStatement(sql, 
										ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
			
			rs = stmt.executeQuery();
			System.out.println("\tstmt.executeQuery()");
			
			int lastRow = getTotal("product");
			rs.absolute(lastRow);
			productVO.setProdNo(rs.getInt("prod_no"));
			productVO.setProdName(rs.getString("prod_name"));
			productVO.setProdDetail(rs.getString("prod_detail"));
			productVO.setManuDate(rs.getString("manufacture_day"));
			productVO.setPrice(rs.getInt("price"));
			productVO.setFileName(rs.getString("image_file"));
			productVO.setRegDate(rs.getDate("reg_date"));
			productVO.setProTranCode(rs.getString("pro_tran_code"));
			
			System.out.println("\t최근 등록내용= "+productVO);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e2) {
			e2.printStackTrace();
			
		} finally {
			close(con, stmt, rs);
			
		}
		
		return productVO;
	}
	
	// 상품목록 조회를 위한 DBMS
	public HashMap<String, Object> getProductList(SearchVO searchVO) {
		
		System.out.println("ProductDAO().getProductList(searchVO)");
		
		Connection con = connect();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		String sql = "SELECT * FROM "
					+ "(SELECT ROWNUM rn, product.* "
					+ "FROM product "
					+ "WHERE pro_tran_code = '1' ";
		
		String searchConditon = searchVO.getSearchCondition();
		String searchKeyword = searchVO.getSearchKeyword();
		String condition = "prod_no";
		
		System.out.println("\tsearchConditon= "+searchConditon);
		
		if (!(searchKeyword == null || searchKeyword.trim().equals(""))) {
			
			switch ((searchConditon==null)? "0" : searchConditon) {
			case "0":	// 상품번호로 검색
				sql += String.format("AND %s LIKE \'%%%s%%\' ", condition, searchKeyword);
				break;
				
			case "1":	// 상품명으로 검색
				condition = "prod_name";
				sql += String.format("AND %s LIKE \'%%%s%%\' ", condition, searchKeyword);
				break;
				
			case "2":	// 상품가격으로 검색
				condition = "price";
				sql += String.format("AND %s >= %s ", condition, searchKeyword);
				break;
			}
		}
		
		sql+= ") ";
		
		System.out.println("\tSQL= "+sql);
		
		try {
			int total = getCounts(sql);
			System.out.println("\ttotalRow= "+total);
			map.put("count", new Integer(total));
			
			// 1차로 검색 조건의 총 개수를 구하고 닫고 원하는 페이지의 위치를 다시 구함
//			close(stmt, rs);
			
			sql += "WHERE rn BETWEEN ? AND ? ORDER BY prod_no";
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
				ProductVO productVO = new ProductVO();
				productVO.setProdNo(rs.getInt("prod_no"));
				productVO.setProdName(rs.getString("prod_name"));
				productVO.setProdDetail(rs.getString("prod_detail"));
				productVO.setManuDate(rs.getString("manufacture_day"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setFileName(rs.getString("image_file"));
				productVO.setRegDate(rs.getDate("reg_date"));
				productVO.setProTranCode(rs.getString("pro_tran_code"));
				
				System.out.println(productVO);
				
				list.add(productVO);
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
	
	// 상품등록을 위한 DBMS
	public void insertProduct(ProductVO productVO) {
		
		System.out.println("ProductVO().insertProduct(productVO)");
		System.out.println("\t"+productVO);
		
		Connection con = connect();
		PreparedStatement stmt = null;
		int rs = -1;
		
		String sql = "INSERT INTO product VALUES(seq_product_prod_no.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?)";
		System.out.println("\tSQL= "+sql);
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, productVO.getProdName());
			stmt.setString(2, productVO.getProdDetail());
			stmt.setString(3, productVO.getManuDate());
			stmt.setInt(4, productVO.getPrice());
			stmt.setString(5, productVO.getFileName());
			stmt.setString(6, productVO.getProTranCode());
			
			rs = stmt.executeUpdate();
			System.out.println("\tstmt.executeUpdate()");
			
			if (rs > 0) {
				System.out.println("\t"+rs+" 행이 추가됐습니다.");
				
			} else {
				System.out.println("\tDB에 정보 추가를 실패했습니다.");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt);
			
		}
		
	}
	
	// 상품정보 수정을 위한 DBMS
	public void updateProduct(ProductVO productVO) {
		
		System.out.println("ProductDAO().updateProduct(productVO)");
		System.out.println(productVO);
		
		Connection con = connect();
		PreparedStatement stmt = null;
		int rs = -1;
		
		String sql = "UPDATE product SET prod_name=?, "
										+ "prod_detail=?, "
										+ "manufacture_day=?, "
										+ "price=?, "
										+ "image_file=? "
					+ "WHERE prod_no=?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, productVO.getProdName());
			stmt.setString(2, productVO.getProdDetail());
			stmt.setString(3, productVO.getManuDate());
			stmt.setInt(4, productVO.getPrice());
			stmt.setString(5, productVO.getFileName());
			stmt.setInt(6, productVO.getProdNo());
			
			System.out.println("\tSQL= "+sql);
			rs = stmt.executeUpdate();
			System.out.println("\tstmt.executeUpdate()");
			
			if (rs > 0) {
				System.out.println("\t"+rs+" 행이 수정되었습니다.");
				
			} else {
				System.out.println("\t수정 실패하였습니다.");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt);;
			
		}
		
	}
	
	// 상품 상태코드 수정하는 DBMS
	public void updateTranCode(int prodNo, String proTranCode) {
		
		System.out.println("ProductDAO().updateTranCode(proTranCode");
		System.out.println("\tproTranCode= "+proTranCode);
		
		TranCodeMapper tranCodeMapper = TranCodeMapper.getInstance();
		Map<String, String> map = tranCodeMapper.getMap();
		Set<String> keySet = map.keySet();
		
		try {
			if (keySet.contains(proTranCode)) {
				System.out.println("\t변경하려는 상품상태= "+map.get(proTranCode));
				
			} else {
				throw new Exception("올바르지 않는 proTranCode입니다");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		Connection con = connect();
		PreparedStatement stmt = null;
		int rs = -1;
		
		String sql = "UPDATE product SET pro_tran_code=? WHERE prod_no=?";
		System.out.println("\tSQL= "+sql);
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, proTranCode);
			stmt.setInt(2, prodNo);
			
			rs = stmt.executeUpdate();
			System.out.println("\tstmt.executeUpdate()");
			
			if (rs > 0) {
				System.out.println("\t"+rs+" 행이 수정되었습니다.");
				
			} else {
				System.out.println("\t수정 실패하였습니다.");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt);
			
		}
		
	}
	

}
// class end
