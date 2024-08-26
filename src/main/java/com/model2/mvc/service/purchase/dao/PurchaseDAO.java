package com.model2.mvc.service.purchase.dao;
// W D 

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.dao.AbstractDAO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class PurchaseDAO extends AbstractDAO {

	// Field
	private UserService userService = new UserServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	

	// Constructor
	public PurchaseDAO() {
		System.out.println("\ncom.model2.mvc.service.purchase.dao.PurchaseDAO");
	}

	// Method
	// 구매정보 상세조회를 위한 DBMS
	public PurchaseVO findPurchase(int tranNo) {
		
		System.out.println("PurchaseDAO().findPurchase(tranNo)");
		
		Connection con = connect();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PurchaseVO purchaseVO = new PurchaseVO();
		ProductService productService = new ProductServiceImpl();
		UserService userService = new UserServiceImpl();
		
		String sql = "SELECT * FROM transaction WHERE tran_no=?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, tranNo);
			
			rs = stmt.executeQuery();
			System.out.println("\tstmt.executeQuery()");
			
			if (rs.next()) {
				purchaseVO.setTranNo(rs.getInt("tran_no"));
				purchaseVO.setPurchaseProd(productService.getProduct(rs.getInt("prod_no")));
				purchaseVO.setBuyer(userService.getUser(rs.getString("buyer_id")));
				purchaseVO.setPaymentOption(rs.getString("payment_option"));
				purchaseVO.setReceiverName(rs.getString("receiver_name"));
				purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
				purchaseVO.setDlvyAddr(rs.getString("dlvy_addr"));
				purchaseVO.setDlvyRequest(rs.getString("dlvy_request"));
				purchaseVO.setTranCode(rs.getString("tran_status_code"));
				purchaseVO.setOrderDate(rs.getDate("order_date"));
				
				/* 받을 때 YYMMDD 식으로 받게끔 수정필요 */
				purchaseVO.setDlvyDate(rs.getString("dlvy_date"));
				
				System.out.println("\t찾은 purchaseVO= "+purchaseVO);
				
			} else {
				System.out.println("\ntranNo= "+tranNo+"인 상품은 등록되어있지 않습니다.");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt, rs);
			
		}
		
		return purchaseVO;
	}
	
	// 최근 등록된 구매정보 조회를 위한 DBMS
	public PurchaseVO findPurchase() {
		
		System.out.println("PurchaseDAO().findPurchase()");
		
		Connection con = connect();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PurchaseVO purchaseVO = new PurchaseVO();
		ProductService productService = new ProductServiceImpl();
		UserService userService = new UserServiceImpl();
		
		String sql = "SELECT * FROM transaction ORDER BY tran_no";
		
		try {
			stmt = con.prepareStatement(sql, 
										ResultSet.TYPE_SCROLL_INSENSITIVE, 
										ResultSet.CONCUR_UPDATABLE);
			
			rs = stmt.executeQuery();
			System.out.println("\tstmt.executeQuery()");
			
			rs.last();
			int lastRow = rs.getRow();
			rs.absolute(lastRow);
			purchaseVO.setTranNo(rs.getInt("tran_no"));
			purchaseVO.setPurchaseProd(productService.getProduct(rs.getInt("prod_no")));
			purchaseVO.setBuyer(userService.getUser(rs.getString("buyer_id")));
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setDlvyAddr(rs.getString("dlvy_addr"));
			purchaseVO.setDlvyRequest(rs.getString("dlvy_request"));
			purchaseVO.setTranCode(rs.getString("tran_status_code"));
			purchaseVO.setOrderDate(rs.getDate("order_date"));
			purchaseVO.setDlvyDate(rs.getString("dlvy_date"));
			
			System.out.println("\t최근 등록내용= "+purchaseVO);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt, rs);
			
		}
		
		return purchaseVO;
	}
	
	// 구매목록 보기를 위한 DBMS
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) {
		
		System.out.println("PurchaseDAO().getPurchaseList(searchVO, tranCode)");
		System.out.println("\tSearchVO= "+searchVO);
		System.out.println("\tbuyerId= "+buyerId);
		
		Connection con = connect();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int total = 0;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<PurchaseVO> list = new ArrayList<PurchaseVO>();
		
		String sql = "SELECT * FROM transaction WHERE buyer_id=? ORDER BY order_date DESC";
		System.out.println("\tSQL= "+sql);
		
		try {
			stmt = con.prepareStatement(sql, 
										ResultSet.TYPE_SCROLL_INSENSITIVE, 
										ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, buyerId);
			
			rs = stmt.executeQuery();
			
			rs.last();
			total = rs.getRow();
			
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
					PurchaseVO vo = new PurchaseVO();
					vo.setTranNo(rs.getInt("tran_no"));
					vo.setPurchaseProd(productService.getProduct(rs.getInt("prod_no")));
					vo.setBuyer(userService.getUser(rs.getString("buyer_id")));
					vo.setPaymentOption(rs.getString("payment_option"));
					vo.setReceiverName(rs.getString("receiver_name"));
					vo.setReceiverPhone(rs.getString("receiver_phone"));
					vo.setDlvyAddr(rs.getString("dlvy_addr"));
					vo.setDlvyRequest(rs.getString("dlvy_request"));
					vo.setTranCode(rs.getString("tran_status_code"));
					vo.setOrderDate(rs.getDate("order_date"));
					vo.setDlvyDate(rs.getString("dlvy_date"));
					
					System.out.println(vo);
					
					list.add(vo);
					
					if (!rs.next()) {
						break;
					}
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		System.out.println("\tlist.size() : "+ list.size());
		// 페이지에 해당하는 UserVO들을 map에 ArrayList타입으로 넣음
		map.put("list", list);
		System.out.println("\tmap().size() : "+ map.size());
		
		return map;	
	}
	
	// 판매목록 보기를 위한 DBMS
	public HashMap getSaleList(SearchVO searchVO) {
		return null;	
	}
	
	// 구매를 위한 DBMS
	public void insertPurchase(PurchaseVO purchaseVO) {
		
		System.out.println("PurchaseDAO().insertPurchase(purchaseVO)");
		System.out.println("\tpurchaseVO= "+purchaseVO);
		
		Connection con = connect();
		PreparedStatement stmt = null;
		int rs = -1;
		
		String sql = "INSERT INTO transaction "
					+ "VALUES(seq_transaction_tran_no.NEXTVAL, "
					+ "?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?)";
		System.out.println("\tSQL= "+sql);
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
			stmt.setString(2, purchaseVO.getBuyer().getUserId());
			stmt.setString(3, purchaseVO.getPaymentOption());	// 1:현금구매, 2: 신용구매
			stmt.setString(4, purchaseVO.getReceiverName());
			stmt.setString(5, purchaseVO.getReceiverPhone());
			stmt.setString(6, purchaseVO.getDlvyAddr());
			stmt.setString(7, purchaseVO.getDlvyRequest());
			System.out.println("\t"+purchaseVO.getTranCode().trim());
			stmt.setString(8, purchaseVO.getTranCode().trim());	// 2:구매완료
			stmt.setString(9, purchaseVO.getDlvyDate());
			
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
	
	// 구매정보 수정을 위한 DBMS
	public void updatePurchase(PurchaseVO purchaseVO) {
		
		System.out.println("PurchaseDAO().updatePurchase(purchaseVO)");
		System.out.println("\tpurchaseVO= "+purchaseVO);
		
		Connection con = connect();
		PreparedStatement stmt = null;
		int rs = -1;
		
		String sql = "UPDATE transaction "
					+ "SET payment_option=?, "
						+ "receiver_name=?, "
						+ "receiver_phone=?, "
						+ "dlvy_addr=?, "
						+ "dlvy_request=?, "
						+ "dlvy_date=? ";
		System.out.println("\tSQL= "+sql);
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, purchaseVO.getPaymentOption().trim());
			stmt.setString(2, purchaseVO.getReceiverName());
			stmt.setString(3, purchaseVO.getReceiverPhone());
			stmt.setString(4, purchaseVO.getDlvyAddr());
			stmt.setString(5, purchaseVO.getDlvyRequest());
			stmt.setString(6, purchaseVO.getDlvyDate());
			
			rs = stmt.executeUpdate();
			System.out.println("\tstmt.executeUpdate()");
			
			if (rs > 0) {
				System.out.println("\t"+rs+" 행이 수정되었습니다.");
				
			} else {
				System.out.println("\tDB 수정 실패하였습니다.");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, stmt);
			
		}
		
	}
	
	// 구매상태코드 수정을 위한 DBMS
	public void updateTranCode(PurchaseVO purchaseVO) {
		
		System.out.println("PurchaseDAO().updateTranCode(purchaseVO)");
		System.out.println("\tpurchaseVO= "+purchaseVO);
		
	}
	
}
// class end
