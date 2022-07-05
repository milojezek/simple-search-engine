package search

val data = mutableListOf<List<String>>()

fun main() {
    enterData()
    enterQueries()
}

fun enterData() {
    println("Enter the number of people:")
    val numberOfPeople = readln().toInt()
    repeat(numberOfPeople) {
        data.add(readln().split(" "))
    }
}

fun enterQueries() {
    println("\nEnter the number of search queries:")
    val numberOfQueries = readln().toInt()

    repeat(numberOfQueries) {
        println("\nEnter data to search people:")
        val dataForSearch = readln().lowercase()

        if (dataForSearch in data.joinToString(" ").lowercase()) {
            println("\nPeople found:")
            outerloop@ for (aList in data) {
                for (aString in aList) {
                    if (dataForSearch in aString.lowercase()) {
                        println(aList.joinToString(" "))
                        continue@outerloop
                    } else {
                        continue
                    }
                }
            }
        } else {
            println("No matching people found.")
        }
    }
}
