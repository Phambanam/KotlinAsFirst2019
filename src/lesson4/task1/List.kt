@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.Int as Int1
import kotlin.String as String1

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int1>): List<Int1> {
    val result = mutableListOf<Int1>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int1>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int1>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int1) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String1): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int1>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs = 0.0
    for (i: Int1 in 0 until v.size) {
        abs += v[i] * v[i]
    }
    return sqrt(abs)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var medium = 0.0

    if (list.isEmpty()) return 0.0
    for (i: Int1 in 0 until list.size) {
        medium += list[i]
    }
    return medium / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    var cp = 0.0
    for (i: Int1 in 0 until list.size) {
        cp += list[i]
    }
    for (i: Int1 in 0 until list.size) {
        list[i] -= cp / list.size
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int1>, b: List<Int1>): Int1 {
    if (a.isEmpty() && b.isEmpty()) return 0
    var c = 0
    for (i: Int1 in 0 until a.size) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */


fun polynom(p: List<Int1>, x: Int1): Int1 {
    var t = 0
    val y: Int1 = x
    if (p.isEmpty()) return 0

    for (i: Int1 in 0 until p.size) {
        val a: Int1 = pow(y.toDouble(), i.toDouble()).toInt()
        t = t + p[i] * a

    }
    return t
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int1>): MutableList<Int1> {
    for (i: Int1 in 1 until list.size) {
        list[i] += list[i - 1]

    }
    return list

}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
// Ham tim so nguyen to
fun prime(n: Int1): Boolean {
    val a: Int1 = n
    if (a < 2) return false
    if (a == 2 && a == 3) return true
    if (a > 3) {
        for (i: Int1 in 2..sqrt(a.toDouble()).toInt())
            if (a % i == 0) {
                return false
            }
    }

    return true

}

fun factorize(n: Int1): List<Int1> {
    val a = mutableListOf<Int1>()
    var b: Int1 = n
    if (prime(b)) {
        a.add(b)
        return a
    }
    for (i in 2..b / 2) {
        while (b % i == 0) {
            if (prime(i) && b % i == 0) {
                a.add(i)
                b /= i
            }
        }
    }
    return a
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int1): String1 {
    var a = ""
    var b: Int1 = n
    if (prime(b)) {
        a = a.plus("$b")
        return a
    }
    for (i in 2..b / 2) {
        while (b % i == 0) {
            if (prime(i) && b % i == 0) {
                a = a.plus("*$i")
                b /= i
            }
        }
    }
    return a.run { substring(1) }

}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int1, base: Int1): List<Int1> {
    var a: Int1 = n
    val t: Int1 = base
    val x = mutableListOf<Int1>()
    val y = mutableListOf<Int1>(0)
    val r = mutableListOf<Int1>()
    var count = 0
    if (a == 0) return y
    while (a > 0) {
        x.add(a % t)
        a /= t
        count += 1

    }

    for (j: Int1 in (x.size - 1) downTo 0) {
        r.add(x[j])

    }
    return r
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int1, base: Int1): String1 {
    var a: Int1 = n
    val t: Int1 = base
    val m = mutableListOf(
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z"
    )
    val x = mutableListOf<String1>()
    var r = ""
    val y = mutableListOf<String1>("0")
    var count = 0
    //Tim cac phan tu cua list
    if (n == 0) return y[0]
    while (a > 0) {
        x.add((a % t).toString())
        a /= t
        count += 1

    }

    for (j: Int1 in (x.size - 1) downTo 0) {
        if (x[j].toInt() >= 10) {
            x[j] = m[x[j].toInt() - 10]
        }
        r = r.plus(x[j])

    }
    return r
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int1>, base: Int1): Int1 {
    val n: Int1 = base
    var s = 0
    var k: Double
    for (i: Int1 in digits.size - 1 downTo 0) {
        k = n.toDouble().pow(digits.size - 1 - i.toDouble())

        s += digits[i] * (k.toInt())

    }
    return s
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String1, base: Int1): Int1 {

    val m = mutableListOf(
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z"
    )
    val x = mutableListOf<String1>()
    // chuyen chuoi t sang list
    for (i: Int1 in 0 until str.length) {
        x.add(str[i].toString())
    }
    val n: Int1 = base
    var s = 0
    var k: Double
    // chuyen chu sang so
    for (i: Int1 in 0 until x.size)
        for (j: Int1 in 0 until m.size) {
            if (x[i] == m[j]) x[i] = (j + 10).toString()
        }
    for (i: Int1 in x.size - 1 downTo 0) {
        k = n.toDouble().pow(x.size - 1 - i.toDouble())
        s += x[i].toInt() * (k.toInt())
    }
    return s
}


/**
 * Сложная
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int1): String1 {
    var m: Int1 = n
    var s = ""
    for (i: Int1 in 1..m / 1000) s += "M"
    m = m % 1000
    if (m >= 900) {
        s += "CM"
        m = m - 900
    }
    if (m >= 500) {
        s += "D"
        m -= 500
    }

    if (m >= 400) {
        s += "CD"
        m -= 400
    }
    if (m >= 100) {
        for (i: Int1 in 1..m / 100) {
            s += "C"
        }
        m %= 100
    }
    if (m >= 90) {
        s += "XC"
        m -= 90
    }
    if (m >= 50) {
        s += "L"
        m -= 50
    }
    if (m >= 40) {
        s += "XL"
        m -= 40
    }
    if (m >= 10) {
        for (i: Int1 in 1..m / 10) {
            s += "X"
        }
        m %= 10
    }
    if (m >= 9) {
        s += "IX"
        m -= 9
    }
    if (m >= 5) {
        s += "V"
        m -= 5
    }
    if (m >= 4) {
        s += "IV"
        m -= 4
    }
    if (m >= 1) {
        for (i: Int1 in 1..m) {
            s += "I"
        }
    }
    return s
}

/**
 * Очень сложная
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int1): String1 {
    var m: Int1 = n
    var k: Int1 = n
    var s = ""
    var count = 0
    while (m > 0) {
        m /= 10
        count++
    }
    val arr = mutableListOf(
        "один",
        "два",
        "три",
        "четыре",
        "пять",
        "шесть",
        "семь",
        "восемь",
        "девять",
        "десять",
        "одиннадцать",
        "двенадцать",
        "тринадцать",
        "четырнадцать",
        "пятнадцать",
        "шестнадцать",
        "семнадцать",
        "восемнадцать",
        "девятнадцать",
        "двадцать",
        "тридцать",
        "сорок",
        "пятьдесят",
        "шестьдесят",
        "семьдесят",
        "восемьдесят",
        "девяносто",
        "сто",
        "двести",
        "триста",
        "четыреста",
        "пятьсот",
        "шестьсот",
        "семьсот",
        "восемьсот",
        "девятьсот",
        "тысяча",
        "тысячи",
        "тысяч"
    )
    //
    if (k in 1..20) return arr[k - 1]
    // neu n co 2 chu so > 20
    if (count == 2 && k > 20) {
        s += arr[(k / 10) + 17] + " " + arr[k % 10 - 1]
        return s
    }
    // Neu n co 3 chu so
    if (count == 3) {
        s += arr[(k / 100) + 26]
        k = k % 100
        if (k > 21 && k % 10 == 0) s += " " + arr[(k / 10) + 17]
        if (k >= 21 && k % 10 > 0) s += " " + arr[(k / 10) + 17] + " " + arr[k % 10 - 1]
        if (k in 1..20) s += " " + arr[k - 1]
        return s
    }
    // neu n co 4 chu so
    if (count == 4) {
        val q: Int1 = k / 1000
        if (q == 1) s += arr[36]
        if (q == 2) s += "две" + " " + arr[37]
        if (q == 3 || q == 4) s += arr[q - 1] + " " + arr[37]
        if (q > 4) s += arr[q - 1] + " " + arr[38]
        k %= 1000
        if (k >= 100) s += " " + arr[(k / 100) + 26]
        k %= 100
        if (k > 21 && k % 10 == 0) s += " " + arr[(k / 10) + 17]
        if (k >= 21 && k % 10 > 0) s += " " + arr[(k / 10) + 17] + " " + arr[k % 10 - 1]
        if (k in 1..20) s += " " + arr[k - 1]
        return s

    }
    // neu n co 5 chu so
    if (count == 5) {
        val q: Int1 = k / 1000
        if ((q % 10 == 1 && q > 11)) s += arr[(q / 10) + 17] + " " + "одна" + " " + arr[36]
        if (q % 10 > 2 && q % 10 <= 4 && q > 20) s += arr[(q / 10) + 17] + " " + arr[q % 10 - 1] + " " + arr[37]
        if (q % 10 == 2 && q > 20) s += arr[(q / 10) + 17] + " " + "две" + " " + arr[37]
        if (q in 10..20) s += arr[q - 1] + " " + arr[38]
        if (q > 20 && q % 10 > 4) s += arr[(q / 10) + 17] + " " + arr[q % 10 - 1] + " " + arr[38]
        k %= 1000
        if (k >= 100) s += " " + arr[(k / 100) + 26]
        k %= 100
        k %= 100
        if (k > 21 && k % 10 == 0) s += " " + arr[(k / 10) + 17]
        if (k >= 21 && k % 10 > 0) s += " " + arr[(k / 10) + 17] + " " + arr[k % 10 - 1]
        if (k in 1..20) s += " " + arr[k - 1]
        return s

    }
    // voi n co 6 chu so
    if (count == 6) {
        val q: Int1
        val p: Int1 = k / 1000
        if (p % 100 == 0) s += arr[(p / 100) + 26] + " " + arr[38]
        else s += arr[(p / 100) + 26]
        q = p % 100

        if (q == 1) s += " " + "одна" + " " + arr[36]
        if (q / 10 == 0) {
            if (q % 10 == 2) s += " " + "две" + " " + arr[37]
            if (q % 10 in 3..4) s += " " + arr[q - 1] + " " + arr[37]
            if (q % 10 > 4) s += " " + arr[q - 1] + " " + arr[38]

        }
        if (q % 10 == 1 && q > 11) s += " " + arr[(q / 10) + 17] + " " + "одна" + " " + arr[36]
        if (q % 10 in 3..4 && q > 20) s += " " + arr[(q / 10) + 17] + " " + arr[q % 10 - 1] + " " + arr[37]
        if (q % 10 == 2 && q > 20) s += " " + arr[(q / 10) + 17] + " " + "две" + " " + arr[37]
        if (q in 10..20) s += " " + arr[q - 1] + " " + arr[38]
        if (q > 20 && q % 10 == 0) s += " " + arr[(q / 10) + 17] + " " + arr[38]
        if (q > 20 && q % 10 > 4) s += " " + arr[(q / 10) + 17] + " " + arr[q % 10 - 1] + " " + arr[38]
        k %= 1000
        if (k >= 100) s += " " + arr[(k / 100) + 26]
        k %= 100
        if (k > 21 && k % 10 == 0) s += " " + arr[(k / 10) + 17]
        if (k >= 21 && k % 10 > 0) s += " " + arr[(k / 10) + 17] + " " + arr[k % 10 - 1]
        if (k in 1..20) s += " " + arr[k - 1]
        return s

    }
    return s
}
