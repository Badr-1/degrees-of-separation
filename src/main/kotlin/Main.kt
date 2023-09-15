import com.github.ajalt.clikt.completion.completionOption

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        DosCommand.completionOption().main(args)
    }
}