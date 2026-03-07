package local.kata.simplepokerhands.poker;

import local.kata.simplepokerhands.cards.Card;
import local.kata.simplepokerhands.tuples.Pair;

public final class TwoPair extends PokerHand {
    public final Pair<Card, Card> first;
    public final Pair<Card, Card> second;
    public final Card kicker;

    public TwoPair(Pair<Card, Card> first, Pair<Card, Card> second, Card kicker) {
        this.first = first;
        this.second = second;
        this.kicker = kicker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoPair twoPair = (TwoPair) o;
        return java.util.Objects.equals(first, twoPair.first)
                && java.util.Objects.equals(second, twoPair.second)
                && java.util.Objects.equals(kicker, twoPair.kicker);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(first, second, kicker);
    }
}
