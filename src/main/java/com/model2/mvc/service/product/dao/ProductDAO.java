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
	// ��ǰ���� ��ȸ�� ���� DBMS
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
				
				System.out.println("\tã�� productVO= "+productVO);
				
			} else {
				System.out.println("\tprodNo= "+prodNo+" �� ��ǰ�� ��ϵǾ����� �ʽ��ϴ�.");
				
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
	
	// �ֱ� ��ϵ� ��ǰ���� ��ȸ�� ���� DBMS
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
			
			System.out.println("\t�ֱ� ��ϳ���= "+productVO);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e2) {
			e2.printStackTrace();
			
		} finally {
			close(con, stmt, rs);
			
		}
		
		return productVO;
	}
	
	// ��ǰ��� ��ȸ�� ���� DBMS
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
			case "0":	// ��ǰ��ȣ�� �˻�
				sql += String.format("AND %s LIKE \'%%%s%%\' ", condition, searchKeyword);
				break;
				
			case "1":	// ��ǰ������ �˻�
				condition = "prod_name";
				sql += String.format("AND %s LIKE \'%%%s%%\' ", condition, searchKeyword);
				break;
				
			case "2":	// ��ǰ�������� �˻�
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
			
			// 1���� �˻� ������ �� ������ ���ϰ� �ݰ� ���ϴ� �������� ��ġ�� �ٽ� ����
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
		// �������� �ش��ϴ� UserVO���� map�� ArrayListŸ������ ����
		map.put("list", list);
		System.out.println("\tmap().size() : "+ map.size());
		
		return map;
	}
	
	// ��ǰ����� ���� DBMS
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
				System.out.println("\t"+rs+" ���� �߰��ƽ��ϴ�.");
				
			} else {
				System.out.println("\tDB�� ���� �߰��� �����߽��ϴ�.");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt);
			
		}
		
	}
	
	// ��ǰ���� ������ ���� DBMS
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
				System.out.println("\t"+rs+" ���� �����Ǿ����ϴ�.");
				
			} else {
				System.out.println("\t���� �����Ͽ����ϴ�.");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt);;
			
		}
		
	}
	
	// ��ǰ �����ڵ� �����ϴ� DBMS
	public void updateTranCode(int prodNo, String proTranCode) {
		
		System.out.println("ProductDAO().updateTranCode(proTranCode");
		System.out.println("\tproTranCode= "+proTranCode);
		
		TranCodeMapper tranCodeMapper = TranCodeMapper.getInstance();
		Map<String, String> map = tranCodeMapper.getMap();
		Set<String> keySet = map.keySet();
		
		try {
			if (keySet.contains(proTranCode)) {
				System.out.println("\t�����Ϸ��� ��ǰ����= "+map.get(proTranCode));
				
			} else {
				throw new Exception("�ùٸ��� �ʴ� proTranCode�Դϴ�");
				
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
				System.out.println("\t"+rs+" ���� �����Ǿ����ϴ�.");
				
			} else {
				System.out.println("\t���� �����Ͽ����ϴ�.");
				
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
