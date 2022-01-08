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
		//�Ķ���Ϳ� ���� ��°�����??? ��¥��?
		//writeForm==> ��ȭ��ȣ �����
		String act= request.getParameter("action");
//		System.out.println(act);
		
		
		if("list".equals(act)) {
			System.out.println("action=list");
		PhoneDao phoneDao=new PhoneDao();
		List<PersonVo> personList=phoneDao.getPersonList();
//		System.out.println(personList);
		
		
		//html�� list�� ��� ǥ���ؾ���
		//servlet���δ� ǥ���� �����Ͽ� jsp���
		
		
		request.setAttribute("pList", personList);
				//������
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/list.jsp");
		rd.forward(request,response);
		
		}else if("writeForm".equals(act)) {
			System.out.println("action=writeForm");
			RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request,response);
			
		}else if("write".equals(act)) {
			System.out.println("action=write");
			//�Ķ���� 3���� �����´�  
			String name=request.getParameter("name");
			String hp=request.getParameter("hp");
			String company=request.getParameter("company");

			//vo�� ������
			PersonVo personVo=new PersonVo(name,hp,company);
			System.out.println(personVo);
			//dao �޸� �ø���
			PhoneDao phonedao=new PhoneDao();
			//dao.insert(vo);
			phonedao.personInsert(personVo);
			
			//�����̷�Ʈ (������x) �ּ��Է�
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
			System.out.println("�Ķ���;���");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
