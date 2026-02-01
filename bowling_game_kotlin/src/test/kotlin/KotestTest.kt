import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class KotestTest :
    StringSpec({
        "test number of frames" {
            val game = Game()
            repeat(10 * 2) {
                game.roll(1)
            }
            // not include in rolls
            game.roll(1)

            game.rolls.size shouldBe 20
        }

        "test number of frames with spare at last" {
            val game = Game()
            repeat(9 * 2) {
                game.roll(0)
            }
            game.roll(7)
            game.roll(3)
            game.roll(1)

            // not include in rolls
            game.roll(1)

            game.rolls.size shouldBe 21
        }

        "test number of frames with strike at last" {
            val game = Game()
            repeat(9 * 2) {
                game.roll(0)
            }
            game.roll(10)
            game.roll(3)
            game.roll(1)

            // not include in rolls
            game.roll(1)

            game.rolls.size shouldBe 21
        }

        "test null score" {
            val game = Game()
            repeat(10 * 2) {
                game.roll(0)
            }
            game.score() shouldBe 0
        }

        "test classic score" {
            val game = Game()
            repeat(10 * 2) {
                game.roll(1)
            }
            game.score() shouldBe 20
        }

        "test spare score" {
            val game = Game()
            game.roll(5)
            game.roll(5)
            game.roll(3)
            game.roll(1)
            game.score() shouldBe 17
        }

        "test strike score" {
            val game = Game()
            game.roll(10)
            game.roll(3)
            game.roll(1)
            game.score() shouldBe 18
        }

        "test with extra ball score from spare" {
            val game = Game()
            repeat(18) {
                game.roll(0)
            }
            game.roll(5)
            game.roll(5)
            game.roll(3)

            game.score() shouldBe 13
        }

        "test with extra balls score from strike" {
            val game = Game()
            repeat(18) {
                game.roll(0)
            }
            game.roll(10)
            game.roll(3)
            game.roll(3)

            game.score() shouldBe 16
        }

        "test max score" {
            val game = Game()
            repeat(10) {
                game.roll(10)
            }
            game.roll(10)
            game.roll(10)

            game.score() shouldBe 300
        }
    })
