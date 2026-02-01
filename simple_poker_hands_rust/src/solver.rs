use crate::cards::Card;
use crate::cards::CardStrength;
use crate::poker::Flush;
use crate::poker::FourOfAKind;
use crate::poker::FullHouse;
use crate::poker::HandEnum;
use crate::poker::HighCard;
use crate::poker::Pair;
use crate::poker::Straight;
use crate::poker::StraightFlush;
use crate::poker::ThreeOfAKind;
use crate::poker::TwoPair;

trait SameCardFinder {
    fn find_same_card(&self, number: usize) -> Option<Vec<Card>>;
}
impl SameCardFinder for Vec<Card> {
    fn find_same_card(&self, how_many: usize) -> Option<Vec<Card>> {
        if self.len() < how_many {
            return None;
        }

        let mut strengths: Vec<_> = self.iter().map(|c| c.strength).collect();
        strengths.sort_by(|a, b| b.cmp(a));
        strengths.dedup();

        for strength in strengths {
            let cards: Vec<Card> = self
                .iter()
                .filter(|c| c.strength == strength)
                .cloned()
                .collect();
            if cards.len() != how_many {
                continue;
            }

            return Some(cards);
        }

        None
    }
}

trait HandFinder {
    fn find_high_card(&self) -> Option<HighCard>;
    fn find_pair(&self) -> Option<Pair>;
    fn find_two_pair(&self) -> Option<TwoPair>;
    fn find_three_of_a_kind(&self) -> Option<ThreeOfAKind>;
    fn find_straight(&self) -> Option<Straight>;
    fn find_flush(&self) -> Option<Flush>;
    fn find_full_house(&self) -> Option<FullHouse>;
    fn find_four_of_a_kind(&self) -> Option<FourOfAKind>;
    fn find_straight_flush(&self) -> Option<StraightFlush>;
}
impl HandFinder for Vec<Card> {
    fn find_high_card(&self) -> Option<HighCard> {
        Some(HighCard {
            card: self.iter().max_by_key(|c| c.strength).cloned()?,
        })
    }

    fn find_pair(&self) -> Option<Pair> {
        let pair = self.find_same_card(2)?;
        let card0 = pair.get(0)?;
        let card1 = pair.get(1)?;
        let kicker = self
            .iter()
            .filter(|c| c.strength != card0.strength)
            .cloned()
            .collect::<Vec<Card>>()
            .find_high_card()?;

        Some(Pair {
            cards: (*card0, *card1),
            kicker: kicker.card,
        })
    }

    fn find_two_pair(&self) -> Option<TwoPair> {
        let first_highest_pair = self.find_pair()?;
        let card0 = first_highest_pair.cards.0;
        let card1 = first_highest_pair.cards.1;

        let second_highest_pair = self
            .iter()
            .filter(|c| c.strength != card0.strength)
            .cloned()
            .collect::<Vec<Card>>()
            .find_pair()?;
        let card2 = second_highest_pair.cards.0;
        let card3 = second_highest_pair.cards.1;

        Some(TwoPair {
            first: (card0, card1),
            second: (card2, card3),
            kicker: second_highest_pair.kicker,
        })
    }

    fn find_three_of_a_kind(&self) -> Option<ThreeOfAKind> {
        let three_of_a_kind = self.find_same_card(3)?;
        let card0 = three_of_a_kind.get(0)?;
        let card1 = three_of_a_kind.get(1)?;
        let card2 = three_of_a_kind.get(2)?;
        let kicker = self
            .iter()
            .filter(|c| c.strength != card0.strength)
            .cloned()
            .collect::<Vec<Card>>()
            .find_high_card()?;

        Some(ThreeOfAKind {
            cards: (*card0, *card1, *card2),
            kicker: kicker.card,
        })
    }

    fn find_straight(&self) -> Option<Straight> {
        let mut sorted_cards = self.clone();
        sorted_cards.sort_by_key(|c| c.strength);
        let mut strengths: Vec<_> = sorted_cards.iter().map(|c| c.strength).collect();
        strengths.dedup();

        if strengths.len() != 5 {
            return None;
        }

        // Regular straight: five consecutive ranks
        if *strengths.last()? as u8 - *strengths.first()? as u8 == 4 {
            return Some(Straight {
                cards: (
                    sorted_cards[0],
                    sorted_cards[1],
                    sorted_cards[2],
                    sorted_cards[3],
                    sorted_cards[4],
                ),
            });
        }

        // Ace-low straight (A-2-3-4-5): ace plays as 1
        let ace_low = [
            CardStrength::Two,
            CardStrength::Three,
            CardStrength::Four,
            CardStrength::Five,
            CardStrength::Ace,
        ];
        if strengths == ace_low {
            return Some(Straight {
                cards: (
                    sorted_cards[4], // ace is the last card
                    sorted_cards[0],
                    sorted_cards[1],
                    sorted_cards[2],
                    sorted_cards[3],
                ),
            });
        }

        None
    }

    fn find_flush(&self) -> Option<Flush> {
        let first_card = self.first()?;
        let cards = self
            .iter()
            .filter(|c| c.suit == first_card.suit)
            .cloned()
            .collect::<Vec<Card>>();
        if cards.len() != 5 {
            return None;
        }

        let card1 = cards.get(1)?;
        let card2 = cards.get(2)?;
        let card3 = cards.get(3)?;
        let card4 = cards.get(4)?;

        Some(Flush {
            cards: (*first_card, *card1, *card2, *card3, *card4),
        })
    }

    fn find_full_house(&self) -> Option<FullHouse> {
        let three_of_a_kind = self.find_three_of_a_kind()?;
        let pair = self
            .iter()
            .filter(|c| c.strength != three_of_a_kind.cards.0.strength)
            .cloned()
            .collect::<Vec<Card>>()
            .find_same_card(2)?;

        let card0 = three_of_a_kind.cards.0;
        let card1 = three_of_a_kind.cards.1;
        let card2 = three_of_a_kind.cards.2;

        let card3 = pair.get(0)?;
        let card4 = pair.get(1)?;

        Some(FullHouse {
            first: (card0, card1, card2),
            second: (*card3, *card4),
        })
    }

    fn find_four_of_a_kind(&self) -> Option<FourOfAKind> {
        let four_of_a_kind = self.find_same_card(4)?;
        let card0 = four_of_a_kind.get(0)?;
        let card1 = four_of_a_kind.get(1)?;
        let card2 = four_of_a_kind.get(2)?;
        let card3 = four_of_a_kind.get(3)?;

        let kicker = self
            .iter()
            .filter(|c| c.strength != card0.strength)
            .cloned()
            .collect::<Vec<Card>>()
            .find_high_card()?;

        Some(FourOfAKind {
            cards: (*card0, *card1, *card2, *card3),
            kicker: kicker.card,
        })
    }

    fn find_straight_flush(&self) -> Option<StraightFlush> {
        let straight = self.find_straight()?;
        self.find_flush()?;

        Some(StraightFlush {
            cards: straight.cards,
        })
    }
}

pub trait Solver {
    fn solve(&self) -> Option<HandEnum>;
}
impl Solver for Vec<Card> {
    fn solve(&self) -> Option<HandEnum> {
        self.find_straight_flush()
            .map(HandEnum::StraightFlushEnum)
            .or_else(|| self.find_four_of_a_kind().map(HandEnum::FourOfAKindEnum))
            .or_else(|| self.find_full_house().map(HandEnum::FullHouseEnum))
            .or_else(|| self.find_flush().map(HandEnum::FlushEnum))
            .or_else(|| self.find_straight().map(HandEnum::StraightEnum))
            .or_else(|| self.find_three_of_a_kind().map(HandEnum::ThreeOfAKindEnum))
            .or_else(|| self.find_two_pair().map(HandEnum::TwoPairEnum))
            .or_else(|| self.find_pair().map(HandEnum::PairEnum))
            .or_else(|| self.find_high_card().map(HandEnum::HighCardEnum))
    }
}

#[cfg(test)]
mod tests {
    use crate::cards::Card;
    use crate::fixture::fixture_flush;
    use crate::fixture::fixture_flush_result;
    use crate::fixture::fixture_four_of_a_kind;
    use crate::fixture::fixture_four_of_a_kind_result;
    use crate::fixture::fixture_full_house;
    use crate::fixture::fixture_full_house_result;
    use crate::fixture::fixture_high_card;
    use crate::fixture::fixture_high_card_result;
    use crate::fixture::fixture_pair;
    use crate::fixture::fixture_pair_result;
    use crate::fixture::fixture_straight;
    use crate::fixture::fixture_straight_flush;
    use crate::fixture::fixture_straight_flush_result;
    use crate::fixture::fixture_straight_result;
    use crate::fixture::fixture_three_of_a_kind;
    use crate::fixture::fixture_three_of_a_kind_result;
    use crate::fixture::fixture_two_pair;
    use crate::fixture::fixture_two_pair_result;
    use crate::poker::HandEnum;
    use crate::solver::HandFinder;
    use crate::solver::Solver;
    use rstest::rstest;

    #[test]
    fn test_find_high_card() {
        let actual = HandFinder::find_high_card(&fixture_high_card());
        let expected = Some(fixture_high_card_result());
        assert_eq!(actual, expected);
    }
    #[test]
    fn test_find_pair() {
        let actual = HandFinder::find_pair(&fixture_pair());
        let expected = Some(fixture_pair_result());
        assert_eq!(actual, expected);
    }
    #[test]
    fn test_find_two_pair() {
        let actual = HandFinder::find_two_pair(&fixture_two_pair());
        let expected = Some(fixture_two_pair_result());
        assert_eq!(actual, expected);
    }
    #[test]
    fn test_find_three_of_a_kind() {
        let actual = HandFinder::find_three_of_a_kind(&fixture_three_of_a_kind());
        let expected = Some(fixture_three_of_a_kind_result());
        assert_eq!(actual, expected);
    }
    #[test]
    fn test_find_straight() {
        let actual = HandFinder::find_straight(&fixture_straight());
        let expected = Some(fixture_straight_result());
        assert_eq!(actual, expected);
    }

    #[test]
    fn test_find_ace_low_straight() {
        use crate::cards::{Card, CardStrength, CardSuit};
        let cards = vec![
            Card {
                strength: CardStrength::Ace,
                suit: CardSuit::Spade,
            },
            Card {
                strength: CardStrength::Two,
                suit: CardSuit::Heart,
            },
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::Four,
                suit: CardSuit::Club,
            },
            Card {
                strength: CardStrength::Five,
                suit: CardSuit::Spade,
            },
        ];
        let result = HandFinder::find_straight(&cards);
        assert!(result.is_some());
        let straight = result.unwrap();
        // Ace is reordered to front
        assert_eq!(straight.cards.0.strength, CardStrength::Ace);
        assert_eq!(straight.cards.1.strength, CardStrength::Two);
        assert_eq!(straight.cards.4.strength, CardStrength::Five);
    }
    #[test]
    fn test_find_flush() {
        let actual = HandFinder::find_flush(&fixture_flush());
        let expected = Some(fixture_flush_result());
        assert_eq!(actual, expected);
    }
    #[test]
    fn test_find_full_house() {
        let actual = HandFinder::find_full_house(&fixture_full_house());
        let expected = Some(fixture_full_house_result());
        assert_eq!(actual, expected);
    }
    #[test]
    fn test_find_four_of_a_kind() {
        let actual = HandFinder::find_four_of_a_kind(&fixture_four_of_a_kind());
        let expected = Some(fixture_four_of_a_kind_result());
        assert_eq!(actual, expected);
    }
    #[test]
    fn test_find_straight_flush() {
        let actual = HandFinder::find_straight_flush(&fixture_straight_flush());
        let expected = Some(fixture_straight_flush_result());
        assert_eq!(actual, expected);
    }

    #[rstest]
    #[case(
        fixture_high_card(),
        HandEnum::HighCardEnum(fixture_high_card_result())
    )]
    #[case(fixture_pair(), HandEnum::PairEnum(fixture_pair_result()))]
    #[case(fixture_two_pair(), HandEnum::TwoPairEnum(fixture_two_pair_result()))]
    #[case(
        fixture_three_of_a_kind(),
        HandEnum::ThreeOfAKindEnum(fixture_three_of_a_kind_result())
    )]
    #[case(fixture_straight(), HandEnum::StraightEnum(fixture_straight_result()))]
    #[case(fixture_flush(), HandEnum::FlushEnum(fixture_flush_result()))]
    #[case(
        fixture_full_house(),
        HandEnum::FullHouseEnum(fixture_full_house_result())
    )]
    #[case(
        fixture_four_of_a_kind(),
        HandEnum::FourOfAKindEnum(fixture_four_of_a_kind_result())
    )]
    #[case(
        fixture_straight_flush(),
        HandEnum::StraightFlushEnum(fixture_straight_flush_result())
    )]
    fn test_solver(#[case] input: Vec<Card>, #[case] expected: HandEnum) {
        let actual = Solver::solve(&input);
        let expected = Some(expected);
        assert_eq!(actual, expected);
    }
}
