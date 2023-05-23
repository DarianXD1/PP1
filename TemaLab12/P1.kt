fun main() {
    val list = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)

    // Eliminarea oricărui număr < 5
    val filteredList = list.filter { it >= 5 }
    println(filteredList) // [21, 75, 39, 7, 35, 31, 7, 8]

    // Gruparea numerelor în perechi
    val pairs = filteredList.windowed(2, 2)
    println(pairs) // [(21, 75), (39, 7), (35, 31), (7, 8)]

    // Multiplicarea numerelor din perechi
    val multipliedPairs = pairs.map { it[0] * it[1] }
    println(multipliedPairs) // [1575, 273, 1085, 56]

    // Sumarea rezultatelor
    val result = multipliedPairs.sum()
    println(result) // 2989
}
