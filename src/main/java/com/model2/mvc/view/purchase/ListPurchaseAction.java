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
// ���Ÿ�� ��û
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
		
		// listPurchase�� ���� ����
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
		
		// listPurchaseHistory�� ���� ����
		Map<String, Object> historyMap = service.getPurchaseList(searchVO, user.getUserId(), "4", true);
		request.setAttribute("historyMap", historyMap);
			
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
		
		// listPurchase�� ���� ����
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
