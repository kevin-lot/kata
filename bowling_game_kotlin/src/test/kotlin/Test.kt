import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    @Test
    fun `test number of frames`() {
        val game = Game()
        repeat(10 * 2) {
            game.roll(1)
        }
        // not include in rolls
        game.roll(1)

        assertEquals(20, game.rolls.size)
    }

    @Test
    fun `test number of frames with spare at last`() {
        val game = Game()
        repeat(9 * 2) {
            game.roll(0)
        }
        game.roll(7)
        game.roll(3)
        game.roll(1)

        // not include in rolls
        game.roll(1)

        assertEquals(21, game.rolls.size)
    }

    @Test
    fun `test number of frames with strike at last`() {
        val game = Game()
        repeat(9 * 2) {
            game.roll(0)
        }
        game.roll(10)
        game.roll(3)
        game.roll(1)

        // not include in rolls
        game.roll(1)

        assertEquals(21, game.rolls.size)
    }

    @Test
    fun `test null score`() {
        val game = Game()
        repeat(10 * 2) {
            game.roll(0)
        }
        assertEquals(0, game.score())
    }

    @Test
    fun `test classic score`() {
        val game = Game()
        repeat(10 * 2) {
            game.roll(1)
        }
        assertEquals(20, game.score())
    }

    @Test
    fun `test spare score`() {
        val game = Game()
        game.roll(5)
        game.roll(5)
        game.roll(3)
        game.roll(1)
        assertEquals(17, game.score())
    }

    @Test
    fun `test strike score`() {
        val game = Game()
        game.roll(10)
        game.roll(3)
        game.roll(1)
        assertEquals(18, game.score())
    }

    @Test
    fun `test with extra ball score from spare`() {
        val game = Game()
        repeat(18) {
            game.roll(0)
        }
        game.roll(5)
        game.roll(5)
        game.roll(3)

        assertEquals(13, game.score())
    }

    @Test
    fun `test with extra balls score from strike`() {
        val game = Game()
        repeat(18) {
            game.roll(0)
        }
        game.roll(10)
        game.roll(3)
        game.roll(3)

        assertEquals(16, game.score())
    }

    @Test
    fun `test max score`() {
        val game = Game()
        repeat(10) {
            game.roll(10)
        }
        game.roll(10)
        game.roll(10)

        assertEquals(300, game.score())
    }
}
