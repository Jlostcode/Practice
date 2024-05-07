<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board Main</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
var selectOption = '${pagination.searchSelect}';

</script>
</head>
 <body id="body-pd">
    <div class="page">

        <header>
            <a href="/loginSuccess/main"><h2>Pratice</h2></a>

            <div class="loginInfo" style="position: relative; top:15px">

                <div class="row profile">
                    <div class="profile-sidebar">
                        <!-- SIDEBAR USERPIC -->
                        <div class="profile-userpic">
                            <img src="https://gravatar.com/avatar/31b64e4876d603ce78e04102c67d6144?s=80&d=https://codepen.io/assets/avatars/user-avatar-80x80-bdcd44a3bfb9a5fd01eb8b86f9e033fa1a9897c3a15b33adfc2649a002dab1b6.png" class="img-responsive" alt="">
                        </div>
                        <!-- END SIDEBAR USERPIC -->
                        <!-- SIDEBAR USER TITLE -->
                        <div class="profile-usertitle">
                            <div class="profile-usertitle-name" id="profile-usertitle-name">

                            </div>
                        </div>
                        <!-- END SIDEBAR USER TITLE -->
                        <!-- SIDEBAR BUTTONS -->
                        <div class="profile-userbuttons">
                            <button type="button" class="btn btn-success btn-sm" onclick="userInfo()" id="userInfoBtn">회원정보</button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="location.href='/logout'"
                                    id="logoutButton">Logout</button>
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
    <div id="contentBig">
        <div class="container-fluid">
					<div>
						<h1 class="h3 mb-2 text-gray-800">캠페인</h1>
					</div>
					<div class="mb-4">
						<p class="d-inline">캠페인 별 접수 현황을 한 눈에 확인하세요.</p>
						<a class="btn btn-outline-primary d-inline float-right excelDown">Excel 다운</a>
					</div>
					<div class="tableOut card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary d-inline">${pagination.type} 캠페인 현황</h6>
							<form action="/board/main" method="GET"
								class="form-inline float-right">
								<select name="searchSelect" id="searchSelect" class="form-control custom-select">
									<option value="campaign_name">작성자</option>
									<option value="guest_name">제목</option>
								</select>
								<div class="form-group mx-2">
									<label for="searchKeyword" class="mr-2"></label>
									<input type="text" id="searchKeyword" name="searchKeyword" class="form-control" placeholder="검색어를 입력하세요." value="${pagination.searchKeyword}">
								</div>
								<div class="form-group mx-2">
									<label for="startDate" class="mr-2">시작일:</label> 
									<input type="text" id="startDate" name="startDate" class="form-control" readonly value="${pagination.startDate}">
								</div>
								<div class="form-group mx-2">
									<label for="endDate" class="mr-2">종료일:</label>
									<input type="text" id="endDate" name="endDate" class="form-control" readonly value="${pagination.endDate}">
								</div>
								<input type="hidden" id="type" name="type" value="${pagination.type}">
								<button type="submit" class="btn btn-primary">검색</button>
							</form>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" style="width : 100%;">
									<thead>
										<colgroup>
											<col width='2%'>
											<col width='12%'>
											<col width='20%'>
											<col width='6%'>
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
													<td>${list.board_title}</td>
													<td onclick="redirectBoardURL(${list.board_id})">
                                <a href="/board/view?board_id=${list.board_id}" class="view-link" style=" width: 800px;"
                                   data-board-id="${list.board_id}">${dto.board_title}</a></td>
													<td>${list.board_regdate}</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								${pageResult}
							</div>
						</div>
						</div>
</div>
    <!-- IONICONS -->
    <script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
    <!-- JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/layout/main.js"></script>
    <script src="/layout/loginInfo.js"></script>

	
</body>
</html>