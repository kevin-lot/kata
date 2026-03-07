use crate::cards::Card;
use crate::cards::CardStrength;
use crate::cards::CardSuit;
use crate::poker::Flush;
use crate::poker::FourOfAKind;
use crate::poker::FullHouse;
use crate::poker::HighCard;
use crate::poker::Pair;
use crate::poker::Straight;
use crate::poker::StraightFlush;
use crate::poker::ThreeOfAKind;
use crate::poker::TwoPair;

pub fn fixture_high_card() -> Vec<Card> {
    vec![
        Card {
            strength: CardStrength::Eight,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Heart,
        },
        Card {
            strength: CardStrength::Nine,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Club,
        },
        Card {
            strength: CardStrength::Two,
            suit: CardSuit::Diamond,
        },
    ]
}
pub fn fixture_high_card_result() -> HighCard {
    HighCard {
        card: Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Club,
        },
    }
}

pub fn fixture_pair() -> Vec<Card> {
    vec![
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::Five,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Club,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Seven,
            suit: CardSuit::Spade,
        },
    ]
}
pub fn fixture_pair_result() -> Pair {
    Pair {
        cards: (
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Spade,
            },
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Diamond,
            },
        ),
        kicker: Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Club,
        },
    }
}

pub fn fixture_two_pair() -> Vec<Card> {
    vec![
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Club,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Seven,
            suit: CardSuit::Spade,
        },
    ]
}
pub fn fixture_two_pair_result() -> TwoPair {
    TwoPair {
        first: (
            Card {
                strength: CardStrength::Jack,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::Jack,
                suit: CardSuit::Club,
            },
        ),
        second: (
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Spade,
            },
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Diamond,
            },
        ),
        kicker: Card {
            strength: CardStrength::Seven,
            suit: CardSuit::Spade,
        },
    }
}

pub fn fixture_three_of_a_kind() -> Vec<Card> {
    vec![
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Club,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Seven,
            suit: CardSuit::Spade,
        },
    ]
}

pub fn fixture_three_of_a_kind_result() -> ThreeOfAKind {
    ThreeOfAKind {
        cards: (
            Card {
                strength: CardStrength::Jack,
                suit: CardSuit::Spade,
            },
            Card {
                strength: CardStrength::Jack,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::Jack,
                suit: CardSuit::Club,
            },
        ),
        kicker: Card {
            strength: CardStrength::Seven,
            suit: CardSuit::Spade,
        },
    }
}

pub fn fixture_straight() -> Vec<Card> {
    vec![
        Card {
            strength: CardStrength::Seven,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::Six,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Five,
            suit: CardSuit::Club,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Four,
            suit: CardSuit::Spade,
        },
    ]
}

pub fn fixture_straight_result() -> Straight {
    Straight {
        cards: (
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::Four,
                suit: CardSuit::Spade,
            },
            Card {
                strength: CardStrength::Five,
                suit: CardSuit::Club,
            },
            Card {
                strength: CardStrength::Six,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::Seven,
                suit: CardSuit::Spade,
            },
        ),
    }
}

pub fn fixture_flush() -> Vec<Card> {
    vec![
        Card {
            strength: CardStrength::Seven,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::Six,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::Five,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::King,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::Four,
            suit: CardSuit::Spade,
        },
    ]
}

pub fn fixture_flush_result() -> Flush {
    Flush {
        cards: (
            Card {
                strength: CardStrength::Seven,
                suit: CardSuit::Spade,
            },
            Card {
                strength: CardStrength::Six,
                suit: CardSuit::Spade,
            },
            Card {
                strength: CardStrength::Five,
                suit: CardSuit::Spade,
            },
            Card {
                strength: CardStrength::King,
                suit: CardSuit::Spade,
            },
            Card {
                strength: CardStrength::Four,
                suit: CardSuit::Spade,
            },
        ),
    }
}

pub fn fixture_full_house() -> Vec<Card> {
    vec![
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Club,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Spade,
        },
    ]
}

pub fn fixture_full_house_result() -> FullHouse {
    FullHouse {
        first: (
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Club,
            },
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Spade,
            },
        ),
        second: (
            Card {
                strength: CardStrength::Jack,
                suit: CardSuit::Spade,
            },
            Card {
                strength: CardStrength::Jack,
                suit: CardSuit::Diamond,
            },
        ),
    }
}

pub fn fixture_four_of_a_kind() -> Vec<Card> {
    vec![
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Spade,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Heart,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Club,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Three,
            suit: CardSuit::Spade,
        },
    ]
}
pub fn fixture_four_of_a_kind_result() -> FourOfAKind {
    FourOfAKind {
        cards: (
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Heart,
            },
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Club,
            },
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::Three,
                suit: CardSuit::Spade,
            },
        ),
        kicker: Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Spade,
        },
    }
}

pub fn fixture_straight_flush() -> Vec<Card> {
    vec![
        Card {
            strength: CardStrength::Ten,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Jack,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Queen,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::King,
            suit: CardSuit::Diamond,
        },
        Card {
            strength: CardStrength::Ace,
            suit: CardSuit::Diamond,
        },
    ]
}
pub fn fixture_straight_flush_result() -> StraightFlush {
    StraightFlush {
        cards: (
            Card {
                strength: CardStrength::Ten,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::Jack,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::Queen,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::King,
                suit: CardSuit::Diamond,
            },
            Card {
                strength: CardStrength::Ace,
                suit: CardSuit::Diamond,
            },
        ),
    }
}
