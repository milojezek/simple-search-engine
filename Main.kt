package search

import java.io.File

val data = mutableListOf<List<String>>()
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
            selectMatchingStrategy()
        }
        "2" -> printListOfPeople()
        "0" -> askingForInput = false
        else -> println("\nIncorrect option! Try again.")
    }
}

fun printListOfPeople() {
    println("\n=== List of people ===")
    for (person in data) {
        println(person.joinToString(" "))
    }
}

fun selectMatchingStrategy() {
    println("Select a matching strategy: ALL, ANY, NONE")
    val input = readln()
    println("\nEnter a name or email to search all matching people.")
    val target = readln().lowercase()
    when (input) {
        "ALL" -> searchAll(target)
        "ANY" -> searchAny(target)
        "NONE" -> searchNone(target)
    }
}

fun searchAll(target: String) {
    val keyWords = target.split(" ")
    val result = mutableListOf<String>()
    var personsFound = 0

    for (personsData in data) {
        if (keyWords.all { it in personsData.joinToString(" ").lowercase() }
            && personsData.joinToString(" ") !in result) {
            result.add(personsData.joinToString(" "))
            personsFound++
        } else {
            continue
        }
    }

    if (result.isNotEmpty()) {
        println("\n$personsFound persons found:")
        for (match in result) {
            println("\n$match")
        }
    } else {
        println("No matching people.")
    }
}

fun searchAny(target: String) {
    val keyWords = target.split(" ")
    if (keyWords.any { it in data.joinToString(" ").lowercase() }) {
        var personsFound = 0
        val result = mutableListOf<String>()
        for (word in keyWords) {
            for (personsData in data) {
                if (word in personsData.joinToString(" ").lowercase()
                    && personsData.joinToString(" ") !in result) {
                    result.add(personsData.joinToString(" "))
                    personsFound++
                } else {
                    continue
                }
            }
        }

        println("\n$personsFound persons found:")
        for (match in result) {
            println("\n$match")
        }

    } else {
        println("No matching people.")
    }
}

fun searchNone(target: String) {
    val keyWords = target.split(" ")
    val result = mutableListOf<String>()
    var personsFound = 0

    for (personsData in data) {
        if (keyWords.all { it !in personsData.joinToString(" ").lowercase()  }
            && personsData.joinToString(" ") !in result) {
            result.add(personsData.joinToString(" "))
            personsFound++
        } else {
            continue
        }
    }

    if (result.isNotEmpty()) {
        println("\n$personsFound persons found:")
        for (match in result) {
            println("\n$match")
        }
    } else {
        println("No matching people.")
    }
}
