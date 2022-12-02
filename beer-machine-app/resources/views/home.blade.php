<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="{{asset("css/main.css")}}">
    <script defer src={{asset("javascript.js")}}></script>
</head>
<body>
<header>
    <img src="{{asset('images/banner.jpg')}}">
</header>
<div class="flex-container">
    <aside>
        <button id="resetBtn" >Reset</button>
        <button id="startBtn" onclick="start()">Start</button>
        <button id="stopBtn">Stop</button>
        <button id="abortBtn">Abort</button>
        <button id="clearBtn">Clear</button>
        <button onclick="location.href='{{ route('submit') }}'">
            Edit</button>


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
                            <input id="temp-id">
                    </div>
                    <div class="data-item">
                        <p>Temperature</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/batch.jpg')}}">
                        <input id="batch-id">
                    </div>
                    <div class="data-item">
                        <p>Batch ID</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/bottle.jpg')}}">
                        <input id="bottled-id">
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
                        <input id="humidity-id">
                    </div>
                    <div class="data-item">
                        <p>Humidity</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/handle.jpg')}}">
                        <input id="amount-id">
                    </div>
                    <div class="data-item">
                        <p>Amount to produce</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/accept.jpg')}}">
                        <input id="accept-id">
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
                        <input id="vibration-id">
                    </div>
                    <div class="data-item">
                        <p>Vibration</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/ppm.jpg')}}">
                        <input id="ppm-id">
                    </div>
                    <div class="data-item">
                        <p>Products per minute</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/denied.jpg')}}">
                        <input id="defect-id">
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
            <div id="myBar"  class="main-fill"></div>
        </div>
        <img src="{{asset('images/main.jpg')}}">
    </aside>
</div>
</body>
</html>
