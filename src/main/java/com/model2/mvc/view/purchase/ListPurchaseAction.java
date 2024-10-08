package com.model2.mvc.view.purchase;
// W D 

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.Paging;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

// listPurchase.do
// 구매목록 요청
public class ListPurchaseAction extends Action {

	// Field

	// Constructor
	public ListPurchaseAction() {
	}

	// Method
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("\n>> ListPurchaseAction");
		
		SearchVO searchVO = new SearchVO(getServletContext());
		
		if (request.getParameter("page") != null) {
			int page = Integer.parseInt(request.getParameter("page"));
			System.out.println("\tpage= "+page);
			searchVO.setPage(page);
			
		}
		
		// listPurchase를 위한 로직
		PurchaseService service = new PurchaseServiceImpl();
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user") == null) {
			return "redirect:/user/loginView.jsp";
		}
		
		UserVO user = (UserVO) session.getAttribute("user");
		Map<String, Object> map = service.getPurchaseList(searchVO, user.getUserId(), "3", false);
		
		System.out.println("\tmap= "+map);
		request.setAttribute("map", map);
		System.out.println("\tsearchVO= "+searchVO);
		request.setAttribute("searchVO", searchVO);
		
		Paging paging = new Paging(getServletContext());
		request.setAttribute("paging", paging);
		
		// listPurchaseHistory를 위한 로직
		SearchVO historySearchVO = new SearchVO(getServletContext());
		
		if (request.getParameter("historyPage") != null) {
			int historyPage = Integer.parseInt(request.getParameter("historyPage"));
			System.out.println("\thistoryPage= "+historyPage);
			searchVO.setPage(historyPage);
			
		}
		
		Map<String, Object> historyMap = service.getPurchaseList(historySearchVO, user.getUserId(), "4", true);
		request.setAttribute("historyMap", historyMap);
		request.setAttribute("historySearchVO", historySearchVO);
			
		return "forward:/purchase/listPurchase.jsp";
	}
	
	
	public void executeAction(	HttpServletRequest request, HttpServletResponse response, SearchVO searchVO)
								throws Exception {
		
		System.out.println("\n>> ListPurchaseAction.executeAction");
		
		if (request.getParameter("page") != null) {
			int page = Integer.parseInt(request.getParameter("page"));
			System.out.println("\tpage= "+page);
			searchVO.setPage(page);
			
		}
		
		// listPurchase를 위한 로직
		PurchaseService service = new PurchaseServiceImpl();
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		Map<String, Object> map = service.getPurchaseList(searchVO, user.getUserId(), "3", false);
		
		System.out.println("\tmap= "+map);
		request.setAttribute("map", map);
		System.out.println("\tsearchVO= "+searchVO);
		request.setAttribute("searchVO", searchVO);
		
	}

}
// class end
