package com.example.mytictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mytictactoe.utils.*
import kotlinx.android.synthetic.main.fragment_board.*

class BoardFragment(val onNavigationListener: OnNavigationListener) : Fragment() {

    var activePlayer = ""
    var currentBoard = board

    private var player1: String? = null
    private var player2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            player1 = it.getString(PLAYER1_PARAM)
            player2 = it.getString(PLAYER2_PARAM)
            activePlayer = player1.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showCurrentPlayerName("$PLAYER_TURN_TEXT")
        button1.setOnClickListener { boardClick(it) }
        button2.setOnClickListener { boardClick(it) }
        button3.setOnClickListener { boardClick(it) }
        button4.setOnClickListener { boardClick(it) }
        button5.setOnClickListener { boardClick(it) }
        button6.setOnClickListener { boardClick(it) }
        button7.setOnClickListener { boardClick(it) }
        button8.setOnClickListener { boardClick(it) }
        button9.setOnClickListener { boardClick(it) }
    }

    private fun showCurrentPlayerName(status: String) {
        tvCurrentPlayer.text = activePlayer + status
    }

    private fun playGame(cellId: Int, buttonSelected: Button) {
        if (activePlayer == player1) {
            buttonSelected.text = BoardState.X.toString()
            buttonSelected.background = context?.getDrawable(R.drawable.button_choco)
            move(cellId, BoardState.X)
        } else {
            buttonSelected.text = BoardState.O.toString()
            buttonSelected.background = context?.getDrawable(R.drawable.button_grey)
            move(cellId, BoardState.O)
        }
    }

    private fun move(cellId: Int, state: BoardState) {
        val pos = boardCoordinate[cellId]
        pos?.apply {
            currentBoard[pos[0]][pos[1]] = state
            val haveAWinner = winCalculation(pos[0], pos[1], state)
            if (!haveAWinner) {
                switchPlayer()
                showCurrentPlayerName("$PLAYER_TURN_TEXT")
            }
        }
    }

    private fun switchPlayer() {
        if (activePlayer == player1) {
            activePlayer = player2!!
        } else {
            activePlayer = player1!!
        }
    }

    private fun winCalculation(currXPos: Int, currYPos: Int, state: BoardState): Boolean {
        for (i in 0..2) {
            if (currentBoard[currXPos][i] != state) {
                break
            }
            if (i == 2) {
                showWinner()
                return true
            }

        }
        for (i in 0..2) {
            if (currentBoard[i][currYPos] != state) {
                break
            }
            if (i == 2) {
                showWinner()
                return true
            }
        }
        return false
    }

    private fun showWinner() {
        showCurrentPlayerName("$WINNER_TEXT")
        clearBoard()
    }

    private fun clearBoard() {
        currentBoard = board
        button1.setText("")
        button1.setBackgroundResource(R.color.white)
        button2.setText("")
        button2.setBackgroundResource(R.color.white)
        button3.setText("")
        button3.setBackgroundResource(R.color.white)
        button4.setText("")
        button4.setBackgroundResource(R.color.white)
        button5.setText("")
        button5.setBackgroundResource(R.color.white)
        button6.setText("")
        button6.setBackgroundResource(R.color.white)
        button7.setText("")
        button7.setBackgroundResource(R.color.white)
        button8.setText("")
        button8.setBackgroundResource(R.color.white)
        button9.setText("")
        button9.setBackgroundResource(R.color.white)
    }

    companion object {

        @JvmStatic
        fun newInstance(player1: String,
                        player2: String,
                        navigationListener: OnNavigationListener) =
            BoardFragment(navigationListener).apply {
                arguments = Bundle().apply {
                    putString(PLAYER1_PARAM, player1)
                    putString(PLAYER2_PARAM, player2)
                }
            }
    }

    fun boardClick(v: View?) {
        var cellId = 0
        val buttonSelected = v as Button
        when (v?.id) {
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }

        playGame(cellId, buttonSelected)
    }
}