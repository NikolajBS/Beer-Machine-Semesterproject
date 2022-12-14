
        $('#startBtn').click(function() {
            maintenanceVal();
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
        });
        let currentHeight=100;
        function maintenanceVal (){
            // when stop is clicked we cancel the interval call.
            document.getElementById('stopBtn').onclick = function (){
              clearInterval(start);

            };
            const start = setInterval(function () {
                currentHeight -= 0.5;
                document.getElementById('myBar').style.height = currentHeight.toString() + "%";
                // if true, stop production
                if (currentHeight <= 20) {
                    clearInterval(start);
                    setTimeout(function (){
                        alert("Machine needs repairing");
                    })
                    mainentenancePause();
                    let refill = setInterval(function (){
                        currentHeight += 0.5;
                        document.getElementById('myBar').style.height = currentHeight.toString() + "%";
                        // if true, start again
                        if(currentHeight>=100){
                            clearInterval(refill);
                            setTimeout(function (){
                                alert("Click to begin production again")
                            })
                            // send start cmd to Java
                            mainentenanceStart();
                            // recursive call
                            maintenanceVal();
                        }
                    },1000);
                }
            }, 1000);
        }

        function mainentenanceStart(){
            $.ajax({
                url: "http://127.0.0.1:3001/post",
                type: "POST",
                data: {
                    CmdChange: 2
                },
                success: function(data) {
                    console.log(data)
                }
            });
        }
        function mainentenancePause(){
            $.ajax({
                url: "http://127.0.0.1:3001/post",
                type: "POST",
                data: {
                    CmdChange: 3
                },
                success: function(data) {
                    console.log(data);
                }
            });
        }

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
        });

