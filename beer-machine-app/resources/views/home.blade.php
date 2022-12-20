<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="{{asset("css/main.css")}}">
    <script defer src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script defer src={{asset("js/cmds.js")}}></script>
    <meta name="csrf-token" content="{{ csrf_token() }}">
</head>
<body>
<header>
    <img src="{{asset('images/BRLOGO.png')}}">
    <h1>Beer Brewer</h1>
    <img src="{{asset('images/SDU_LOGO.png')}}">
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
                <div id="container-div">
                    <div id="progress-bar-container">
                        <div id="progress-bar-barley">
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-item">
                <label>Hops</label>
                <div id="container-div">
                    <div id="progress-bar-container">
                        <div id="progress-bar-hops">

                        </div>
                    </div>
                </div>
            </div>
            <div class="container-item">
                <label>Malt</label>
                <div id="container-div">
                    <div id="progress-bar-container">
                        <div id="progress-bar-malt">

                        </div>
                    </div>
                </div>
            </div>
            <div class="container-item">
                <label>Wheat</label>
                <div id="container-div">
                    <div id="progress-bar-container">
                        <div id="progress-bar-wheat">

                        </div>
                    </div>
                </div>
            </div>
            <div class="container-item">
                <label>Yeast</label>
                <div id="container-div">
                    <div id="progress-bar-container">
                        <div id="progress-bar-yeast">
                        </div>
                    </div>
                </div>

            </div>
        </article>
        <article class="data-container">
            <div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/thermometer.svg')}}">
                        <input readonly id="temp-id" value="0">
                    </div>
                    <div class="data-item">
                        <p>Temperature</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/package.svg')}}">
                        <input type="text" readonly id="batch-id" value="{{$batch->batch}}">
                    </div>
                    <div class="data-item">
                        <p>Batch ID</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/beer.svg')}}">
                        <input readonly  id="bottled-id" value="0">
                    </div>
                    <div class="data-item">
                        <p>Produced</p>
                    </div>
                </div>
            </div>
            <div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/humidity.png')}}">
                        <input readonly  id="humidity-id" value="0">
                    </div>
                    <div class="data-item">
                        <p>Humidity</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/beertap.svg')}}" >
                        <input readonly id="amount-id" value="{{$batch->amount}}">
                    </div>
                    <div class="data-item">
                        <p>Amount to produce</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/accept.svg')}}">
                        <input readonly  id="accept-id" value="0">
                    </div>
                    <div class="data-item">
                        <p>Acceptable products</p>
                    </div>
                </div>
            </div>
            <div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/vibration.svg')}}">
                        <input readonly id="vibration-id" value="0">
                    </div>
                    <div class="data-item">
                        <p>Vibration</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/speedometer.svg')}}">
                        <input readonly  id="ppm-id" value="{{$batch->speed}}">
                    </div>
                    <div class="data-item">
                        <p>Products per minute</p>
                    </div>
                </div>
                <div class="test">
                    <div class="data-item">
                        <img src="{{asset('images/defect.svg')}}">
                        <input readonly  id="defect-id" value="0">

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
        <img src="{{asset('images/maintenance.svg')}}">
    </aside>
</div>
</body>
</html>
