package kupco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Impossible")
    fun Sumbigs() {
        assertEquals(
            "1089",
            Sumbigs("99", "990")
        )
    }

    @Test
    @Tag("Impossible")
    fun Diff() {
        assertEquals(
            "89",
            diff("100", "11")
        )
    }

    @Test
    @Tag("Impossible")
    fun Productbig() {
        assertEquals(
            "1089",
            Productbig("11", "99")
        )
    }

    @Test
    @Tag("Impossible")
    fun division() {
        assertEquals(
            Pair("909", "0"),
            division("9999", "11")
        )
        assertEquals(
            Pair("0", "10"),
            division("10", "11")
        )
        assertEquals(
            Pair("100", "5"),
            division("1005", "10")
        )
        assertEquals(
            Pair("100", "5"),
            division("10000000000000000000000000000000000005", "100000000000000000000000000000000000")
        )
    }
}

