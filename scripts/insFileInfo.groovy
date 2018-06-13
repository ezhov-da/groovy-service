import groovy.json.JsonBuilder

import java.util.logging.Logger

def logger = Logger.getLogger("SAVE FILE")

String name = params.get("name")
logger.info("name file for show text: " + name)

response.setContentType("application/json")
def builder = new JsonBuilder()
if (name == null || "".equals(name)) {
    builder.call(
            [
                    "failure": true,
                    "msg"    : "Parameter [name] must not be null or empty"
            ]
    )
} else {
    def defaultPath = new File("").getAbsolutePath() + "/../webapps/" + request.getContextPath().replace("/", "") + "/" + name
    logger.info("Path to file for return text: " + defaultPath)
    def file = new File(defaultPath)
    if (file.exists()) {
        builder.call(
                [
                        "success": true,
                        "msg"    : """${file.text}"""
                ]
        )
    } else {
        builder.call(
                [
                        "failure": true,
                        "msg"    : """File [${defaultPath}] does not exist"""
                ]
        )
    }
}

println builder.toString()



