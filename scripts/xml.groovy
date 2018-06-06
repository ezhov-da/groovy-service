import java.util.logging.Logger
import java.util.logging.Level

response.setContentType("application/xml")

def generate = new Random().nextInt(1000)
def logger = Logger.getLogger("random word")
logger.log(Level.INFO, "connect: [" + request.getRemoteAddr() + "] and generate: [" + generate + "]")

println """
<test><random>${generate}</random></test>
"""