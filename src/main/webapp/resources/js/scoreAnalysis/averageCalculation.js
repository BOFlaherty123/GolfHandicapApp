$(document).ready ( function() {

    $(function(){

        var courseSelection = $("#_courseSelection").val();

        // if a course selection value is stored in the session, display value on screen
        if(courseSelection != '') {
            $("#courseNameSelection").val(courseSelection);
            $("#selectedCourse").val(courseSelection);
        }

        $("#courseNameSelection").change(function(){
            var value = $('#courseNameSelection option:selected').val();

            $("#selectedCourse").val(value);
            $("#calculateAvgByCourseName").attr("href", "/MyHandicapApp//scoreAnalysis/averageCourseName/" + value);
        });
    });

    // JQuery UI slider to populate hole par
    $(function() {

        var parSelection = $("#_parSelection").val();

        $("#slider-hole-par").slider({
            range: "max",
            min: 3,
            max: 5,
            value: parSelection,
            slide: function( event, ui ) {
                $("#selectedPar").val( ui.value );
                $('#calculatePar').attr("href", "/MyHandicapApp/scoreAnalysis/averagePar/" + ui.value);
            }
        });
        $("#selectedPar").val( $("#slider-hole-par").slider("value"));


    });

    // JQuery UI slider to populate hole yardage
    $(function() {

        var yardSelection = $("#_yardSelection").val();

        $("#slider-hole-yardage").slider({
            range: "max",
            min: 50,
            max: 450,
            step: 50,
            value: yardSelection,
            slide: function( event, ui ) {
                $("#selectedYardage").val( ui.value );
                $('#calculateYardage').attr("href", "/MyHandicapApp/scoreAnalysis/averageYardage/" + ui.value);
            }
        });
        $("#selectedYardage").val( $("#slider-hole-yardage").slider("value"));
    });

});