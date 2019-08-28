package dk.martin.reduxcatapi.reducers

import com.github.kittinunf.fuel.httpGet
import dk.martin.reduxcatapi.actions.NextCatImageAction
import dk.martin.reduxcatapi.model.Cat
import dk.martin.reduxcatapi.state.AppState
import org.rekotlin.Action

var cat: Cat? = null

fun nextCatImageReducer(action: Action, state: AppState?): AppState {
    var state = state ?: AppState()

    when (action) {
        is NextCatImageAction -> {
            getNextCatImage()
            state =
                state.copy(catImageUrl = cat?.url ?: "https://cdn2.thecatapi.com/images/3a8.jpg")
        }
    }

    return state
}

fun getNextCatImage() {
    "https://api.thecatapi.com/v1/images/search?category_ids=1"
        .httpGet()
        .header("x-api-key", "5c062d46-83d7-411b-ba44-e395dd7a5fed")
        .responseObject(Cat.Deserializer()) { _, _, result ->
            val catArray = result.component1()
            cat = catArray?.get(0)
        }
}