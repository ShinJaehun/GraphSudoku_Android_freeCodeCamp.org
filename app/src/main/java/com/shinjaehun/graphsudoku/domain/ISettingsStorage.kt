package com.shinjaehun.graphsudoku.domain

interface ISettingsStorage {
    suspend fun getSettings(): SettingsStorageResult
    suspend fun updateSettings(settings: Settings): SettingsStorageResult

}

sealed class SettingsStorageResult {
    data class OnSuccess(val settings: Settings) : SettingsStorageResult()
    object OnComplete : SettingsStorageResult() // 헐... 이건 강의에 없었고 github 코드에만 있음...
    data class OnError(val exception: Exception) : SettingsStorageResult()
}