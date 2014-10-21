$(document).ready ( function() {
    $('#input-dateOfPlay').datepicker();

    // setup page on load
    $("[id^='row_']").hide();
    $("#row_1").show();

    // add additional rows as the user is completing the form
    $("[id^='increase_par_value_']").click(function() {
        var value = $(this).attr('id').replace(/\D/g,'');

        $("#row_" + value).show();
    });

});

function addAndSubtractCountButton(fieldId, action, maxValue) {

    var fieldValue = "";

    if (action == "add") {
        fieldValue = increaseTotal(fieldId, fieldValue, action, maxValue);
    } else {
        fieldValue = decreaseTotal(fieldId, fieldValue, action, maxValue);
    }

    $("#" + fieldId).val(fieldValue.toString());

};

function increaseTotal(fieldId, fieldValue, action, maxValue) {

    if ($('#' + fieldId).val() == "") {
        $('#' + fieldId).val(0);
    }
    // max value
    if ($('#' + fieldId).val() < maxValue) {
        fieldValue = parseInt($('#' + fieldId).val(), 10) + 1;
    } else {
        fieldValue = parseInt($('#' + fieldId).val(), 10);
    }

    return fieldValue;
}

function decreaseTotal(fieldId, fieldValue, action, maxValue) {

    if($('#' + fieldId).val() == 1) {
        return;
    } else {
        fieldValue = parseInt($('#' + fieldId).val(), 10) - 1;
    }

    return fieldValue;

}