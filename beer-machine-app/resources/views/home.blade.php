<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="{{asset("css/main.css")}}">
    <script defer src={{asset("javascript.js")}}></script>
</head>
<body>
<header>
    <img src="{{asset('images/banner.PNG')}}">
</header>
<div class="flex-container">
    <aside>
        <button id="resetBtn">Reset</button>
        <button id="startBtn">Start</button>
        <button id="stopBtn">Stop</button>
        <button id="abortBtn">Abort</button>
        <button id="clearBtn">Clear</button>
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
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/thermometer.png')}}">
                        <label>100</label>
                    </div>
                    <div class="data-item">
                        Temperature
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/batch.png')}}">
                        <label>100</label>
                    </div>
                    <div class="data-item">
                        Temperature
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/bottle.png')}}">
                        <label>100</label>
                    </div>
                    <div class="data-item">
                        Temperature
                    </div>
                </div>
            </div>
            <div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/humidity.png')}}">
                        <label>100</label>
                    </div>
                    <div class="data-item">
                        Temperature
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/hmm.png')}}">
                        <label>100</label>
                    </div>
                    <div class="data-item">
                        Temperature
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/accepted.png')}}">
                        <label>100</label>
                    </div>
                    <div class="data-item">
                        Temperature
                    </div>
                </div>
            </div>
            <div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/vibration.png')}}">
                        <label>100</label>
                    </div>
                    <div class="data-item">
                        Temperature
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/ppm.png')}}">
                        <label>100</label>
                    </div>
                    <div class="data-item">
                        Temperature
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/defected.png')}}">
                        <label>100</label>
                    </div>
                    <div class="data-item">
                        Temperature
                    </div>
                </div>
            </div>
        </article>
    </main>
    <aside>
        <div class="main-container">
            <div id="myBar"  class="main-fill"></div>
        </div>
        <img src="{{asset('images/maintenance.png')}}">
    </aside>
</div>
</body>
</html>
