package project.dondi.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_credentials.*

import android.content.Intent
import android.text.TextUtils
import com.google.firebase.database.FirebaseDatabase


class CredentialsActivity : AppCompatActivity() {
    var mAuth = FirebaseAuth.getInstance();

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credentials)


    }

    fun buttonClick(view: View) {
        var selected = view as Button
        when (selected.id) {
            R.id.signup -> {
                mAuth.createUserWithEmailAndPassword(email.text.toString(),
                        password.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(FragmentActivity.TAG, "createUserWithEmail:success")
                                val user = mAuth.currentUser
                                Log.d("MYLOGIN", user.toString())
                                saveAndStart()
//                        updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
//                        Log.w(FragmentActivity.TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(this@CredentialsActivity, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show()
                                Log.d("MYLOGIN", "Authentication failed")
//                        updateUI(null)
                            }

                            // ...
                        }
            }
            R.id.signin -> {
                mAuth.signInWithEmailAndPassword(email.text.toString(),
                                                password.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                saveAndStart()
                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(FragmentActivity.TAG, "signInWithEmail:success")
//                                val user = mAuth.currentUser
//                                updateUI(user)
                            } else {
                                Toast.makeText(this, "authentication failed", Toast.LENGTH_SHORT).show()
                                // If sign in fails, display a message to the user.
//                                Log.w(FragmentActivity.TAG, "signInWithEmail:failure", task.exception)
//                                Toast.makeText(this@EmailPasswordActivity, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show()
//                                updateUI(null)
                            }

                            // ...
                        }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        saveAndStart()
//        updateUI(currentUser)
    }

    private fun saveAndStart() {
        val currentUser = mAuth.currentUser
        Log.d("MYLOGIN", currentUser.toString())
        if (currentUser != null) {
            myRef.child("Users").child(currentUser.email!!.split("@")[0]).child("Request").setValue(currentUser.uid)
            val i : Intent = Intent(this, MainActivity::class.java)
            i.putExtra("email", currentUser.email!!.split("@")[0])
            startActivity(i)
        }
    }


}
