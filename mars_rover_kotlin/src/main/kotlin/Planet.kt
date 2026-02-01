data class Rock(
    val x: Int,
    val y: Int,
)

data class Planet(
    val size: Pair<Int, Int>,
    val rocks: List<Rock>,
)
