package local.kata.simplepokerhands.poker;

import local.kata.simplepokerhands.cards.Card;

public final class HighCard extends PokerHand {
    public final Card card;

    public HighCard(Card card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighCard highCard = (HighCard) o;
        return java.util.Objects.equals(card, highCard.card);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(card);
    }
}
