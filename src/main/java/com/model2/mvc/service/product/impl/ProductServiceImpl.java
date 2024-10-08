package com.model2.mvc.service.product.impl;
// W D 

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductServiceImpl implements ProductService {

	// Field
	private ProductDAO productDAO;

	// Constructor
	public ProductServiceImpl() {
		System.out.println("\ncom.modle2.mvc.service.product.impl.ProductServiceImpl");
		productDAO = new ProductDAO();
	}
	
	// 상품 등록을 위한 BL
	@Override
	public ProductVO addProduct(ProductVO productVO) {
		
		productDAO.insertProduct(productVO);
		System.out.println("\t"+productVO+"\n\tDB 등록완료");
		ProductVO prodVO = productDAO.findProduct();
		System.out.println("\tDB 내용= "+prodVO);
		
		return prodVO;		
	}
	
	// 상품정보 조회를 위한 BL 
	@Override
	public ProductVO getProduct(int prodNo) {
		
		return productDAO.findProduct(prodNo);
	}
	
	// 상품목록 조회를 위한 BL
	@Override
	public HashMap<String, Object> getProductList(SearchVO searchVO) {
		
		return productDAO.getProductList(searchVO);
	}
	
	// 상품정보 수정을 위한 BL
	@Override
	public ProductVO updateProduct(ProductVO productVO) {
		
		productDAO.updateProduct(productVO);
		System.out.println("\tDB 수정완료");
		ProductVO prodVO = productDAO.findProduct(productVO.getProdNo());
		System.out.println("\tDB 내용= "+prodVO);
		
		return prodVO;
	}
	
	// 상품상태 수정을 위한 BL
	@Override
	public void updateTranCode(int prodNo, String proTranCode) {
		
		productDAO.updateTranCode(prodNo, proTranCode);
		
	}
	

}
// class end
