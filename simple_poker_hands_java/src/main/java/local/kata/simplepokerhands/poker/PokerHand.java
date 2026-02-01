package local.kata.simplepokerhands.poker;

public sealed class PokerHand
        permits HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush {}
