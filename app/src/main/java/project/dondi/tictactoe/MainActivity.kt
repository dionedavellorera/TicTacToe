package project.dondi.tictactoe

import android.content.res.Configuration
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import project.dondi.tictactoe.R.id.email
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import com.google.firebase.database.DataSnapshot



class MainActivity : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference()
    var myEmail :String? = null

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCells = ArrayList<Int>()
    var activePlayer = 1
    var winner = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bundle: Bundle = intent.extras
        myEmail = bundle.getString("email")


        incomingInvitation()

        request.setOnClickListener(View.OnClickListener {
            var enemyEmail = name.text.toString()
            myRef.child("Users").child(enemyEmail).child("Request").push().setValue(myEmail)
            playerOnline(myEmail+ enemyEmail)
            playerSymbol = "X"
        })

        accept.setOnClickListener(View.OnClickListener {
            var enemyEmail = name.text.toString()
            myRef.child("Users").child(enemyEmail).child("Request").push().setValue(myEmail)
            playerOnline(enemyEmail+ myEmail)
            playerSymbol = "O"
        })
        var cellId = 0
        b1.setOnClickListener(View.OnClickListener {
            cellId = 1
            clicked(cellId.toString())
        })
        b2.setOnClickListener(View.OnClickListener {
            cellId = 2
            clicked(cellId.toString())
        })
        b3.setOnClickListener(View.OnClickListener {
            cellId = 3
            clicked(cellId.toString())
        })
        b4.setOnClickListener(View.OnClickListener {
            cellId = 4
            clicked(cellId.toString())
        })
        b5.setOnClickListener(View.OnClickListener {
            cellId = 5
            clicked(cellId.toString())
        })
        b6.setOnClickListener(View.OnClickListener {
            cellId = 6
            clicked(cellId.toString())
        })
        b7.setOnClickListener(View.OnClickListener {
            cellId = 7
            clicked(cellId.toString())
        })
        b8.setOnClickListener(View.OnClickListener {
            cellId = 8
            clicked(cellId.toString())
        })
        b9.setOnClickListener(View.OnClickListener {
            cellId = 9
            clicked(cellId.toString())
        })
    }




    fun clicked(cellId: String) {
        myRef.child("PlayerOnline").child(sessionId).child(cellId).setValue(myEmail)
    }


    fun playGame(cellId: Int, selectedButton: Button) {

        Log.d("ACTIVEPLAYEr", activePlayer.toString())
        if (activePlayer == 1) {
            selectedButton.text = "X"
            selectedButton.setBackgroundColor(Color.GREEN)
            player1.add(cellId)
            activePlayer = 2
        } else {
            selectedButton.text = "O"
            selectedButton.setBackgroundColor(Color.BLUE)
            player2.add(cellId)
            activePlayer = 1
        }

        selectedButton.isEnabled  = false


        checkWinner(selectedButton)
    }

    fun checkWinner(btn: Button) {

        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }

        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }

        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }

        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner != -1) {
            if (winner == 1) {
                Toast.makeText(this, "player 1 won", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "player 2 won", Toast.LENGTH_SHORT).show()
            }
            defaults()
        }
    }




    private fun defaults() {
        activePlayer = 1
        player1.clear()
        player2.clear()
        emptyCells.clear()
        myRef.child("PlayerOnline").child(sessionId).removeValue()
        winner = -1
        b1.setText("")
        b1.isEnabled = true
        b1.setBackgroundColor(Color.WHITE)

        b2.setText("")
        b2.isEnabled = true
        b2.setBackgroundColor(Color.WHITE)

        b3.setText("")
        b3.isEnabled = true
        b3.setBackgroundColor(Color.WHITE)

        b4.setText("")
        b4.isEnabled = true
        b4.setBackgroundColor(Color.WHITE)

        b5.setText("")
        b5.isEnabled = true
        b5.setBackgroundColor(Color.WHITE)

        b6.setText("")
        b6.isEnabled =true
        b6.setBackgroundColor(Color.WHITE)

        b7.setText("")
        b7.isEnabled = true
        b7.setBackgroundColor(Color.WHITE)

        b8.setText("")
        b8.isEnabled = true
        b8.setBackgroundColor(Color.WHITE)

        b9.setText("")
        b9.isEnabled = true
        b9.setBackgroundColor(Color.WHITE)

    }

    fun incomingInvitation() {
        myRef.child("Users").child(myEmail).child("Request")
                .addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        try {
                            val td = dataSnapshot!!.value as HashMap<String, Any>
                            if (td != null) {
                                var value: String
                                for (key in td.keys) {
                                    value = td[key] as String
                                    name.setText(value)
                                    myRef.child("Users").child(myEmail).child("Request").setValue(true)
                                    break

                                }
                            }
                        }catch (ex: Exception) {

                        }
                    }
                })
    }
    var sessionId: String? = null
    var playerSymbol: String? = null
    fun playerOnline(sessionID:String){
        this.sessionId=sessionID
        Log.d("WATAFAK", sessionId)

        myRef.child("PlayerOnline").child(sessionID)
                .addValueEventListener(object:ValueEventListener{
                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        try{
                            player1.clear()
                            player2.clear()
                            for (postSnapshot in dataSnapshot!!.getChildren()) {
                                Log.d("DDD", postSnapshot.value.toString())
                                Log.d("DDD", postSnapshot.key.toString())

//                                value= td[key] as String

                                if(postSnapshot.value.toString().equals(myEmail)){
                                    activePlayer= if(playerSymbol==="X") 1 else 2
                                }else{
                                    activePlayer= if(playerSymbol==="X") 2 else 1
                                }

                                autoPlay(postSnapshot.key.toInt())

                            }
//                            if(td!=null){
//
//                                var value:String
//                                for (key in td.keys){

//
//
//                                }
//
//                            }

                        }catch (ex:Exception){
                            Log.d("THISVAL", ex.message)
                        }
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                })

    }

    fun autoPlay(cellId: Int) {
        var selectedButton: Button
        when(cellId) {
            1-> {
                selectedButton = b1
            }
            2-> {
                selectedButton = b2
            }
            3-> {
                selectedButton = b3
            }
            4-> {
                selectedButton = b4
            }
            5-> {
                selectedButton = b5
            }
            6-> {
                selectedButton = b6
            }
            7-> {
                selectedButton = b7
            }
            8-> {
                selectedButton = b8
            }
            9-> {
                selectedButton = b9
            }
            else -> {
                selectedButton = b1
            }
        }

        playGame(cellId, selectedButton)
    }

    class Test {
        var str: String? = null
        var str2: String? = null
        constructor(str:String, str2:String) {
            this.str = str
            this.str2 = str2
        }
    }
}
