import kotlin.collections.map

typealias Kicker = Card

sealed class PokerHand {
    data class HighCard(
        val card: Card,
    ) : PokerHand()

    data class OnePair(
        val cards: Pair<Card, Card>,
        val kicker: Kicker,
    ) : PokerHand()

    data class TwoPair(
        val first: Pair<Card, Card>,
        val second: Pair<Card, Card>,
        val kicker: Kicker,
    ) : PokerHand()

    data class ThreeOfAKind(
        val cards: Triple<Card, Card, Card>,
        val kicker: Kicker,
    ) : PokerHand()

    data class Straight(
        val cards: Quintuple<Card, Card, Card, Card, Card>,
    ) : PokerHand() {
        init {
            val cardsList = cards.toList().sortedBy { it.strength.ordinal }
            require(cardsList.zipWithNext().all { (a, b) -> b.strength.ordinal == a.strength.ordinal + 1 })
        }
    }

    data class Flush(
        val cards: Quintuple<Card, Card, Card, Card, Card>,
    ) : PokerHand() {
        init {
            require(
                cards
                    .toList()
                    .map { it.suit }
                    .distinct()
                    .size == 1,
            )
        }
    }

    data class FullHouse(
        val first: Triple<Card, Card, Card>,
        val second: Pair<Card, Card>,
    ) : PokerHand()

    data class FourOfAKind(
        val cards: Quadruple<Card, Card, Card, Card>,
        val kicker: Kicker,
    ) : PokerHand()

    data class StraightFlush(
        val cards: Quintuple<Card, Card, Card, Card, Card>,
    ) : PokerHand()
}
