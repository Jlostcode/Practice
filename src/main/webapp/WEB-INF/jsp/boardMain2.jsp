<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board Main</title>
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
body {
	font-family: 'Noto Sans KR', sans-serif;
	/* 한글 폰트 설정 (여기서는 Noto Sans KR을 예시로 사용) */
}

.btn-dark { -
	-cui-btn-color: rgba(255, 255, 255, 0.87); -
	-cui-btn-bg: #000000; -
	-cui-btn-border-color: #4f5d73; -
	-cui-btn-hover-color: #fff; -
	-cui-btn-hover-bg: #697588; -
	-cui-btn-hover-border-color: #616d81; -
	-cui-btn-focus-shadow-rgb: 100, 112, 132; -
	-cui-btn-active-color: #fff; -
	-cui-btn-active-bg: #727d8f; -
	-cui-btn-active-border-color: #616d81; -
	-cui-btn-active-shadow: inset 0 3px 5px rgba(0, 0, 21, 0.125); -
	-cui-btn-disabled-color: rgba(255, 255, 255, 0.87); -
	-cui-btn-disabled-bg: #4f5d73; -
	-cui-btn-disabled-border-color: #4f5d73;
}
</style>
<script>
var selectOption = '${pagination.searchSelect}';
function redirectBoardURL(index) {
    let goURL = '/board/view?board_id=' + index;
    window.location.href = goURL;
}
</script>
</head>
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
				<a href="/board/main2" class="small_nav__link">
					<ion-icon name="reader-outline" class="small_nav__icon"></ion-icon>
					<span class="small_nav_name">게시판2</span>
				</a> 
				<a th:href="@{/logout}" class="small_nav__link" onclick="logout()">
					<ion-icon name="log-out-outline" class="small_nav__icon"></ion-icon>
					<span class="small_nav_name">Log out</span>
				</a>

			</div>
		</nav>
	</div>
	<div id="contentBig">
		<div class="container-fluid">
			<div>
				<h1 class="h3 mb-2 text-gray-800">예제 게시판</h1>
			</div>

			<div class="tableOut card shadow mb-4">
				<div class="card-header py-3">
					<form action="/board/main2" method="GET" class="form-inline d-flex justify-content-between align-items-center">
						<div>
							<h6 class="m-0 font-weight-bold text-primary">검색</h6>
						</div>
						<div class="d-flex align-items-center">
							<select name="searchSelect" id="searchSelect" class="form-control mx-2" style="width: 100px">
								<option value="user_name">작성자</option>
								<option value="board_title">제목</option>
							</select>
							<div class="form-group mx-6">
								<input type="text" id="searchKeyword" name="searchKeyword" class="form-control" placeholder="검색어를 입력하세요." value="${pagination.searchKeyword}">
							</div>
							<div class="form-group mx-2">
								<div class="d-flex align-items-center">
									<!-- 시작: 라벨을 포함하는 열 -->
									<label for="startDate" class="mr-2">시작:</label>
									<input type="text" id="startDate" name="startDate" class="form-control" readonly value="${pagination.startDate}">
								</div>
							</div>
							<div class="form-group mx-2">
								<div class="d-flex align-items-center">
									<!-- 종료: 라벨을 포함하는 열 -->
									<label for="endDate" class="mr-2">종료:</label>
									<input type="text" id="endDate" name="endDate" class="form-control" readonly value="${pagination.endDate}">
								</div>
							</div>
							<input type="hidden" id="type" name="type" value="${pagination.type}">
							<button type="submit" class="btn btn-primary">검색</button>
						</div>
					</form>
				</div>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table style="width: 100%;">
						<thead>
						<colgroup>
							<col width='1%'>
							<col width='5%'>
							<col width='35%'>
							<col width='9%'>
						</colgroup>
						<tr>
							<th style="width: 10px">No.</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성일자</th>

						</tr>
						</thead>
						<tbody>
							<c:if test="${not empty boardList}">
								<c:forEach var="list" items="${boardList}">
									<tr>
										<td style="width: 10px">${list.row_num}</td>
										<td>${list.user_name}</td>
										<td onclick="redirectBoardURL(${list.board_id})">
											<a href="/board/view?board_id=${list.board_id}" class="view-link" style="width: 800px;" data-board-id="${list.board_id}">${list.board_title}</a>
										</td>
										<td>${list.board_regdateF}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					${pageResult}
					<div style="text-align: end; display: flex; justify-content: flex-end;">
						<button type="button" class="btn btn-dark" onclick="location.href='/board/insert'">글 작성</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="dataTableBoard container ">
		<table id="dataTableBoard" class="display" style="width: 100%">
			<colgroup>
				<col width='1%'>
				<col width='5%'>
				<col width='35%'>
				<col width='9%'>
			</colgroup>
			<thead>
				<tr>
					<th class="text-center">No.</th>
					<th class="text-center">작성자</th>
					<th class="text-center">제목</th>
					<th class="text-center">작성일자</th>

				</tr>
			</thead>
			<tbody id="dataTableBoardBody">
			</tbody>
		</table>
	</div>
	<div id="load">
		<img src="/board/loading.gif" alt="loading">
	</div>
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