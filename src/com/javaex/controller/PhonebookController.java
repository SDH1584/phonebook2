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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		System.out.println("PhonebookController");
		//파라미터에 따라 출력값변경??? 진짜로?
		//writeForm==> 전화번호 등록폼
		String act= request.getParameter("action");
//		System.out.println(act);
		
		
		if("list".equals(act)) {
			System.out.println("action=list");
		PhoneDao phoneDao=new PhoneDao();
		List<PersonVo> personList=phoneDao.getPersonList();
//		System.out.println(personList);
		
		
		//html과 list를 섞어서 표현해야함
		//servlet으로는 표현이 복잡하여 jsp사용
		
		
		request.setAttribute("pList", personList);
				//포워드
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/list.jsp");
		rd.forward(request,response);
		
		}else if("writeForm".equals(act)) {
			System.out.println("action=writeForm");
			RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request,response);
			
		}else if("write".equals(act)) {
			System.out.println("action=write");
			//파라미터 3개의 꺼내온다  
			String name=request.getParameter("name");
			String hp=request.getParameter("hp");
			String company=request.getParameter("company");

			//vo로 만들기기
			PersonVo personVo=new PersonVo(name,hp,company);
			System.out.println(personVo);
			//dao 메모리 올리기
			PhoneDao phonedao=new PhoneDao();
			//dao.insert(vo);
			phonedao.personInsert(personVo);
			
			//리다이렉트 (포워드x) 주소입력
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}else if("delete".equals(act)) {
				System.out.println(act);
				
				int personId = Integer.parseInt(request.getParameter("personId"));
				
				PhoneDao phoneDao = new PhoneDao();
				
				phoneDao.personDelete(personId);
				
				response.sendRedirect("/phonebook2/pbc?action=list");
			}
			else if("updateForm".equals(act)) {
				System.out.println(act);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
				rd.forward(request, response);			
			}
			else if("update".equals(act)) {
				System.out.println(act);
				
				PhoneDao phoneDao = new PhoneDao();
				
				String name = request.getParameter("name");
				String hp = request.getParameter("hp");
				String company = request.getParameter("company");
				int personId = Integer.parseInt(request.getParameter("personId"));
				
				PersonVo pvo = new PersonVo(personId, name, hp, company);
				
				phoneDao.personUpdate(pvo);
				request.setAttribute("vo", pvo);

				response.sendRedirect("/phonebook2/pbc?action=list");
			
			
		}else {
			System.out.println("파라미터없다");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
