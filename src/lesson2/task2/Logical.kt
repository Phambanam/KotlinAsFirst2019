@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    var a = 0
    var b = 0
    var s: Int
    s = number
    while (s > 99) { //
        a = a + s % 10// tinh tong 2 chu so cuoi cung
        s = s / 10
    }
    while (s > 0) {
        b = b + s % 10 // tinh tong 2 chu so dau tien
        s = s / 10
    }
    if (a != b) return false
    return true
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    if (x1 == x2 || y1 == y2 || sqr(x1 - x2) == sqr(y1 - y2)) return true
    return false
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    when (month) {
        1 -> return 31
        2 -> if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) return 29 else return 28
        3 -> return 31
        4 -> return 30
        5 -> return 31
        6 -> return 30
        7 -> return 31
        8 -> return 31
        9 -> return 30
        10 -> return 31
        11 -> return 30
        12 -> return 31


    }
    return -1
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    if (r2 >= (sqrt(sqr(x1 - x2) + sqr(y1 - y2)) + r1)) return true
    return false
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    val n: Int = max(a, b)
    val k: Int = max(n, c)
    val t: Int = min(a, b)
    val j: Int = min(t, c)
    val q: Int = a + b + c - k - j
    if ((r >= q && s >= j) || (r >= j && s >= q)) return true
    return false
}