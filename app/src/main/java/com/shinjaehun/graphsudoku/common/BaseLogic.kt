package com.shinjaehun.graphsudoku.common

import kotlinx.coroutines.Job

abstract class BaseLogic<EVENT> {
    // this will be used for presentation logic classes within the "ui" package.
    protected lateinit var jobTracker: Job
    abstract fun onEvent(event: EVENT)
}