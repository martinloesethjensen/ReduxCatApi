package dk.martin.reduxcatapi.view

import android.content.Context
import android.view.View
import dk.martin.reduxcatapi.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

class HomeViewUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        constraintLayout {
            val name = editText("Write something here.") {
            }
            button("Say Hello") {
                onClick { ctx.toast("Hello, ${name.text}!") }
            }
        }
    }

}