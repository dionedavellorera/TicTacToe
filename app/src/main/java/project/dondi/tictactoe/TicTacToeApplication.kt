package project.dondi.tictactoe

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics

class TicTacToeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseAnalytics.getInstance(this)
        println("TicTacToeApplication started")
    }
}