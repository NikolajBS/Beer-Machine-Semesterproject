<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="{{asset("css/main.css")}}">
    <script defer src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

    <script defer src={{asset("javascript.js")}}></script>
    <meta name="csrf-token" content="{{ csrf_token() }}">
</head>
<body>
<header>
    <img src="{{asset('images/banner.jpg')}}">
</header>
<div class="flex-container">

    <aside>
        <button id="resetBtn">Reset</button>
        <button id="startBtn">Start</button>
        <button id="stopBtn">Stop</button>
        <button id="abortBtn">Abort</button>
        <button id="clearBtn">Clear</button>
        <button id="editBtn" onclick="location.href='{{ route('submit') }}'">
            Edit</button>
        <button id="createPdf">Create PDF</button>
    </aside>
    <main class="flex-container-main">
        <article class="container-article">
            <div class="container-item">
                <label>Barley</label>
                <img src="{{asset('images/container.jpg')}}">
            </div>
            <div class="container-item">
                <label>Hops</label>
                <img src="{{asset('images/container.jpg')}}">
            </div>
            <div class="container-item">
                <label>Malt</label>
                <img src="{{asset('images/container.jpg')}}">
            </div>
            <div class="container-item">
                <label>Wheat</label>
                <img src="{{asset('images/container.jpg')}}">
            </div>
            <div class="container-item">
                <label>Yeast</label>
                <img src="{{asset('images/container.jpg')}}">

            </div>
        </article>
        <article class="data-container">
            <div>
                <div class="test">
                    <div class="data-item">
                            <img src="{{asset('images/thermometer.jpg')}}">
                            <input readonly id="temp-id" >
                    </div>
                    <div class="data-item">
                        <p>Temperature</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/batch.jpg')}}">
                        <input type="text" readonly id="batch-id" value="{{$batch->id}}">
                    </div>
                    <div class="data-item">
                        <p>Batch ID</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/bottle.jpg')}}">
                        <input readonly  id="bottled-id">
                    </div>
                    <div class="data-item">
                        <p>Bottles</p>
                    </div>
                </div>
            </div>
            <div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/humid.jpg')}}">
                        <input readonly  id="humidity-id">
                    </div>
                    <div class="data-item">
                        <p>Humidity</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/handle.jpg')}}" >
                        <input readonly id="amount-id" value="{{$batch->amount}}">
                    </div>
                    <div class="data-item">
                        <p>Amount to produce</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/accept.jpg')}}">
                        <input readonly  id="accept-id">
                    </div>
                    <div class="data-item">
                        <p>Acceptable products</p>
                    </div>
                </div>
            </div>
            <div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/vibration.jpg')}}">
                        <input readonly id="vibration-id">
                    </div>
                    <div class="data-item">
                        <p>Vibration</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/ppm.jpg')}}">
                        <input readonly  id="ppm-id" value="{{$batch->speed}}">
                    </div>
                    <div class="data-item">
                        <p>Products per minute</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/denied.jpg')}}">
                        <input readonly  id="defect-id">

                    </div>
                    <div class="data-item">
                        <p>Defect products</p>
                    </div>
                </div>
            </div>
        </article>
    </main>
    <aside>
        <div class="main-container">
            <div id="myBar"></div>
        </div>
        <img src="{{asset('images/main.jpg')}}">
    </aside>
</div>
</body>
{{--TO DO:--}}
{{--should the script first be called when we press start -> if so fix it --}}
{{--data sent to java should be interpreted and written to UA EXPERT--}}
{{--When we click create pdf button, a pdf should be created, check javascript.js for inspiration--}}
{{--maintenance and maybe containers should drop in % and then stop production maybe by sending stop values
, and then when full again, send start values.--}}
{{--report--}}
<script>setInterval(function (){
        $.ajax({
            url: "api/collection/"+{{$batch->id}},
            type: "GET",
            success: function(data) {
                if(data['temp']!=null){
                    document.getElementById('temp-id').value=data['temp'].temperature;
                }
                if(data['batch']!=null){
                    document.getElementById('bottled-id').value=data['batch'].producedAmount;
                    document.getElementById('defect-id').value=data['batch'].defectAmount;
                    document.getElementById('accept-id').value=data['batch'].acceptedAmount;
                }
                if(data['humidity']!=null){
                    document.getElementById('humidity-id').value=data['humidity'].humidity;
                }
                if(data['vibration']!=null){
                    document.getElementById('vibration-id').value=data['vibration'].vibration;
                }
            }
        })
    },1000)</script>
</html>
