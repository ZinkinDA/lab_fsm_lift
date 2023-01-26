$(document).ready(function (){
    showLift();
});


function showLift(){
    $.get('/api/elevator/', function (data){
        console.log(data);

        let view = '' +
            '<div align="center" >' +
            '<h4>LIFT ID : ' + data.id + '</h4><br>' +
            '<h4>LIFT STAGE : ' + (data.currentLevel) + '</h4><br>' +
            '<h4>LIFT STAICONDITION : ' + data.state + '</h4><br>' +
            '<h4>DOOR : ' + data.door + '</h4><br>' +
            '</div>';

        $('#fsm').html(view);
    });
}

function transportToLevel() {
    let level = document.getElementById('level').value;
    let time = fetch('/api/elevator/level').then(data.json());
    console.log(time)


    $.ajax({
        url:'/api/elevator/transport',
        dataType: 'json',
        contentType: 'application/json',
        type : 'POST',
        cache : false,
        data: JSON.stringify(level)
    });

    showLift();
    setTimeout(showLift,500);

    setTimeout(showLift,1520);
    setTimeout(showLift,3520);
}