fun main() {
    // init
    val rover =
        Rover
            .from(
                Position(0, 0, Orientation.North),
                Planet(Pair(10, 10), listOf(Rock(1, 1))),
            ).getOrElse { e ->
                println("Failed to create rover: ${e.message}")
                return
            }
    val servoCommand = ServoCommand(rover)

    // command
    val commands = "frfffffff"
    val (finalRover, message) = servoCommand.moveRover(commands)
    if (message != null) println(message)

    // Print the planet map with the rover's position represented by "R", rocks by "X", and empty spaces by "."
    val planet = finalRover.planet
    val (sizeX, sizeY) = planet.size
    val rocks = planet.rocks

    println("Planet map:")
    for (y in sizeY - 1 downTo 0) {
        for (x in 0 until sizeX) {
            when {
                x == finalRover.position.x && y == finalRover.position.y -> print("R")
                rocks.contains(Rock(x, y)) -> print("X")
                else -> print(".")
            }
        }
        println()
    }
}
