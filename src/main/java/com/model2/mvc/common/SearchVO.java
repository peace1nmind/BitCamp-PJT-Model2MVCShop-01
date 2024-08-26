package com.model2.mvc.common;

import javax.servlet.ServletContext;

public class SearchVO {
	
	// ** page dafault 값 변경 page; → page = 1;
	private int page = 1;
	String searchCondition;
	String searchKeyword;
	int pageUnit;
	
	public SearchVO(ServletContext servletContext){
		// 인스턴스를 생성할때 Action에서 ActionServlet에서 받은 servletContext를 넘겨 받아서
		// web.xml에 있는 param 값을 가져옴
		pageUnit = Integer.parseInt(servletContext.getInitParameter("pageSize"));
	}
	
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	@Override
	public String toString() {
		
		return String.format("SearchVO: [page= %d, searchCondition= %s, searchKeyword= %s, pageUnit= %d]",
												page, searchCondition, searchKeyword, pageUnit);
	}
}