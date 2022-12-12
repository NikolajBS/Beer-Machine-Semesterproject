
        $('#startBtn').click(function() {
            $.ajax({
                url: "http://127.0.0.1:80/data",
                type: "GET",
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
                url: "http://127.0.0.1:80/data",
                type: "GET",
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

        $('#stopBtn').click(function() {
            $.ajax({
                url: "http://127.0.0.1:80/data",
                type: "GET",
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
                url: "http://127.0.0.1:80/data",
                type: "GET",
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
                url: "http://127.0.0.1:80/data",
                type: "GET",
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
                url: "http://127.0.0.1:80/data",
                type: "GET",
                data: {
                    CmdChange: 4
                },
                success: function(data) {
                    // Print out the data
                    console.log(data);
                }
            });
        });


//     const select = document.getElementById('product-type');
//     var input_amount = document.getElementById('speed-id');
//     select.onchange = function (){
//     if(select.value==="0"){
//         input_amount.setAttribute("max",600);
//         input_amount.setAttribute("min",1);
//     }
//     if(select.value==="1"){
//         input_amount.setAttribute("max",300);
//         input_amount.setAttribute("min",1);
//     }if(select.value==="2"){
//         input_amount.setAttribute("max",150);
//             input_amount.setAttribute("min",1);
//     }if(select.value==="3"){
//         input_amount.setAttribute("max",200);
//         input_amount.setAttribute("min",1);
//     }if(select.value==="4"){
//         input_amount.setAttribute("max",100);
//         input_amount.setAttribute("min",1);
//     }if(select.value==="6"){
//         input_amount.setAttribute("max",125);
//         input_amount.setAttribute("min",1);
//     }
// }
