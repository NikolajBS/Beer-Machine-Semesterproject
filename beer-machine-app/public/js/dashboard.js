let pie = document.getElementById("pie").getContext("2d");
let defect = document.getElementById("defect").innerText;
let acceptable = document.getElementById("acceptable").innerText;

let pieLabels = ["Defect", "Acceptable"];
let bgColorPie = ["red", "green"];
let pieData = [defect, acceptable];

let pieChart = new Chart(pie, {
    type: "pie",
    data: {
        labels: pieLabels,
        datasets: [{
            backgroundColor: bgColorPie,
            data: pieData
        }]
    },
    options: {
        title: {
            display: true,
            text: "Defect and acceptable products"
        }
    }
});

let state1 = document.getElementById("state1").innerHTML;
let state2 = document.getElementById("state2").innerHTML;
let state3 = document.getElementById("state3").innerHTML;
let state4 = document.getElementById("state4").innerHTML;
let state5 = document.getElementById("state5").innerHTML;
let barLabel = ["State 1","State 2", "State 3", "State 4", "State 5"]
let bgColorBar = ["red", "green","blue","yellow","cyan"]
let data = [state1, state2, state3, state4, state5]
console.log(state1);

let bar = document.getElementById("bar").getContext("2d");
let barChart = new Chart(bar, {
    type: "bar",
    data: {
        labels: barLabel,
        datasets: [{
            backgroundColor: bgColorBar,
            data: data
        }]
    },
    options: {
        scales: {
            y: {
                min: 0
            }
        },
        title: {
            display: true,
            text: "State time bar graph"
        }
    }
});

function timer(){
    switch (state){
        case 1:
            document.getElementById("state1").innerHTML = state1++;
            //console.log(data);// = state1;
            data[0] = state1;
            break;
        case 2:
            document.getElementById("state2").innerHTML = state2++;
            data[1] = state2;
            break;
        case 3:
            document.getElementById("state3").innerHTML = state3++;
            data[2] = state3;
            break;
        case 4:
            document.getElementById("state4").innerHTML = state4++;
            data[3] = state4;
            break;
        case 5:
            document.getElementById("state5").innerHTML = state5++;
            data[4] = state5;
            break;
    }
}
setInterval(function (){
    timer();
    barChart.update();
}, 1000);

document.getElementById("timer").addEventListener('click', function (){
    state++;
    console.log(state);
    if (state > 5){
        state = 1;
    }

})
