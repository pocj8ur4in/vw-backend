function validateLoginId() {
    var username = document.getElementById("id").value;
    var errorMessage = document.getElementById("id-error-message");

    if (username.trim() === "") {
        errorMessage.textContent = "· 아이디를 입력해주세요.";
    } else {
        errorMessage.textContent = "";
    }
}

function validateLoginPassword() {
    var password = document.getElementById("password").value;
    var errorMessage = document.getElementById("password-error-message");

    if (password.trim() === "") {
        errorMessage.textContent = "· 비밀번호를 입력해주세요.";
    } else {
        errorMessage.textContent = "";
    }
}

function validateLogin() {
    var username = document.getElementById("id").value;
    var password = document.getElementById("password").value;

    if (username.trim() === "" || password.trim() === "") {
        alert("아이디 혹은 비밀번호를 입력해주세요.");
        return false;
    }

    return true;
}