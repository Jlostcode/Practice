// function checkNullBoard() {
//     // 각 입력 필드의 값을 가져와서 null 체크
//     var board_title = document.getElementsByName('board_title')[0].value;
//     var board_sub = document.getElementsByName('board_sub')[0].value;
//
//
//     if (board_title.trim() === "") {
//         showAlert("글 제목을 입력해주세요.");
//         return false;
//     }
//
//     if (board_sub.trim() === "") {
//         showAlert("글 내용을 입력해주세요.");
//         return false;
//     }
//
//     return true;
// }
//
// function showAlert(message) {
//     Swal.fire({
//         icon: 'warning',
//         iconColor:'#12192c',
//         title: '알림',
//         text: message,
//         confirmButtonColor: '#3085d6',
//         confirmButtonText: '확인'
//     });
// }
