package model;

import java.util.List;

import dao.ContactDAO;

/**
 * コンタクトの制御クラス
 * @author user111
 *
 */
public class ContactLogic {
	/**
	 * 引数で受け取ったデータをDBへインサート
	 * @param contact
	 * @return 登録できたらtrue
	 */
	public boolean postContact(Contact contact) {
		ContactDAO dao = new ContactDAO();
		return dao.create(contact);
	}

	/**
	 * DBよりデータを受け取りリストとして返す
	 * @return　contactList
	 */
	public List<Contact> getContactList() {
		ContactDAO dao = new ContactDAO();
		List<Contact> contactList = dao.read();
		return contactList;
	}

}
