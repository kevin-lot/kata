enum class Orientation {
    North,
    East,
    South,
    West,
    ;

    fun pivotLeft(): Orientation =
        when (this) {
            North -> West
            East -> North
            South -> East
            West -> South
        }

    fun pivotRight(): Orientation =
        when (this) {
            North -> East
            East -> South
            South -> West
            West -> North
        }
}
