$(document).ready(function() {
    $.ajax({
        url: "/profile",
        type: "GET",
        success: function (response) {
            // 서버에서 받은 데이터 중 사용자 이름을 가져와 프로필 카드에 표시
            var userName = response.profileName; // 서버에서 보내는이름
            var role = response.profileRole;

            var type = '';
            if (role === 1) {
                type = "관리자";
                // 프로필 카드의 HTML 요소에 사용자 이름을 삽입
                console.log(userName);
                document.getElementById('profile-usertitle-name').innerHTML = '(관리자)' + userName + ' 님' + '<br> 환영합니다.';
            } else if (role === 2) {
                type = "사용자";
                // 프로필 카드의 HTML 요소에 사용자 이름을 삽입
                console.log(userName);
                document.getElementById('profile-usertitle-name').innerHTML = userName + ' ' + '님' + '<br> 환영합니다.';
            }

        },
        error: function(xhr, status, error) {
            // 에러 처리
            console.error(error);
        }
    });
});

// document.getElementById('logoutButton').addEventListener('click', function() {
//     window.location.href = '/logout';
// });