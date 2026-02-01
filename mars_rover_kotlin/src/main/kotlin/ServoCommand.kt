data class ResultRover(val rover: Rover, val error: String? = null)

class ServoCommand(
    private val rover: Rover,
) {
    fun moveRover(commands: String): ResultRover =
        commands.fold(ResultRover(rover)) { acc, char ->
            val command = Command.from(char) ?: return@fold acc

            val newRover = acc.rover.apply(command)
            if (newRover.hasHitRock()) return ResultRover(rover, "collision with rock")

            ResultRover(newRover)
        }
}
