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
            findPerson(readln())
        }
        "2" -> printListOfPeople()
        "0" -> askingForInput = false
        else -> println("\nIncorrect option! Try again.")
    }
}

fun findPerson(dataForSearch: String) {
    val focus = dataForSearch.lowercase()
    if (focus in data.joinToString(" ").lowercase()) {
        perPerson@ for (personsData in data) {
            for (pieceOfData in personsData) {
                if (focus in pieceOfData.lowercase()) {
                    println(personsData.joinToString(" "))
                    continue@perPerson
                } else {
                    continue
                }
            }
        }
    } else {
        println("No matching people found.")
    }
}

fun printListOfPeople() {
    println("=== List of people ===")
    for (person in data) {
        println("\n" + person.joinToString(" "))
    }
}
