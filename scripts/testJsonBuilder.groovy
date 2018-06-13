import groovy.json.JsonBuilder

def builder = new JsonBuilder()

builder.call(
        [
                "failure": true,
                "msg"    : "\"FUCK\""
        ]
)

println builder.toString()
