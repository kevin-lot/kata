package local.kata.simplepokerhands.poker;

import local.kata.simplepokerhands.cards.Card;
import local.kata.simplepokerhands.tuples.Quintuple;

public final class Flush extends PokerHand {
    public final Quintuple<Card, Card, Card, Card, Card> cards;

    public Flush(Quintuple<Card, Card, Card, Card, Card> cards) {
        var suits = cards.toList().stream()
                .map(card -> ((Card) card).suit())
                .distinct()
                .count();

        if (suits != 1) {
            throw new IllegalArgumentException("Cards are not all the same suit");
        }

        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flush flush = (Flush) o;
        return java.util.Objects.equals(cards, flush.cards);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(cards);
    }
}
