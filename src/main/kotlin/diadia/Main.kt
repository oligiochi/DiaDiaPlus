package diadia

import diadia.console.IO
import diadia.console.IOConsole

fun main(args: Array<String>) {
        val io: IO = IOConsole()
        val gioco = DiaDia(io)
        gioco.gioca()
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}
