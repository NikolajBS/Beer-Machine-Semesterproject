
    document.getElementById("resetBtn").onclick=function (){
    document.getElementById("startBtn").onclick=function (){
        var bar = document.getElementById("myBar");
        var height = 100;
        bar.style.height = height+'%';
    }
};
        function start(){
        setInterval(function () {
            let xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {

                    data = JSON.parse(this.responseText)
                    // setting value to be displayed
                    // further development - which should be updated instantly(0 seconds)
                    // and others every 3rd second? ie. temp/humidity every 5th sec
                    document.getElementById('vibration-id').value = data[0].amount;
                    document.getElementById('temp-id').value = data[0].amount;
                    document.getElementById('batch-id').value = data[0].amount;
                    document.getElementById('bottled-id').value = data[0].amount;
                    document.getElementById('humidity-id').value = data[0].amount;
                    document.getElementById('amount-id').value = data[0].amount;
                    document.getElementById('accept-id').value = data[0].amount;
                    document.getElementById('ppm-id').value = data[0].amount;
                    document.getElementById('defect-id').value = data[0].amount;
                } };
            // getting response from elements url
        xhttp.open("GET", "elements", true);
        xhttp.send();

            // Do this every 0 seconds
            }, 0);
    }
