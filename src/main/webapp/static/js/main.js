$(document).ready(function() {
    $("#repeatPassword").keyup(validate);
});

function validate() {
    var password1 = $("#password").val();
    var password2 = $("#repeatPassword").val();


    if(password1 == password2) {
        $("#validate-status").text("valid");
        $("#submit").removeAttr("disabled");
    }
    else {
        $("#validate-status").text("invalid");
        $("#submit").attr("disabled", "disabled");
    }

}
