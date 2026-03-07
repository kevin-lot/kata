package local.kata.simplepokerhands.poker;

import java.util.Comparator;
import local.kata.simplepokerhands.cards.Card;
import local.kata.simplepokerhands.tuples.Quintuple;

public final class Straight extends PokerHand {
    public final Quintuple<Card, Card, Card, Card, Card> cards;

    public Straight(Quintuple<Card, Card, Card, Card, Card> cards) {
        var list = cards.toList().stream()
                .map(item -> (Card) item)
                .sorted(Comparator.comparingInt(card -> card.strength().ordinal()))
                .toList();

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1).strength().ordinal() != list.get(i).strength().ordinal() + 1) {
                throw new IllegalArgumentException("Cards are not consecutive");
            }
        }

        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Straight straight = (Straight) o;
        return java.util.Objects.equals(cards, straight.cards);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(cards);
    }
}
