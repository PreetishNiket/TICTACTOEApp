package com.example.tictactoe

import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {
    private val buttons = Array(3) { arrayOfNulls<Button?>(3) }
    private var xturn = true
    private var roundcount = 0
    private var xpoints = 0
    private var opoints = 0
    private var drawPoints=0
    private var x1: TextView? = null
    private var o1: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        supportActionBar?.hide()
        x1 = findViewById(id.xwins)
        o1 = findViewById(id.owins)

        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById<Button?>(resID)
                buttons[i][j]!!.setOnClickListener(this)
            }
        }
        val buttonReset: Button = findViewById(id.reset)
        buttonReset.setOnClickListener {
            resetGame()
        }
        settings.setOnClickListener {
            startActivity(Intent(this,SettingsActivity::class.java))
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
        drawPoints++
        draw.text="Draw:$drawPoints"
        Toast.makeText(this, "DRAW", Toast.LENGTH_LONG).show()
        rest()
    }

    private fun oWins() {
        opoints++
        Toast.makeText(this, "O WINS", Toast.LENGTH_LONG).show()
        updatePoints()
        rest()
    }

    private fun xWins() {
        xpoints++
        Toast.makeText(this, "X WINS", Toast.LENGTH_LONG).show()
        updatePoints()
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

    private fun updatePoints() {
        if (xpoints==0)
        {
            x1!!.text = "X Wins:"
        }
        else
        {
            x1!!.text = "X Wins:$xpoints"
        }
        if (opoints==0)
        {
            o1!!.text = "O Wins:"
        }
        else{
            o1!!.text = "O Wins:$opoints"
        }
    }

    private fun resetGame() {
        xpoints = 0
        opoints = 0
        draw.text="Draw:"
        updatePoints()
        rest()
    }
}