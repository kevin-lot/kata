use crate::cards::Card;

type Kicker = Card;

#[derive(Debug, PartialEq)]
pub enum HandEnum {
    HighCardEnum(HighCard),
    PairEnum(Pair),
    TwoPairEnum(TwoPair),
    ThreeOfAKindEnum(ThreeOfAKind),
    StraightEnum(Straight),
    FlushEnum(Flush),
    FullHouseEnum(FullHouse),
    FourOfAKindEnum(FourOfAKind),
    StraightFlushEnum(StraightFlush),
}

#[derive(Debug, PartialEq)]
pub struct HighCard {
    pub card: Card,
}
#[derive(Debug, PartialEq)]
pub struct Pair {
    pub cards: (Card, Card),
    pub kicker: Kicker,
}
#[derive(Debug, PartialEq)]
pub struct TwoPair {
    pub first: (Card, Card),
    pub kicker: Kicker,
    pub second: (Card, Card),
}
#[derive(Debug, PartialEq)]
pub struct ThreeOfAKind {
    pub cards: (Card, Card, Card),
    pub kicker: Kicker,
}
#[derive(Debug, PartialEq)]
pub struct Straight {
    pub cards: (Card, Card, Card, Card, Card),
}
#[derive(Debug, PartialEq)]
pub struct Flush {
    pub cards: (Card, Card, Card, Card, Card),
}
#[derive(Debug, PartialEq)]
pub struct FullHouse {
    pub first: (Card, Card, Card),
    pub second: (Card, Card),
}
#[derive(Debug, PartialEq)]
pub struct FourOfAKind {
    pub cards: (Card, Card, Card, Card),
    pub kicker: Kicker,
}
#[derive(Debug, PartialEq)]
pub struct StraightFlush {
    pub cards: (Card, Card, Card, Card, Card),
}
