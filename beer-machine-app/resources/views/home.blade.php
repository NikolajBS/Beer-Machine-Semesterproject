<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="{{asset("css/main.css")}}">
</head>
<body>
<header>
    <img src="{{asset('images/banner.PNG')}}">
</header>
<div class="flex-container">
    <aside>
        <button>Reset</button>
        <button>Start</button>
        <button>Stop</button>
        <button>Abort</button>
        <button>Clear</button>
    </aside>
    <main class="flex-container-main">
        <article>
            <div class="container-item">
                <label>Barley</label>
                <img src="{{asset('images/container.png')}}">
            </div>
            <div class="container-item">
                <label>Hops</label>
                <img src="{{asset('images/container.png')}}">
            </div>
            <div class="container-item">
                <label>Malt</label>
                <img src="{{asset('images/container.png')}}">
            </div>
            <div class="container-item">
                <label>Wheat</label>
                <img src="{{asset('images/container.png')}}">
            </div>
            <div class="container-item">
                <label>Yeast</label>
                <img src="{{asset('images/container.png')}}">
            </div>
        </article>
        <article class="data-container">
            <div>
                <div class="data-item">
                    <img src="{{asset('images/thermometer.png')}}">
                    <label>Temperature</label>
                </div>
                <div class="data-item">
                    <img src="{{asset('images/batch.png')}}">
                    <label>Batch ID</label>
                </div>
                <div class="data-item">
                    <img src="{{asset('images/bottle.png')}}">
                    <label>Produced</label>
                </div>
            </div>
            <div>
                <div class="data-item">
                    <img src="{{asset('images/humidity.png')}}">
                    <label>Humidity</label>
                </div>
                <div class="data-item">
                    <img src="{{asset('images/hmm.png')}}">
                    <label>Amount to produce</label>
                </div>
                <div class="data-item">
                    <img src="{{asset('images/accepted.png')}}">
                    <label>Acceptable products</label>
                </div>
            </div>
            <div>
                <div class="data-item">
                    <img src="{{asset('images/vibration.png')}}">
                    <label>Vibrance</label>
                </div>
                <div class="data-item">
                    <img src="{{asset('images/ppm.png')}}">
                    <label>Products per minute</label>
                </div>
                <div class="data-item">
                    <img src="{{asset('images/defected.png')}}">
                    <label>Defect products</label>
                </div>
            </div>
        </article>
    </main>
    <aside>
        <div class="container-item">
            <button class="main-fill"></button>
            <img src="{{asset('images/maintenance.png')}}">
        </div>
    </aside>
</div>
</body>
</html>
