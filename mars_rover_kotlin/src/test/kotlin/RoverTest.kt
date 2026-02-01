import io.kotest.matchers.shouldBe
import kotlin.test.Test

class RoverTest {
    private val planet = Planet(Pair(10, 10), emptyList())

    @Test
    fun `from fails when position is out of bounds`() {
        Rover.from(Position(20, 0, Orientation.North), planet).isFailure shouldBe true
        Rover.from(Position(0, 20, Orientation.North), planet).isFailure shouldBe true
    }

    @Test
    fun `from fails when rover starts on a rock`() {
        val planetWithRock = Planet(Pair(10, 10), listOf(Rock(5, 5)))
        Rover.from(Position(5, 5, Orientation.North), planetWithRock).isFailure shouldBe true
    }

    @Test
    fun `from succeeds with valid position`() {
        Rover.from(Position(5, 5, Orientation.North), planet).isSuccess shouldBe true
    }

    @Test
    fun `apply moves and rotates rover correctly`() {
        val cases =
            listOf(
                Triple(Orientation.North, Command.MoveBackward, Triple(5, 4, Orientation.North)),
                Triple(Orientation.North, Command.MoveForward, Triple(5, 6, Orientation.North)),
                Triple(Orientation.North, Command.PivotLeft, Triple(5, 5, Orientation.West)),
                Triple(Orientation.North, Command.PivotRight, Triple(5, 5, Orientation.East)),
                Triple(Orientation.West, Command.MoveBackward, Triple(6, 5, Orientation.West)),
                Triple(Orientation.West, Command.MoveForward, Triple(4, 5, Orientation.West)),
                Triple(Orientation.West, Command.PivotLeft, Triple(5, 5, Orientation.South)),
                Triple(Orientation.West, Command.PivotRight, Triple(5, 5, Orientation.North)),
                Triple(Orientation.South, Command.MoveBackward, Triple(5, 6, Orientation.South)),
                Triple(Orientation.South, Command.MoveForward, Triple(5, 4, Orientation.South)),
                Triple(Orientation.South, Command.PivotLeft, Triple(5, 5, Orientation.East)),
                Triple(Orientation.South, Command.PivotRight, Triple(5, 5, Orientation.West)),
                Triple(Orientation.East, Command.MoveBackward, Triple(4, 5, Orientation.East)),
                Triple(Orientation.East, Command.MoveForward, Triple(6, 5, Orientation.East)),
                Triple(Orientation.East, Command.PivotLeft, Triple(5, 5, Orientation.North)),
                Triple(Orientation.East, Command.PivotRight, Triple(5, 5, Orientation.South)),
            )

        for ((orientation, command, expected) in cases) {
            val rover = Rover.from(Position(5, 5, orientation), planet).getOrThrow()
            val newRover = rover.apply(command)
            newRover.position.x shouldBe expected.first
            newRover.position.y shouldBe expected.second
            newRover.position.orientation shouldBe expected.third
        }
    }

    @Test
    fun `rockIsIt returns false when no rock at position`() {
        val planetWithRocks = Planet(Pair(5, 5), listOf(Rock(4, 4), Rock(1, 3)))
        val rover = Rover.from(Position(2, 2, Orientation.North), planetWithRocks).getOrThrow()
        rover.hasHitRock() shouldBe false
    }

    @Test
    fun `checkCollision returns true when rock is at position`() {
        val planetWithRocks = Planet(Pair(5, 5), listOf(Rock(4, 4), Rock(1, 3)))
        Rover.from(Position(1, 3, Orientation.South), planetWithRocks).isFailure shouldBe true
    }
}
