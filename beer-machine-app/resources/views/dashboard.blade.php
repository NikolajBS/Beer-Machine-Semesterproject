<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="{{asset("css/dashboard.css")}}">
    <script defer src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script defer type="module" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <script defer src={{asset("js/cmds.js")}}></script>
    <script defer src={{asset("js/dashboard.js")}}></script>
    <meta name="csrf-token" content="{{ csrf_token() }}">
</head>
<body>
<header>
    <img src="{{asset('images/banner.jpg')}}">
</header>


<div class="dashboard-info">
    <div class="nav">
        <button>Home</button>
        <button id="timer">Switch state test</button>
    </div>
    <div class="batch-info">
        <div>
            <h2>Batch id</h2>
            <h2>Type</h2>
            <h2>Total produced</h2>
            <h2>Total defects</h2>
            <h2>Total acceptable</h2>
            <h2>State 1</h2>
            <h2>State 2</h2>
            <h2>State 3</h2>
            <h2>State 4</h2>
            <h2>State 5</h2>
            <h2>Avg. temperature</h2>
            <h2>Avg. humidity</h2>
        </div>
        <div>
            <h2>{{$batch->id}}</h2>
            <h2>{{$batch->type}}</h2>
            <h2>{{$batch->producedAmount}}</h2>
            <h2 id="defect">{{$batch->defectAmount}}</h2>
            <h2 id="acceptable">{{$batch->producedAmount - $batch->defectAmount}}</h2>
            <h2 id="state1">0</h2>
            <h2 id="state2">0</h2>
            <h2 id="state3">0</h2>
            <h2 id="state4">0</h2>
            <h2 id="state5">0</h2>
            <h2>{{$temp}} &#8451</h2>
            <h2>{{$humidity}} %</h2>


        </div>
    </div>

    <div class="graph-info">
        <div class="pie-chart">
            <canvas id="pie"></canvas>
        </div>
        <div class="state-bar-graph">
            <canvas id="bar"></canvas>
        </div>
    </div>
</div>
