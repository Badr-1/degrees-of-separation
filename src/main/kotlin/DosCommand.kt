import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.kinquirer.KInquirer
import com.github.kinquirer.components.promptInput
import kotlin.system.exitProcess

object DosCommand : CliktCommand(name = "dos", help = "degree of separation finder") {
    private val verbose: Boolean by option("-v", "--verbose").flag()
    private val datasetPath: String by option(
        "-d",
        "--dataset",
        metavar = "path/to/dataset",
        help = "dataset should have a consistent separator"
    ).required()
    private val separator: String by option(
        "-s",
        "--separator",
        metavar = "separator",
        help = "separator should be a single character"
    ).required()
    private val queryString: String? by option(
        "-q",
        "--query",
        metavar = "from/to",
        help = "query should be in the form of from/to"
    )

    override fun run() {
        Dos.loadDataset(datasetPath, separator, verbose)
        val query: Query = if (queryString != null) {
            if (queryString!!.matches(Regex(".+/.+"))) {
                val input = queryString?.split("/")
                Query(input!![0], input[1])
            } else {
                println("Invalid query format")
                exitProcess(1)
            }
        } else {
            val queryString = KInquirer.promptInput(
                "Enter query in the form of from/to: ",
                validation = { it.matches(Regex(".+/.+")) })
            val input = queryString.split("/")
            Query(input[0], input[1])
        }
        if (Dos.validateQuery(query))
            Dos.findDegreeOfSeparation(query, verbose)
        else {
            println("Invalid query")
            exitProcess(1)
        }
    }
}