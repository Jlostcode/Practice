<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Melon Top 100</title>
<link href="https://cdn.jsdelivr.net/npm/@coreui/coreui@4.3.0/dist/css/coreui.min.css" rel="stylesheet" integrity="sha384-2E9/b2fZ+VJoP6eRIpzzMFkuqbh0qDkIFVLzJJwkESsdKPEwzb0n6E55enZ+Ee8V" crossorigin="anonymous">
<link href="/board/css/boardMainTable.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/layout/layout.css">
<link rel="stylesheet" type="text/css" href="/layout/styles.css">
<link rel="stylesheet" type="text/css" href="/layout/loginInfo.css">

<link href="/css/jquery-ui.min.css" rel="stylesheet" type="text/css">
<link href="/css/customDatepicker.css" rel="stylesheet" type="text/css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css" href="/board/css/datatables.min.css" />
<link rel="stylesheet" type="text/css" href="/board/css/customDataTable.css" />

<!-- 엑셀 -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>

<style>
.btn-dark {
	-cui-btn-color: rgba(255, 255, 255, 0.87);
	-cui-btn-bg: #000000;
	-cui-btn-border-color: #4f5d73;
	-cui-btn-hover-color: #fff;
	-cui-btn-hover-bg: #697588;
	-cui-btn-hover-border-color: #616d81;
	-cui-btn-focus-shadow-rgb: 100, 112, 132;
	-cui-btn-active-color: #fff;
	-cui-btn-active-bg: #727d8f;
	-cui-btn-active-border-color: #616d81;
	-cui-btn-active-shadow: inset 0 3px 5px rgba(0, 0, 21, 0.125);
	-cui-btn-disabled-color: rgba(255, 255, 255, 0.87);
	-cui-btn-disabled-bg: #4f5d73;
	-cui-btn-disabled-border-color: #4f5d73;
}

body {
	overflow: hidden;
}

#load {
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	position: fixed;
	display: block;
	opacity: 0.8;
	background: gray;
	z-index: 99;
	text-align: center;
}

#load>img {
	position: absolute;
	top: 50%;
	left: 50%;
	z-index: 100;
}
</style>
</head>
<body>
<body id="body-pd">
	<div class="page">

		<header>
			<a href="/loginSuccess/main">
				<h2>Pratice</h2>
			</a>

			<div class="loginInfo" style="position: relative; top: 15px">

				<div class="row profile">
					<div class="profile-sidebar">
						<!-- SIDEBAR USERPIC -->
						<div class="profile-userpic">
							<img src="https://gravatar.com/avatar/31b64e4876d603ce78e04102c67d6144?s=80&d=https://codepen.io/assets/avatars/user-avatar-80x80-bdcd44a3bfb9a5fd01eb8b86f9e033fa1a9897c3a15b33adfc2649a002dab1b6.png" class="img-responsive" alt="">
						</div>
						<!-- END SIDEBAR USERPIC -->
						<!-- SIDEBAR USER TITLE -->
						<div class="profile-usertitle">
							<div class="profile-usertitle-name" id="profile-usertitle-name"></div>
						</div>
						<!-- END SIDEBAR USER TITLE -->
						<!-- SIDEBAR BUTTONS -->
						<div class="profile-userbuttons">
							<button type="button" class="btn btn-success btn-sm" onclick="userInfo()" id="userInfoBtn">회원정보</button>
							<button type="button" class="btn btn-danger btn-sm" onclick="location.href='/logout'" id="logoutButton">Logout</button>
						</div>
						<!-- END SIDEBAR BUTTONS -->
					</div>
				</div>

			</div>



		</header>
	</div>
	<div class="big_nav" id="navbar">
		<nav class="small_nav">
			<div>

				<div class="small_nav_brand">
					<ion-icon name="menu-outline" class="small_nav__toggle" id="nav-toggle"></ion-icon>
					<a href="/" class="small_nav__logo">Practice</a>
				</div>
				<a href="/user/schedule" class="small_nav__link">
					<ion-icon name="calendar-outline" class="small_nav__icon"></ion-icon>
					<span class="small_nav_name">일정</span>
				</a>

				<a href="/melon100" class="small_nav__link">
					<ion-icon name="musical-notes-outline" class="small_nav__icon"></ion-icon>
					<span class="small_nav_name">멜론 차트</span>
				</a>
				<a href="/melonPage" class="small_nav__link">
					<ion-icon name="musical-notes-outline" class="small_nav__icon"></ion-icon>
					<span class="small_nav_name">멜론 차트2</span>
				</a>

				<a href="/board/main" class="small_nav__link">
					<ion-icon name="reader-outline" class="small_nav__icon"></ion-icon>
					<span class="small_nav_name">게시판</span>
				</a>

				<a th:href="@{/logout}" class="small_nav__link" onclick="logout()">
					<ion-icon name="log-out-outline" class="small_nav__icon"></ion-icon>
					<span class="small_nav_name">Log out</span>
				</a>

			</div>
		</nav>
	</div>
	<!-- content start -->
	<div id="contentBig">
		<div id="load">
			<img src="/board/loading.gif" alt="loading">
		</div>
		<div class="wrapper">
			<div class="title-text" style="text-align: center; width: 1400px;">
				<h1>멜론 TOP 100</h1>
				<div class="d-grid gap-2 d-flex row justify-content-between  align-items-center">

					<div class="tableOut card shadow mb-4">
						<div class="card-header py-3">
							<form action="/melonPage" method="GET" class="form-inline d-flex justify-content-between align-items-center">
								<div>
									<a id="excelDownloadLink" href="#" class="btn btn-ghost-dark col-auto">멜론 차트 엑셀 다운</a>
								</div>
								<div class="d-flex align-items-center">
									<select name="searchSelect" id="searchSelect" class="form-control mx-2" style="width: 100px">
										<option value="melon_artist">아티스트</option>
										<option value="melon_title">제목</option>
									</select>
									<div class="form-group mx-6">
										<input type="text" id="searchKeyword" name="searchKeyword" class="form-control" placeholder="검색어를 입력하세요." value="${pagination.searchKeyword}">
									</div>
									<input type="hidden" id="type" name="type" value="${pagination.type}">
									<button type="submit" class="btn btn-primary">검색</button>
								</div>
							</form>
						</div>
					</div>

				</div>
			</div>
			<c:if test="${melonList.isEmpty()}">
				<div>
					<p>게시글이 없습니다.</p>
				</div>
			</c:if>
			<table>
				<colgroup>
					<col width='2%'>
					<col width='5%'>
					<col width='35%'>
					<col width='9%'>
				</colgroup>
				<thead>
					<tr>
						<th>순위</th>
						<th>아티스트</th>
						<th>앨범 제목</th>
						<th>좋아요</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty melonList}">
						<c:forEach var="list" items="${melonList}">
							<tr>
								<td>${list.rowNum}</td>
								<td>
									<div>
										<a text="" href="https://www.melon.com/artist/timeline.htm?artistId=${list.melonArtistLink}" target="_blank">${list.melonArtist}</a>
									</div>
								</td>
								<td>
									<div>
										<a text="" href="https://www.melon.com/song/detail.htm?songId=${list.melonTitleLink}" target="_blank"> ${list.melonTitle} </a>
									</div>
								<td>
									<ion-icon name="heart-circle-outline" class="small_nav__icon"></ion-icon>
									${list.melonLike}
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			${pageResult}
		</div>

	</div>
	<script>
		// 문서가 로드된 후에 실행될 함수
		document.addEventListener("DOMContentLoaded", function() {
			// 3초 후에 로딩 이미지를 숨깁니다.
			setTimeout(function() {
				var loadDiv = document.getElementById("load");
				if (loadDiv) {
					loadDiv.style.display = "none"; // 로딩 이미지 숨기기
					document.body.style.overflow = "auto"; // body 스크롤 활성화
				}
			}, 2000);
		});

		document.getElementById('excelDownloadLink').addEventListener('click', function() {
	        // searchKeyword 및 searchSelect 값 가져오기
	        var searchKeyword = document.getElementById('searchKeyword').value;
	        var searchSelect = document.getElementById('searchSelect').value;
	        
	        // URL 생성
	        var downloadUrl = '/melonExcelDown2?listSize=${pagination.listSize}&page=${pagination.page}&searchSelect=' + encodeURIComponent(searchSelect) + '&searchKeyword=' + encodeURIComponent(searchKeyword);
	        
	        // 다운로드 링크로 이동
	        window.location.href = downloadUrl;
	    });
	</script>
	<!-- IONICONS -->
	<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
	<!-- JS -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="/layout/main.js"></script>
	<script src="/layout/loginInfo.js"></script>

	<!-- datePicker -->
	<script src="/js/jquery-ui.min.js"></script>
	<script src="/js/datepicker.js"></script>

	<!-- dataTables -->

	<script type="text/javascript" src="/board/js/datatables.min.js"></script>
	<script type="text/javascript" src="/board/js/customDataTable.js"></script>
</body>
</html>