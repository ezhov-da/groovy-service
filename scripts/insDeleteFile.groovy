import groovy.json.JsonBuilder

import java.util.logging.Logger

def logger = Logger.getLogger("DELETE FILE")

String name = params.get("name")
logger.info("name file for delete: " + name)

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
    def file = new File(defaultPath)
    if (!file.exists()) {
        builder.call(
                [
                        "failure": true,
                        "msg"    : "File ${file.getAbsolutePath()} not exists"
                ]
        )
        println builder.toString()
    } else {
        if (!file.delete()) {
            builder.call(
                    [
                            "failure": true,
                            "msg"    : "File ${file.getAbsolutePath()} not delete"
                    ]
            )
            println builder.toString()
        } else {
            println "Файл ${file.getAbsolutePath()} удален"
        }
    }
}






