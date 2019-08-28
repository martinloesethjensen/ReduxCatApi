package dk.martin.reduxcatapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import coil.transform.RoundedCornersTransformation
import dk.martin.reduxcatapi.actions.CountDecrementAction
import dk.martin.reduxcatapi.actions.CountIncrementAction
import dk.martin.reduxcatapi.actions.NextCatImageAction
import dk.martin.reduxcatapi.reducers.nextCatImageReducer
import dk.martin.reduxcatapi.selectors.Selectors
import dk.martin.reduxcatapi.state.AppState
import kotlinx.android.synthetic.main.activity_main.*
import org.rekotlin.Store
import org.rekotlin.StoreSubscriber

val mainStore = Store(
    reducer = ::nextCatImageReducer,
    state = null
)

class MainActivity : AppCompatActivity(), StoreSubscriber<AppState> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonNextCatImage.setOnClickListener {
            mainStore.dispatch(NextCatImageAction())
        }

        incrementButton.setOnClickListener {
            mainStore.dispatch(NextCatImageAction())
            mainStore.dispatch(CountIncrementAction())
        }

        decrementButton.setOnClickListener {
            mainStore.dispatch(NextCatImageAction())
            mainStore.dispatch(CountDecrementAction())
        }

        mainStore.subscribe(this)
    }

    override fun newState(state: AppState) {
        catImageView.load(state.catImageUrl) {
            crossfade(true)
            crossfade(500)
            transformations(RoundedCornersTransformation(35f))
        }

        likesTextView.text = "Likes: ${Selectors.getLikesCount(state)}"

        //Selectors.getFavoriteCount(state)
    }
}
