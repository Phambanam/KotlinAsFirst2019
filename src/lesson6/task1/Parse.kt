@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import kotlin.math.pow

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрен
 *
 * ия календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val month = listOf(
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    )
    val date = str.split(" ")
    if (date.size != 3) return ""
    return try {
        val day = date[0].toInt()
        val year = date[2].toInt()
        val mont = month.indexOf(date[1]) + 1
        if (mont <= 0) return ""
        if (day in 1..daysInMonth(mont, year)) return String.format("%02d.%02d.%d", day, mont, year)
        return ""
    } catch (e: NumberFormatException) {
        return ""
    }
//    if (date.size != 3) return ""
//    if (!month.contains(date[1]))
//        return ""
//    if (date[0].length == 1) date[0] = "0" + date[0]
//    for (i in 0 until month.size) if (month[i] == date[1]) {
//        val a = i + 1
//        if (i in 0..8) {
//            date[1] = "0$a"
//        } else date[1] = "$a"
//        break
//    }
//    when (date[1].toInt()) {
//        1, 3, 5, 7, 8, 10, 12 -> if (date[0].toInt() > 31) return ""
//        2 -> if (date[0].toInt() > daysInMonth(2, date[2].toInt())) return ""
//        4, 6, 9, 11 -> if (date[0].toInt() > 30) return ""
//    }
//    return date.joinToString(".")
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val month = listOf(
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    )
    val date = digital.split(".").toMutableList()
    if (date.size != 3) return ""
    return try {
        val day = date[0].toInt()
        val year = date[2].toInt()
        if (date[1].toInt() !in 1..12) return ""
        val mont = month[date[1].toInt() - 1]
        if (day <= 0 || date[1].toInt() <= 0 || year < 0) return ""
        if (day in 1..daysInMonth(date[1].toInt(), year)) return String.format("%d %s %d", day, mont, year)
        ""
    } catch (e: NumberFormatException) {
        ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {

    val p = phone.split(" ", ")", "-", "(").filter { it != "" }.toMutableList()
    if (phone.contains("()")) return ""
    return if (!Regex("""(\+|[0-9]+)[0-9]+""").matches(p.joinToString(""))) "" else p.joinToString("")
//    try {
//        for (i in 0 until phone.length) if (phone[i].toString() == "(" && phone[i + 1].toString() == ")") return ""
//    } catch (e: StringIndexOutOfBoundsException) {
//        return ""
//    }
//    return try {
//        p.filter { it.toInt() >= 0 }.joinToString("")
//    } catch (e: NumberFormatException) {
//        return ""
//    }
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int? {
    val jumpsA = jumps.split(" ", "%", "-").filter { it != "" && it != " " }
    return try {
        jumpsA.map { it.toInt() }.max() ?: -1
    } catch (e: NumberFormatException) {
        return -1
    }
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val jumpsA = jumps.split(" ", "%", "-").filter { it != "" && it != " " }
    if (jumpsA.isEmpty()) return -1
    var b = 0
    return try {
        jumpsA.all { it == "+" || it.toInt() % 1 == 0 }
        for (i in 0 until jumpsA.size)
            if (jumpsA[i] == "+" && jumpsA[i - 1].toInt() > b) b = jumpsA[i - 1].toInt()
        b
    } catch (e: NumberFormatException) {
        return -1
    }
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val a = expression.split(" ")
        var result = a[0].toInt()

        for (i in 1 until a.size - 1 step 2) {
            when (a[i]) {
                "+" -> result += a[i + 1].toInt()
                "-" -> result -= a[i + 1].toInt()
                else -> throw IllegalArgumentException("")
            }
        }
        return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val a = str.toLowerCase().split(" ")
    try {
        var count = 0
        for (i in a.indices) {
            if (a[i] == a[i + 1]) return count
            count += a[i].length + 1
        }
        return -1
    } catch (e: Exception) {
        return -1
    }
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val a = description.split(";")
    try {
        var max = 0.0
        var str = ""
        for (i in a) {
            val k = i.split(" ").toMutableList()
            if (k.size != 2 && k[0] != "") return ""
            val b = k.filter { it != "" }

            val j = b[1].split(".")
            val n: Double
            n = if (b[1] == "0") 0.0
            else j[0].toDouble() + (j[1].toDouble() / (10.0.pow(j[1].length.toDouble())))
            if (n >= max) {
                max = n
                str = b[0]
            }
        }
        return str
    } catch (e: Exception) {
        return ""
    }
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    val rom = mutableListOf(
        Pair(1000, "M"), Pair(500, "D"),
        Pair(100, "C"), Pair(50, "L"), Pair(10, "X"),
        Pair(5, "V"), Pair(1, "I")
    )
    val a = roman.split("").filter { it != "" }.toMutableList()
    try {
        for (j in rom)
            for (i in 0 until a.size)
                if (a[i] == j.second) a[i] = j.first.toString()
        if (a.size == 1) return a[0].toInt()
        var result = a[0].toInt()
        for (i in 0 until a.size - 1) {
            val x = a[i].toInt()
            val y = a[i].toInt()
            if (x >= y) result += x
            else result += y - 2 * x
        }
        return result
    } catch (e: Exception) {
        return -1
    }
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должн1о быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
