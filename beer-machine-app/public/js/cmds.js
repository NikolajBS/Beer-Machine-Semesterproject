function setStateCookie(state = 0){
    document.cookie = "state="+state; //https://www.w3schools.com/js/js_cookies.asp
}
setStateCookie();


$('#startBtn').click(function() {
    $.ajax({
        url: "http://127.0.0.1:3001/post",
        type: "POST",
        data: {
            CmdChange: 2
        },
        success: function(data) {
            // Print out the data
            console.log(data);
        }
    });
    setStateCookie(2);
});

$('#submitBtn').click(function() {
    let batchId = document.getElementById('batch-id').value;
    let productType = document.getElementById('product-type').value;
    let speedId = document.getElementById('speed-id').value;
    let amountId = document.getElementById('amount-id').value;
    $.ajax({
        url: "http://127.0.0.1:3001/post",
        type: "POST",
        data: {
            id: batchId,
            type: productType,
            speed: speedId,
            amount: amountId
            },
        success: function(data) {
            // Print out the data
            console.log(data);
        }
    });
    setStateCookie(6);
});

$('#stopBtn').click(function() {
    $.ajax({
        url: "http://127.0.0.1:3001/post",
        type: "POST",
        data: {
            CmdChange: 3
        },
        success: function(data) {
            // Print out the data
            console.log(data);
        }
    });
    setStateCookie(3);
});

$('#resetBtn').click(function() {
    $.ajax({
        url: "http://127.0.0.1:3001/post",
        type: "POST",
        data: {
            CmdChange: 1
        },
        success: function(data) {
            // Print out the data
            console.log(data);
        }
    });
    setStateCookie(1);
});

$('#clearBtn').click(function() {
    $.ajax({
        url: "http://127.0.0.1:3001/post",
        type: "POST",
        data: {
            CmdChange: 5
        },
        success: function(data) {
            // Print out the data
            console.log(data);
        }
    });
    setStateCookie(5);
});

$('#abortBtn').click(function() {
    $.ajax({
        url: "http://127.0.0.1:3001/post",
        type: "POST",
        data: {
            CmdChange: 4
        },
        success: function(data) {
            // Print out the data
            console.log(data);
        }
    });
    setStateCookie(4);
});

document.getElementById('startBtn').addEventListener('click', function() {
            $test = setInterval(function () {
                console.log("hi")
                // break out of interval when all bottles are produced
                if (document.getElementById('bottled-id').value === document.getElementById('amount-id').value) {
                    clearInterval($test)
                }
                $.ajax({
                    url: "api/collection",
                type: "GET",
                    success: function (data) {
                    if (data['temp'] != null) {
                        document.getElementById('temp-id').value = data['temp'].temperature;
                    }
                    if (data['batch'] != null) {
                        document.getElementById('bottled-id').value = data['batch'].producedAmount;
                        document.getElementById('defect-id').value = data['batch'].defectAmount;
                        document.getElementById('accept-id').value = data['batch'].acceptedAmount;
                    }
                    if (data['humidity'] != null) {
                        document.getElementById('humidity-id').value = data['humidity'].humidity;
                    }
                    if (data['vibration'] != null) {
                        document.getElementById('vibration-id').value = data['vibration'].vibration;
                    }

                    document.getElementById('progress-bar-barley').style.height = (data['inventory'].barley / 35000) * 100 + "%";
                    document.getElementById('progress-bar-hops').style.height = (data['inventory'].hops / 35000) * 100 + "%";
                    document.getElementById('progress-bar-malt').style.height = (data['inventory'].malt / 35000) * 100 + "%";
                    document.getElementById('progress-bar-yeast').style.height = (data['inventory'].yeast / 35000) * 100 + "%";
                    document.getElementById('progress-bar-wheat').style.height = (data['inventory'].wheat / 35000) * 100 + "%";
                    document.getElementById('myBar').style.height = 100 - (data['inventory'].maintenance / 30000) * 100 + "%";
                }
            })
            }, 500)
        });
