package com.shinjaehun.graphsudoku.ui.activegame.buildlogic

import android.content.Context
import com.shinjaehun.graphsudoku.common.ProductionDispatcherProvider
import com.shinjaehun.graphsudoku.persistence.*
import com.shinjaehun.graphsudoku.persistence.settingsDataStore
import com.shinjaehun.graphsudoku.ui.activegame.ActiveGameContainer
import com.shinjaehun.graphsudoku.ui.activegame.ActiveGameLogic
import com.shinjaehun.graphsudoku.ui.activegame.ActiveGameViewModel

internal fun buildActiveGameLogic(
    container: ActiveGameContainer,
    viewModel: ActiveGameViewModel,
    context: Context
) : ActiveGameLogic {
    return ActiveGameLogic(
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