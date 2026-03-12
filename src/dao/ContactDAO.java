package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Contact;

/**
 * コンタクトのDAO
 * @author user111
 *
 */
public class ContactDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/hiiragi";
	private final String DB_USER = "sa";
	private final String DB_PASS = "pass";

	/**
	 * DBよりcontactのデータを読み込み、リストとして返す
	 * @return　contactList
	 */
	public List<Contact> read() {
		List<Contact> contactList = new ArrayList<>();

		//JDBCドライバの確認
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		//DB接続、データ読み込み、リスト化
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID,NAME,FURIGANA,TEL,EMAIL,CHIIKI,COMMENT "
					+ "FROM CONTACT";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String furigana = rs.getString("FURIGANA");
				String tel = rs.getString("TEL");
				String mail = rs.getString("EMAIL");
				String chiikiStr = rs.getString("CHIIKI");
				String[] chiiki = (chiikiStr != null) ? chiikiStr.split(",") : new String[0];
				String comment = rs.getString("COMMENT");

				Contact contact = new Contact(id,name,furigana,tel,mail,chiiki,comment);
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return contactList;
	}

	/**
	 * 引数で受け取ったcontactの情報をDBへインサートする
	 * @param contact
	 * @return DBに登録できればtrue できなければfalse
	 */
	public boolean create(Contact contact) {
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try (Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)) {

			//INSERT文の準備(idは自動連番なので指定しなくてよい)
			String sql = "INSERT INTO CONTACT(NAME,FURIGANA,TEL,EMAIL,CHIIKI,COMMENT)VALUES(?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文中の「?」に使用する値を設定してSQL文を完成
			pStmt.setString(1, contact.getName());
			pStmt.setString(2, contact.getFurigana());
			pStmt.setString(3, contact.getTel());
			pStmt.setString(4, contact.getMail());
			String[] chiikiArray = contact.getChiiki();
			String chiikiJoined = (chiikiArray != null) ? String.join(",", chiikiArray) : "";
			pStmt.setString(5, chiikiJoined);
			pStmt.setString(6, contact.getComment());

			//INSERT文を実行（resultには追加された行数が代入される）
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
