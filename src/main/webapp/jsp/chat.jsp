<html>
<head>
    <title>Chat</title>
</head>
<body>
<h1>Chat</h1>
<div>
    <input type="text" id="username" placeholder="Username"/>
    <button type="button" onclick="client.connect();">Connect</button>
</div>
<textarea readonly="true" rows="10" cols="80" id="log"></textarea>
<div>
    <input type="text" size="51" id="msg" placeholder="Message"/>
    <button type="button" onclick="client.send();">Send</button>
</div>
</body>
<script src="js/chat.js"></script>
<script type="application/javascript">
    const client = new ChatClient();
</script>
</html>