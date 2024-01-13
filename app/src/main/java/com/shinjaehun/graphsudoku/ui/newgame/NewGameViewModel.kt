package com.shinjaehun.graphsudoku.ui.newgame

import com.shinjaehun.graphsudoku.domain.UserStatistics
import com.shinjaehun.graphsudoku.domain.Settings

class NewGameViewModel {
    //values don't change while this feature is active, so there's no need for pub/sub
    internal lateinit var settingsState: Settings
    internal lateinit var statisticsState: UserStatistics
    internal var loadingState: Boolean = true
        set(value) {
            field = value
            subLoadingState?.invoke(field)
        }

    internal var subLoadingState: ((Boolean) -> Unit)? = null
}