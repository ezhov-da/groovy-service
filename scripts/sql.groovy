import java.util.logging.Level
import java.util.logging.Logger

response.setContentType("text/html")

def generate = new Random().nextInt(1000)
def logger = Logger.getLogger("sql")
logger.log(Level.INFO, "connect: [" + request.getRemoteAddr() + "]")

println """
<html>
    <head>
        <title>Gist</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf8"></meta>
        
        <link rel = "stylesheet" type = "text/css" 
         href = "https://cdnjs.cloudflare.com/ajax/libs/extjs/4.2.1/resources/css/ext-all.css" / >
        <script type = "text/javascript" 
         src = "https://cdnjs.cloudflare.com/ajax/libs/extjs/4.2.1/ext-all.js"> </script>
        
        <!--
        <link rel="stylesheet" type="text/css" href="https://cdn.corp.tander.ru/libs/extjs-4.1.1/css/ext-all.css" />
        <script type="text/javascript" src="https://cdn.corp.tander.ru/libs/extjs-4.1.1/js/ext-all-debug.js"></script>
        -->
        <!--<script type="text/javascript" src="app.js"></script>-->
        <!--<script type="text/javascript" src="gist.js"></script>-->
        <script type="text/javascript" src="sql.js"></script>
    </head>

    <body>
    </body>            
</html>
"""