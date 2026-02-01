import io.kotest.matchers.shouldBe
import kotlin.test.Test

class PositionTest {
    @Test
    fun `moveBackward moves in the opposite direction of orientation`() {
        Position(5, 5, Orientation.North).moveBackward(100, 100).let {
            it.x shouldBe 5
            it.y shouldBe 4
        }
        Position(5, 5, Orientation.West).moveBackward(100, 100).let {
            it.x shouldBe 6
            it.y shouldBe 5
        }
        Position(5, 5, Orientation.South).moveBackward(100, 100).let {
            it.x shouldBe 5
            it.y shouldBe 6
        }
        Position(5, 5, Orientation.East).moveBackward(100, 100).let {
            it.x shouldBe 4
            it.y shouldBe 5
        }
    }

    @Test
    fun `moveForward moves in the direction of orientation`() {
        Position(5, 5, Orientation.North).moveForward(100, 100).let {
            it.x shouldBe 5
            it.y shouldBe 6
        }
        Position(5, 5, Orientation.West).moveForward(100, 100).let {
            it.x shouldBe 4
            it.y shouldBe 5
        }
        Position(5, 5, Orientation.South).moveForward(100, 100).let {
            it.x shouldBe 5
            it.y shouldBe 4
        }
        Position(5, 5, Orientation.East).moveForward(100, 100).let {
            it.x shouldBe 6
            it.y shouldBe 5
        }
    }

    @Test
    fun `pivotLeft changes orientation without moving`() {
        val cases =
            listOf(
                Orientation.North to Orientation.West,
                Orientation.West to Orientation.South,
                Orientation.South to Orientation.East,
                Orientation.East to Orientation.North,
            )
        for ((input, expected) in cases) {
            val pos = Position(5, 5, input).pivotLeft()
            pos.x shouldBe 5
            pos.y shouldBe 5
            pos.orientation shouldBe expected
        }
    }

    @Test
    fun `pivotRight changes orientation without moving`() {
        val cases =
            listOf(
                Orientation.North to Orientation.East,
                Orientation.East to Orientation.South,
                Orientation.South to Orientation.West,
                Orientation.West to Orientation.North,
            )
        for ((input, expected) in cases) {
            val pos = Position(5, 5, input).pivotRight()
            pos.x shouldBe 5
            pos.y shouldBe 5
            pos.orientation shouldBe expected
        }
    }
}
