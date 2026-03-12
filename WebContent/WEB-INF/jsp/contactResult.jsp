<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Contact"%>
<%
	Contact contact = (Contact) request.getAttribute("contact");
	if (contact == null) {
		out.print("お問い合わせ情報が取得できませんでした。時間をおいて再度お試しください。");
		return;
	}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>お問い合わせ：ひいらぎ不動産</title>
<link rel="stylesheet" href="/hiiragi/css/mystyle.css">
<meta name="description" content="ひいらぎ不動産のお問い合わせ先です。">
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
					<li><a href="/hiiragi/point.html">物件選びのポイント</a></li>
					<li><a href="/hiiragi/bukken01.html">おすすめ物件</a></li>
					<li><a href="/hiiragi/company.html">会社案内</a></li>
					<li>お問い合わせ</li>
				</ul>
			</nav>
		</div>
	</header>
	<article class="page">
		<ol>
			<li><a href="/hiiragi/index.html">トップ</a>&nbsp;&nbsp;&gt;</li>
			<li>お問い合わせ</li>
		</ol>
		<h1 class="sub-h1">お問い合わせが完了しました</h1>
		<p>お問い合わせいただきありがとうございました。お問い合わせを受け付けました。折り返し、担当者よりご連絡いたしますので、恐れ入りますが、しばらくお待ちください。</p>
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
			<tr>
				<th>希望地域</th>
				<td>
					<%
						String[] chiiki = contact.getChiiki();
						if (chiiki != null) {
							out.print(String.join("、", chiiki));
						} else {
							out.print("未選択");
						}
					%>
				</td>
			</tr>
			<tr>
				<th>お問い合わせ内容</th>
				<td><%=contact.getComment()%></td>
			</tr>
		</table>
	</article>
	<footer>
		<p>川崎市のお部屋探しは「ひいらぎ不動産」におまかせください</p>
		<address>
			〒212-0014 神奈川県川崎市幸区大宮町1-5 JR川崎タワー<br> TEL 044-XXX-XXXX
		</address>
		<small>&copy; 2024 Hiiragi Real Estate. All Rights Reserved.</small>
	</footer>
</body>
</html>