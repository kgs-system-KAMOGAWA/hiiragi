<%@page import="model.Contact,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面：ひいらぎ不動産</title>
<link rel="stylesheet" href="/hiiragi/css/mystyle.css">
<meta name="description" content="ひいらぎ不動産の管理者画面です。">
<meta name="viewport" content="width=device-width">
</head>
<body>
	<header>
		<div class="header-in">
			<a href="/hiiragi/index.html" id="pagetop"><img
				src="/hiiragi/image/logo.png" width="300" height="56"
				alt="Hiiragi Real Estate"></a>
			<nav>
				<ul>
					<li><a href="/hiiragi/index.html">トップ</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<article class="page">
		<ol>
			<li><a href="/hiiragi/index.html">トップ</a>&nbsp;&nbsp;&gt;</li>
			<li>管理者画面</li>
		</ol>
		<h1 class="sabu-h1">管理者画面</h1>
		<div style="overflow-x: auto;">
			<table class="contact-table">
				<tr>
					<th>ID</th>
					<th>名前</th>
					<th>フリガナ</th>
					<th>電話番号</th>
					<th>メールアドレス</th>
					<th>希望地域</th>
					<th>お問い合わせ内容</th>
				</tr>
				<%
					// リクエストスコープからリストを取得
					List<Contact> contactList = (List<Contact>) request.getAttribute("contactList");
					if (contactList != null) {
						for (Contact contact : contactList) {
				%>
				<tr>
					<td><%=contact.getId()%></td>
					<td><%=contact.getName()%></td>
					<td><%=contact.getFurigana()%></td>
					<td><%=contact.getTel()%></td>
					<td><%=contact.getMail()%></td>
					<td>
						<%
							String[] chiikiArray = contact.getChiiki();
									if (chiikiArray != null && chiikiArray.length > 0) {
										out.print(String.join(", ", chiikiArray));
									} else {
										out.print("未選択");
									}
						%>
					</td>
					<td><%=contact.getComment()%></td>
				</tr>
				<%
						}
					}else{
						out.print("データの読み込みに失敗しました。");
					}
				%>
			</table>
		</div>
	</article>
	<footer>

		<small>&copy; 2024 Hiiragi Real Estate. All Rights Reserved.</small>
	</footer>
</body>
</html>