package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contact;
import model.ContactLogic;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SESSION_ATTR_CONTACT = "contact";
	private static final String SESSION_ATTR_FLASH_ERRORS = "contactErrors";
	private static final Pattern TEL_PATTERN = Pattern.compile("^[0-9]{10,11}$");
	private static final Pattern MAIL_PATTERN = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String status = request.getParameter("status");
		if ("success".equals(status)) {
			HttpSession session = request.getSession(false);
			Contact contact = (session != null) ? (Contact) session.getAttribute(SESSION_ATTR_CONTACT) : null;
			if (session != null) {
				session.removeAttribute(SESSION_ATTR_CONTACT);
			}
			request.setAttribute("contact", contact);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/contactResult.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// 通常アクセスはフォームへ
		response.sendRedirect(request.getContextPath() + "/contact.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//各変数にrequestからのパラメータを格納
		String name = trimToNull(request.getParameter("username"));
		String furigana = trimToNull(request.getParameter("userfurigana"));
		String tel = trimToNull(request.getParameter("usertel"));
		String mail = trimToNull(request.getParameter("usermail"));
		String comment = trimToNull(request.getParameter("usercomment"));
		String[] chiiki = request.getParameterValues("userchiiki");

		List<String> errors = validate(name, furigana, tel, mail, comment, chiiki);
		if (!errors.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.setAttribute("errors", errors);
			request.setAttribute("contact", new Contact(name, furigana, tel, mail, chiiki, comment));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/contactError.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//各パラメータの値をコンタクトクラスに代入
		Contact contact = new Contact(name, furigana, tel, mail, chiiki, comment );

		//コンタクトの値をDBへインサート処理
		ContactLogic logic = new ContactLogic();
		boolean created = logic.postContact(contact);
		if (!created) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			errors = List.of("現在お問い合わせを受け付けられません。時間をおいて再度お試しください。");
			request.setAttribute("errors", errors);
			request.setAttribute("contact", contact);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/contactError.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// PRG: 二重送信防止のためリダイレクト
		HttpSession session = request.getSession(true);
		session.setAttribute(SESSION_ATTR_CONTACT, contact);
		session.removeAttribute(SESSION_ATTR_FLASH_ERRORS);
		response.sendRedirect(request.getContextPath() + "/ContactServlet?status=success");
	}

	private static String trimToNull(String s) {
		if (s == null) return null;
		String t = s.trim();
		return t.isEmpty() ? null : t;
	}

	private static List<String> validate(String name, String furigana, String tel, String mail, String comment,
			String[] chiiki) {
		List<String> errors = new ArrayList<>();

		if (name == null) {
			errors.add("名前（必須）を入力してください。");
		} else if (name.length() > 50) {
			errors.add("名前は50文字以内で入力してください。");
		}

		if (furigana != null && furigana.length() > 50) {
			errors.add("フリガナは50文字以内で入力してください。");
		}

		if (tel == null) {
			errors.add("電話番号（必須）を入力してください。");
		} else if (!TEL_PATTERN.matcher(tel).matches()) {
			errors.add("電話番号はハイフンなしの10〜11桁の半角数字で入力してください。");
		}

		if (mail == null) {
			errors.add("メールアドレス（必須）を入力してください。");
		} else if (mail.length() > 254 || !MAIL_PATTERN.matcher(mail).matches()) {
			errors.add("メールアドレスの形式が正しくありません。");
		}

		if (comment != null && comment.length() > 2000) {
			errors.add("お問い合わせ内容は2000文字以内で入力してください。");
		}

		if (chiiki != null && chiiki.length > 20) {
			errors.add("希望地域の選択数が多すぎます。");
		}

		return errors;
	}

}
