function validateRegisterId(id) {
    const errorMessage = document.getElementById("id-error-message");
    const pattern = /^[a-zA-Z0-9]*$/;

    if (id.trim() === "") {
        errorMessage.textContent = "· 아이디를 입력해주세요.";
    } else if (!pattern.test(id)) {
        errorMessage.textContent = "· 아이디는 알파벳 대소문자와 숫자만 사용 가능합니다.";
    } else if (id.trim().length < 5) {
        errorMessage.textContent ="· 아이디의 길이를 5자 이상로 해주세요.";
    } else if (id.trim().length > 16) {
        errorMessage.textContent = "· 아이디의 길이를 16자 이하로 해주세요.";
    }

    // 중복된 아이디 확인 to 서버

    else {
        errorMessage.textContent = "";
    }
}

function validateRegisterPassword(password) {
    const errorMessage = document.getElementById("password-error-message");

    const pattern = /(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$^*+=-])(?=.*\d)(?!.*([A-Za-z0-9])\1{3,})(?=.{8,})(?=.{1,64}$)/;
    const patternLowerCase = /(?=.*[a-z])/;
    const patternUpperCase = /(?=.*[A-Z])/;
    const patternSpecialChar = /(?=.*[!@#$^*+=-])/;
    const patternNumber = /(?=.*\d)/;
    const patternNoRepeatedChars = /(?!.*([A-Za-z0-9])\1{3,})/;
    const patternMinLength8 = /(?=.{8,})/;
    const patternMaxLength64 = /(?=.{1,64}$)/;

    if (password.trim() === "") {
        errorMessage.textContent = "· 비밀번호를 입력해주세요.";
    } else if (!pattern.test(password) || !validateRegisterPasswordChk(password)) {
        errorMessage.textContent = "";

        if (!/(?=.*[a-z])/.test(password)) {
            errorMessage.innerHTML += "· 비밀번호에 소문자를 최소 하나 이상 포함해야 합니다.<br>";
        }

        if (!/(?=.*[A-Z])/.test(password)) {
            errorMessage.innerHTML += "· 비밀번호에 대문자를 최소 하나 이상 포함해야 합니다.<br>";
        }

        if (!/(?=.*[!@#$^*+=-])/.test(password)) {
            errorMessage.innerHTML += "· 비밀번호에 특수 문자를 최소 하나 이상 포함해야 합니다.<br>";
        }

        if (!/(?=.*\d)/.test(password)) {
            errorMessage.innerHTML += "· 비밀번호에 숫자를 최소 하나 이상 포함해야 합니다.<br>";
        }

        if (/(?=.*([A-Za-z0-9])\1{3,})/.test(password)) {
            errorMessage.innerHTML += "· 비밀번호에 동일한 문자가 과도하게 사용될 수 없습니다.<br>";
        }

        if (!validateRegisterPasswordContinue(password)) {
            errorMessage.innerHTML += "· 비밀번호에 연속된 문자열이 존재할 수 없습니다.<br>";
        }

        if (!/(?=.{8,})/.test(password)) {
            errorMessage.innerHTML += "· 비밀번호는 최소 8자 이상이어야 합니다.<br>";
        }

        if (!/(?=.{1,64}$)/.test(password)) {
            errorMessage.innerHTML += "· 비밀번호는 최대 64자 이하여야 합니다.<br>";
        }
    }


    else {
        errorMessage.textContent = "";
    }
}

function validateRegisterPasswordContinue(password) {
    let ascSeqCharCnt = 0, descSeqCharCnt = 0;
    let char_0, char_1, char_2;
    let diff_0_1, diff_1_2;

    for (let i = 0; i < password.length - 2; i++) {
        char_0 = password.charAt(i);
        char_1 = password.charAt(i + 1);
        char_2 = password.charAt(i + 2);

        diff_0_1 = char_0.charCodeAt(0) - char_1.charCodeAt(0);
        diff_1_2 = char_1.charCodeAt(0) - char_2.charCodeAt(0);

        if (diff_0_1 === 1 && diff_1_2 === 1) {
            ascSeqCharCnt += 1;
        }

        if (diff_0_1 === -1 && diff_1_2 === -1) {
            descSeqCharCnt -= 1;
        }
    }

    return !(ascSeqCharCnt > 1 || descSeqCharCnt > 1);
}

function validateRegisterPasswordChk(passwordChk, password) {
    const errorMessage = document.getElementById("passwordChk-error-message");

    if(passwordChk.trim() === ""){
        errorMessage.textContent = "· 비밀번호 확인을 입력해주세요.";
    } else if (passwordChk === password) {
        errorMessage.textContent = "";
    } else {
        errorMessage.textContent = "· 입력한 비밀번호와 비밀번호 확인이 일치하지 않습니다.";
    }
}

function validateRegisterNickname(nickname) {
    const errorMessage = document.getElementById("nickname-error-message");
    const pattern = /^[가-힣a-zA-Z0-9]+$/;

    if (nickname.trim() === "") {
        errorMessage.textContent = "· 닉네임을 입력해주세요.";
    } else if (!pattern.test(nickname)) {
        errorMessage.textContent = "· 닉네임은 한글, 알파벳 대소문자, 숫자만 사용 가능합니다.";
    } else if (nickname.trim().length < 2) {
        errorMessage.textContent ="· 닉네임의 길이를 2자 이상로 해주세요.";
    } else if (nickname.trim().length > 12) {
        errorMessage.textContent = "· 닉네임의 길이를 12자 이하로 해주세요.";
    }

    // 중복된 닉네임 확인 to 서버

    else {
        errorMessage.textContent = "";
    }
}

async function validateRegisterEmail(email) {
    const errorMessage = document.getElementById("email-error-message");
    const pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (email.trim() === "") {
        errorMessage.textContent = "· 이메일을 입력해주세요.";
    } else if (!pattern.test(email)) {
        errorMessage.textContent = "· 올바른 이메일 형식을 입력해주세요.";
    } else {
        $.ajax({
            data: {
                email: email
            },
            url: "/register/chk/email",
            success: function (data) {
                if (data === '1') {
                    errorMessage.textContent = "· 이메일이 중복되었습니다.";
                } else {
                    errorMessage.textContent = "";
                }
            }
        })
    }
}

function sendEmailVerification(email) {
    const errorMessage = document.getElementById("email-error-message");
    const pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (email.trim() === "") {
        errorMessage.textContent = "· 이메일을 입력해주세요.";
    } else if (!pattern.test(email)) {
        errorMessage.textContent = "· 올바른 이메일 형식을 입력해주세요.";
    } else {
        errorMessage.textContent = "해당 이메일로 인증 메일이 전송되었습니다. 인증 메일을 확인해주세요. (유효 시간 : 최대 3분)";
    }
}