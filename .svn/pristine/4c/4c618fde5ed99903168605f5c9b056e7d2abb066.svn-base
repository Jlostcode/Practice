package com.example.practice.board.util;

import lombok.Data;

@Data
public class Pagination {
	private int listSize = 30; // 페이지당 행 수
	private int rangeSize = 10; // 페이지 범위 (Ex. 1~10, 11~20 페이지까지)
	private int page = 1; // 현재 페이지
	private int range = 1; // 현재 범위
	private int listCnt; // 총 행 수
	private int pageCnt; // 총 페이지 수
	private int startPage; // 시작 페이지
	private int startList; // 게시판 시작 번호
	private int endPage; // 마지막 페이지
	private boolean prev; // 이전 버튼 유무
	private boolean next; // 다음 버튼 유무
	private boolean first; // 첫번째 페이지로 버튼 유무
	private boolean last; // 마지막 페이지로 버튼 유무
	private String url; // 호출 주소
	private String searchYear = DateContainer.getNowDate().split("-")[0];
	private String searchMonth = DateContainer.getNowDate().split("-")[1];
	
	private String searchSelect;	// 검색 컬럼
	private String searchKeyword;	// 검색어
	private String type;			// 캠페인명 타입
	
	private String searchDate;
	private String startDate  = DateContainer.getBeforeYearStart(DateContainer.getNowDate(), 0);
	private String endDate = DateContainer.getNowDate();
	private int searchType;
	private String periodType; //daily, weekly
	private String customOption;
	private String orderByCol; //정렬할 칼럼
	private String orderBy; //asc&desc
	
	private int startNo;
	private int endNo;
	
	
	public void pageInfo(int listCnt) {
		this.listCnt = listCnt;
		// 전체 페이지수
		this.pageCnt = (int) Math.ceil((float) listCnt / listSize);

		// 시작 페이지
		this.startPage = (range - 1) * rangeSize + 1;

		// 끝 페이지
		this.endPage = (range * rangeSize);

		// 게시판 시작번호
		this.startList = (page - 1) * listSize;

		// 이전 버튼 상태
		this.prev = range == 1 ? false : true;

		// 처음으로 버튼 상태
		this.first = page == 1 ? false : true;

		// 다음 버튼 상태
		this.next = endPage >= pageCnt ? false : true;
		if (this.endPage > this.pageCnt) {
			this.endPage = this.pageCnt;
			this.next = false;
		}

		// 마지막 버튼 상태
		this.last = (page < pageCnt ? true : false);

	}

	public String pagingResult() {
		String result = "";
		String searchOption = "";
			if (this.searchYear != null) {
				searchOption += "&searchYear=" + this.searchYear;
			}
			if (this.searchMonth != null) {
				searchOption += "&searchMonth=" + this.searchMonth;
			}
			if (this.startDate != null) {
				searchOption += "&startDate=" + this.startDate;
			}
			if (this.endDate != null) {
				searchOption += "&endDate=" + this.endDate;
			}
			if (this.searchKeyword != null) {
				searchOption += "&searchKeyword=" + this.searchKeyword;
			}
			if (this.searchSelect != null) {
				searchOption += "&searchSelect=" + this.searchSelect;
			}
			if (this.searchType != 0) {
				searchOption += "&searchType=" + this.searchType;
			}
			if (this.searchDate != null) {
				searchOption += "&searchDate=" + this.searchDate;
			}
			if (this.orderByCol != null) {
				searchOption += "&orderByCol=" + this.orderByCol;
			}
			if (this.orderBy != null) {
				searchOption += "&orderBy=" + this.orderBy;
			}
			if (this.type != null) {
				searchOption += "&type=" + this.type;
			}
			if (this.listSize != 0) {
				searchOption += "&listSize=" + this.listSize;
			}

		result += "<div id='paginationBox' style='text-align: center;'>";
		result += "<ul class='pagination' style='justify-content: center;'>";
		if (this.first) {
			result += "<li class='page-item'><a class='page-link' href='" + url + "?page=1&range=1" + searchOption + "'>&lt;&lt;</a></li>";
		}

		if (this.prev) {
			result += "<li class='page-item'><a class='page-link'  href='" + url + "?page=" + (((this.range - 1) * this.rangeSize)) + "&range=" + (this.range - 1) + searchOption + "'>Prev</a></li>";
		}

		for(int i = this.startPage; i <= this.endPage; i++) {
			if (i == this.page) {
				result += "<li class='page-item active'><a class='page-link' href='" + url + "?page=" + i + "&range=" + this.range + searchOption + "'>" + i + "</a></li>";
			} else {
				result += "<li class='page-item'><a class='page-link' href='" + url + "?page=" + i + "&range=" + this.range + searchOption + "'>" + i + "</a></li>";
			}
		}

		if (this.next) {
			result += "<li class='page-item'><a class='page-link' href='" + url + "?page=" + ((this.range * this.rangeSize) + 1) + "&range=" + (this.range + 1) + searchOption + "'>Next</a></li>";
		}
		if (this.last) {
			result += "<li class='page-item'><a class='page-link' href='" + url + "?page=" + this.pageCnt + "&range=" + (int) (((this.pageCnt - 1) / this.rangeSize) + 1) + searchOption + "'>&gt;&gt;</a></li>";
		}
		result += "</ul>";
		result += "</div>";
		return result;
	}

}
