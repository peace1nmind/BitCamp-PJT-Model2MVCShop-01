package com.model2.mvc.service;
// W D 

import javax.servlet.ServletContext;

public class Paging {

	// Field
	private int start = 1;
	private int end = 1;
	private boolean left;
	private boolean right;
	int pageUnit;
	
	
	// Constructor
	public Paging(ServletContext servletContext) {
		
		System.out.println("\ncom.model2.mvc.service");
		System.out.println("Paging");
		
		pageUnit = Integer.parseInt(servletContext.getInitParameter("pageSize"));
		
	}

	
	// Method
	public void calculatePage (int totalPage, int currentPage) {
		
		System.out.println("Paging().calculatePage");
		System.out.println("\ttotalPage= "+totalPage);
		System.out.println("\tcurrentPage= "+currentPage);
		
		if (totalPage > 0) {
			
			end = (int) Math.ceil(currentPage * 1.0 / pageUnit) * pageUnit;
			if (end > totalPage) {
				end = totalPage;
			}
			
			start = (int) (Math.ceil(currentPage * 1.0 / pageUnit) -1 ) * pageUnit + 1;
			
			left = currentPage > pageUnit;
			right = end < totalPage;
			
		}
		
		System.out.println("\tstartPage= "+start);
		System.out.println("\tendPage= "+end);
		System.out.println("\tleft?= "+left);
		System.out.println("\trigt?= "+right);
		
	}
	
	
	// Getter
	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public int getPageUnit() {
		return pageUnit;
	}
	
}
// class end
