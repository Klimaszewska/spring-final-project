$.get("navbar.html", function(data){
    $("#nav-placeholder").replaceWith(data);
});


$("#register-submit-button").click(function () {
    var userEmail = $("#user-email").val();
    var userPassword = $("#user-password").val();


    var user = {
        email: userEmail,
        password: userPassword
    };

    $.post({
        url: "/unauth/register",
        data: JSON.stringify(user),
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            clearValidationErrors();
            resetRegistrationForm();
            console.log(user);
        },
        error: function (xhr) {
            console.log(xhr);
            handleValidationError(xhr.responseJSON);
        }
    });
    return false;
});

function resetRegistrationForm() {
    $("#user-email").val("");
    $("#user-password").val("");
}

function handleValidationError(fieldValidationErrors) {
    clearValidationErrors();
    for (var i = 0; i < fieldValidationErrors.length; i++) {
        var fieldValidationError = fieldValidationErrors[i];
        console.log(fieldValidationError);
        var $field = $("#user-" + fieldValidationError.field);
        $field.addClass("is-invalid");
        $field.siblings(".invalid-feedback").text("Error!");
/*        $field.siblings(".invalid-feedback").text(fieldValidationError.message);*/
    }
    $(".form-control:not(.is-invalid)").addClass("is-valid");
}

function clearValidationErrors() {
    $(".form-control").removeClass("is-invalid").removeClass("is-valid");
}
