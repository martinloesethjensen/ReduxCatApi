package dk.martin.reduxcatapi.network

import com.github.kittinunf.fuel.httpGet
import dk.martin.reduxcatapi.model.Cat
import dk.martin.reduxcatapi.reducers.cat

object Api {
    const val BASE_URL = "https://api.thecatapi.com/v1/"

    fun getNextCatImage() {
        "${BASE_URL}images/search?category_ids=1"
            .httpGet()
            .header("x-api-key", "5c062d46-83d7-411b-ba44-e395dd7a5fed")
            .responseObject(Cat.Deserializer()) { _, _, result ->
                val catArray = result.component1()
                cat = catArray?.get(0)
            }
    }
}