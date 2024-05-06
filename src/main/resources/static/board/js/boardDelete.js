document.addEventListener('DOMContentLoaded', function () {
    var buttons = document.querySelectorAll('.devare_btn');

    buttons.forEach(function (button) {
        button.addEventListener('click', function () {
            var user_name = button.getAttribute('data-user-name');
            var board_id = button.getAttribute('data-board-id');
            // SweetAlert을 사용하여 사용자 입력 받기
            Swal.fire({
                title: "해당 게시글을 삭제하시겠습니까?",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: '삭제',
                cancelButtonText: '취소',
                input: 'text',
                inputPlaceholder: '삭제를 원하시면 "' + user_name + '"를 입력하세요.',
                inputValidator: (value) => {
                    // 입력값 검증 함수
                    return value === user_name ? undefined : '입력이 올바르지 않습니다.';
                }
            }).then((result) => {
                if (result.isConfirmed) {
                    // 사용자가 확인 버튼을 눌렀을 경우
                    $.ajax({
                        type: 'GET',
                        url: '/board/devare',
                        data: { board_id:board_id },
                        success: function (response) {
                            // 삭제 완료 메시지
                            Swal.fire({
                                title: '해당 게시글을 삭제하였습니다.',
                                icon: 'success',
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    // 확인 버튼을 눌렀을 때 페이지 이동
                                    window.location.href = '/board/main';
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
