package dk.martin.reduxcatapi.view

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.squareup.contour.ContourLayout
import dk.martin.reduxcatapi.MainActivity
import org.jetbrains.anko.colorAttr
import kotlin.contracts.ExperimentalContracts

@SuppressLint("SetTextI18n")
class MainActivityView(context: MainActivity) : ContourLayout(context) {

    private val catImageView = ImageView(context).apply {
        load("https://cdn2.thecatapi.com/images/3a8.jpg") {
            crossfade(true)
            crossfade(250)
            transformations(RoundedCornersTransformation(35f))
        }
    }

    private val nextButton = Button(context).apply {
        text = "Next"
        textSize = 20f
    }

    override fun onInitializeLayout() {
        catImageView.applyLayout(
            centerHorizontallyTo {
                parent.centerX()
            }.widthOf { 150.dip.toXInt() },
            topTo {
                parent.top() - 50.dip
            }.heightOf { 500.dip.toYInt() }
        )
        nextButton.applyLayout(
            centerHorizontallyTo {
                parent.centerX()
            },
            bottomTo { parent.bottom() - 150.dip }
        )
    }

}
