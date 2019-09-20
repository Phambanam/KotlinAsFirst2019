@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt
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
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
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
fun invertPositives(list: MutableList<Int>) {
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
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

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
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs : Double = 0.0
    for(i :Int in 0 until v.size){
        abs += v[i]*v[i]
    }
    return sqrt(abs)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var medium : Double = 0.0

    if(list.size == 0) return 0.0
    for(i :Int in 0 until list.size ){
     medium += list[i]
    }
    return medium/list.size
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
    var Cp : Double = 0.0
    for(i: Int in 0 until list.size){
        Cp += list[i]
    }
    for(i: Int in 0 until list.size){
        list[i]-= Cp/list.size
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
fun times(a: List<Int>, b: List<Int>): Int {
    if(a.size ==0 && b.size == 0) return 0
    var c: Int =0
    for (i:Int in 0 until a.size){
        c += a[i]*b[i]
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


fun polynom(p: List<Int>, x: Int): Int {
    var t : Int = 0
    var y : Int = x
    if(p.size == 0) return 0

    for(i:Int in 0 until p.size){
        var a:Int = pow(y.toDouble(),i.toDouble()).toInt()
        t = t + p[i]*a

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
fun accumulate(list: MutableList<Int>): MutableList<Int> {
   for(i : Int in 1 until list.size){
       list[i]+=list[i-1]

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
fun Prime(n :Int): Boolean {
    var a : Int = n
    if( a < 2)return false
    if( a==2 && a==3 ) return true
    if(a>3){
        for (i:Int in 2 .. sqrt(a.toDouble()).toInt())
            if (a%i==0 ){
                return false
                break
    }
    }

        return true

}
fun factorize(n: Int): List<Int> {
    var a = mutableListOf<Int>()
    var b :Int = n
    if(Prime(b)) {
        a.add(b)
        return a
    }
    for(i in 2 .. b/2) {
        while (b %i==0) {
            if (Prime(i) == true && b % i == 0) {
                a.add(i)
                b = b / i
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
fun factorizeToString(n: Int): String1 {
    var a : String1 =""
    var b :Int = n
    if(Prime(b)) {
        a=a.plus("$b")
        return a
    }
    for(i in 2 .. b/2) {
        while (b %i==0) {
            if (Prime(i) == true && b % i == 0) {
                a=a.plus("*$i")
                b = b / i
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
fun convert(n: Int, base: Int): List<Int> {
    var a: Int = n
    var t : Int = base
    val x: MutableList<Int> = mutableListOf<Int>()
    val y: MutableList<Int> = mutableListOf<Int>(0)
    val r = mutableListOf<Int>()
    var count : Int = 0
    if(a==0) return y
    while(a>0){
        x.add( a%t)
        a=a/t
        count+=1

    }

        for (j: Int in (x.size - 1) downTo  0){
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
fun convertToString(n: Int, base: Int): String1 {
    var a: Int = n
    var t : Int = base
    var N = mutableListOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")
    val x = mutableListOf<String1>()
    var r: String1 =""
    val y: MutableList<String1> = mutableListOf<String1>("0")
    var count : Int = 0
    //Tim cac phan tu cua list
   if(n==0) return y[0]
    while(a>0){
        x.add( (a%t).toString())
        a=a/t
        count+=1

    }

     for (j: Int in (x.size - 1) downTo  0){
        if(x[j].toInt() >= 10) {
            x[j]=N[x[j].toInt()-10]
        }
        r=r.plus(x[j])

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
fun decimal(digits: List<Int>, base: Int): Int {
    val x  =digits
    var n :Int = base
     var s: Int = 0
    var k: Double = 0.0
    for(i :Int in  x.size-1 downTo 0){
      k = n.toDouble().pow(x.size-1-i.toDouble())

        s+= x[i]*(k.toInt())

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
fun decimalFromString(str: String1, base: Int): Int {
    var T = str
    var N = mutableListOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")
    val x = mutableListOf<String1>()
    // chuyen chuoi t sang list
    for(i:Int in 0 until str.length){
        x.add(str[i].toString())
    }
    var n :Int = base
    var s: Int = 0
    var k: Double = 0.0
    // chuyen chu sang so
    for(i:Int in 0 until x.size)
        for(j:Int in 0 until N.size)
        {
        if(x[i]==N[j]) x[i]=(j+10).toString()
        }
    for(i :Int in  x.size-1 downTo 0){
        k = n.toDouble().pow(x.size-1-i.toDouble())
        s=s+ x[i].toInt()*(k.toInt())
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
fun roman(n: Int): String1
{
    var m : Int = n
    var s : String1  =""
    for(i:Int in 1 .. m/1000) s=s+"M"
    m=m%1000
    if(m>=900){
        s += "CM"
        m = m-900
    }
    if(m>=500){
        s+="D"
        m = m-500
    }

    if(m>= 400){
        s+="CD"
        m = m - 400
    }
    if(m>=100){
        for(i:Int in 1 ..m/100){
            s+="C"
        }
        m=m%100
    }
    if(m>=90){
        s+="XC"
        m=m-90
    }
    if(m>=50){
        s+="L"
        m=m-50
    }
    if(m>=40){
        s+="XL"
        m=m-40
    }
    if(m>=10){
        for(i:Int in 1 .. m/10){
            s+="X"
        }
        m=m%10
    }
    if (m>=9){
        s+="IX"
        m=m-9
    }
    if(m>=5){
        s+="V"
        m=m-5
    }
    if(m>=4){
        s+="IV"
        m=m-4
    }
    if(m>=1){
        for(i:Int in 1..m){
            s=s+"I"
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
fun russian(n: Int): String1 {
    var m:Int = n
    var k:Int = n
    var s: String1 = ""
    var count :Int = 0
     while(m>0){
         m=m/10
         count++
     }
    var A = mutableListOf("один","два","три","четыре","пять","шесть","семь","восемь","девять","десять","одиннадцать","двенадцать","тринадцать","четырнадцать","пятнадцать","шестнадцать","семнадцать","восемнадцать","девятнадцать","двадцать","тридцать","сорок","пятьдесят","шестьдесят","семьдесят","восемьдесят","девяносто","сто","двести","триста","четыреста","пятьсот","шестьсот","семьсот","восемьсот","девятьсот","тысяча","тысячи","тысяч")
    // neu n co 1 chu so
    if(k>1&& k<=20) return A[k-1]
    // neu n co 2 chu so > 20
    if(count==2 && k >20 ){
        s=s+A[(k/10)+17]+" "+A[k%10-1]
        return s
    }
    // Neu n co 3 chu so
    if(count==3){
        s=s+A[(k/100)+26]
        k=k%100
       if(k>10) s=s+" "+A[(k/10)+17]+" "+A[k%10-1]
        if(k>1&& k<=20) return A[k-1]

         return s
    }
    // neu n co 4 chu so
    if(count==4){
        var q:Int
        q = k/1000
        if(q==1) s=s+A[36]
        if(q==2) s=s+"две"+" "+A[37]
        if(q==3 || q==4) s=s+A[q-1]+" "+A[37]
        if(q>4)s=s+A[q-1]+" "+A[38]
        k=k%1000
        if(k>=100)s=s+" "+A[(k/100)+26]
        k=k%100
        if(k>=10)s=s+" "+A[(k/10)+17]+" "+A[k%10-1]
        if(k>1&& k<=20) s+=" "+A[k-1]
        return s

    }
    // neu n co 5 chu so
    if(count==5){
        var q:Int
        q=k/1000
        if((q%10==1 && q>11))s=s+A[(q/10)+17]+" "+"одна"+" "+" "+A[36]
        if(q%10>2 && q%10<=4 && q>20) s=s+A[(q/10)+17]+" "+A[q%10-1]+" "+A[37]
        if(q%10==2 && q%10<=4 && q>20) s=s+A[(q/10)+17]+" "+"две"+" "+A[37]
        if(q>9&&q<21)s=s+A[q-1]+" "+A[38]
        if(q>20 && q%10 >4) s=s+A[(q/10)+17]+" "+A[q%10-1]+" "+A[38]
        k=k%1000
        if(k>=100)s=s+" "+A[(k/100)+26]
        k=k%100
        if(k>=10)s=s+" "+A[(k/10)+17]+" "+A[k%10-1]
        if(k>1&& k<=20)  s+=" "+ A[k-1]
        return s

    }
    // voi n co 6 chu so
     if(count==6){
            var q:Int
            var p :Int
                   p = k/1000
                  if(p%100==0) s+=A[(p/100)+26]+" "+A[38]
                  else s+=A[(p/100)+26]
                   q=p%100
            if(q==1) s+=" " + "одна"+" "+" "+A[36]
            if(q%10==1 && q>11)s=s+" "+A[(q/10)+17]+" "+"одна"+" "+" "+A[36]
            if(q%10>2 && q%10<=4 && q>20) s=s+" "+A[(q/10)+17]+" "+A[q%10-1]+" "+A[37]
            if(q%10==2 && q%10<=4 && q>20) s=s+" "+A[(q/10)+17]+" "+"две"+" "+A[37]
            if(q>9&&q<21)s=s+" "+A[q-1]+" "+A[38]
            if(q>20 && q%10 >4) s=s+" "+A[(q/10)+17]+" "+A[q%10-1]+" "+A[38]
            k=k%1000
            if(k>=100)s=s+" "+A[(k/100)+26]
            k=k%100
            if(k>=10)s=s+" "+A[(k/10)+17]+" "+A[k%10-1]
            if(k>1&& k<=20)  s+=" "+ A[k-1]
            return s

        }
    return s
}

