function checkNullSignUp() {
    var user_name = document.getElementsByName('user_name')[0].value;
	var user_id = document.getElementsByName('user_id')[0].value;
	var user_pw = document.getElementsByName('user_pw')[0].value;
	var user_tel = document.getElementsByName('user_tel')[0].value;
	var user_gen = document.getElementsByName('user_gen')[0].value;

    var noneGen = document.getElementById("noneGen");
    var male = document.getElementById("male");
    var female = document.getElementById("female");


	console.log(user_gen)
    if (user_name.trim() === "") {
        showAlert("이름을 입력해주세요.");
        return false;
    }

    if (user_id.trim() === "") {
        showAlert("아이디를 입력해주세요.");
        return false;
    }

    if (user_pw.trim() === "") {
        showAlert("비밀번호를 입력해주세요.");
        return false;
    }
    if (user_tel.trim() === "") {
        showAlert("전화 번호를 입력해주세요.");
        return false;
    }
    if (user_gen.trim() === "" ) {
        showAlert("성별을 선택해주세요.");
        return false;
    }
    if (!male.checked && !female.checked) {
        noneGen.checked = true; // 라디오 버튼을 선택하지 않은 경우 기본값으로 설정
        showAlert("성별을 선택해주세요.");
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