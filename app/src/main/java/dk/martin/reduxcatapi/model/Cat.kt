package dk.martin.reduxcatapi.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


data class Cat(
    val breeds: List<Any>,
    val categories: List<Category>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
) {
    class Deserializer : ResponseDeserializable<Array<Cat>> {
        override fun deserialize(content: String): Array<Cat>? =
            Gson().fromJson(content, Array<Cat>::class.java)
    }
}