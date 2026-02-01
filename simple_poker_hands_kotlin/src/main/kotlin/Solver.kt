import PokerHand.Straight.Companion.aceLow

class Solver(
    private val cards: List<Card>,
) {
    fun solve(): Result<PokerHand> {
        val pokerHand =
            listOf(
                cards.findStraightFlush(),
                cards.findFourOfAKind(),
                cards.findFullHouse(),
                cards.findFlush(),
                cards.findStraight(),
                cards.findThreeOfAKind(),
                cards.findTwoPair(),
                cards.findOnePair(),
                cards.findHighCard(),
            ).firstNotNullOfOrNull { it.getOrNull() }

        if (pokerHand == null) {
            return Result.failure(Exception("Not solved"))
        }

        return Result.success(pokerHand)
    }
}

fun List<Card>.findHighCard(): Result<PokerHand.HighCard> {
    val card = this.maxByOrNull { it.strength } ?: return Result.failure(Exception("Not HighCard"))

    return Result.success(PokerHand.HighCard(card))
}

fun List<Card>.findOnePair(): Result<PokerHand.OnePair> {
    val pair = this.findSameCards(2).getOrNull() ?: return Result.failure(Exception("Not OnePair"))
    val kicker =
        this.filter { it.strength != pair.first().strength }.findHighCard().getOrNull() ?: return Result.failure(
            Exception("Not OnePair"),
        )

    return Result.success(
        PokerHand.OnePair(
            cards = Pair(pair[0], pair[1]),
            kicker = kicker.card,
        ),
    )
}

fun List<Card>.findTwoPair(): Result<PokerHand.TwoPair> {
    val firstPair = this.findOnePair().getOrNull() ?: return Result.failure(Exception("Not TwoPair"))
    val secondPair =
        this.filter { it.strength != firstPair.cards.first.strength }.findOnePair().getOrNull()
            ?: return Result.failure(Exception("Not TwoPair"))

    return Result.success(
        PokerHand.TwoPair(
            first = firstPair.cards,
            second = secondPair.cards,
            kicker = secondPair.kicker,
        ),
    )
}

fun List<Card>.findThreeOfAKind(): Result<PokerHand.ThreeOfAKind> {
    val threeOfAKind = this.findSameCards(3).getOrNull() ?: return Result.failure(Exception("Not ThreeOfAKind"))
    val kicker =
        this.filter { it.strength != threeOfAKind.first().strength }.findHighCard().getOrNull()
            ?: return Result.failure(Exception("Not ThreeOfAKind"))

    return Result.success(
        PokerHand.ThreeOfAKind(
            cards = Triple(threeOfAKind[0], threeOfAKind[1], threeOfAKind[2]),
            kicker = kicker.card,
        ),
    )
}

fun List<Card>.findStraight(): Result<PokerHand.Straight> {
    val sortedCards = this.sortedBy { it.strength }
    val strengths = sortedCards.map { it.strength }.toSet()

    if (strengths.size != 5) return Result.failure(Exception("Not Straight"))

    if (strengths.last().ordinal - strengths.first().ordinal == 4) {
        return Result.success(
            PokerHand.Straight(
                cards =
                    Quintuple(
                        sortedCards[0],
                        sortedCards[1],
                        sortedCards[2],
                        sortedCards[3],
                        sortedCards[4],
                    ),
            ),
        )
    }

    val aceLow = aceLow
    if (strengths == aceLow) {
        // For A-2-3-4-5, place the Ace before the Two so the Quintuple reflects low-to-high order
        return Result.success(
            PokerHand.Straight(
                cards =
                    Quintuple(
                        sortedCards[4],
                        sortedCards[0],
                        sortedCards[1],
                        sortedCards[2],
                        sortedCards[3],
                    ),
            ),
        )
    }

    return Result.failure(Exception("Not Straight"))
}

fun List<Card>.findFlush(): Result<PokerHand.Flush> {
    if (this.size != 5) return Result.failure(Exception("Not Flush"))
    val suit = this.getOrNull(0)?.suit ?: return Result.failure(Exception("Not Flush"))
    val suitedCards = this.filter { it.suit == suit }
    if (suitedCards.size != 5) return Result.failure(Exception("Not Flush"))

    return Result.success(
        PokerHand.Flush(
            Quintuple(
                suitedCards[0],
                suitedCards[1],
                suitedCards[2],
                suitedCards[3],
                suitedCards[4],
            ),
        ),
    )
}

fun List<Card>.findFullHouse(): Result<PokerHand.FullHouse> {
    val counts = this.groupBy { it.strength }
    val triples =
        counts.filter { it.value.size == 3 }.values.firstOrNull()
            ?: return Result.failure(Exception("Not FullHouse"))
    val pairs =
        counts.filter { it.value.size == 2 }.values.firstOrNull()
            ?: return Result.failure(Exception("Not FullHouse"))

    return Result.success(
        PokerHand.FullHouse(
            first = Triple(triples[0], triples[1], triples[2]),
            second = Pair(pairs[0], pairs[1]),
        ),
    )
}

fun List<Card>.findFourOfAKind(): Result<PokerHand.FourOfAKind> {
    val fourOfAKind = this.findSameCards(4).getOrNull() ?: return Result.failure(Exception("Not FourOfAKind"))
    val kicker =
        this.filter { it.strength != fourOfAKind[0].strength }.findHighCard().getOrNull()
            ?: return Result.failure(Exception("Not FourOfAKind"))

    return Result.success(
        PokerHand.FourOfAKind(
            cards = Quadruple(fourOfAKind[0], fourOfAKind[1], fourOfAKind[2], fourOfAKind[3]),
            kicker = kicker.card,
        ),
    )
}

fun List<Card>.findStraightFlush(): Result<PokerHand.StraightFlush> {
    val straight = this.findStraight().getOrNull() ?: return Result.failure(Exception("Not StraightFlush"))
    this.findFlush().getOrNull() ?: return Result.failure(Exception("Not StraightFlush"))

    return Result.success(
        PokerHand.StraightFlush(
            cards = straight.cards,
        ),
    )
}

private fun List<Card>.findSameCards(howMany: Int): Result<List<Card>> {
    val groups = this.groupBy { it.strength }
    val matchingStrengths = groups.filter { it.value.size == howMany }.keys.sortedDescending()

    if (matchingStrengths.isEmpty()) {
        return Result.failure(Exception("Not found $howMany same cards"))
    }

    return Result.success(groups[matchingStrengths.first()]!!)
}
