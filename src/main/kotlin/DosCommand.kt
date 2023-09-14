import com.github.ajalt.clikt.completion.completionOption
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import java.nio.file.Files

object DosCommand : CliktCommand(name = "dos", help = "degree of separation finder") {
    private val verbose: Boolean by option("-v", "--verbose").flag()
    private val datasetPath: String by option(
        "-d",
        "--dataset",
        metavar = "path/to/dataset",
        help = "dataset should be a `csv` or `tsv`"
    ).required()
    private val query: String? by option(
        "-q",
        "--query",
        metavar = "from/to",
        help = "query should be in the form of from/to"
    )

    override fun run() {
        Dos.loadDataset(datasetPath, verbose)
        //TODO: find degree of separation
        //TODO: print result
    }
}