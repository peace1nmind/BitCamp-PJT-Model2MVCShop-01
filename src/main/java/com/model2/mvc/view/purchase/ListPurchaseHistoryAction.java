package com.model2.mvc.view.purchase;
// W D 

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.Paging;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class ListPurchaseHistoryAction extends Action {

	// Field

	// Constructor
	public ListPurchaseHistoryAction() {
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* listPurchase도 페이지가 같이 넘어가는거 해결하기 데이터가 안나옴 */
		System.out.println("\n>> ListPurchaseHistoryAction.execute");
		
		SearchVO searchVO = new SearchVO(getServletContext());
		
		new ListPurchaseAction().executeAction(request, response, searchVO);
				
		if (request.getParameter("historyPage") != null) {
			int page = Integer.parseInt(request.getParameter("historyPage"));
			System.out.println("\tpage= "+page);
			searchVO.setPage(page);
			
		}
		
		PurchaseService service = new PurchaseServiceImpl();
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		Map<String, Object> map = service.getPurchaseList(searchVO, user.getUserId(), "4", true);
		
		System.out.println("\tmap= "+map);
		request.setAttribute("historyMap", map);
		System.out.println("\tsearchVO= "+searchVO);
		request.setAttribute("searchVO", searchVO);
		
		Paging paging = new Paging(getServletContext());
		request.setAttribute("paging", paging);
		
		return "forward:/purchase/listPurchase.jsp";
	}
	
//	public void executeAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		System.out.println("\n>> ListPurchaseHistoryAction.executeAction");
//		
//		SearchVO searchVO = (SearchVO) request.getAttribute("searchVO");
//		
//		if (request.getParameter("page") != null) {
//			int page = Integer.parseInt(request.getParameter("page"));
//			System.out.println("\tpage= "+page);
//			searchVO.setPage(page);
//			
//		}
//		
//		PurchaseService service = new PurchaseServiceImpl();
//		HttpSession session = request.getSession();
//		UserVO user = (UserVO) session.getAttribute("user");
//		Map<String, Object> map = service.getPurchaseList(searchVO, user.getUserId(), "4", true);
//		
//		System.out.println("\tmap= "+map);
//		request.setAttribute("map", map);
//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/purchase/listPurchaseHistory.jsp");
//		dispatcher.forward(request, response);
//	}
}
// class end
