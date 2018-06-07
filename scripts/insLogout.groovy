response.setHeader("Authorization", "")
response.setHeader("WWW-Authenticate", "Basic")
response.setStatus(401)
println "Logout"
