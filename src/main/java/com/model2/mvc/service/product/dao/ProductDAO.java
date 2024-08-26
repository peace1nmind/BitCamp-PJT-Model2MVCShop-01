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

// TODO productVO.proTranCode에 대한 설정고려 (DB에 넣을지, B/L에서만 다룰지)
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
			
			rs.last();
			int lastRow = rs.getRow();
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
		
		int total = 0;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		String sql = "SELECT * FROM product ";
		
		String searchConditon = searchVO.getSearchCondition();
		String searchKeyword = searchVO.getSearchKeyword();
		String condition = "prod_no";
		
		System.out.println("\tsearchConditon= "+searchConditon);
		switch ((searchConditon==null)? "0" : searchConditon) {
		case "0":	// 상품번호로 검색
			
			break;
			
		case "1":	// 상품명으로 검색
			condition = "prod_name";
			break;
			
		case "2":	// 상품가격으로 검색
			condition = "price";
		}
		
		if (searchKeyword != null) {
			sql += String.format("WHERE %s =  '%s' ORDER BY prod_no", condition, searchKeyword);
		}
		
		System.out.println("\tSQL= "+sql);
		
		try {
			stmt = con.prepareStatement(sql, 
										ResultSet.TYPE_SCROLL_INSENSITIVE, 
										ResultSet.CONCUR_UPDATABLE);
			
			rs = stmt.executeQuery();
			System.out.println("\tstmt.executeQuery()");
			
			rs.last();
			total = rs.getRow();
			System.out.println("\tRow= "+total);
			
			// map에 "count"값 추가 : 전체 레코드의 수
			map.put("count", new Integer(total));
			System.out.println("map.get(\"count\")= "+map.get("count"));
			
			// 커서를 page, pageUnit을 이용하여 해당 페이지에 보여줘야할 유저의 첫번째 레코드로 이동
			rs.absolute((searchVO.getPage() - 1) * searchVO.getPageUnit() + 1);
			System.out.println("searchVO.getPage():" + searchVO.getPage());
			System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
			
			// DB의 product 내용 ProductVO에 담고 list에 넣기
			if (total > 0) {
				for (int i = 0; i < searchVO.getPageUnit(); i++) {
					ProductVO vo = new ProductVO();
					vo.setProdNo(rs.getInt("prod_no"));
					vo.setProdName(rs.getString("prod_name"));
					vo.setProdDetail(rs.getString("prod_detail"));
					vo.setManuDate(rs.getString("manufacture_day"));
					vo.setPrice(rs.getInt("price"));
					vo.setFileName(rs.getString("image_file"));
					vo.setRegDate(rs.getDate("reg_date"));
					vo.setProTranCode(rs.getString("pro_tran_code"));
					
					System.out.println(vo);
					
					list.add(vo);
					
					if (!rs.next()) {
						break;
					}
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt, rs);
			
		}
		
		System.out.println("list.size() : "+ list.size());
		// 페이지에 해당하는 UserVO들을 map에 ArrayList타입으로 넣음
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());
		
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
