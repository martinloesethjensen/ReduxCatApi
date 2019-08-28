package dk.martin.reduxcatapi.state

import org.rekotlin.StateType

data class AppState(
    val catImageUrl: String = "https://cdn2.thecatapi.com/images/3a8.jpg",
    val favorites: List<String> = listOf(),
    val likesCount: Int = 0
) : StateType
