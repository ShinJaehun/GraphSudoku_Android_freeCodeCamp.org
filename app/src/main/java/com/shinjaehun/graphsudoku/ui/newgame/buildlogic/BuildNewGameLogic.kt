package com.shinjaehun.graphsudoku.ui.newgame.buildlogic

import android.content.Context
import com.shinjaehun.graphsudoku.common.ProductionDispatcherProvider
import com.shinjaehun.graphsudoku.persistence.*
import com.shinjaehun.graphsudoku.persistence.settingsDataStore
import com.shinjaehun.graphsudoku.ui.newgame.NewGameContainer
import com.shinjaehun.graphsudoku.ui.newgame.NewGameLogic
import com.shinjaehun.graphsudoku.ui.newgame.NewGameViewModel


internal fun buildNewGameLogic(
    container: NewGameContainer,
    viewModel: NewGameViewModel,
    context: Context
): NewGameLogic {
    return NewGameLogic(
        container,
        viewModel,
        GameRepositoryImpl(
            LocalGameStorageImpl(context.filesDir.path),
            LocalSettingsStorageImpl(context.settingsDataStore)
        ),
        LocalStatisticsStorageImpl(
            context.statsDataStore
        ),
        ProductionDispatcherProvider
    )
}