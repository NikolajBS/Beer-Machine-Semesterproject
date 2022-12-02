<html>
<head>
    <link rel="stylesheet" href="{{asset("css/create.css")}}">
    <script defer src={{asset("javascript.js")}}></script>
</head>
    <body id="temp-container">
        <div >
            <form action="{{route('store')}}" method="post">
                @csrf
                <label>Type</label>
                <select id="product-type" name="type">
                    <option selected="selected" value="0">Pilsner</option>
                    <option value="1">Wheat</option>
                    <option value="2">IPA</option>
                    <option value="3">Stout</option>
                    <option value="4">Ale</option>
                    <option value="5">Alcohol free</option>
                </select>
                <label>Amount</label>
                <input type="number" id="amount-id" name="amount" max="600" min="1">
                <button type="submit">Submit</button>
            </form>
        </div>
    </body>
</html>
