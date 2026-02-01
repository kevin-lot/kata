import io.kotest.matchers.shouldBe
import kotlin.test.Test

class OrientationTest {
    @Test
    fun `pivotLeft turns correctly`() {
        Orientation.North.pivotLeft() shouldBe Orientation.West
        Orientation.West.pivotLeft() shouldBe Orientation.South
        Orientation.South.pivotLeft() shouldBe Orientation.East
        Orientation.East.pivotLeft() shouldBe Orientation.North
    }

    @Test
    fun `pivotRight turns correctly`() {
        Orientation.North.pivotRight() shouldBe Orientation.East
        Orientation.East.pivotRight() shouldBe Orientation.South
        Orientation.South.pivotRight() shouldBe Orientation.West
        Orientation.West.pivotRight() shouldBe Orientation.North
    }
}
