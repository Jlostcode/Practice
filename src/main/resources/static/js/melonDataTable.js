var clickedCellValue; 			// 정렬할 컬럼이름
var clickedCellSortDirection;	// 정렬 방식 (desc, asc)
var selectValue;				// 목록 사이즈 select 갯수
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
let table = $('#dataTableMelon').DataTable({
	dom: 'lBfrtip',          // l =
	order : [[ 0, "asc" ]], // 0열 desc 정렬 기본
	info: true,
	language: lang_kor,
     ajax: {
        url:"/melon/dataTableList", 
        type: "GET",
        dataSrc : function(res){   // 받아온 데이터 가공하여 columns로 추가
			var data = res.melonList;
			var totalLength = data.length;
			for (let i = 0; i < data.length; i++) {
				 data[i].listIndex = i + 1;

				 let melonLike = "<ion-icon name='heart-circle-outline' class='small_nav__icon'></ion-icon>" + data[i].melonLike;
				 data[i].melonLike = melonLike;
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
        {"data": "melonArtist"}, 
        {"data": "melonTitle"},
        {"data": "melonLike"}
    ],
    buttons: [    // 버튼 추가 
        {
            extend: 'excel',  // 플러그인 추가 시 copy, csv, pdf, print 
            title: 'MELON_CHART_TOP100_' + getTodayDate(),
            text: '엑셀 다운로드',
            className: 'btn btn-outline-primary excelBtn'
        },
        {
            text: '내 버튼',
            className: 'btn btn-outline-primary excelBtn',
            action: function () {

            	$(document).ready(function() {
	                // 페이지가 로드되면 실행됩니다.
	            	let now = $('.current').text();
	            	let search = $('#dataTableMelon_filter input').val();
	            	let select = $('select[name=dataTableMelon_length]').val();
	            	let col;
	            	let by;
	            	switch (clickedCellValue) {
	            	  case "순위":
	            		col = "sub.melonRank";
	            	    break;
	            	  case "아티스트":
	            		col = "sub.melonArtist";
	            	    break;
	            	  case "제목":
	            		col = "sub.melonTitle";
	            	    break;
	            	  case "좋아요":
		            	col = "sub.melonLike";
		            	break;
	            	}
	            	switch (clickedCellSortDirection) {
	            	  case "ascending":
	            		by = "asc";
	            	    break;
	            	  case "descending":
	            		by = "desc";
	            	    break;
	            	}
	            	console.log(now)
	            	console.log(search)
	            	console.log(col)
	            	console.log(by)
	            	console.log(select)
	            	let url = "/melon/melonDataTableExcel?nowPage=" + now + "&searchKeyword=" + search + "&orderByCol=" + col +
	            	"&orderBy=" + by + "&listSize=" + select;
	            	
	            	location.href = url;          	
            	
            	});
            }
        }
    ],
    rowCallback: function(row, data) {
    	$(row).find('td').eq(1).on('click', function() {
        	let artistLink = data.melonArtistLink;	
        	let goURL = 'https://www.melon.com/artist/timeline.htm?artistId=' + artistLink;
        	window.open(goURL, '_blank');

        });
        $(row).find('td').eq(2).on('click', function() {
        	let titleLink = data.melonTitleLink;	
        	let goURL = 'https://www.melon.com/song/detail.htm?songId=' + titleLink;
        	window.open(goURL, '_blank');

        });
        
    }
   
     
});

$(document).ready(function() {           	
    // 페이지가 로드되면 실행됩니다.
    let $thList = $('#dataTableMelon th');

    $thList.on('click', function() {
        // 클릭된 셀의 값을 저장
        clickedCellValue = $(this).text();
        // 클릭된 셀의 정렬 방향을 저장
        clickedCellSortDirection = $(this).attr('aria-sort');
    });

});

function getTodayDate() {  // 오늘 날짜 구하는 함수
    var today = new Date();
    var year = today.getFullYear();
    var month = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더해줍니다.
    var day = String(today.getDate()).padStart(2, '0');
    var todayDate = year + '-' + month + '-' + day;
    return todayDate;
}


