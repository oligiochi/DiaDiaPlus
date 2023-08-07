package diadia.util

import java.io.File

fun getAbsolutePath(relativePath: String): String {
    val rootPath = System.getProperty("user.dir")
    val rootPathTagliata = rootPath.substringBefore("src")
    return File(rootPathTagliata, relativePath).absolutePath
}
fun getClassesInPackage(packageName: String): Set<String> {
    val packag = File(packageName).listFiles()
    val names= mutableSetOf<String>()
    for (file in packag!!){
        names.add(file.name.removeSuffix(".kt"))
    }
    return names
}