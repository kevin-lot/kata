data class Rover(
    val position: Position,
    val planet: Planet,
) {
    companion object {
        fun from(
            position: Position,
            planet: Planet,
        ): Result<Rover> {
            val rover = Rover(position, planet)

            if (!rover.hasValidPosition()) return Result.failure(Exception("out of bounds"))
            if (rover.hasHitRock()) return Result.failure(Exception("rover crashes directly on a rock"))

            return Result.success(rover)
        }
    }

    fun apply(command: Command): Rover {
        val limitX = planet.size.first - 1
        val limitY = planet.size.second - 1
        val newPosition = when (command) {
            Command.MoveBackward -> position.moveBackward(limitX, limitY)
            Command.MoveForward -> position.moveForward(limitX, limitY)
            Command.PivotLeft -> position.pivotLeft()
            Command.PivotRight -> position.pivotRight()
        }
        return copy(position = newPosition)
    }

    fun hasHitRock(): Boolean {
        val virtualRock = Rock(position.x, position.y)
        return planet.rocks.contains(virtualRock)
    }

    private fun hasValidPosition(): Boolean = position.x < planet.size.first && position.y < planet.size.second
}
