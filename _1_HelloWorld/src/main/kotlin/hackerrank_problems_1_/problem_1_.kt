package hackerrank_problems_1_

import java.util.*

// HackerRank - Algorithms

// 1- Solve Me First
// Complete the function solveMeFirst to compute the sum of two integers.

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val num1 = sc.nextInt()
    val num2 = sc.nextInt()
    val sum = solveMeFirst(num1, num2)
    println(sum)
}

// ilk akla gelen yol:

fun solveMeFirst1(a: Int, b: Int): Int {
    return a + b
}

// farkli yol 1:

fun solveMeFirst(a: Int, b: Int) = a + b
// return ya da body kullanmadan, = ile return'u belirttik.
// ve a ile b nin int olmasindan oturu return'un int olacagi kesin oldugu icin, return type'i da
// belirtmemize gerek kalmadi.