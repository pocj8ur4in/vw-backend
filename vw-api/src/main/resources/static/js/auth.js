$(document).ready(function authEmail() { // 페이지 로드 시, 이메일 인증 확인을 실행
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    $.ajax({
        url: "/v1/user/register/auth/email",
        type: "POST",
        data : {
            email: urlParams.get('email'),
            key: urlParams.get('key')
        },
        success: function(response) {
            alert(response);
        },
        error: function(error) {
            alert(error.responseText);
            window.close();
        }
    });
});