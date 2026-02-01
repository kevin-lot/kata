private sealed interface Frame10 {
    data class InProgress(
        val balls: List<Int>,
    ) : Frame10 {
        val acceptsNext: Boolean =
            when (balls.size) {
                0, 1 -> true
                2 -> balls[0] == 10 || balls[0] + balls[1] == 10
                else -> false
            }
    }

    object NotReached : Frame10
}

class Game {
    companion object {
        const val MAX_TRIES = 10
    }

    val rolls: MutableList<Int> = mutableListOf()

    // Returns the number of rolls consumed by the frame at index i,
    // or null if the frame hasn't been completed yet.
    private fun frameRollCount(i: Int): Int? =
        when {
            rolls.size <= i -> null

            // pick only slot, if it is strike
            rolls[i] == 10 -> 1

            rolls.size <= i + 1 -> null

            else -> 2
        }

    private fun frame10State(): Frame10 {
        var i = 0
        repeat(MAX_TRIES - 1) {
            val frameRollCount = frameRollCount(i) ?: return Frame10.NotReached
            i += frameRollCount
        }
        return Frame10.InProgress(rolls.subList(i, rolls.size))
    }

    fun roll(value: Int) {
        when (val f10 = frame10State()) {
            is Frame10.NotReached -> rolls.add(value)
            is Frame10.InProgress -> if (f10.acceptsNext) rolls.add(value)
        }
    }

    fun score(): Int {
        var result = 0
        var i = 0

        repeat(MAX_TRIES) {
            val current = rolls.getOrElse(i) { 0 }
            val next = rolls.getOrElse(i + 1) { 0 }
            val nextNext = rolls.getOrElse(i + 2) { 0 }

            if (current == 10) {
                result += 10 + next + nextNext
                i += 1
                return@repeat
            }

            if (current + next == 10) {
                result += 10 + nextNext
                i += 2
                return@repeat
            }

            result += current + next
            i += 2
        }

        return result
    }
}
