package kupco

import kotlin.math.max

fun compareMax(num1: String, num2: String): Int {
    require(Regex("""\d+""").matches(num1)) {}
    require(Regex("""\d+""").matches(num2)) {}
    val len1 = num1.length
    val len2 = num2.length
    var t = 0
    if (len1 > len2) t = 1
    if (len1 < len2) t = -1
    if (len1 == len2) {
        for (i in 0 until len1) {
            if (num1[i].toString().toInt() > num2[i].toString().toInt()) {
                t = 1
                break
            }
            if (num1[i].toString().toInt() < num2[i].toString().toInt()) {
                t = -1
                break
            }
        }
    }
    if (num1 == num2) t = 0
    return t
}

fun Sumbigs(num1: String, num2: String): String {
    require(Regex("""\d+""").matches(num1)) {}
    require(Regex("""\d+""").matches(num2)) {}
    val len1 = num1.length
    val len2 = num2.length
    val len = max(len1, len2)
    var sum = ""
    var d = 0
    val minNumb = if (len1 >= len2) "0".repeat((len - len2)) + num2
    else "0".repeat((len - len1)) + num1
    for (i in 0 until len) {
        d += if (minNumb.length != len1)
            minNumb[len - i - 1].toString().toInt() + num2[len - i - 1].toString().toInt()
        else
            minNumb[len - i - 1].toString().toInt() + num1[len - i - 1].toString().toInt()
        if (d >= 10) {
            sum += (d % 10).toString()
            d = 1
            if (i == len - 1) sum += "1"
        } else {
            sum += d.toString()
            d = 0
        }
    }
    return sum.reversed()
}

fun diff(num1: String, num2: String): String {
    require(Regex("""\d+""").matches(num1)) {}
    require(Regex("""\d+""").matches(num2)) {}
    if (num1 == num2) return "0"
    val len1 = num1.length
    val len2 = num2.length
    val len = max(len1, len2)
    var t = ""
    if (len1 > len2) t = num1
    if (len1 < len2) t = num2
    if (len1 == len2) {
        for (i in 0 until len1) {
            if (num1[i].toString().toInt() > num2[i].toString().toInt()) {
                t = num1
                break
            }
            if (num1[i].toString().toInt() < num2[i].toString().toInt()) {
                t = num2
                break
            }
        }
    }
    if (num1 == num2) t = num1
    var differ = ""
    var d = 0
    val newnumb = if (t == num1) "0".repeat((len1 - len2)) + num2
    else "0".repeat((len2 - len1)) + num1
    for (i in 0 until len) {
        d += -newnumb[len - i - 1].toString().toInt() + t[len - i - 1].toString().toInt()
        if (d < 0) {
            differ += (10 + d).toString()
            d = -1
        } else {
            differ += d.toString()
            d = 0
        }
    }
    while (differ.reversed()[0] == '0') differ = differ.reversed().substring(1)
    if (t == num2) return "-$differ"
    return differ
}

fun Productbig(num1: String, num2: String): String {
    require(Regex("""\d+""").matches(num1)) {}
    require(Regex("""\d+""").matches(num2)) {}
    var product = "0"
    for (i in num1.indices) {
        var d = 0
        var sum = ""
        for (j in num2.indices) {
            d += num1[num1.length - 1 - i].toString().toInt() * num2[num2.length - 1 - j].toString().toInt()
            if (d >= 10) {
                sum += (d % 10).toString()
                d /= 10
            } else {
                sum += d.toString()
                d = 0
            }
        }
        product = Sumbigs(product, sum.reversed() + "0".repeat(i))
    }
    return product
}

fun division(num1: String, num2: String): Pair<String, String> {
    require(Regex("""\d+""").matches(num1)) {}
    require(Regex("""\d+""").matches(num2)) {}
    if (compareMax(num1, num2) == -1) return Pair("0", num1)
    if (num1 == num2) return Pair("1", "0")
    val len2 = num2.length
    val len1 = num1.length
    var p = ""
    var str = num1.substring(0, len2)
    if ((compareMax(str, num2) >= 0)) {
        for (i in len2 - 1 until len1) {
            var d = 0
            while (compareMax(str, num2) >= 0) {
                str = diff(str, num2)
                d++
            }
            if (str[0] == '0' && str != "0") str = str.substring(1)
            if (i < len1 - 1) str += "${num1[i + 1]}"
            p += d.toString()
            if (p[0] == '0') p = p.substring(1)
        }
    } else {
        str += "${num1[len2]}"
        for (i in len2 until len1) {
            var d = 0
            while (compareMax(str, num2) >= 0) {
                str = diff(str, num2)
                d++
            }
            if (str[0] == '0' && str != "0") str = str.substring(1)
            if (i < len1 - 1) str += "${num1[i + 1]}"
            p += d.toString()
            if (p[0] == '0') p = p.substring(1)
        }
    }
    return Pair(p, str)
}