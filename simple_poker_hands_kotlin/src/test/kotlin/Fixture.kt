val fixtureHighCard =
    listOf(
        Card(strength = CardStrength.EIGHT, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.THREE, suit = CardSuit.HEART),
        Card(strength = CardStrength.NINE, suit = CardSuit.SPADE),
        Card(strength = CardStrength.JACK, suit = CardSuit.CLUB),
        Card(strength = CardStrength.TWO, suit = CardSuit.DIAMOND),
    )

val fixtureHighCardResult =
    PokerHand.HighCard(
        card = Card(strength = CardStrength.JACK, suit = CardSuit.CLUB),
    )

val fixtureOnePair =
    listOf(
        Card(strength = CardStrength.THREE, suit = CardSuit.SPADE),
        Card(strength = CardStrength.FIVE, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.JACK, suit = CardSuit.CLUB),
        Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.SEVEN, suit = CardSuit.SPADE),
    )

val fixtureOnePairResult =
    PokerHand.OnePair(
        cards =
            Pair(
                Card(strength = CardStrength.THREE, suit = CardSuit.SPADE),
                Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
            ),
        kicker =
            Card(
                strength = CardStrength.JACK,
                suit = CardSuit.CLUB,
            ),
    )

val fixtureTwoPair =
    listOf(
        Card(strength = CardStrength.THREE, suit = CardSuit.SPADE),
        Card(strength = CardStrength.JACK, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.JACK, suit = CardSuit.CLUB),
        Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.SEVEN, suit = CardSuit.SPADE),
    )

val fixtureTwoPairResult =
    PokerHand.TwoPair(
        first =
            Pair(
                Card(strength = CardStrength.JACK, suit = CardSuit.DIAMOND),
                Card(strength = CardStrength.JACK, suit = CardSuit.CLUB),
            ),
        second =
            Pair(
                Card(strength = CardStrength.THREE, suit = CardSuit.SPADE),
                Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
            ),
        kicker = Card(strength = CardStrength.SEVEN, suit = CardSuit.SPADE),
    )

val fixtureThreeOfAKind =
    listOf(
        Card(strength = CardStrength.JACK, suit = CardSuit.SPADE),
        Card(strength = CardStrength.JACK, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.JACK, suit = CardSuit.CLUB),
        Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.SEVEN, suit = CardSuit.SPADE),
    )

val fixtureThreeOfAKindResult =
    PokerHand.ThreeOfAKind(
        cards =
            Triple(
                Card(strength = CardStrength.JACK, suit = CardSuit.SPADE),
                Card(strength = CardStrength.JACK, suit = CardSuit.DIAMOND),
                Card(strength = CardStrength.JACK, suit = CardSuit.CLUB),
            ),
        kicker = Card(strength = CardStrength.SEVEN, suit = CardSuit.SPADE),
    )

val fixtureStraight =
    listOf(
        Card(strength = CardStrength.SEVEN, suit = CardSuit.SPADE),
        Card(strength = CardStrength.SIX, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.FIVE, suit = CardSuit.CLUB),
        Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.FOUR, suit = CardSuit.SPADE),
    )

val fixtureStraightResult =
    PokerHand.Straight(
        cards =
            Quintuple(
                Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
                Card(strength = CardStrength.FOUR, suit = CardSuit.SPADE),
                Card(strength = CardStrength.FIVE, suit = CardSuit.CLUB),
                Card(strength = CardStrength.SIX, suit = CardSuit.DIAMOND),
                Card(strength = CardStrength.SEVEN, suit = CardSuit.SPADE),
            ),
    )

val fixtureFlush =
    listOf(
        Card(strength = CardStrength.SEVEN, suit = CardSuit.SPADE),
        Card(strength = CardStrength.SIX, suit = CardSuit.SPADE),
        Card(strength = CardStrength.FIVE, suit = CardSuit.SPADE),
        Card(strength = CardStrength.KING, suit = CardSuit.SPADE),
        Card(strength = CardStrength.FOUR, suit = CardSuit.SPADE),
    )

val fixtureFlushResult =
    PokerHand.Flush(
        cards =
            Quintuple(
                Card(strength = CardStrength.SEVEN, suit = CardSuit.SPADE),
                Card(strength = CardStrength.SIX, suit = CardSuit.SPADE),
                Card(strength = CardStrength.FIVE, suit = CardSuit.SPADE),
                Card(strength = CardStrength.KING, suit = CardSuit.SPADE),
                Card(strength = CardStrength.FOUR, suit = CardSuit.SPADE),
            ),
    )

val fixtureFullHouse =
    listOf(
        Card(strength = CardStrength.JACK, suit = CardSuit.SPADE),
        Card(strength = CardStrength.JACK, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.THREE, suit = CardSuit.CLUB),
        Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.THREE, suit = CardSuit.SPADE),
    )

val fixtureFullHouseResult =
    PokerHand.FullHouse(
        first =
            Triple(
                Card(strength = CardStrength.THREE, suit = CardSuit.CLUB),
                Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
                Card(strength = CardStrength.THREE, suit = CardSuit.SPADE),
            ),
        second =
            Pair(
                Card(strength = CardStrength.JACK, suit = CardSuit.SPADE),
                Card(strength = CardStrength.JACK, suit = CardSuit.DIAMOND),
            ),
    )

val fixtureFourOfAKind =
    listOf(
        Card(strength = CardStrength.JACK, suit = CardSuit.SPADE),
        Card(strength = CardStrength.THREE, suit = CardSuit.HEART),
        Card(strength = CardStrength.THREE, suit = CardSuit.CLUB),
        Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.THREE, suit = CardSuit.SPADE),
    )

val fixtureFourOfAKindResult =
    PokerHand.FourOfAKind(
        cards =
            Quadruple(
                Card(strength = CardStrength.THREE, suit = CardSuit.HEART),
                Card(strength = CardStrength.THREE, suit = CardSuit.CLUB),
                Card(strength = CardStrength.THREE, suit = CardSuit.DIAMOND),
                Card(strength = CardStrength.THREE, suit = CardSuit.SPADE),
            ),
        kicker = Card(strength = CardStrength.JACK, suit = CardSuit.SPADE),
    )

val fixtureStraightFlush =
    listOf(
        Card(strength = CardStrength.TEN, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.JACK, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.QUEEN, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.KING, suit = CardSuit.DIAMOND),
        Card(strength = CardStrength.ACE, suit = CardSuit.DIAMOND),
    )

val fixtureStraightFlushResult =
    PokerHand.StraightFlush(
        cards =
            Quintuple(
                Card(strength = CardStrength.TEN, suit = CardSuit.DIAMOND),
                Card(strength = CardStrength.JACK, suit = CardSuit.DIAMOND),
                Card(strength = CardStrength.QUEEN, suit = CardSuit.DIAMOND),
                Card(strength = CardStrength.KING, suit = CardSuit.DIAMOND),
                Card(strength = CardStrength.ACE, suit = CardSuit.DIAMOND),
            ),
    )
