<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Contact"%>
<%
	@SuppressWarnings("unchecked")
	List<String> errors = (List<String>) request.getAttribute("errors");
	Contact contact = (Contact) request.getAttribute("contact");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>お問い合わせ：エラー</title>
<link rel="stylesheet" href="/hiiragi/css/mystyle.css">
<meta name="viewport" content="width=device-width">
</head>
<body>
	<header>
		<div class="header-in">
			<a href="/hiiragi/index.html" id="pagetop"><img src="/hiiragi/image/logo.png" width="300" height="56"
				alt="Hiiragi Real Estate"></a>
		</div>
	</header>

	<article class="page">
		<ol>
			<li><a href="/hiiragi/index.html">トップ</a>&nbsp;&nbsp;&gt;</li>
			<li>お問い合わせ</li>
		</ol>
		<h1 class="sub-h1">お問い合わせを送信できませんでした</h1>

		<%
			if (errors != null && !errors.isEmpty()) {
		%>
		<ul>
			<%
				for (String e : errors) {
			%>
			<li><%=e%></li>
			<%
				}
			%>
		</ul>
		<%
			} else {
		%>
		<p>時間をおいて再度お試しください。</p>
		<%
			}
		%>

		<p>
			<a href="/hiiragi/contact.html">お問い合わせフォームへ戻る</a>
		</p>

		<%
			if (contact != null) {
		%>
		<h2>送信内容（参考）</h2>
		<table>
			<tr>
				<th>名前</th>
				<td><%=contact.getName()%></td>
			</tr>
			<tr>
				<th>フリガナ</th>
				<td><%=contact.getFurigana()%></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><%=contact.getTel()%></td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td><%=contact.getMail()%></td>
			</tr>
		</table>
		<%
			}
		%>
	</article>
</body>
</html>

