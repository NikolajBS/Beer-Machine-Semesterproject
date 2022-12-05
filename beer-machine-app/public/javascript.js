window.onload = function () {


    document.getElementById('startBtn').onclick = function () {
        // let csrf = document.querySelector('meta[name="csrf-token"]').content;
        // let xhttp = new XMLHttpRequest();
        // xhttp.onreadystatechange = function () {
        //     if (this.readyState == 4 && this.status == 200) {
        //
        //         data = JSON.parse(this.responseText)
        //         console.log(data);
        //     }
        // };
        // xhttp.open("POST", "register", true);
        // xhttp.setRequestHeader('X-CSRF-TOKEN', csrf);
        // xhttp.setRequestHeader('Accept', 'application/json');
        // xhttp.setRequestHeader('Content-Type', 'application/json');
        // xhttp.send(JSON.stringify({amount: 300, type: 0, speed: 200}));
        console.log("yep");

    }
}
    const select = document.getElementById('product-type');
    var input_amount = document.getElementById('speed-id');
    select.onchange = function (){
    if(select.value==="0"){
        input_amount.setAttribute("max",600);
        input_amount.setAttribute("min",1);
    }
    if(select.value==="1"){
        input_amount.setAttribute("max",300);
        input_amount.setAttribute("min",1);
    }if(select.value==="2"){
        input_amount.setAttribute("max",150);
            input_amount.setAttribute("min",1);
    }if(select.value==="3"){
        input_amount.setAttribute("max",200);
        input_amount.setAttribute("min",1);
    }if(select.value==="4"){
        input_amount.setAttribute("max",100);
        input_amount.setAttribute("min",1);
    }if(select.value==="6"){
        input_amount.setAttribute("max",125);
        input_amount.setAttribute("min",1);
    }
}
