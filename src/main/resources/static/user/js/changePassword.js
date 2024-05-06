$('.changeGo').click(function() {
    var password_first = document.getElementById("password_first").value;

    // 두 번째 비밀번호 입력 필드의 값을 가져옴
    var password_second = document.getElementById("password_second").value;
    console.log(password_first)
    console.log(password_second)

    // 여기서 비밀번호 일치 여부를 확인하거나 다른 작업을 수행할 수 있음
    if (password_first === password_second && password_first !== "" && password_second !== "") {
        // 비밀번호가 일치하는 경우 처리
        console.log("비밀번호 일치");
        // AJAX 요청 보내기
        $.ajax({
            type: 'GET',
            url: '/changePassword',
            data: {changePassword: password_first},
            success: function (response) {
                // 삭제 완료 메시지
                Swal.fire({
                    title: '로그아웃 후 재 로그인 부탁드립니다.',
                    icon: 'success',
                }).then((result) => {
                    if (result.isConfirmed) {
                        // 확인 버튼을 눌렀을 때 페이지 이동
                        window.location.href = '/logout';
                    }
                });

            },
            error: function (error) {
                console.error('Error fetching data:', error);
            }
        });

    }if(password_first !== password_second || password_first === "" || password_second === "") {
        // 비밀번호가 일치하지 않는 경우 처리
        console.log("비밀번호 불일치");
        // 이곳에서 추가 작업 수행 가능
        Swal.fire({
            icon: 'error',
            title: '비밀번호가 일치하지 않거나 입력하지 않았습니다.',
            showConfirmButton: false,
            timer: 1000
        }).then(() => {
            // 이전 페이지로 이동

        });
    }


});