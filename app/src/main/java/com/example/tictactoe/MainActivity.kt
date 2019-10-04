package com.example.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R.id
import com.example.tictactoe.R.layout

class MainActivity : AppCompatActivity(), OnClickListener {
    private val buttons = Array(3) { arrayOfNulls<Button?>(3) }
    private var xturn = true
    private var roundcount = 0
    private var xpoints = 0
    private var opoints = 0
    private var x1: TextView? = null
    private var o1: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        x1 = findViewById<TextView?>(id.xwins)
        o1 = findViewById<TextView?>(id.owins)
        for (i in 0..2) {
            for (j in 0..2) {
                val ButtonID = "button$i$j"
                val resID = resources.getIdentifier(ButtonID, "id", packageName)
                buttons[i][j] = findViewById<Button?>(resID)
                buttons[i][j]!!.setOnClickListener(this)
            }
        }
        val buttonreset: Button = findViewById(id.reset)
        buttonreset.setOnClickListener {
            resetgame()
        }
    }

    override fun onClick(view: View) {
        if ((view as Button).text.toString() != "") {
            return
        } else if (xturn) {
            view.text = "X"
            view.setTextColor(Color.RED)

        } else {
            view.text = "O"
            view.setTextColor(Color.MAGENTA)
        }
        roundcount++
        if (checkwin()) {
            if (xturn) {
                xWins()
            } else {
                oWins()
            }
        } else if (roundcount == 9) {
            draw()
        } else {
            xturn = !xturn
        }
    }

    private fun checkwin(): Boolean {
        val map = Array(3) { arrayOfNulls<String?>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                map[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (map[i][0] == map[i][1] && map[i][0] == map[i][2] && map[i][0] != "") {
                return true
            }
            if (map[0][i] == map[1][i] && map[0][i] == map[2][i] && map[0][i] != "") {
                return true
            }
        }
        if (map[0][0] == map[1][1] && map[0][0] == map[2][2] && map[0][0] != "") {
            return true
        }
        if (map[0][2] == map[1][1] && map[0][2] == map[2][0] && map[0][2] != "") {
            return true
        }
        return false
    }

    private fun draw() {
        Toast.makeText(this, "DRAW", Toast.LENGTH_LONG).show()
        rest()
    }

    private fun oWins() {
        opoints++
        Toast.makeText(this, "O WINS", Toast.LENGTH_LONG).show()
        updatepoints()
        rest()
    }

    private fun xWins() {
        xpoints++
        Toast.makeText(this, "X WINS", Toast.LENGTH_LONG).show()
        updatepoints()
        rest()
    }

    private fun rest() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]!!.text = ""
            }
        }
        roundcount = 0
        xturn = true
    }

    private fun updatepoints() {
        x1!!.text = "X Points:$xpoints"
        o1!!.text = "O Points:$opoints"
    }

    private fun resetgame() {
        xpoints = 0
        opoints = 0
        updatepoints()
        rest()
    }
}