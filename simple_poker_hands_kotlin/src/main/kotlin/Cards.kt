data class Card(
    val strength: CardStrength,
    val suit: CardSuit,
)

enum class CardSuit {
    SPADE,
    HEART,
    DIAMOND,
    CLUB,
}

enum class CardStrength {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE,
}
