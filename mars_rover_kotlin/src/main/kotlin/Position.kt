data class Position(
    val x: Int,
    val y: Int,
    val orientation: Orientation,
) {
    private fun wrap(
        value: Int,
        limit: Int,
    ): Int {
        val size = limit + 1
        return ((value % size) + size) % size
    }

    fun moveBackward(
        limitX: Int,
        limitY: Int,
    ): Position {
        val (nx, ny) =
            when (orientation) {
                Orientation.East -> Pair(wrap(x - 1, limitX), y)
                Orientation.North -> Pair(x, wrap(y - 1, limitY))
                Orientation.South -> Pair(x, wrap(y + 1, limitY))
                Orientation.West -> Pair(wrap(x + 1, limitX), y)
            }
        return copy(x = nx, y = ny)
    }

    fun moveForward(
        limitX: Int,
        limitY: Int,
    ): Position {
        val (nx, ny) =
            when (orientation) {
                Orientation.East -> Pair(wrap(x + 1, limitX), y)
                Orientation.North -> Pair(x, wrap(y + 1, limitY))
                Orientation.South -> Pair(x, wrap(y - 1, limitY))
                Orientation.West -> Pair(wrap(x - 1, limitX), y)
            }
        return copy(x = nx, y = ny)
    }

    fun pivotLeft(): Position = copy(orientation = orientation.pivotLeft())

    fun pivotRight(): Position = copy(orientation = orientation.pivotRight())
}
