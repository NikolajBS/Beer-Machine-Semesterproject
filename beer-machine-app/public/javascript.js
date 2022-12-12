
        $('#startBtn').click(function() {
            // let speed = document.getElementById('label-speed-id').value;
            // let productID = document.getElementById('label-type-type').value;
            // let productAmount = document.getElementById('label-amount-id').value;
            var speed = $("#label-speed-id").val();
            var productID = $("#label-type-id").val();
            var productAmount = $("#label-amount-id").val();

            $.ajax({
                url: "http://127.0.0.1:80/data",
                type: "GET",
                data: {
                    CmdChange: 2,
                    CmdChangeRequest:true,
                    MachSpeed: speed,
                    productID: productID,
                    productAmount: productAmount
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
                    CmdChange: 3,
                    CmdChangeRequest:true
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
                    CmdChange: 1,
                    CmdChangeRequest:true
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
                    CmdChange: 5,
                    CmdChangeRequest:true
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
                    CmdChange: 4,
                    CmdChangeRequest:true
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
