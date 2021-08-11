package com.example.tictactoe

import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.Color.colorToHSV
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var PLAYER: Boolean = true
    private var TURN_COUNT = 0
    var boardStatus = Array(3){Array(3){-1}}

    //{IntArray(3)}
    private lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        //to force disallow dark theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        initializeBoardStatus()

        for(i in board) {
            for(button in i) {
                button.setOnClickListener(this)
            }
        }
        resetButton.setOnClickListener {
            initializeBoardStatus()
            TURN_COUNT = 0
            PLAYER = true
            updateDisplay("PLAYER X TURN")
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2) {
            for(j in 0..2) {
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
                board[i][j].setBackgroundColor(Color.rgb(42, 42, 42))
                board[i][j].setTextColor(Color.rgb(3, 218, 197))
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.button1 -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)
            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)
            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)
            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)
            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)
            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)
            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)
            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)
            }
        }
        checkWinner()
        PLAYER = !PLAYER
        TURN_COUNT++
        if(displayTV.text.contains("WINNER"))
            return
        if(PLAYER) {
            updateDisplay("PLAYER X TURN")
        } else {
            updateDisplay("PLAYER O TURN")
        }
        if(TURN_COUNT == 9)
            updateDisplay("GAME DRAW")
    }

    private fun updateDisplay(s: String) {
        displayTV.text = s
        Log.d(TAG, "updateDisplay: $s")
        if(s.contains("WINNER"))
            disableButtons()
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text: String = if(player) "X" else "O"
        val value: Int = if(player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
            setBackgroundColor(Color.DKGRAY)
        }
        boardStatus[row][col] = value

    }

    private fun checkWinner() {
        //horizontal rows
        for(i in 0..2) {
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if(boardStatus[i][0] == 1) {
                    updateDisplay("PLAYER X IS WINNER")
                    board[i][0].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    board[i][1].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    board[i][2].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    break
                } else if(boardStatus[i][0] == 0) {
                    updateDisplay("PLAYER O IS WINNER")
                    board[i][0].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    board[i][1].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    board[i][2].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    break
                }
            }
        }
        //vertical columns
        for(i in 0..2) {
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if(boardStatus[0][i] == 1) {
                    updateDisplay("PLAYER X IS WINNER")
                    board[0][i].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    board[1][i].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    board[2][i].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    break
                } else if(boardStatus[0][i] == 0) {
                    updateDisplay("PLAYER O IS WINNER")
                    board[0][i].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    board[1][i].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    board[2][i].apply{
                        setBackgroundColor(Color.rgb(1, 135, 134))
                        setTextColor(Color.WHITE)
                    }
                    break
                }
            }
        }
        //diagonal
        if(boardStatus[1][1] == boardStatus[0][0] && boardStatus[1][1] == boardStatus[2][2]) {
            if(boardStatus[1][1] == 1) {
                updateDisplay("PLAYER X IS WINNER")
                board[0][0].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
                board[1][1].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
                board[2][2].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
            } else if(boardStatus[1][1] == 0) {
                updateDisplay("PLAYER O IS WINNER")
                board[0][0].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
                board[1][1].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
                board[2][2].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
            }

        } else if(boardStatus[1][1] == boardStatus[0][2] && boardStatus[1][1] == boardStatus[2][0]) {
            if(boardStatus[1][1] == 1) {
                updateDisplay("PLAYER X IS WINNER")
                board[0][2].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
                board[1][1].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
                board[2][0].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
            } else if(boardStatus[1][1] == 0) {
                updateDisplay("PLAYER O IS WINNER")
                board[0][2].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
                board[1][1].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
                board[2][0].apply{
                    setBackgroundColor(Color.rgb(1, 135, 134))
                    setTextColor(Color.WHITE)
                }
            }

        }

    }

    private fun disableButtons() {
        for(i in 0..2) {
            for(j in 0..2) {
                board[i][j].isEnabled = false
            }
        }
    }
}