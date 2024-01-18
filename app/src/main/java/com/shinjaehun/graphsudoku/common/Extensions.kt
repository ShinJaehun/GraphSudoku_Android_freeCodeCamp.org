package com.shinjaehun.graphsudoku.common

import android.app.Activity
import android.widget.Toast
import com.shinjaehun.graphsudoku.R
import com.shinjaehun.graphsudoku.domain.Difficulty

internal fun Activity.makeToast(message: String) {
    // "internal" visibility is good for code that needs to be accessed in many
    // different packages, but not by external programs/modules.
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()
}

internal fun Long.toTime(): String {
    if(this >= 3600) return "+59:59" // this가 Long이지...
    var minutes = ((this % 3600) / 60).toString()
    if(minutes.length == 1) minutes = "0$minutes"
    var seconds = (this % 60).toString()
    if(seconds.length == 1) seconds = "0$seconds"
    return String.format("$minutes:$seconds")
}

internal val Difficulty.toLocalizedResource: Int
    get(){
        return when(this){ // 당연히 this는 Difficulty
            Difficulty.EASY -> R.string.easy
            Difficulty.MEDIUM -> R.string.medium
            Difficulty.HARD -> R.string.hard
        }
    }