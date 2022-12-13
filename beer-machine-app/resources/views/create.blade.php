<html>
<head>
    <link rel="stylesheet" href="{{asset("css/create.css")}}">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script defer src={{asset("js/cmds.js")}}></script>
    <script defer src={{asset("js/create.js")}}></script>

    <meta name="csrf-token" content="{{ csrf_token() }}">
</head>
    <body id="temp-container">
        <div >
            <form action="{{route('store')}}" method="post">
                @csrf
                <label>batch ID</label>
                <input name="batch" id="batch-id" min="0" max="65535">
                <label>Type</label>
                <select id="product-type" name="type">
                    <option selected="selected" value="0">Pilsner</option>
                    <option value=1>Wheat</option>
                    <option value=2>IPA</option>
                    <option value=3>Stout</option>
                    <option value=4>Ale</option>
                    <option value=5>Alcohol free</option>
                </select>
                <label>Speed</label>
                <input type="number" id="speed-id" name="speed" max="600" min="1">
                <label for="amount-id">Amount</label>
                <input type="number" id="amount-id" name="amount" max="65535" min="1">
                <button id="submitBtn" type="submit">Submit</button>
            </form>
        </div>
    </body>
</html>
