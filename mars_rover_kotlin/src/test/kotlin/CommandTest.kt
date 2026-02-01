import io.kotest.matchers.shouldBe
import kotlin.test.Test

class CommandTest {
    @Test
    fun `from returns correct command for known chars`() {
        Command.from('b') shouldBe Command.MoveBackward
        Command.from('f') shouldBe Command.MoveForward
        Command.from('l') shouldBe Command.PivotLeft
        Command.from('r') shouldBe Command.PivotRight
    }

    @Test
    fun `from returns null for unknown char`() {
        Command.from('a') shouldBe null
    }
}
