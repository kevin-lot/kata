package local.kata.simplepokerhands;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import local.kata.simplepokerhands.cards.Card;
import local.kata.simplepokerhands.cards.CardStrength;
import local.kata.simplepokerhands.cards.CardSuit;
import local.kata.simplepokerhands.poker.*;
import local.kata.simplepokerhands.tuples.Pair;
import local.kata.simplepokerhands.tuples.Quadruple;
import local.kata.simplepokerhands.tuples.Quintuple;
import local.kata.simplepokerhands.tuples.Triple;

public final class Solver {
    private final List<Card> cards;

    public Solver(List<Card> cards) {
        this.cards = cards;
    }

    public Optional<? extends PokerHand> solve() {
        return Stream.<Optional<? extends PokerHand>>of(
                        findStraightFlush(cards),
                        findFourOfAKind(cards),
                        findFullHouse(cards),
                        findFlush(cards),
                        findStraight(cards),
                        findThreeOfAKind(cards),
                        findTwoPair(cards),
                        findOnePair(cards),
                        findHighCard(cards))
                .flatMap(Optional::stream)
                .findFirst();
    }

    public static Optional<HighCard> findHighCard(List<Card> cards) {
        return cards.stream()
                .max(Comparator.comparingInt(item -> item.strength().ordinal()))
                .map(HighCard::new);
    }

    public static Optional<OnePair> findOnePair(List<Card> cards) {
        Optional<List<Card>> pairOpt = findSameCards(cards, 2);
        if (pairOpt.isEmpty()) return Optional.empty();
        List<Card> pair = pairOpt.get();

        List<Card> list = cards.stream()
                .filter(card -> card.strength() != pair.getFirst().strength())
                .toList();
        Optional<HighCard> kickerOpt = findHighCard(list);
        if (kickerOpt.isEmpty()) return Optional.empty();
        HighCard kicker = kickerOpt.get();

        return Optional.of(new OnePair(new Pair<>(pair.get(0), pair.get(1)), kicker.card));
    }

    public static Optional<TwoPair> findTwoPair(List<Card> cards) {
        Optional<OnePair> firstOpt = findOnePair(cards);
        if (firstOpt.isEmpty()) return Optional.empty();
        OnePair first = firstOpt.get();

        List<Card> list = cards.stream()
                .filter(card -> card.strength() != first.cards.first().strength())
                .toList();
        Optional<OnePair> secondOpt = findOnePair(list);
        if (secondOpt.isEmpty()) return Optional.empty();
        OnePair second = secondOpt.get();

        return Optional.of(new TwoPair(first.cards, second.cards, second.kicker));
    }

    public static Optional<ThreeOfAKind> findThreeOfAKind(List<Card> cards) {
        Optional<List<Card>> tripleOpt = findSameCards(cards, 3);
        if (tripleOpt.isEmpty()) return Optional.empty();
        List<Card> triple = tripleOpt.get();

        List<Card> list = cards.stream()
                .filter(card -> card.strength() != triple.getFirst().strength())
                .toList();
        Optional<HighCard> kickerOpt = findHighCard(list);
        if (kickerOpt.isEmpty()) return Optional.empty();
        HighCard kicker = kickerOpt.get();

        return Optional.of(new ThreeOfAKind(new Triple<>(triple.get(0), triple.get(1), triple.get(2)), kicker.card));
    }

    public static Optional<Straight> findStraight(List<Card> cards) {
        List<Card> sortedCards = cards.stream()
                .sorted(Comparator.comparingInt(card -> card.strength().ordinal()))
                .toList();
        List<CardStrength> strengths = sortedCards.stream().map(Card::strength).toList();
        if (strengths.stream().distinct().count() != 5) return Optional.empty();
        if (strengths.get(4).ordinal() - strengths.get(0).ordinal() != 4) return Optional.empty();

        return Optional.of(new Straight(new Quintuple<>(
                sortedCards.get(0), sortedCards.get(1), sortedCards.get(2), sortedCards.get(3), sortedCards.get(4))));
    }

    public static Optional<Flush> findFlush(List<Card> cards) {
        if (cards.size() != 5) return Optional.empty();
        CardSuit suit = cards.getFirst().suit();
        List<Card> suited = cards.stream().filter(card -> card.suit() == suit).toList();
        if (suited.size() != 5) return Optional.empty();

        return Optional.of(
                new Flush(new Quintuple<>(suited.get(0), suited.get(1), suited.get(2), suited.get(3), suited.get(4))));
    }

    public static Optional<FullHouse> findFullHouse(List<Card> cards) {
        Optional<ThreeOfAKind> threeOfAKindOpt = findThreeOfAKind(cards);
        if (threeOfAKindOpt.isEmpty()) return Optional.empty();
        ThreeOfAKind threeOfAKind = threeOfAKindOpt.get();

        var groups = cards.stream().collect(java.util.stream.Collectors.groupingBy(Card::strength));
        var triple = groups.values().stream().filter(g -> g.size() == 3).findFirst();
        var pair = groups.values().stream().filter(g -> g.size() == 2).findFirst();

        if (triple.isEmpty() || pair.isEmpty()) return Optional.empty();

        var t = triple.get();
        var p = pair.get();

        return Optional.of(new FullHouse(new Triple<>(t.get(0), t.get(1), t.get(2)), new Pair<>(p.get(0), p.get(1))));
    }

    public static Optional<FourOfAKind> findFourOfAKind(List<Card> cards) {
        Optional<List<Card>> fourOfAKindOpt = findSameCards(cards, 4);
        if (fourOfAKindOpt.isEmpty()) return Optional.empty();
        List<Card> fourOfAKind = fourOfAKindOpt.get();

        List<Card> list = cards.stream()
                .filter(card -> card.strength() != fourOfAKind.getFirst().strength())
                .toList();
        Optional<HighCard> kickerOpt = findHighCard(list);
        if (kickerOpt.isEmpty()) return Optional.empty();
        HighCard kicker = kickerOpt.get();

        return Optional.of(new FourOfAKind(
                new Quadruple<>(fourOfAKind.get(0), fourOfAKind.get(1), fourOfAKind.get(2), fourOfAKind.get(3)),
                kicker.card));
    }

    public static Optional<StraightFlush> findStraightFlush(List<Card> cards) {
        Optional<Straight> straightOpt = findStraight(cards);
        if (straightOpt.isEmpty()) return Optional.empty();
        Straight straight = straightOpt.get();

        Optional<Flush> flushOpt = findFlush(cards);
        if (flushOpt.isEmpty()) return Optional.empty();

        return Optional.of(new StraightFlush(straight.cards));
    }

    private static Optional<List<Card>> findSameCards(List<Card> cards, int howMany) {
        var groups = cards.stream()
                .sorted(Comparator.comparingInt(card -> card.strength().ordinal()))
                .collect(java.util.stream.Collectors.groupingBy(Card::strength));

        return groups.values().stream().filter(g -> g.size() == howMany).findFirst();
    }
}
