
$('#statusModal').on('show.bs.modal', function (e) {
	 var button = $(event.relatedTarget); // 모달을 연 버튼
	    var campaignId = $(e.relatedTarget).data('campaign-id'); // data-campaignid 속성에서 값을 가져옴
	    var modal = $(this); // 모달 자체
	    let approval_status = $(e.relatedTarget).data('approval-status');
        var username = $(e.relatedTarget).data('guestname');
	    
	    // hidden input에 campaign_id 값을 설정
	    modal.find('input[name="campaign_id"]').val(campaignId);
	    


        console.log(approval_status);

        $('#nowName').text(username);
        $('.modal-title').text(approval_status + " 상태입니다.")

    } //여기까지했어
);

$(document).ready(function() {
    // selecBbox
    if(selectOption != ''){
        $("#searchSelect").val(selectOption).prop("selected", true);
    }
    // 엑셀 다운로드 
    $('.excelDown').click(function() {
        location.href = "/campaignDownload?searchSelect=" + $('#searchSelect').val() + "&searchKeyword=" + $('#searchKeyword').val() 
            + "&startDate=" + $('#startDate').val() + "&endDate=" + $('#endDate').val() + "&type=" + $('#type').val() + "&role=" + role;
    });

    // 삭제 버튼 클릭 시
    $('#confirmDeleteButton').click(function() {
        // 삭제 여부를 확인하는 팝업창 띄우기
        if (confirm('정말 삭제하시겠습니까?')) {
            // 확인 버튼을 눌렀을 때, 폼 제출
            $('#deleteForm').submit();
        }
    });
});