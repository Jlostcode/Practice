/* EXPANDER MENU */
const showMenu = (toggleId, navbarId, bodyId) => {
    const toggle = document.getElementById(toggleId),
    navbar = document.getElementById(navbarId),
    bodypadding = document.getElementById(bodyId)

    if( toggle && navbar ) {
        toggle.addEventListener('click', ()=>{
            navbar.classList.toggle('expander');

            bodypadding.classList.toggle('body-pd')
        })
    }
}

showMenu('nav-toggle', 'navbar', 'body-pd')

/* LINK ACTIVE */
const linkColor = document.querySelectorAll('.nav__link')
function colorLink() {
    linkColor.forEach(l=> l.classList.remove('active'))
    this.classList.add('active')
}
linkColor.forEach(l=> l.addEventListener('click', colorLink))

/* COLLAPSE MENU */
const linkCollapse = document.getElementsByClassName('collapse__link')
var i

for(i=0;i<linkCollapse.length;i++) {
    linkCollapse[i].addEventListener('click', function(){
        const collapseMenu = this.nextElementSibling
        collapseMenu.classList.toggle('showCollapse')

        const rotate = collapseMenu.previousElementSibling
        rotate.classList.toggle('rotate')
    });
}

function logout() {
    $.ajax({
        type: "POST",
        url: "/custom-logout",
        success: function(response) {
            // 로그아웃이 성공했을 때 수행할 동작을 여기에 작성합니다.
            console.log("로그아웃 성공");
            // 예를 들어, 로그아웃 후 화면을 새로고침하거나 다른 동작을 수행할 수 있습니다.
            window.location.reload(); // 새로고침
        },
        error: function(xhr, status, error) {
            // 로그아웃 요청이 실패했을 때 수행할 동작을 여기에 작성합니다.
            console.error("로그아웃 실패");
            // 예를 들어, 오류 메시지를 출력하거나 다른 동작을 수행할 수 있습니다.
        }
    });
}


function userInfo() {
    // 비밀번호를 입력받아 AJAX 요청 보내기
    Swal.fire({
        title: '비밀번호 입력',
        input: 'password',
        inputPlaceholder: '비밀번호를 입력하세요',
        inputAttributes: {
            autocapitalize: 'off',
            autocorrect: 'off'
        },
        showCancelButton: true,
        confirmButtonText: '확인',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            // 입력된 비밀번호 가져오기
            const password = result.value;

            // AJAX 요청 보내기
            $.ajax({
                type: 'GET',
                url: '/checkPassword',
                data: { check_password: password },
                success: function(response) {
                    // 성공 시 처리
                    if (response.status === "true") {
                        // 비밀번호가 맞을 때
                        window.location.href = '/userInfo';
                    }
                },
                error: function(xhr, status, error) {
                    // 실패 시 처리
                    console.log(error);
                    Swal.fire({
                        icon: 'error',
                        title: '비밀번호가 일치하지 않습니다.',
                        showConfirmButton: false,
                        timer: 1000
                    }).then(() => {
                        // 이전 페이지로 이동
                        history.go();
                    });
                }
            });
        }
    });
}