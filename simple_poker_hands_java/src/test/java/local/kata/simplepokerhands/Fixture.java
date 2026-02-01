package local.kata.simplepokerhands;

import java.util.List;
import local.kata.simplepokerhands.cards.Card;
import local.kata.simplepokerhands.cards.CardStrength;
import local.kata.simplepokerhands.cards.CardSuit;
import local.kata.simplepokerhands.poker.*;
import local.kata.simplepokerhands.poker.PokerHand;
import local.kata.simplepokerhands.tuples.Pair;
import local.kata.simplepokerhands.tuples.Quadruple;
import local.kata.simplepokerhands.tuples.Quintuple;
import local.kata.simplepokerhands.tuples.Triple;

public class Fixture {
    static final List<Card> fixtureHighCard = List.of(
            new Card(CardStrength.EIGHT, CardSuit.DIAMOND),
            new Card(CardStrength.THREE, CardSuit.HEART),
            new Card(CardStrength.NINE, CardSuit.SPADE),
            new Card(CardStrength.JACK, CardSuit.CLUB),
            new Card(CardStrength.TWO, CardSuit.DIAMOND));

    static final PokerHand fixtureHighCardResult = new HighCard(new Card(CardStrength.JACK, CardSuit.CLUB));

    static final List<Card> fixtureOnePair = List.of(
            new Card(CardStrength.THREE, CardSuit.SPADE),
            new Card(CardStrength.FIVE, CardSuit.DIAMOND),
            new Card(CardStrength.JACK, CardSuit.CLUB),
            new Card(CardStrength.THREE, CardSuit.DIAMOND),
            new Card(CardStrength.SEVEN, CardSuit.SPADE));

    static final PokerHand fixtureOnePairResult = new OnePair(
            new Pair<>(new Card(CardStrength.THREE, CardSuit.SPADE), new Card(CardStrength.THREE, CardSuit.DIAMOND)),
            new Card(CardStrength.JACK, CardSuit.CLUB));

    static final List<Card> fixtureTwoPair = List.of(
            new Card(CardStrength.THREE, CardSuit.SPADE),
            new Card(CardStrength.JACK, CardSuit.DIAMOND),
            new Card(CardStrength.JACK, CardSuit.CLUB),
            new Card(CardStrength.THREE, CardSuit.DIAMOND),
            new Card(CardStrength.SEVEN, CardSuit.SPADE));

    static final PokerHand fixtureTwoPairResult = new TwoPair(
            new Pair<>(new Card(CardStrength.JACK, CardSuit.DIAMOND), new Card(CardStrength.JACK, CardSuit.CLUB)),
            new Pair<>(new Card(CardStrength.THREE, CardSuit.SPADE), new Card(CardStrength.THREE, CardSuit.DIAMOND)),
            new Card(CardStrength.SEVEN, CardSuit.SPADE));

    static final List<Card> fixtureThreeOfAKind = List.of(
            new Card(CardStrength.JACK, CardSuit.SPADE),
            new Card(CardStrength.JACK, CardSuit.DIAMOND),
            new Card(CardStrength.JACK, CardSuit.CLUB),
            new Card(CardStrength.THREE, CardSuit.DIAMOND),
            new Card(CardStrength.SEVEN, CardSuit.SPADE));

    static final PokerHand fixtureThreeOfAKindResult = new ThreeOfAKind(
            new Triple<>(
                    new Card(CardStrength.JACK, CardSuit.SPADE),
                    new Card(CardStrength.JACK, CardSuit.DIAMOND),
                    new Card(CardStrength.JACK, CardSuit.CLUB)),
            new Card(CardStrength.SEVEN, CardSuit.SPADE));

    static final List<Card> fixtureStraight = List.of(
        new Card(CardStrength.SEVEN, CardSuit.SPADE),
        new Card(CardStrength.SIX, CardSuit.DIAMOND),
        new Card(CardStrength.FIVE, CardSuit.CLUB),
        new Card(CardStrength.THREE, CardSuit.DIAMOND),
        new Card(CardStrength.FOUR, CardSuit.SPADE));

    static final PokerHand fixtureStraightResult = new Straight(new Quintuple<>(
            new Card(CardStrength.THREE, CardSuit.DIAMOND),
            new Card(CardStrength.FOUR, CardSuit.SPADE),
            new Card(CardStrength.FIVE, CardSuit.CLUB),
            new Card(CardStrength.SIX, CardSuit.DIAMOND),
            new Card(CardStrength.SEVEN, CardSuit.SPADE)));

    static final List<Card> fixtureAceLowStraight = List.of(
        new Card(CardStrength.ACE, CardSuit.SPADE),
        new Card(CardStrength.THREE, CardSuit.DIAMOND),
        new Card(CardStrength.FOUR, CardSuit.CLUB),
        new Card(CardStrength.TWO, CardSuit.DIAMOND),
        new Card(CardStrength.FIVE, CardSuit.SPADE));

    static final PokerHand fixtureAceLowStraightResult = new Straight(new Quintuple<>(
        new Card(CardStrength.ACE, CardSuit.SPADE),
        new Card(CardStrength.TWO, CardSuit.DIAMOND),
        new Card(CardStrength.THREE, CardSuit.DIAMOND),
        new Card(CardStrength.FOUR, CardSuit.CLUB),
        new Card(CardStrength.FIVE, CardSuit.SPADE)));

    static final List<Card> fixtureFlush = List.of(
            new Card(CardStrength.SEVEN, CardSuit.SPADE),
            new Card(CardStrength.SIX, CardSuit.SPADE),
            new Card(CardStrength.FIVE, CardSuit.SPADE),
            new Card(CardStrength.KING, CardSuit.SPADE),
            new Card(CardStrength.FOUR, CardSuit.SPADE));

    static final PokerHand fixtureFlushResult = new Flush(new Quintuple<>(
            new Card(CardStrength.SEVEN, CardSuit.SPADE),
            new Card(CardStrength.SIX, CardSuit.SPADE),
            new Card(CardStrength.FIVE, CardSuit.SPADE),
            new Card(CardStrength.KING, CardSuit.SPADE),
            new Card(CardStrength.FOUR, CardSuit.SPADE)));

    static final List<Card> fixtureFullHouse = List.of(
            new Card(CardStrength.JACK, CardSuit.SPADE),
            new Card(CardStrength.JACK, CardSuit.DIAMOND),
            new Card(CardStrength.THREE, CardSuit.CLUB),
            new Card(CardStrength.THREE, CardSuit.DIAMOND),
            new Card(CardStrength.THREE, CardSuit.SPADE));

    static final PokerHand fixtureFullHouseResult = new FullHouse(
            new Triple<>(
                    new Card(CardStrength.THREE, CardSuit.CLUB),
                    new Card(CardStrength.THREE, CardSuit.DIAMOND),
                    new Card(CardStrength.THREE, CardSuit.SPADE)),
            new Pair<>(new Card(CardStrength.JACK, CardSuit.SPADE), new Card(CardStrength.JACK, CardSuit.DIAMOND)));

    static final List<Card> fixtureFourOfAKind = List.of(
            new Card(CardStrength.JACK, CardSuit.SPADE),
            new Card(CardStrength.THREE, CardSuit.HEART),
            new Card(CardStrength.THREE, CardSuit.CLUB),
            new Card(CardStrength.THREE, CardSuit.DIAMOND),
            new Card(CardStrength.THREE, CardSuit.SPADE));

    static final PokerHand fixtureFourOfAKindResult = new FourOfAKind(
            new Quadruple<>(
                    new Card(CardStrength.THREE, CardSuit.HEART),
                    new Card(CardStrength.THREE, CardSuit.CLUB),
                    new Card(CardStrength.THREE, CardSuit.DIAMOND),
                    new Card(CardStrength.THREE, CardSuit.SPADE)),
            new Card(CardStrength.JACK, CardSuit.SPADE));

    static final List<Card> fixtureStraightFlush = List.of(
            new Card(CardStrength.TEN, CardSuit.DIAMOND),
            new Card(CardStrength.JACK, CardSuit.DIAMOND),
            new Card(CardStrength.QUEEN, CardSuit.DIAMOND),
            new Card(CardStrength.KING, CardSuit.DIAMOND),
            new Card(CardStrength.ACE, CardSuit.DIAMOND));

    static final PokerHand fixtureStraightFlushResult = new StraightFlush(new Quintuple<>(
            new Card(CardStrength.TEN, CardSuit.DIAMOND),
            new Card(CardStrength.JACK, CardSuit.DIAMOND),
            new Card(CardStrength.QUEEN, CardSuit.DIAMOND),
            new Card(CardStrength.KING, CardSuit.DIAMOND),
            new Card(CardStrength.ACE, CardSuit.DIAMOND)));
}
