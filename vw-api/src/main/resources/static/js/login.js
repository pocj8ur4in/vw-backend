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

function login() { // 로그인 버튼 클릭 시, 아이디 혹은 비밀번호가 공란인지 확인하고 로그인 실행
    const id = document.getElementById("id").value;
    const password = document.getElementById("password").value;

    if (id.trim() === "") {
        alert("아이디를 입력해주세요.");
        location.reload();
        return false;
    } else if(password.trim() === "") {
        alert("비밀번호를 입력해주세요.");
        location.reload();
        return false;
    }

    $.ajax({
        url: "/v1/user/login",
        type : 'POST',
        async : false,
        data: {
            id: id,
            password: password
        },
        success: function (data) {
            saveAccessToken(data.accessToken);
            saveRefreshToken(data.refreshToken);


            window.location.assign("/"); // 로그인 성공 후의 리디렉션
        },
        error: function (error) {
            alert(error.responseText); // 에러 메세지 출력
            location.reload();
            return false;
        }
    })
}

function saveAccessToken(accessToken) {
    const cookieName = "accessToken";
    const expirationDate = new Date();

    expirationDate.setTime(expirationDate.getTime() + (15 * 60 * 1000)); // 토큰을 쿠키에 저장 (15분을 밀리초로 변환하여 더함)

    document.cookie = `${cookieName}=${accessToken}; expires=${expirationDate.toUTCString()}; path=/`;
}

function saveRefreshToken(refreshToken) { // refreshToken 쿠키에 저장
    const cookieName = "refreshToken";
    const expirationDate = new Date();

    expirationDate.setDate(expirationDate.getDate() + 7); // 토큰을 쿠키에 저장 (유효 기간은 7일로 설정)
    document.cookie = `${cookieName}=${refreshToken}; expires=${expirationDate.toUTCString()}; path=/`;
}