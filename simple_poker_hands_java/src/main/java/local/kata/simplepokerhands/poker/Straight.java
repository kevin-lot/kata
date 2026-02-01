package local.kata.simplepokerhands.poker;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import local.kata.simplepokerhands.cards.Card;
import local.kata.simplepokerhands.cards.CardStrength;
import local.kata.simplepokerhands.tuples.Quintuple;

public final class Straight extends PokerHand {
    public static final Set<CardStrength> aceLow = Set.of(CardStrength.TWO, CardStrength.THREE, CardStrength.FOUR, CardStrength.FIVE, CardStrength.ACE);

    public final Quintuple<Card, Card, Card, Card, Card> cards;

    public Straight(Quintuple<Card, Card, Card, Card, Card> cards) {
        var sortedCards = cards.toList().stream()
                .map(item -> (Card) item)
                .sorted(Comparator.comparingInt(card -> card.strength().ordinal()))
                .toList();

        var isRegular = true;
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            if (sortedCards.get(i + 1).strength().ordinal() != sortedCards.get(i).strength().ordinal() + 1) {
                isRegular = false;
                break;
            }
        }

        var strengths = sortedCards.stream().map(Card::strength).collect(Collectors.toSet());
        var isAceLow = strengths.equals(aceLow);

        if (!isRegular && !isAceLow) {
            throw new IllegalArgumentException("Cards are not consecutive");
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
