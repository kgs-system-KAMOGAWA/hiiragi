<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログイン画面：ひいらぎ不動産</title>
<link rel="stylesheet" href="/hiiragi/css/mystyle.css">
<meta name="description" content="ひいらぎ不動産の管理者ログイン画面です。">
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
			<li>管理者ログイン画面</li>
		</ol>
		<h1 class="sabu-h1">管理者ログイン画面</h1>
		<form action="/hiiragi/LoginServlet" method="post">
			<p>
				<label>ユーザーID： <input type="text" name="userId" required>
				</label>
			</p>
			<p>
				<label>パスワード： <input type="password" name="userPass"
					required>
				</label>
			</p>
			<p>
				<button type="submit">ログイン</button>
			</p>
		</form>
	</article>
	<footer>

		<small>&copy; 2024 Hiiragi Real Estate. All Rights Reserved.</small>
	</footer>
</body>
</html>