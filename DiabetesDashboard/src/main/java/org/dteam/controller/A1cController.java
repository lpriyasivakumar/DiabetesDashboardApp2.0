package org.dteam.controller;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.dteam.dao.A1cDAO;
import org.dteam.dao.DAOFactory;
import org.dteam.utilities.CalcA1cEst;

@Controller
public class A1cController {

	@RequestMapping(value = "/A1c", method = RequestMethod.GET)
	public ModelAndView viewA1c(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();		
		if (session.getAttribute("userID") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			String action = request.getParameter("action");
			String userID = session.getAttribute("userID").toString();
			String calcA1c = "";
			if (action.equals("CalcA1c")) {
				DecimalFormat df = new DecimalFormat("#.00");
				calcA1c = df.format(CalcA1cEst.getCalcA1cEstimate(userID));
				session.setAttribute("calcA1c",calcA1c);
			}			
			return new ModelAndView("redirect:/dashboard");
			
		}
	}

	@RequestMapping(value = "/A1c", method = RequestMethod.POST)
	public ModelAndView calculateA1c(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		A1cDAO a1cdao = getA1cDAO();
		String action = request.getParameter("action");
		String userID = session.getAttribute("userID").toString();
		if (userID == null || userID.isEmpty()) {
			return new ModelAndView("redirect:/login");
		} else {
			if (action.equals("saveLabValue")) {
				double labA1c = Double.parseDouble(request.getParameter("labA1c"));
				a1cdao.addLabValue(labA1c, userID);
			}

			return new ModelAndView("redirect:/dashboard");

		}
	}

	public A1cDAO getA1cDAO() {
		return DAOFactory.getDAOFactory(DAOFactory.MYSQL).getA1cDAO();
	}

}
