package com.example.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var xTurn: Boolean = true
    private var boxes = arrayOfNulls<String>(9)
    private var winner: String? = null
    private var count = 0
    private var scoreX = 0
    private var scoreY = 0
    private val lines: Array<IntArray> = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val x = "X"
        button10.text = x
        val y = "O"
        button11.text = y
        button10.setBackgroundColor(Color.GREEN)
        button11.setBackgroundColor(Color.RED)
        textViewX.text = scoreX.toString()
        textViewY.text = scoreY.toString()
        button1.setOnClickListener { doSomething(button1, 1) }
        button2.setOnClickListener { doSomething(button2, 2) }
        button3.setOnClickListener { doSomething(button3, 3) }
        button4.setOnClickListener { doSomething(button4, 4) }
        button5.setOnClickListener { doSomething(button5, 5) }
        button6.setOnClickListener { doSomething(button6, 6) }
        button7.setOnClickListener { doSomething(button7, 7) }
        button8.setOnClickListener { doSomething(button8, 8) }
        button9.setOnClickListener { doSomething(button9, 9) }
        button10.setOnClickListener {
            xTurn = true
            button10.setBackgroundColor(Color.GREEN)
            button11.setBackgroundColor(Color.RED)
        }
        button11.setOnClickListener {
            xTurn = false
            button11.setBackgroundColor(Color.GREEN)
            button10.setBackgroundColor(Color.RED)
        }
        button12.setOnClickListener { restartGame() }
    }

    private fun restartGame() {
        newGame()
        scoreX = 0
        scoreY = 0
        textViewX.text = scoreX.toString()
        textViewY.text = scoreY.toString()
        xTurn = true
        button10.setBackgroundColor(Color.GREEN)
        button11.setBackgroundColor(Color.RED)
    }

    private fun newGame() {
        boxes = arrayOfNulls(9)
        winner = null
        count = 0
        button1.isClickable = true
        button2.isClickable = true
        button3.isClickable = true
        button4.isClickable = true
        button5.isClickable = true
        button6.isClickable = true
        button7.isClickable = true
        button8.isClickable = true
        button9.isClickable = true

        button1.text = ""
        button2.text = ""
        button3.text = ""
        button4.text = ""
        button5.text = ""
        button6.text = ""
        button7.text = ""
        button8.text = ""
        button9.text = ""
    }

    private fun doSomething(button: Button?, id: Int) {

        val temp = if (xTurn) {
            "X"
        } else {
            "O"
        }
        button!!.text = temp
        boxes[id - 1] = temp
        winner = calculateWinner()
        button.isClickable = false
        xTurn = !xTurn
        if (xTurn) {
            button10.setBackgroundColor(Color.GREEN)
            button11.setBackgroundColor(Color.RED)
        } else {
            button11.setBackgroundColor(Color.GREEN)
            button10.setBackgroundColor(Color.RED)
        }

        count++
        if (count > 0) {
            button10.isClickable = false
            button11.isClickable = false
        }
        if (count == 9 && winner == null) {
            winner = "Nobody"
            val js = winner + "wins"
            textView1.text = js
            newGame()
        }
        if (winner != null) {
            if (winner == "X") {
                scoreX++
            } else if (winner == "O") {
                scoreY++
            }
            textViewX.text = scoreX.toString()
            textViewY.text = scoreY.toString()
            val js = winner + "wins"
            textView1.text = js
            newGame()
        }


    }

    private fun calculateWinner(): String? {

        for (i in lines.indices) {
            val arr = lines[i]
            val a = arr[0]
            val b = arr[1]
            val c = arr[2]
            if (boxes[a] == boxes[b] && boxes[a] == boxes[c]) {
                return boxes[a]
            }
        }
        return null
    }

}