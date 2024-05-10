// Korean datatable한국어 설정 
var lang_kor = {
	"decimal" : "",
	"emptyTable" : "데이터가 없습니다.",
	"info" : "_START_ - _END_ (총 _TOTAL_ 개)",
	"infoEmpty" : "0 개",
	"infoFiltered" : "(전체 _MAX_ 개 중 검색결과)",
	"infoPostFix" : "",
	"thousands" : ",",
	"lengthMenu" : "_MENU_ 개씩 보기",
	"loadingRecords" : "로딩중...",
	"processing" : "처리중...",
	"search" : "전체 검색 : ",
	"zeroRecords" : "검색된 데이터가 없습니다.",
	"paginate" : {
		"first" : "첫 페이지",
		"last" : "마지막 페이지",
		"next" : "다음",
		"previous" : "이전"
	},
	"aria" : {
		"sortAscending" : " :  오름차순 정렬",
		"sortDescending" : " :  내림차순 정렬"
	}
};

// datatable 설정
let table = $('#dataTableBoard').DataTable({
	dom: 'lBfrtip',          // l =
	order : [[ 0, "desc" ]], // 0열 desc 정렬 기본
	info: true,
	language: lang_kor,
     ajax: {
        url:"/board/dataTableList", 
        type: "GET",
        dataSrc : function(res){   // 받아온 데이터 가공하여 columns로 추가
			var data = res.boardList;
			var totalLength = data.length;
			for (let i = 0; i < data.length; i++) {
				 data[i].listIndex = totalLength - i;
				 board_title = "<a href='/board/view?board_id=" + data[i].board_id + 
				 "' class='view-link' style='width: 800px;' data-board-id='" + data[i].board_id + "'>" + data[i].board_title + "</a>";
				 data[i].board_title = board_title;
			}
			return data;
        },
        beforeSend : function() {  // Ajax요청이 서버로 전송되기전 실행 콜백 함수

		},
	complete : function(data) { //  Ajax 요청의 완료 시점에 추가적인 작업을 수행
		var loadDiv = document.getElementById("load");
			if (loadDiv) {
				loadDiv.style.display = "none"; // 로딩 이미지 숨기기
				document.body.style.overflow = "auto"; // body 스크롤 활성화
			}
		info = data['responseJSON']
        console.log('데이터 확인', info);
		},
     },
    columns: [    // datatable 표에 들어갈 columns 
    	{"data": "listIndex"},
        {"data": "user_name"}, 
        {"data": "board_title"},
        {"data": "board_regdateF"}
    ],
    buttons: [    // 버튼 추가 
        {
            extend: 'excel',  // 플러그인 추가 시 copy, csv, pdf, print 
            title: '게시판 데이터_' + getTodayDate(),
            text: '엑셀 다운로드',
            className: 'btn btn-outline-primary excelBtn'
        }
    ]
});

function getTodayDate() {  // 오늘 날짜 구하는 함수
    var today = new Date();
    var year = today.getFullYear();
    var month = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더해줍니다.
    var day = String(today.getDate()).padStart(2, '0');
    var todayDate = year + '-' + month + '-' + day;
    return todayDate;
}

$(function() {                    // datepicker 설정 / 안썼음
	$.datepicker.setDefaults({
		dateFormat : 'yy-mm-dd',
		autoclose : true,
		todayHighlight : true,
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년',
        maxDate: 0,
    });
	$('#startDate2, #endDate2').datepicker();
})


