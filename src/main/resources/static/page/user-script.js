$.get("navbar.html", function(data){
    $("#nav-placeholder").replaceWith(data);
});

function fillTable() {
    $("#user-table tbody tr:not(#row-template)").remove();
    $.get({
        url: "/unauth/users",
        success: function (response) {
            $rowTemplate = $("#row-template");
            for (var i = 0; i < response.length; i++) {
                var user = response[i];
                var $row = $rowTemplate.clone();
                $row.removeAttr("id").removeClass("d-none");
                $row.find(".user-id").text(user.id);
                $row.find(".user-displayedUserName").text(user.displayedUserName);
                $row.find(".user-email").text(user.email);
                $("#user-table tbody").append($row);
            }
        }
    })
}

fillTable();