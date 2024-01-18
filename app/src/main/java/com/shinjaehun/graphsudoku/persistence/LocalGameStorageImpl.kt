package com.shinjaehun.graphsudoku.persistence

import com.shinjaehun.graphsudoku.domain.GameStorageResult
import com.shinjaehun.graphsudoku.domain.IGameDataStorage
import com.shinjaehun.graphsudoku.domain.SudokuPuzzle
import com.shinjaehun.graphsudoku.domain.getHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*

private const val FILE_NAME = "game_state.txt"

class LocalGameStorageImpl(
    fileStorageDirectory: String,
    private val pathToStorageFile: File = File(fileStorageDirectory, FILE_NAME)
) : IGameDataStorage {
    override suspend fun updateGame(game: SudokuPuzzle): GameStorageResult = withContext(Dispatchers.IO) {
        // withContext()를 왜 이렇게 쓰는지 모르겠음!
        // 일단 파일로 game을 저장할꺼니까 IO context에서 하는게 맞지 밥팅아!
        try {
            updateGameData(game)
            GameStorageResult.OnSuccess(game)
        } catch (e: Exception) {
            GameStorageResult.OnError(e)
        }
    }

    private fun updateGameData(game: SudokuPuzzle) {
        try {
            val fileOutputStream = FileOutputStream(pathToStorageFile)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(game)
            objectOutputStream.close()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun updateNode(x: Int, y: Int, color: Int, elapsedTime: Long): GameStorageResult = withContext(Dispatchers.IO) {
        try {
            val game = getGame()
            game.graph[getHash(x, y)]!!.first.color = color // 어쨌든 이 SudokuPuzzle의 graph가 실재 어떻게 생겼는지는 좀 알아보고 싶다...
            game.elapsedTime = elapsedTime
            updateGameData(game)
            GameStorageResult.OnSuccess(game)
        } catch (e: Exception) {
            GameStorageResult.OnError(e)
        }
    }

    private fun getGame(): SudokuPuzzle {
        try {
            var game: SudokuPuzzle
            val fileInputStream = FileInputStream(pathToStorageFile)
            val objectInputStream = ObjectInputStream(fileInputStream)
            game = objectInputStream.readObject() as SudokuPuzzle
            objectInputStream.close()

            return game
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getCurrentGame(): GameStorageResult = withContext(Dispatchers.IO) {
        try {
            GameStorageResult.OnSuccess(getGame())
        } catch (e: Exception) {
            GameStorageResult.OnError(e)
        }
    }
}