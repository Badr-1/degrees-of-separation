import com.github.ajalt.mordant.animation.progressAnimation
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Spinner
import java.io.File
import java.util.LinkedList
import java.util.Queue

class Network(val name: String)
class Node(val name: String) {
    var connections: MutableList<String> = mutableListOf()
    var networks: MutableList<String> = mutableListOf()
    var visited: Boolean = false
    var parent: Node? = null
    var distance: Int = 0
}

class Query(val from: String, val to: String)

object Dos {
    private val networks = mutableMapOf<String, Network>()
    private val nodes = mutableMapOf<String, Node>()
    fun loadDataset(path: String, separator: String, verbose: Boolean = false) {
        val dataset = File(path)
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
        val delimiter = when(separator){
            "\\t" -> "\t"
            else -> separator
        }
        for (line in dataset.readLines()) {
            val entry = line.split(delimiter).toMutableList()
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
        if (verbose) {
            progress.stop()
            println()
        }
    }

    fun findDegreeOfSeparation(query: Query, verbose: Boolean = false) {
        // Using BFS
        val queue: Queue<String> = LinkedList()
        queue.add(query.from)
        nodes[query.from]?.visited = true

        while (queue.isNotEmpty()) {
            val node = queue.poll()
            if (verbose) {
                println("Visiting $node")
            }
            if (node == query.to) {
                break
            }
            nodes[node]?.connections?.forEach { connection ->
                if (!nodes[connection]?.visited!!) {
                    queue.add(connection)
                    nodes[connection]?.visited = true
                    nodes[connection]?.parent = nodes[node]
                    nodes[connection]?.distance = nodes[node]?.distance!! + 1
                }
            }
        }
        println("Degree of separation between ${query.from} and ${query.to} is ${nodes[query.to]?.distance}")
        printResult(query)
    }

    fun validateQuery(query: Query): Boolean {
        return nodes.containsKey(query.from) && nodes.containsKey(query.to)
    }

    private fun printResult(query: Query) {
        var node = nodes[query.to]
        val path = mutableListOf<String>()
        while (node?.parent != null) {
            path.add(node.name)
            node = node.parent
        }
        path.add(query.from)
        path.reverse()
        path.forEach {
            print(it)
            if (it != path.last())
                print(" -> ")
        }
    }
}