package com.shinjaehun.graphsudoku.common

import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    fun provideUIContext(): CoroutineContext
    // coroutines != threads, but we can use these dispatchers to tell
    // our coroutines which threads to run on

    fun provideIOContext(): CoroutineContext
}