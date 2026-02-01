import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Test {
    @Test
    fun testFindFunctions() {
        val cases =
            listOf(
                Pair(fixtureHighCard.findHighCard(), fixtureHighCardResult),
                Pair(fixtureOnePair.findOnePair(), fixtureOnePairResult),
                Pair(fixtureTwoPair.findTwoPair(), fixtureTwoPairResult),
                Pair(fixtureThreeOfAKind.findThreeOfAKind(), fixtureThreeOfAKindResult),
                Pair(fixtureStraight.findStraight(), fixtureStraightResult),
                Pair(fixtureFlush.findFlush(), fixtureFlushResult),
                Pair(fixtureFullHouse.findFullHouse(), fixtureFullHouseResult),
                Pair(fixtureFourOfAKind.findFourOfAKind(), fixtureFourOfAKindResult),
                Pair(fixtureStraightFlush.findStraightFlush(), fixtureStraightFlushResult),
            )

        for ((result, expected) in cases) {
            assertTrue(result.isSuccess)
            assertEquals(expected, result.getOrNull())
        }
    }

    @Test
    fun testSolverEndToEnd() {
        val cases =
            listOf(
                Pair(fixtureHighCard, fixtureHighCardResult),
                Pair(fixtureOnePair, fixtureOnePairResult),
                Pair(fixtureTwoPair, fixtureTwoPairResult),
                Pair(fixtureThreeOfAKind, fixtureThreeOfAKindResult),
                Pair(fixtureStraight, fixtureStraightResult),
                Pair(fixtureFlush, fixtureFlushResult),
                Pair(fixtureFullHouse, fixtureFullHouseResult),
                Pair(fixtureFourOfAKind, fixtureFourOfAKindResult),
                Pair(fixtureStraightFlush, fixtureStraightFlushResult),
            )

        for ((fixture, expected) in cases) {
            val solver = Solver(cards = fixture)
            val result = solver.solve()

            assertTrue(result.isSuccess)
            assertEquals(expected, result.getOrNull())
        }
    }
}
