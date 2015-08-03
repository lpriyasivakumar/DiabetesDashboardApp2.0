package org.dteam.controller;

import java.sql.SQLException;


import javax.servlet.http.HttpServletRequest;

import static org.dteam.utilities.GetUser.*;
import org.dteam.dao.DAOFactory;
import org.dteam.dao.ReadingDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChartBloodGlucoseReadingsController {	
	
	@RequestMapping(value = "/chart", method = RequestMethod.GET)
    public String viewChart(HttpServletRequest request){  
		if(getUserInfo(request,"id")==null || getUserInfo(request,"id").isEmpty())
			return "login";
		return "dashboard";	
    }

	@RequestMapping(value = "/chart", method = RequestMethod.POST)
	public @ResponseBody
    String doChart(HttpServletRequest request) 		
		        throws ClassNotFoundException, SQLException {		 	   
			    String userID = getUserInfo(request,"id");
				ReadingDAO readingDAO = getDAO();
				String dateRange = request.getParameter("range");
				readingDAO.getReadings(dateRange, userID);				
				return "dashboard";		        		   
		    	
	}	
	
	public ReadingDAO getDAO(){
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ReadingDAO readingDAO = mysqlFactory.getReadingDAO();
		return readingDAO;
		
	}
    

}


