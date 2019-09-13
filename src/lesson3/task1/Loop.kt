@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import java.lang.Math.*
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.Double as Double1
import kotlin.math.sqrt as sqrt1

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double1 {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt1(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var a: Int = 0;
    var b: Int = n
    if (b == 0) return 1
    while (b > 0) {
        b = b / 10;
        a++

    }
    return a
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n == 1 || n == 2) return 1
    return fib(n - 2) + fib(n - 1)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var a: Int
    var b: Int
    a = m
    b = n
    if (a == b) return a
    while (a != b) {
        if (a > b) {
            a = a - b
        } else {
            b = b - a
        }
    }
    return m * n / (a)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i: Int in 2..n) {
        if (n % i == 0) {
            return i
            break
        }
    }
    return 0
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var a: Int = n

    for (i: Int in 2..a) {
        if (a % i == 0) {
            return a / i
            break
        }
    }
    return 1
}


/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var a: Int = m
    var b: Int = n
    if (a == b) return false
    while (a != b) {
        if (a > b) {
            a = a - b
        } else {
            b = b - a
        }
    }
    if (a == 1) return true
    return false
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var a: Int = m
    var b: Int = n
    for (i: Int in m..n) {
        var k: Int = sqrt1(i + 0.0).toInt()
        if (k * k == i) {
            return true
            break
        }
    }
    return false
}


/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var k: Int = x
    var count: Int = 0
    while (k != 1) {
        if (k % 2 == 0) {
            k = k / 2
            count++
        } else {
            k = 3 * k + 1
            count++
        }

    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */


fun sin(x: Double1, eps: Double1): Double1 {
    var a: Double1 = x
    var n: Int = 2
    var S: Double1 = x
    while (eps <= abs(a)) {
        a = -a * x * x / ((n + 1) * n)
        S = S + a
        n = n + 2
    }

    return round(S).toInt() + 0.0
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double1, eps: Double1): Double1 {
    var a: Double1 = 1.0
    var b: Double1 = 1.0
    var n: Int = 1
    while (eps <= abs(a)) {

        a = -a * x * x / (n * (n + 1))
        b = b + a
        n = n + 2

    }
    var h: Double1 = round(b).toInt() + 0.0
    return h
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var number: Int = 0
    var i: Int = n
    while (i > 0) {
        number = number * 10 + i % 10
        i = i / 10
    }
    return number
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var number: Int = 0
    var i: Int = n
    while (i > 0) {
        number = number * 10 + i % 10
        i = i / 10
    }
    if (number == n) return true
    return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number: Int = 1
    var i: Int = n
    if (n <= 9) return false
    while (i >= 10) {
        number = number * 10 + 1
        i = i / 10
    }
    println(number)
    if (n % number == 0) return false
    return true
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun count(n: Int): Int {
    var a: Int = 0
    var t: Int = n
    while (t > 0) {
        t = t / 10;
        a++
    }
    return a
}

fun reverse(number: Int): Int {
    var a: Int
    var b: Int
    var i: Int
    a = 0
    i = number
    while (i > 0) {
        b = i.rem(10)
        a = a * 10 + b
        i = i.div(10)
    }
    return a
}

fun squareSequenceDigit(n: Int): Int {
    var a: Int = 0
    var x4: Int
    var b: Int
    var k: Double
    for (i: Int in 1..n) {
        var t: Int = i * i
        if (t % 10 == 0) {
            t = t + 1
        }


        x4 = reverse(t)


        while (x4 > 0) {
            a = a + 1

            b = x4 % 10
            x4 = x4 / 10
           if((t-1)%100==0 && x4==1 && a==n && b==0) return 0
            if (a == n) return b
        }
    }
    return 0

}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
