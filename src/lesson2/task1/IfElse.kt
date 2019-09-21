@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt
import kotlin.Int as Int1

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int1 {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int1): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: kotlin.Int): String {
    val k: kotlin.Int = age

    val a: kotlin.Int
    if (k in 105..120) return "$k лет"
    else {
        if (k in 5..20) return "$k лет"
        else {

            a = k.rem(10)
            if (a == 1) return "$k год"
            else
                if (a in 2..4) return "$k года"
                else return "$k лет"
        }
    }

}


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val f3: Double = v3
    val f2: Double = v2
    val f1: Double = v1
    val s = (t1 * f1 + t2 * f2 + t3 * f3) / 2
    if (s > (t1 * f1 + t2 * f2)) return (t1 + t2 + (s - (t1 * f1 + t2 * f2)) / f3)
    else {
        if (s > t1 * f1) return (t1 + (s - t1 * f1) / f2)
        else {
            if (s < f1 * t1) return t1 - (f1 * t1 - s) / f1
        }
    }
    return -1.0
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int1, kingY: Int1,
    rookX1: Int1, rookY1: Int1,
    rookX2: Int1, rookY2: Int1
): Int1 {
    if ((kingX == rookX1 || kingY == rookY1) && (kingY == rookY2 || kingX == rookX2)) {
        return 3
    }
    if (kingX == rookX1 || kingY == rookY1) return 1

    if (kingX == rookX2 || kingY == rookY2) return 2

    return 0

}


/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только о т ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int1, kingY: Int1,
    rookX: Int1, rookY: Int1,
    bishopX: Int1, bishopY: Int1
): Int1 {
    if (abs(kingX - bishopX) == abs(kingY - bishopY) && (kingX == rookX || kingY == rookY)) return 3
    if (abs(kingX - bishopX) == abs(kingY - bishopY)) return 2
    if (kingX == rookX || kingY == rookY) return 1
    return 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int1 {
    if (a + b < c || a + c < b || b + c < a) return -1
    else {
        if (a * a == b * b + c * c || a * a + b * b == c * c || a * a + c * c == b * b) return 1
        if (a * a > b * b + c * c || a * a + b * b < c * c || a * a + c * c < b * b) return 2
        return 0
    }

}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int1, b: Int1, c: Int1, d: Int1): Int1 {
    if (a > d) return -1
    if (c > b) return -1
    if (b >= d && c >= a) return (d - c)
    if (b >= d && a >= c) return (d - a)
    if (d >= b && c >= a) return (b - c)
    if (d >= b && a >= c) return (b - a)
    return 0
}
