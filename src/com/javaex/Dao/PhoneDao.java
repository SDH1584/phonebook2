package com.javaex.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhoneDao {
	
	// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		private String driver = "oracle.jdbc.driver.OracleDriver";
		private String url = "jdbc:oracle:thin:@localhost:1521:xe";
		private String id = "phonedb";
		private String pw = "phonedb";

		private void getConnection() {
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);

				// 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, id, pw);
				// System.out.println("접속성공");

			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		public void close() {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		// 사람 추가
		public int personInsert(PersonVo personVo) {
			int count = 0;
			getConnection();

			try {

				// 3. SQL문 준비 / 바인딩 / 실행
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " INSERT INTO person ";
				query += " VALUES (seq_person_id.nextval, ?, ?, ?) ";
				// System.out.println(query);

				pstmt = conn.prepareStatement(query); // 쿼리로 만들기

				pstmt.setString(1, personVo.getName()); // ?(물음표) 중 1번째, 순서중요
				pstmt.setString(2, personVo.getHp()); // ?(물음표) 중 2번째, 순서중요
				pstmt.setString(3, personVo.getCompany()); // ?(물음표) 중 3번째, 순서중요

				count = pstmt.executeUpdate(); // 쿼리문 실행

				// 4.결과처리
				// System.out.println("[" + count + "건 추가되었습니다.]");

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			close();
			return count;
		}
		
		
		//사람 리스트(검색안할때)
		public List<PersonVo> getPersonList() {
			return getPersonList("");
		}

		// 사람 리스트(검색할때)
		public List<PersonVo> getPersonList(String keword) {
			List<PersonVo> personList = new ArrayList<PersonVo>();

			getConnection();

			try {

				// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
				String query = "";
				query += " select  person_id, ";
				query += "         name, ";
				query += "         hp, ";
				query += "         company ";
				query += " from person";

				if (keword != "" || keword == null) {
					query += " where name like ? ";
					query += " or hp like  ? ";
					query += " or company like ? ";
					pstmt = conn.prepareStatement(query); // 쿼리로 만들기

					pstmt.setString(1, '%' + keword + '%'); // ?(물음표) 중 1번째, 순서중요
					pstmt.setString(2, '%' + keword + '%'); // ?(물음표) 중 2번째, 순서중요
					pstmt.setString(3, '%' + keword + '%'); // ?(물음표) 중 3번째, 순서중요
				} else {
					pstmt = conn.prepareStatement(query); // 쿼리로 만들기
				}

				rs = pstmt.executeQuery();

				// 4.결과처리
				while (rs.next()) {
					int personId = rs.getInt("person_id");
					String name = rs.getString("name");
					String hp = rs.getString("hp");
					String company = rs.getString("company");

					PersonVo personVo = new PersonVo(personId, name, hp, company);
					personList.add(personVo);
				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			close();

			return personList;

		}


		// 사람 수정
		public int personUpdate(PersonVo personVo) {
			int count = 0;
			getConnection();

			try {

				// 3. SQL문 준비 / 바인딩 / 실행
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " update person ";
				query += " set name = ? , ";
				query += "     hp = ? , ";
				query += "     company = ? ";
				query += " where person_id = ? ";

				pstmt = conn.prepareStatement(query); // 쿼리로 만들기

				pstmt.setString(1, personVo.getName()); // ?(물음표) 중 1번째, 순서중요
				pstmt.setString(2, personVo.getHp()); // ?(물음표) 중 2번째, 순서중요
				pstmt.setString(3, personVo.getCompany()); // ?(물음표) 중 3번째, 순서중요
				pstmt.setInt(4, personVo.getPersonId()); // ?(물음표) 중 4번째, 순서중요

				count = pstmt.executeUpdate(); // 쿼리문 실행

				// 4.결과처리
				// System.out.println(count + "건 수정되었습니다.");

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			close();
			return count;
		}

		// 사람 삭제
		public int personDelete(int personId) {
			int count = 0;
			getConnection();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " delete from person ";
				query += " where person_id = ? ";
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기

				pstmt.setInt(1, personId);// ?(물음표) 중 1번째, 순서중요

				count = pstmt.executeUpdate(); // 쿼리문 실행

				// 4.결과처리
				// System.out.println(count + "건 삭제되었습니다.");

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			close();
			return count;
		}
		
		public PersonVo getPerson(int personId) {
			PersonVo personVo = null;
		      
			getConnection();
		
			try {
		         // 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
				 String query = "";
				 query += " select  person_id, ";
				 query += "         name, ";
				 query += "         hp, ";
				 query += "         company ";
				 query += " from person ";
				 query += " where person_id = ? ";
				
				 pstmt = conn.prepareStatement(query); // 쿼리로 만들기
				
				 pstmt.setInt(1, personId); // ?(물음표) 중 1번째, 순서중요
				 
				 
				 rs = pstmt.executeQuery();
				
				 // 4.결과처리
				 rs.next();
				 int id = rs.getInt("person_id");
				 String name = rs.getString("name");
				 String hp = rs.getString("hp");
				 String company = rs.getString("company");
				
				        personVo = new PersonVo(id, name, hp, company);
				     
			} catch (SQLException e) {
		    	System.out.println("error:" + e);
			}
		
		      close();
		
		      return personVo;
		   }

		
}