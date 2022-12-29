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
    }if(select.value==="5"){
        input_amount.setAttribute("max",125);
        input_amount.setAttribute("min",1);
    }
}
