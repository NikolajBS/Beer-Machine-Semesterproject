<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="{{asset("css/main.css")}}">
    <script defer src={{asset("javascript.js")}}></script>
</head>
<body>
<header>
    <img src="{{asset('images/banner.jpg')}}" onclick="location.href='{{ route('return') }}'">
</header>
<div class="flex-container">

    <main class="flex-container-main">
        <label>Product Amount</label> <br>
        <input name="amount"></input> <br><br>

        <label>Product Type</label> <br>
        <select name="beersType"> Beers
            <option value="0"> Pilsner</option>
            <option value="1"> Wheat </option>
            <option value="2"> IPA</option>
            <option value="3"> Stout</option>
            <option value="4"> Ale</option>
            <option value="5"> Alcohol Free</option>
        </select>
    </main>
</div>
</body>
</html>
