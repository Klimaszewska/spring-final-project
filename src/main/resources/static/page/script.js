$.get("navbar.html", function(data){
    $("#nav-placeholder").replaceWith(data);
});

$("#create-button").click(function () {
    var eventName = $("#event-name").val();
    var eventDate = $("#event-date").val();
    var eventAddress = $("#event-address").val();
    var eventAccess = $("#event-access").val();
    var eventOrganizer = $("#event-organizer").val();

    var event = {
        eventName: eventName,
        eventDate: eventDate,
        eventAddress: eventAddress,
        eventAccess: eventAccess,
        eventOrganizer: eventOrganizer
    };

    $.post({
        url: "/unauth/event",
        data: JSON.stringify(event),
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            clearValidationErrors();
            // fillTable();
            console.log(event);
        },
        error: function (xhr) {
            handleValidationError(xhr.responseJSON);
        }
    });
    return false;
});


function createEventRow(event) {
    var $row = $rowTemplate.clone();
    $row.removeAttr("id").removeClass("d-none");

    var $eventId = $row.find(".event-id");
    $eventId.text(event.eventId);

    /*                $row.find(".event-id").text(event.eventId);*/
    $row.find(".event-name").text(event.eventName);
    $row.find(".event-location").text(event.eventAddress);
    $row.find(".event-date").text(event.eventDate);
    $row.find(".event-access").text(event.eventAccess);
    $row.find(".event-organizer").text(event.eventOrganizer);

    /* $row.data - przypisaliśmy do calego wiersza;
    * przy zmiennej szukamy najbliższego elementu tr*/
    $row.data("event", event);

    $("#event-table tbody").append($row);
}

function setOnDeleteClick() {
    $(".event-delete").click(function() {
        // $(this) - element, który został kliknięty
        var event = $(this).closest("tr").data("event");
        $("#event-id").val(event.eventId);
        $("#event-name").val(event.eventName);
        $("#event-location").val(event.eventAddress);
        $("#event-date").val(event.eventDate);
        $("#event-access").val(event.eventAccess);
        $("#event-organizer").val(event.eventOrganizer);

        $.ajax({
            url: "/unauth/event/" + event.eventId,
            method: "DELETE",
            success: function(response) {
                fillTable();
            },
            error: function(xhr) {
                handleValidationError(xhr.responseJSON);
            }
        });
        return false;
    });
}

function fillTable() {
    $("#event-table tbody tr:not(#event-row-template)").remove();
    $.get({
        url: "/unauth/event",
        success: function (events) {
            $rowTemplate = $("#event-row-template");
            for (var i = 0; i < events.length; i++) {
                createEventRow(events[i]);
            }
            setOnDeleteClick();
        }
    })
}

function resetForm() {
    $("#event-name").val("");
    $("#event-date").val("");
    $("#event-address").val("");
    $("#event-organizer").val("");
    $("#event-access").val("");
}

$("#cancel-button").click(function() {
    resetForm();
    return false;
});

function clearValidationErrors() {
    $(".form-control").removeClass("is-invalid").removeClass("is-valid");
}

function handleValidationError(fieldValidationErrors) {
    clearValidationErrors();
    for (var i = 0; i < fieldValidationErrors.length; i++) {
        var fieldValidationError = fieldValidationErrors[i];
        var $field = $("#" + fieldValidationError.fieldName);
        $field.addClass("is-invalid");
        $field.siblings(".invalid-feedback").text(fieldValidationError.message);
    }
    $(".form-control:not(.is-invalid)").addClass("is-valid");
}


function deleteEvent(id) {
    $.ajax({
        url: "/unauth/event" + id,
        method: "DELETE",
        success: function (event) {
            console.log("Event deleted: ", event);
            fillTable();
        }
    })
}

function showDeleteModal(id) {
    $("#delete-modal").modal("show");
    $("#delete-car-confirm-button").off("click");
    $("#delete-car-confirm-button").click(function () {
        $.ajax({
            url: "/unauth/car/" + id,
            method: "DELETE",
            success: function (car) {
                console.log("Car deleted: ", car);
                fillTable();
                $("#delete-modal").modal("hide");
            }
        })
    })
}


fillTable();