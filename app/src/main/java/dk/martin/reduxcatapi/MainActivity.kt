package dk.martin.reduxcatapi

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import coil.transform.RoundedCornersTransformation
import dk.martin.reduxcatapi.actions.CountDecrementAction
import dk.martin.reduxcatapi.actions.CountIncrementAction
import dk.martin.reduxcatapi.actions.NextCatImageAction
import dk.martin.reduxcatapi.reducers.nextCatImageReducer
import dk.martin.reduxcatapi.selectors.Selectors
import dk.martin.reduxcatapi.state.AppState
import org.rekotlin.Store
import org.rekotlin.StoreSubscriber
import kotlinx.android.synthetic.main.activity_main.*
import com.special.ResideMenu.ResideMenu
import com.special.ResideMenu.ResideMenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import okhttp3.Route


val mainStore = Store(
    reducer = ::nextCatImageReducer,
    state = null
)

class MainActivity : AppCompatActivity(), StoreSubscriber<AppState> {

    private lateinit var resideMenu: ResideMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Anko
        //HomeViewUI().setContentView(this)

        // Contour
        //setContentView(MainActivityView(this))

        setupMenuItems()

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

    private fun setupMenuItems() {
        // attach to current activity;
        resideMenu = ResideMenu(this)
        resideMenu.attachToActivity(this)
        resideMenu.setBackground(R.color.design_default_color_primary)
        resideMenu.setShadowVisible(false)
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        // create menu items;
        val titles = arrayOf("Home", "Profile")
        val icon = intArrayOf(
            R.drawable.ic_launcher,
            R.drawable.ic_launcher_background
        )

        val homeItem = ResideMenuItem(this, R.drawable.ic_launcher, "Home")
        val favoritesItem = ResideMenuItem(this, R.drawable.ic_launcher_background, "Favorites")
        resideMenu.addMenuItem(homeItem, ResideMenu.DIRECTION_LEFT)
        resideMenu.addMenuItem(favoritesItem, ResideMenu.DIRECTION_LEFT)

        homeItem.setOnClickListener {
            Toast.makeText(applicationContext, "Home", Toast.LENGTH_LONG).show()

        }

        favoritesItem.setOnClickListener {
            Toast.makeText(applicationContext, "Favorites", Toast.LENGTH_LONG).show()
        }
    }

    private fun changeFragment(targetFragment: Fragment) {
        resideMenu.clearIgnoredViewList()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment, targetFragment, "fragment")
            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
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

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return resideMenu.dispatchTouchEvent(ev)
    }
}
