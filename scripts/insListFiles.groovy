import groovy.json.JsonBuilder

response.setContentType("application/json")

class OwnClass {
    def name
    def size
    def pathToDownload

    OwnClass(name, size, pathToDownload) {
        this.name = name
        this.size = size
        this.pathToDownload = pathToDownload
    }
}

String defaultPath = new File("").getAbsolutePath() + "/../webapps/" + request.getContextPath().replace("/", "")
File rootFile = new File(defaultPath)

File[] files = rootFile.listFiles(new FileFilter() {
    @Override
    boolean accept(File pathname) {
        return !pathname.isDirectory()
    }
})

def listFiles = Arrays.asList(files)
listFiles.sort(new Comparator<File>() {
    @Override
    int compare(File o1, File o2) {
        return o1.getName() <=> o2.getName()
    }
})
def ownClassList = listFiles
        .collect { file ->
    new OwnClass(
            file.name,
            file.length(),
            """insFileDownload.groovy?name=${file.name}"""
    )
}

def builder = new JsonBuilder(ownClassList)

println builder.toString()