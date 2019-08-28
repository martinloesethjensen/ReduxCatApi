package dk.martin.reduxcatapi

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import dk.martin.reduxcatapi.actions.NextCatImageAction
import dk.martin.reduxcatapi.reducers.nextCatImageReducer
import dk.martin.reduxcatapi.selectors.Selectors
import dk.martin.reduxcatapi.state.AppState
import org.rekotlin.Store
import org.rekotlin.StoreSubscriber

val mainStore = Store(
    reducer = ::nextCatImageReducer,
    state = null
)

class MainActivity : AppCompatActivity(), StoreSubscriber<AppState> {

    private val buttonNextCatImage: Button by lazy {
        this.findViewById(R.id.button) as Button
    }

    private val imageView: ImageView by lazy {
        this.findViewById(R.id.imageView) as ImageView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.buttonNextCatImage.setOnClickListener {
            mainStore.dispatch(NextCatImageAction())
        }

        mainStore.subscribe(this)
    }

    override fun newState(state: AppState) {
        this.imageView.load(state.catImageUrl) {
            crossfade(true)
        }

        Selectors.getFavoriteCount(state)
    }
}
