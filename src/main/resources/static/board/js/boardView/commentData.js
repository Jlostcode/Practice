// MODAL

$('#exampleModal').on('show.bs.modal', function (e) {
        var username = $(e.relatedTarget).data('username');
        var comsub = $(e.relatedTarget).data('comsub');


        console.log(username);
        console.log(comsub);

        document.getElementById('user_name_edit').value = username;
        document.getElementById('comment_sub_up').value = comsub;

    }
);

// COMMENT 수정- 저장

document.addEventListener('DOMContentLoaded', function () {
    var buttons = document.querySelectorAll('.com_save_btn');

    buttons.forEach(function (button,index) {
        button.addEventListener('click', function () {
            var comment_sub = document.getElementById('comment_sub_up').value;
            var comment_id = document.getElementById('edit_btn_'+index).getAttribute('data-comid');
            var board_id = document.getElementById('edit_btn_'+index).getAttribute('data-boardid');
            console.log(comment_sub)
            console.log(comment_id)
            console.log(board_id)
            // SweetAlert을 사용하여 사용자 입력 받기
            if (!checkNullCommentModal()) {
                return; // 널 체크 함수에서 false가 반환되면 함수 종료
            }
            Swal.fire({
                title: "해당 게시글을 수정하시겠습니까?",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: '수정',
                cancelButtonText: '취소',
            }).then((result) => {
                if (result.isConfirmed) {
                    // 사용자가 확인 버튼을 눌렀을 경우
                    $.ajax({
                        type: 'GET',
                        url: '/board/view/commentUpdate',
                        data: { comment_sub:comment_sub, comment_id:comment_id, board_id:board_id },
                        success: function (response) {
                            // 삭제 완료 메시지
                            Swal.fire({
                                title: '해당 게시글을 수정하였습니다.',
                                icon: 'success',
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    // 확인 버튼을 눌렀을 때 페이지 이동
                                    window.location.href = '/board/view?board_id=' + board_id;
                                }
                            });

                        },
                        error: function (error) {
                            console.error('Error fetching data:', error);
                        }
                    });
                } else {
                    // 사용자가 취소 버튼을 눌렀을 경우
                    Swal.fire({
                        title: "수정을 취소했습니다.",
                        icon: 'info',
                    });
                }
            });
        });
    });
});

// COMMENT 삭제

document.addEventListener('DOMContentLoaded', function () {
    var buttons = document.querySelectorAll('.devareCom_btn');

    buttons.forEach(function (button, index) {
        button.addEventListener('click', function () {
            var comid = document.getElementById('devareCom'+index).getAttribute('data-comid');
            var board_id = document.getElementById('edit_btn_'+index).getAttribute('data-boardid');
            console.log(comid)
            // SweetAlert을 사용하여 사용자 입력 받기
            Swal.fire({
                title: "해당 게시글을 삭제하시겠습니까?",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: '삭제',
                cancelButtonText: '취소',

            }).then((result) => {
                if (result.isConfirmed) {
                    // 사용자가 확인 버튼을 눌렀을 경우
                    $.ajax({
                        type: 'GET',
                        url: '/board/view/commentDelete',
                        data: { comment_id:comid, board_id: board_id },
                        success: function (response) {
                            // 삭제 완료 메시지
                            Swal.fire({
                                title: '해당 댓글을 삭제하였습니다.',
                                icon: 'success',
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    // 확인 버튼을 눌렀을 때 페이지 이동
                                    window.location.href = '/board/view?board_id=' + board_id;
                                }
                            });

                        },
                        error: function (error) {
                            console.error('Error fetching data:', error);
                        }
                    });
                } else {
                    // 사용자가 취소 버튼을 눌렀을 경우
                    Swal.fire({
                        title: "삭제를 취소했습니다.",
                        icon: 'info',
                    });
                }
            });
        });
    });
});







//CHECKNULLCOMMENT
function checkNullCommentModal() {
    // 각 입력 필드의 값을 가져와서 null 체크
    var comment_sub = document.getElementById('comment_sub_up').value;



    if (comment_sub.trim() === "") {
        showAlert("댓글 내용을 입력해주세요.");
        return false;
    }

    return true;
}
//SHOWALERT 기본 경고
function showAlert(message) {
    Swal.fire({
        icon: 'warning',
        iconColor:'#12192c',
        title: '알림',
        text: message,
        confirmButtonColor: '#3085d6',
        confirmButtonText: '확인'
    });
}
