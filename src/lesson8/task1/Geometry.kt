@file:Suppress("UNUSED_PARAMETER")

package lesson8.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = sqrt(sqr(x - other.x) + sqr(y - other.y))
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Triangle private constructor(private val points: Set<Point>) {

    private val pointList = points.toList()

    val a: Point get() = pointList[0]

    val b: Point get() = pointList[1]

    val c: Point get() = pointList[2]

    constructor(a: Point, b: Point, c: Point) : this(linkedSetOf(a, b, c))

    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }

    override fun equals(other: Any?) = other is Triangle && points == other.points

    override fun hashCode() = points.hashCode()

    override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double =
        maxOf(sqrt(sqr(other.center.x - center.x) + sqr(other.center.y - center.y)) - radius - other.radius, 0.0)

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = sqrt(sqr(p.x - center.x) + sqr(p.y - center.y)) - radius < 0.0
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    override fun equals(other: Any?) =
        other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    override fun hashCode() =
        begin.hashCode() + end.hashCode()
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    var b = Segment(Point(0.0, 0.0), Point(0.0, 0.0))
    require(points.size >= 2)
    var max = 0.0
    for (i in points.indices)
        for (j in i + 1 until points.size) {
            val k = points[j].distance(points[i])
            if (k > max) {
                max = k
                b = Segment(points[i], points[j])
            }
        }
    return b
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val r = diameter.begin.distance(diameter.end) / 2
    val o = Point(
        (diameter.begin.x + diameter.end.x) / 2,
        (diameter.begin.y + diameter.end.y) / 2
    )
    return Circle(o, r)
}

/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 */
class Line private constructor(val b: Double, val angle: Double) {
    init {
        require(angle >= 0 && angle < PI) { "Incorrect line angle: $angle" }
    }

    constructor(point: Point, angle: Double) : this(point.y * cos(angle) - point.x * sin(angle), angle)

    /**
     * Средняя
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */
    fun crossPoint(other: Line): Point {
        val x = (other.b * cos(angle) - b * cos(other.angle)) / sin(angle - other.angle)
        val y = (x * sin(other.angle) + other.b) / cos(other.angle)
        return Point(x, y)
    }

    override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

    override fun hashCode(): Int {
        var result = b.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

    override fun toString() = "Line(${cos(angle)} * y = ${sin(angle)} * x + $b)"
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    var angle = 0.0
    if (s.begin.y == s.end.y) angle = 0.0
    if (s.begin.x == s.end.x) angle = PI / 2
    if (s.begin.y != s.end.y && s.begin.x != s.end.x) angle = atan((s.begin.y - s.end.y) / (s.begin.x - s.end.x))
    return if (angle < 0) Line(s.begin, angle + PI)
    else Line(s.begin, angle)
}

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line = lineBySegment(Segment(a, b))

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val line = lineByPoints(a, b)
    val point = Point((a.x + b.x) / 2, (a.y + b.y) / 2)
    return if (line.angle < PI / 2) Line(point, line.angle + PI / 2)
    else Line(point, line.angle - PI / 2)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    require(circles.size >= 2)
    var min = circles[0].distance(circles[1])
    var pair = Pair(circles[0], circles[1])
    for (i in circles.indices)
        for (j in i + 1 until circles.size)
            if (circles[i].distance(circles[j]) < min) {
                min = circles[i].distance(circles[j])
                pair = Pair(circles[i], circles[j])
            }
    return pair
}

/**
 * Сложная
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
    val line1 = bisectorByPoints(a, b)
    val line2 = bisectorByPoints(b, c)
    val o = line1.crossPoint(line2)
    return Circle(o, o.distance(a))
}

/**
 * Очень сложная
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */


fun minContainingCircle(vararg points: Point): Circle {
    require(points.isNotEmpty())
    var c = Circle(Point(0.0, 0.0), 0.0)
    if (points.size == 1) return Circle(points[0], 0.0)
    if (points.size == 2) return Circle(
        Point((points[0].x + points[1].x) / 2, (points[0].y + points[1].y) / 2),
        points[0].distance(points[1]) / 2
    )
    for (i in points.indices)
        for (j in i + 1 until points.size)
            for (k in j + 1 until points.size)
                if (circleByThreePoints(points[i], points[j], points[k]).radius < points.map {
                        it.distance(
                            circleByThreePoints(points[i], points[j], points[k]).center
                        )
                    }.min()!!) {
                    c = circleByThreePoints(points[i], points[j], points[k])
                }
    var b = Circle(Point(0.0, 0.0), 0.0)
    for (i in points.indices)
        for (j in i + 1 until points.size) {
            if (Circle(
                    Point((points[i].x + points[j].x) / 2, (points[i].y + points[j].y) / 2),
                    points[i].distance(points[j]) / 2
                ).radius < points.map {
                    it.distance(Point((points[i].x + points[j].x) / 2, (points[i].y + points[j].y) / 2))
                }.min()!!
            ) {
                b = Circle(
                    Point((points[i].x + points[j].x) / 2, (points[i].y + points[j].y) / 2),
                    points[i].distance(points[j]) / 2
                )
            }
        }
    return if (b.radius > c.radius) c else b
}

