function validateLoginId() { // 아이디 입력 창을 벗어났을 때, 아이디가 공란인지 확인
    const username = document.getElementById("id").value;
    const errorMessage = document.getElementById("id-error-message");

    if (username.trim() === "") {
        errorMessage.textContent = "아이디를 입력해주세요.";
    } else {
        errorMessage.textContent = "";
    }
}

function validateLoginPassword() { // 비밀번호 입력창을 벗어났을 때, 비밀번호가 공란인지 확인
    const password = document.getElementById("password").value;
    const errorMessage = document.getElementById("password-error-message");

    if (password.trim() === "") {
        errorMessage.textContent = "비밀번호를 입력해주세요.";
    } else {
        errorMessage.textContent = "";
    }
}

function validateLogin() { // 로그인 버튼 클릭 시, 아이디 혹은 비밀번호가 공란인지 확인
    const username = document.getElementById("id").value;
    const password = document.getElementById("password").value;

    if (username.trim() === "") {
        alert("아이디를 입력해주세요.");
        return false;
    } else if(password.trim() === "") {
        alert("비밀번호를 입력해주세요.");
        return false;
    }

    return true;
}

function login() { // 일반 로그인
    const id = document.getElementById("id").value;
    const password = document.getElementById("password").value;

    $.ajax({
        url: "/v1/user/login",
        type : 'POST',
        data: {
            id: id,
            password: password
        },
        error: function (xhr) {
            alert(xhr.responseText); // 에러 메세지 출력
        }
    })
}