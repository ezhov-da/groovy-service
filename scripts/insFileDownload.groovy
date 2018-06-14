import groovy.json.JsonBuilder

import java.util.logging.Logger

def logger = Logger.getLogger("LOAD FILE")

String name = params.get("name")
logger.info("name file for show text: " + name)

def builder = new JsonBuilder()
if (name == null || "" == name) {
    response.setContentType("application/json")
    builder.call(
            [
                    "failure": true,
                    "msg"    : "Parameter [name] must not be null or empty"
            ]
    )
    println builder.toString()
} else {
    def defaultPath = new File("").getAbsolutePath() + "/../webapps/" + request.getContextPath().replace("/", "") + "/" + name
    logger.info("Path to file download: " + defaultPath)
    def file = new File(defaultPath)
    if (file.exists()) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.name + "\"")
        sout.write(file.getBytes())
        sout.flush()
        sout.close()
    } else {
        response.setContentType("application/json")
        builder.call(
                [
                        "failure": true,
                        "msg"    : """File [${defaultPath}] does not exist"""
                ]
        )
        println builder.toString()
    }
}





