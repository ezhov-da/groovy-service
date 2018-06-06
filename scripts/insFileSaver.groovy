import java.util.logging.Logger

def logger = Logger.getLogger("SAVE FILE")

String name = params.get("name")
String text = params.get("text")

def defaultPath = new File("").getAbsolutePath() + "/../webapps/" + request.getContextPath().replace("/", "")
logger.info("name " + name)
logger.info("text " + text)

if ((name == null || "".equals(name)) || (text == null || "".equals(text))) {
    println """Параметры [name] или [text] не могут быть пустыми или null"""
} else {
    try {
        response.setContentType("text/html")
        response.setCharacterEncoding("UTF-8")

        def file = new File(defaultPath + "/" + name)

        def result
        if (file.exists()) {
            result = """Файл [${file.getAbsolutePath()}] перезаписан"""
        } else {
            result = """Файл [${file.getAbsolutePath()}] записан"""
        }
        file.write(text)
        println """${result}"""
    } catch (all) {
        println """${all.message}"""
    }
}