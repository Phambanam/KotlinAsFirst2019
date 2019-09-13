package lesson1.task1

import lesson2.task1.minBiRoot
import lesson2.task1.whichRookThreatens
import lesson3.task1.cos
import lesson2.task1.ageDescription as ageDescription

/**
 * Пример главной функции
 */
fun main() {
    val x1x2 = quadraticRootProduct(1.0, 13.0, 42.0)
    println("Root product: $x1x2")
    val h = seconds(5, 10, 30)
    println("Seconds: $h")
    val Met = lengthInMeters(8, 2, 11)
    println("Met = $Met")
    val Radian = angleInRadian(36, 14, 35)
    println("Radian = $Radian")
    val length = trackLength(3.0, 0.0, 0.0, 4.0)
    println("length = $length")
    val s = accountInThreeYears(100, 10)
    println("s = $s")
    val N = numberRevert(number = 478)
    println("Revert is $N")
    val travling = travelMinutes(9, 25, 13, 1)
    println ("$travling")
   // val a = whichRookThreatens(5,3,7,3,4,8)
   // println(a)


}



