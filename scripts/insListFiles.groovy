
response.setContentType("application/json")

@groovy.transform.Immutable
class OwnClass {
    String name
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
def ownClassList = listFiles.collect { file -> new OwnClass(file.getName()) }

def builder = new groovy.json.JsonBuilder(ownClassList)

println builder.toString()