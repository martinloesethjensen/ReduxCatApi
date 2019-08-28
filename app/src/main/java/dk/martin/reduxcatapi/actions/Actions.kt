package dk.martin.reduxcatapi.actions

import org.rekotlin.Action

data class NextCatImageAction(val unit: Unit = Unit) : Action
data class CountIncrementAction(val unit: Unit = Unit) : Action
data class CountDecrementAction(val unit: Unit = Unit) : Action
