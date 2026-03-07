package local.kata.simplepokerhands;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import local.kata.simplepokerhands.cards.Card;
import local.kata.simplepokerhands.poker.PokerHand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SolverJunitTest {
    private static Stream<Arguments> provideFindFunction() {
        return Stream.of(
                Arguments.of(Solver.findHighCard(Fixture.fixtureHighCard).orElse(null), Fixture.fixtureHighCardResult),
                Arguments.of(Solver.findOnePair(Fixture.fixtureOnePair).orElse(null), Fixture.fixtureOnePairResult),
                Arguments.of(Solver.findTwoPair(Fixture.fixtureTwoPair).orElse(null), Fixture.fixtureTwoPairResult),
                Arguments.of(
                        Solver.findThreeOfAKind(Fixture.fixtureThreeOfAKind).orElse(null),
                        Fixture.fixtureThreeOfAKindResult),
                Arguments.of(Solver.findStraight(Fixture.fixtureStraight).orElse(null), Fixture.fixtureStraightResult),
                Arguments.of(Solver.findFlush(Fixture.fixtureFlush).orElse(null), Fixture.fixtureFlushResult),
                Arguments.of(
                        Solver.findFullHouse(Fixture.fixtureFullHouse).orElse(null), Fixture.fixtureFullHouseResult),
                Arguments.of(
                        Solver.findFourOfAKind(Fixture.fixtureFourOfAKind).orElse(null),
                        Fixture.fixtureFourOfAKindResult),
                Arguments.of(
                        Solver.findStraightFlush(Fixture.fixtureStraightFlush).orElse(null),
                        Fixture.fixtureStraightFlushResult));
    }

    private static Stream<Arguments> provideFixture() {
        return Stream.of(
                Arguments.of(Fixture.fixtureHighCard, Fixture.fixtureHighCardResult),
                Arguments.of(Fixture.fixtureOnePair, Fixture.fixtureOnePairResult),
                Arguments.of(Fixture.fixtureTwoPair, Fixture.fixtureTwoPairResult),
                Arguments.of(Fixture.fixtureThreeOfAKind, Fixture.fixtureThreeOfAKindResult),
                Arguments.of(Fixture.fixtureStraight, Fixture.fixtureStraightResult),
                Arguments.of(Fixture.fixtureFlush, Fixture.fixtureFlushResult),
                Arguments.of(Fixture.fixtureFullHouse, Fixture.fixtureFullHouseResult),
                Arguments.of(Fixture.fixtureFourOfAKind, Fixture.fixtureFourOfAKindResult),
                Arguments.of(Fixture.fixtureStraightFlush, Fixture.fixtureStraightFlushResult));
    }

    @ParameterizedTest
    @MethodSource("provideFindFunction")
    public void testFindFunction(PokerHand result, PokerHand expected) {
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideFixture")
    public void testSolver(List<Card> cards, PokerHand expected) {
        Solver solver = new Solver(cards);
        Optional<? extends PokerHand> result = solver.solve();

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(expected, result.orElse(null));
    }
}
