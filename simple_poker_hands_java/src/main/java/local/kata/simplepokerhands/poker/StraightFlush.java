package local.kata.simplepokerhands.poker;

import local.kata.simplepokerhands.cards.Card;
import local.kata.simplepokerhands.tuples.Quintuple;

public final class StraightFlush extends PokerHand {
    public final Quintuple<Card, Card, Card, Card, Card> cards;

    public StraightFlush(Quintuple<Card, Card, Card, Card, Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StraightFlush that = (StraightFlush) o;
        return java.util.Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(cards);
    }
}
