function validateRegisterId() { // 아이디 유효성 검사
    const id = document.getElementById("id").value;
    const errorMessage = document.getElementById("id-error-message");

    $.ajax({
        url: "/v1/user/register/chk/id",
        type : 'POST',
        data : { id: id },
        success : function (data) {
            errorMessage.textContent = data; // 성공 메시지 출력
        },
        error: function (xhr) {
            errorMessage.textContent = xhr.responseText; // 에러 메시지 출력
        }
    })
}

function validateRegisterNickname() { // 닉네임 유효성 검사
    const nickname = document.getElementById("nickname").value;
    const errorMessage = document.getElementById("nickname-error-message");

    $.ajax({
        url: "/v1/user/register/chk/nickname",
        type : 'POST',
        data: { nickname: nickname },
        success : function (data) {
            errorMessage.textContent = data; // 성공 메시지 출력
        },
        error: function (xhr) {
            errorMessage.textContent = xhr.responseText; // 에러 메시지 출력
        }
    })
}

function validateRegisterEmail() { // 이메일 유효성 검사
    const email = document.getElementById("email").value;
    const errorMessage = document.getElementById("email-error-message");

    $.ajax({
        url: "/v1/user/register/chk/email",
        type : 'POST',
        data: { email: email },
        success : function (data) {
            errorMessage.textContent = data; // 성공 메시지 출력
        },
        error: function (xhr) {
            errorMessage.textContent = xhr.responseText; // 에러 메시지 출력
        }
    })
}

function validateRegisterPassword() { // 비밀번호 유효성 검사
    const password1 = document.getElementById("password1").value;
    const errorMessage = document.getElementById("password-error-message");

    const patternLowerCase = /(?=.*[a-z])/;
    const patternUpperCase = /(?=.*[A-Z])/;
    const patternSpecialChar = /(?=.*[!@#$^*+=-])/;
    const patternNumber = /(?=.*\d)/;
    const patternMinLength8 = /(?=.{8,})/;
    const patternMaxLength64 = /(?=.{1,64}$)/;

    const pattern = new RegExp(patternLowerCase
        + patternUpperCase
        + patternSpecialChar
        + patternNumber
        + patternMinLength8
        + patternMaxLength64);

    errorMessage.textContent = "";

    if (password1.trim() === "") {
        errorMessage.textContent = "비밀번호를 입력해주세요.";
    } else if (!pattern.test(password1)) {
        if (!patternLowerCase.test(password1)) {
            errorMessage.innerHTML += "비밀번호에 소문자를 최소 하나 이상 포함해야 합니다.<br>";
        }
        if (!patternUpperCase.test(password1)) {
            errorMessage.innerHTML += "비밀번호에 대문자를 최소 하나 이상 포함해야 합니다.<br>";
        }
        if (!patternSpecialChar.test(password1)) {
            errorMessage.innerHTML += "비밀번호에 특수 문자를 최소 하나 이상 포함해야 합니다.<br>";
        }
        if (!patternNumber.test(password1)) {
            errorMessage.innerHTML += "비밀번호에 숫자를 최소 하나 이상 포함해야 합니다.<br>";
        }
        if (!patternMinLength8.test(password1)) {
            errorMessage.innerHTML += "비밀번호는 최소 8자 이상이어야 합니다.<br>";
        }
        if (!patternMaxLength64.test(password1)) {
            errorMessage.innerHTML += "비밀번호는 최대 64자 이하여야 합니다.<br>";
        }
    }
}

function validateRegisterPasswordChk() { // 비밀번호 확인 유효성 검사
    const password1 = document.getElementById("password1").value;
    const password2 = document.getElementById("password2").value;
    const errorMessage = document.getElementById("passwordChk-error-message");

    if(password2.trim() === ""){
        errorMessage.textContent = "비밀번호 확인을 입력해주세요.";
    } else if (password2 === password1) {
        errorMessage.textContent = "";
    } else {
        errorMessage.textContent = "입력한 비밀번호와 비밀번호 확인이 서로 일치하지 않습니다.";
    }
}

function sendEmailVerification() { // 이메일 인증
    const email = document.getElementById("email").value;

    $.ajax({
        url: "/v1/user/register/send/email",
        type: 'POST',
        data: {
            email: email
        },
        success : function (data) {
            alert(data); // 성공 메시지 출력
        },
        error: function (xhr) {
            alert(xhr.responseText); // 에러 메시지 출력
        }
    })
}

function register() { // 일반 회원가입
    event.preventDefault();

    const id = document.getElementById("id").value;
    const password1 = document.getElementById("password1").value;
    const password2 = document.getElementById("password2").value;
    const nickname = document.getElementById("nickname").value;
    const email = document.getElementById("email").value;
    const receiveEmail = document.getElementById("receiveEmail").value;

    $.ajax({
        url: "/v1/user/register",
        type: 'POST',
        data: {
            id: id,
            password1: password1,
            password2: password2,
            nickname: nickname,
            email: email,
            receiveEmail: receiveEmail},
        success : function (data) {
            alert(data); // 성공 메시지 출력

            window.location.assign("/");
        },
        error: function (xhr) {
            alert(xhr.responseText); // 에러 메시지 출력
        }
    })
}