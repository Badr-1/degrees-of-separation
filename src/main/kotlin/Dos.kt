import com.github.ajalt.mordant.animation.progressAnimation
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Spinner
import java.io.File

class Network(val name: String)
class Node(val name: String) {
    var connections: MutableList<String> = mutableListOf()
    var networks: MutableList<String> = mutableListOf()
    var visited: Boolean = false
    var parent: Node? = null
    var distance: Int = 0
}

object Dos {
    private val networks = mutableMapOf<String, Network>()
    private val nodes = mutableMapOf<String, Node>()
    fun loadDataset(path: String, verbose: Boolean = false) {
        val dataset = File(path)
        val separator = if (dataset.extension == "csv") "," else "\t"
        val terminal = Terminal()
        val progress = terminal.progressAnimation {
            spinner(Spinner.Dots(TextColors.brightGreen))
            text("Loading dataset...")
            completed()
        }
        if (verbose) {
            progress.start()
            progress.updateTotal(dataset.readLines().size.toLong())
        }
        for (line in dataset.readLines()) {
            val entry = line.split(separator).toMutableList()
            val network = Network(entry[0])
            networks[network.name] = network
            entry.filter { it != network.name }.forEach { node ->
                if (nodes.containsKey(node)) {
                    nodes[node]?.networks?.add(network.name)
                } else {
                    val newNode = Node(node)
                    newNode.networks.add(network.name)
                    nodes[node] = newNode
                }
                nodes[node]?.connections?.addAll(entry.filter { it != network.name && it != node })
            }
            if (verbose) {
                progress.advance(1)
                Thread.sleep(200)
            }
        }
        if (verbose)
            progress.stop()
    }

    fun findDegreeOfSeparation(from: String, to: String, verbose: Boolean = false) {
        //TODO: not implemented yet
    }

    fun printResult() {
        //TODO: not implemented yet
    }
}