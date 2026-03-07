package local.kata.simplepokerhands.poker;

import local.kata.simplepokerhands.cards.Card;
import local.kata.simplepokerhands.tuples.Pair;
import local.kata.simplepokerhands.tuples.Triple;

public final class FullHouse extends PokerHand {
    public final Triple<Card, Card, Card> first;
    public final Pair<Card, Card> second;

    public FullHouse(Triple<Card, Card, Card> first, Pair<Card, Card> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullHouse fullHouse = (FullHouse) o;
        return java.util.Objects.equals(first, fullHouse.first) && java.util.Objects.equals(second, fullHouse.second);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(first, second);
    }
}
