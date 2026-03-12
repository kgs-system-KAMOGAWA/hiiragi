package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contact;
import model.ContactLogic;
import model.Login;
import model.LoginLogic;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//パラメータの値を変数へ代入
		String userId = request.getParameter("userId");
		String pass = request.getParameter("userPass");

		//ログインの処理の初期化
		Login login = new Login(userId, pass);
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);

		//ログイン処理　認証成功でadminへ遷移。失敗するとログイン画面へ
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);

			ContactLogic contactLogic = new ContactLogic();
			List<Contact> contactList = contactLogic.getContactList();
			request.setAttribute("contactList", contactList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("/hiiragi/LoginServlet");
		}
	}

}
