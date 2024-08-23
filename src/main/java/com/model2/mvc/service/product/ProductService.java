package com.model2.mvc.service.product;
// W D 

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;

public interface ProductService {

	// Field

	// Method
	public ProductVO addProduct(ProductVO productVO);
	
	public ProductVO getProdut(int prodNo);
	
	public HashMap<String, Object> getProductList(SearchVO searchVO);
	
	public ProductVO updateProduct(ProductVO productVO);
	
}
// class end
