package local.kata.simplepokerhands.poker;

import local.kata.simplepokerhands.cards.Card;
import local.kata.simplepokerhands.tuples.Quadruple;

public final class FourOfAKind extends PokerHand {
    public final Quadruple<Card, Card, Card, Card> cards;
    public final Card kicker;

    public FourOfAKind(Quadruple<Card, Card, Card, Card> cards, Card kicker) {
        this.cards = cards;
        this.kicker = kicker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FourOfAKind that = (FourOfAKind) o;
        return java.util.Objects.equals(cards, that.cards) && java.util.Objects.equals(kicker, that.kicker);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(cards, kicker);
    }
}
