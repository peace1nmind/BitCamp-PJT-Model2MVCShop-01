package com.model2.mvc.service.product.dao;
// W D 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.dao.AbstractDAO;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductDAO extends AbstractDAO {

	// Field
	
	
	// Constructor
	public ProductDAO() {
		System.out.println("\ncom.model2.mvc.service.product.dao.ProductDAO");
	}
	
	
	// Method
	// 상품정보 조회를 위한 DBMS
	public ProductVO findProduct(int prodNo) {
		
		System.out.println("\new ProductDAO().findProduct(prodNo)");
		
		Connection con = connect();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM product WHERE prod_no = ?";
		
		return null;
	}
	
	// 상품목록 조회를 위한 DBMS
	public HashMap<String, Object> getProductList(SearchVO searchVO) {
		
		System.out.println("\tnew ProductDAO().getProductList(searchVO)");
		
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
//					vo.setProTranCode("판매중");
					
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
		
	}
	
	// 상품정보 수정을 위한 DBMS
	public void updateProduct(ProductVO productVO) {
		
	}

}
// class end
