package com.shinjaehun.graphsudoku.domain

import com.shinjaehun.graphsudoku.computationlogic.buildNewSudoku
import java.util.*
import kotlin.collections.LinkedHashMap

data class SudokuPuzzle(
    val boundary: Int,
    val difficulty: Difficulty,
    val graph: LinkedHashMap<Int, LinkedList<SudokuNode>>
        = buildNewSudoku(boundary, difficulty).graph,
    var elapsedTime: Long = 0L
) : java.io.Serializable {
    fun getValue(): LinkedHashMap<Int, LinkedList<SudokuNode>> = graph
}
