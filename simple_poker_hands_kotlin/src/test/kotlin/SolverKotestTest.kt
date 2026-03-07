import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SolverKotestTest :
    StringSpec({
        "testFindFunction" {
            table(
                headers("actual", "result"),
                row(fixtureHighCard.findHighCard(), fixtureHighCardResult),
                row(fixtureOnePair.findOnePair(), fixtureOnePairResult),
                row(fixtureTwoPair.findTwoPair(), fixtureTwoPairResult),
                row(fixtureThreeOfAKind.findThreeOfAKind(), fixtureThreeOfAKindResult),
                row(fixtureStraight.findStraight(), fixtureStraightResult),
                row(fixtureFlush.findFlush(), fixtureFlushResult),
                row(fixtureFullHouse.findFullHouse(), fixtureFullHouseResult),
                row(fixtureFourOfAKind.findFourOfAKind(), fixtureFourOfAKindResult),
                row(fixtureStraightFlush.findStraightFlush(), fixtureStraightFlushResult),
            ).forAll { hand, expected ->
                hand.isSuccess.shouldBeTrue()
                hand.getOrNull() shouldBe expected
            }
        }

        "testSolver" {
            table(
                headers("actual", "result"),
                row(fixtureHighCard, fixtureHighCardResult),
                row(fixtureOnePair, fixtureOnePairResult),
                row(fixtureTwoPair, fixtureTwoPairResult),
                row(fixtureThreeOfAKind, fixtureThreeOfAKindResult),
                row(fixtureStraight, fixtureStraightResult),
                row(fixtureFlush, fixtureFlushResult),
                row(fixtureFullHouse, fixtureFullHouseResult),
                row(fixtureFourOfAKind, fixtureFourOfAKindResult),
                row(fixtureStraightFlush, fixtureStraightFlushResult),
            ).forAll { cards, expected ->
                val solver = Solver(cards = cards)
                val result = solver.solve()

                result.isSuccess.shouldBeTrue()
                result.getOrNull() shouldBe expected
            }
        }
    })
