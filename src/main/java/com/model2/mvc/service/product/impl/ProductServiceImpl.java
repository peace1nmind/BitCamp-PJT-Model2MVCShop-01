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
		productDAO = new ProductDAO();
	}
	
	// TODO return Type ���� �ʿ��غ���
	// ��ǰ ����� ���� BL
	@Override
	public ProductVO addProduct(ProductVO productVO) {
		return null;		
	}
	
	// ��ǰ���� ��ȸ�� ���� BL 
	@Override
	public ProductVO getProdut(int prodNo) {
		return null;
	}
	
	// ��ǰ��� ��ȸ�� ���� BL
	@Override
	public HashMap getProductList(SearchVO searchVO) {
		return null;
	}
	
	// ��ǰ���� ������ ���� BL
	@Override
	public ProductVO updateProduct(ProductVO productVO) {
		return null;
	}

}
// class end
