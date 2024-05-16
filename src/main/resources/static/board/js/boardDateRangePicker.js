$(document).ready(function(){

	$("#boardDate").daterangepicker({
		"showDropdowns": true,
		locale :{ 
	        format: 'YYYY-MM-DD',
	        separator: ' ~ ',
	        applyLabel: "적용",
	        cancelLabel: "닫기",
	        "customRangeLabel": "직접 선택",
	        "daysOfWeek": ["일","월","화","수","목","금","토"],
	        "monthNames": ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"]
	     },
	     "alwaysShowCalendars": true,
	     "ranges" : {
	    	 '오늘' : [ moment(), moment() ],
             '어제' : [ moment().subtract(1, 'days'), moment().subtract(1, 'days') ],
             '지난 주' : [ moment().subtract(1, 'week').day("Monday"), moment().day("Sunday") ],
             '지난 달' : [ moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month') ],
             '이번 달' : [ moment().startOf('month'), moment().endOf('month') ],
             '지난 7일' : [ moment().subtract(6, 'days'), moment() ],
             '지난 30일' : [ moment().subtract(29, 'days'), moment() ],
         }
	});
	
	// 날짜 변경
	$("#boardDate").change(function(){
		$('#searchKeyword').val("");
		changeStatus();
	});
});
function changeStatus(){
	var boardDate = $('#boardDate').val();
	console.log(boardDate);
	var str = boardDate.split(" ~ ");
	$("#startDate").val(str[0]);
	$("#endDate").val(str[1]);
	$("#form").submit();
}