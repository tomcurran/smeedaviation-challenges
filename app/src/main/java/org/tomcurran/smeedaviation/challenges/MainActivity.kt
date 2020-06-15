package org.tomcurran.smeedaviation.challenges

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import org.tomcurran.smeedaviation.challenges.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        if (intent.data != null) {
            val error = intent.data?.getQueryParameter("error")
            if (error != null && error.isNotBlank()) {
                snackMessage("Auth error: $error") // e.g access_denied
            }

            val code = intent.data?.getQueryParameter("code")
            val scope = intent.data?.getQueryParameter("scope")
            if (code != null && code.isNotBlank() && scope != null && scope.isNotBlank()) {
                snackMessage("Code=$code, Scope=$scope")
            } else {
                snackMessage("Unexpected error")
            }

            // then post to get tokens
        } else {
            // ??
        }
    }

    private fun snackMessage(message: String) {
        val content = findViewById<View>(android.R.id.content)
        Snackbar.make(content, message, Snackbar.LENGTH_SHORT).show()
    }
}