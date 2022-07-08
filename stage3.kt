package search

val data = mutableListOf<List<String>>()
var askingForInput = true

fun main() {
    enterData()
    while (askingForInput) {
        selectAction()
    }
}

fun enterData() {
    println("Enter the number of people:")
    val numberOfPeople = readln().toInt()
    repeat(numberOfPeople) {
        data.add(readln().split(" "))
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
