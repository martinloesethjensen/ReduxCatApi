package dk.martin.reduxcatapi.selectors

import dk.martin.reduxcatapi.state.AppState

class Selectors {
    companion object {
        fun getFavoriteCount(state: AppState): Int {
            return state.favorites.count()
        }

        fun getLikesCount(state: AppState): Int {
            return state.likesCount
        }
    }
}