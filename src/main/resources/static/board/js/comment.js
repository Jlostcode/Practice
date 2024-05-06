document.addEventListener('DOMContentLoaded', function () {
    var buttons = document.querySelectorAll('.comment_insert_btn');


    buttons.forEach(function (button) {
        button.addEventListener('click', function () {
            // 널 체크 함수 호출
            if (!checkNullComment()) {
                return ; // 널 체크 함수에서 false가 반환되면 함수 종료
            }
            var comment_sub = document.getElementById('commentEdit').value;
            var board_id = button.getAttribute('data-board-id');
            console.log(comment_sub)

            // 사용자가 확인 버튼을 눌렀을 경우
            $.ajax({
                type: 'GET',
                url: '/board/view/commentInsert',
                data: {comment_sub: comment_sub, board_id: board_id},
                success: function (response) {
                    var board_id = response.board_id;
                    // 삭제 완료 메시지
                    Swal.fire({
                        title: '댓글이 정상적으로 등록되었습니다.',
                        icon: 'success',
                    }).then((result) => {
                        if (result.isConfirmed) {
                            // 확인 버튼을 눌렀을 때 페이지 이동
                            window.location.href = '/board/view?board_id='+board_id;
                        }
                    });

                },
                error: function (error) {
                    console.error('Error fetching data:', error);
                }
            });


        });
    });
});


function checkNullComment() {
    // 각 입력 필드의 값을 가져와서 null 체크
    var comment_sub = document.getElementById('commentEdit').value;


    if (comment_sub.trim() === "") {
        showAlert("댓글의 내용을 입력해주세요.");
        return false;
    }



    return true;
}

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
