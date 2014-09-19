$(document).ready ( function() {

    $(function(){
        $("#courseNameSelection").change(function(){
            var value = $('#courseNameSelection option:selected').val();

            $("#selectedCourse").val(value);
            $("#calculateAvgByCourseName").attr("href", "/MyHandicapApp//scoreAnalysis/averageCourseName/" + value);
        });
    });

    // JQuery UI slider to populate hole par
    $(function() {
        $("#slider-hole-par").slider({
            range: "max",
            min: 3,
            max: 5,
            value: 3,
            slide: function( event, ui ) {
                $("#selectedPar").val( ui.value );
                $('#calculatePar').attr("href", "/MyHandicapApp/scoreAnalysis/averagePar/" + ui.value);
            }
        });
        $("#selectedPar").val( $("#slider-hole-par").slider("value"));
    });

    // JQuery UI slider to populate hole yardage
    $(function() {
        $("#slider-hole-yardage").slider({
            range: "max",
            min: 50,
            max: 450,
            step: 50,
            value: 50,
            slide: function( event, ui ) {
                $("#selectedYardage").val( ui.value );
                $('#calculateYardage').attr("href", "/MyHandicapApp/scoreAnalysis/averageYardage/" + ui.value);
            }
        });
        $("#selectedYardage").val( $("#slider-hole-yardage").slider("value"));
    });

});