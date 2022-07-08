package search

import java.io.File

val data = mutableListOf<List<String>>()
lateinit var indexList: Map<String, List<Int>>
var askingForInput = true

fun main(args: Array<String>) {
    if (args[0] == "--data") {
        val file = File(args[1])
        if (file.exists()) {
            val lines = file.readLines()
            for (line in lines) {
                data.add(line.split(" "))
            }
        }
    }

    indexList = getInvertedIndex(data)

    while (askingForInput) {
        selectAction()
    }
}

fun selectAction() {
    println("\n=== Menu ===" +
            "\n1. Find a person" +
            "\n2. Print all people" +
            "\n0. Exit")
    when(readln()) {
        "1" -> {
            println("\nEnter a name or email to search all suitable people.")
            searchInIndex(readln(), indexList)
        }
        "2" -> printListOfPeople()
        "0" -> askingForInput = false
        else -> println("\nIncorrect option! Try again.")
    }
}

fun printListOfPeople() {
    println("=== List of people ===")
    for (person in data) {
        println("\n" + person.joinToString(" "))
    }
}

fun getInvertedIndex(people: MutableList<List<String>>): Map<String, List<Int>> {
    val invertedIndex = mutableMapOf<String, MutableList<Int>>()
    people.forEachIndexed { index, line ->
        line.forEach { word ->
            invertedIndex.getOrPut(word.trim().lowercase()) { mutableListOf() }.add(index)
        }
    }

    return invertedIndex
}

fun searchInIndex(dataForSearch: String, indexList: Map<String, List<Int>>) {
    val focus = dataForSearch.lowercase()
    if (focus in indexList.keys.map { it.lowercase() }) {
        for (k in indexList.keys) {
            if (focus == k.lowercase()) {
                for (i in indexList[k]!!) {
                    println("\n" + data[i].joinToString(" "))
                }
            }
        }
    } else {
        println("No matching people.")
    }
}
