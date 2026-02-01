enum class Command {
    MoveBackward,
    MoveForward,
    PivotLeft,
    PivotRight,
    ;

    companion object {
        fun from(c: Char): Command? =
            when (c) {
                'b' -> MoveBackward
                'f' -> MoveForward
                'l' -> PivotLeft
                'r' -> PivotRight
                else -> null
            }
    }
}
