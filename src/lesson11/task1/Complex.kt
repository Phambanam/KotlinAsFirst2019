@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson1.task1.sqr

/**
 * Класс "комплексое число".
 *
 * Общая сложность задания -- лёгкая.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(s: String) : this(
        """[^0123456789]""".toRegex().split(s)[0].toDouble(),
        if (s["""[^0123456789]""".toRegex().split(s)[0].length] == '+')
            """[^0123456789]""".toRegex().split(s)[1].toDouble() else """[^0123456789]""".toRegex().split(s)[1].toDouble() * (-1)
    )

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(other.re + re, other.im + im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-im, -re)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(re * other.re - im * other.im, re * other.im + im * other.re)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re + im * other.im) / (sqr(other.re) + sqr(other.im)),
        (other.re * im - other.im * re) / (sqr(other.re) + sqr(other.im))
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean =
        (other.toString() ==
                this.toString())


    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(re)
        if (im > 0) sb.append("+")
        sb.append(im)
        sb.append("i")
        return "$sb" // or, sb.toString()
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}