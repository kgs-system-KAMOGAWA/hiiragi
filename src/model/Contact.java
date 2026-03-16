package model;

import java.io.Serializable;

/**
 * コンタクトの処理クラス
 * @author user111
 *
 */
public class Contact implements Serializable {
	private int id;
	private String name;
	private String furigana;
	private String tel;
	private String mail;
	private String comment;
	private String[] chiiki;

	//コンストラクタ
	public Contact() {
	}

	public Contact(String name, String furigana, String tel, String mail, String[] chiiki, String comment) {
		this.name = name;
		this.furigana = furigana;
		this.tel = tel;
		this.mail = mail;
		this.comment = comment;
		this.chiiki = chiiki;
	}

	public Contact(int id, String name, String furigana, String tel, String mail,  String[] chiiki,String comment) {
		this.id = id;
		this.name = name;
		this.furigana = furigana;
		this.tel = tel;
		this.mail = mail;
		this.comment = comment;
		this.chiiki = chiiki;
	}

	//セッター
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setChiiki(String[] chiiki) {
		this.chiiki = chiiki;
	}

	//ゲッター
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFurigana() {
		return furigana;
	}

	public String getTel() {
		return tel;
	}

	public String getMail() {
		return mail;
	}

	public String getComment() {
		return comment;
	}

	public String[] getChiiki() {
		return chiiki;
	}

	//git練習中

}
