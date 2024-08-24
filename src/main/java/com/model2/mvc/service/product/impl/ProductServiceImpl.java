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
	
	// TODO return Type ���� �ʿ��غ���
	// ��ǰ ����� ���� BL
	@Override
	public ProductVO addProduct(ProductVO productVO) {
		
		productDAO.insertProduct(productVO);
		System.out.println("\t"+productVO+"\n\tDB ��ϿϷ�");
		ProductVO prodVO = productDAO.findProduct();
		System.out.println("\tDB ����= "+prodVO);
		
		return prodVO;		
	}
	
	// ��ǰ���� ��ȸ�� ���� BL 
	@Override
	public ProductVO getProdut(int prodNo) {
		
		return productDAO.findProduct(prodNo);
	}
	
	// ��ǰ��� ��ȸ�� ���� BL
	@Override
	public HashMap<String, Object> getProductList(SearchVO searchVO) {
		
		return productDAO.getProductList(searchVO);
	}
	
	// ��ǰ���� ������ ���� BL
	@Override
	public ProductVO updateProduct(ProductVO productVO) {
		
		productDAO.updateProduct(productVO);
		System.out.println("\tDB �����Ϸ�");
		ProductVO prodVO = productDAO.findProduct(productVO.getProdNo());
		System.out.println("\tDB ����= "+prodVO);
		
		return prodVO;
	}

}
// class end
