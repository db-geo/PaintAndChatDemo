<!DOCTYPE HTML>

<html>
<head>
    <link rel="stylesheet" href="css/draw.css"/>
    <script src="js/draw.js"></script>
</head>

<body onload="init();">
<canvas id="mycanvas" height="150px" width="150px"></canvas>

<script type="text/javascript">
    function init() {
        new Draw(document.getElementById('mycanvas'));
    }
</script>
</body>
</html>