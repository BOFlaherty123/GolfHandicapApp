$(document).ready ( function() {
    $("[id^='golf_']").hide();
});

var display = {
    "SHOW" : 'show'};

function displayHoleDataForRound(value, showOrHide) {
    (showOrHide == display.SHOW) ?  $("[id^=golf_" + value + "_hole], #golf_hole_" + value).show() :
        $("[id^=golf_" + value + "_hole], #golf_hole_" + value).hide();
}