package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.Dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
    public PhonebookController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("phonebook controller");
		String action = request.getParameter("action");
		
		System.out.println(action);
		
		if( "list".equals(action)) {
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.personSelect();
	    
			System.out.println(personList);
			request.setAttribute("pList", personList);

			// 포워드 
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/list.jsp");
			rd.forward(request, response);
		}
		
		else if("writeForm".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/writeForm.jsp");
			rd.forward(request, response);
		}
		
		else if("updateForm".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			PersonVo personVo = new PhoneDao().getPerson(id);
			request.setAttribute("pVo", personVo);
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/updateForm.jsp");
			rd.forward(request, response);
		}
		
		else if("update".equals(action)) {
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int personId = Integer.parseInt(request.getParameter("id"));
			
			PersonVo personVo = new PersonVo(personId, name, hp, company);
			new PhoneDao().personUpdate(personVo);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
		}
		
		else if("write".equals(action)) {
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo personVo = new PersonVo(name, hp, company);
			new PhoneDao().personInsert(personVo);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
		}

		else if( "delete".equals(action)) {
			int personId = Integer.parseInt(request.getParameter("id"));
			new PhoneDao().personDelete(personId);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
		}
		
		else
			System.out.println("파라미터 없음");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}