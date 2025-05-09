package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import dao.BookDAO;
import model.Book;

@WebServlet("/BookShop/bookDetail.do")
public class BookDetailController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//bookDAO클래스를 사용하여 dto 인스턴스 생성
		BookDAO dao = new BookDAO();
		//bookList.jsp에서 매개변수로 넘긴 bookID값을 가져와서 INT로 바뀐뒤 BOOKID에 저장
		int bookID = Integer.parseInt(req.getParameter("bookID"));
		//bookID값을 dao인스턴스의 selectOne메서드의 매개변수로 넘기고, selectOne메서드의 결과값을 받아옴 
		Book dto = dao.selectOne(bookID);
		
	
		List<Book> bookList = dao.bookList();
		int totalCount = dao.selectCount();
		//dto의 결과값을 view(bookDetail.jsp)로 넘기기 위해 속성지정
		req.setAttribute("dto", dto);
		req.setAttribute("bookLists", bookList);
		req.setAttribute("totalCount", totalCount);
		//request, response 내장객체를 가지고 view(bookDetail.jsp)로 이동
		req.getRequestDispatcher("bookDetail.jsp").forward(req, res);
	}

}
