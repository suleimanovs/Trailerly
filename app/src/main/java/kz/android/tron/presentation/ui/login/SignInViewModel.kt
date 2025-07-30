package kz.android.tron.presentation.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kz.android.tron.R
import javax.inject.Inject

class SignInViewModel @Inject constructor() : ViewModel(), OnCompleteListener<AuthResult> {

    private val auth: FirebaseAuth = Firebase.auth

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    fun signIn(email: String, password: String) {
        checkValidate(email, password) {
            _state.value = State.Success
        }
    }

    private fun checkValidate(email: String, password: String, call: () -> Unit) {
        val resId = when {
            email.isBlank() || Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                R.string.email_error
            }

            password.isBlank() || password.length < 6 -> {
                R.string.password_error
            }

            else -> {
                call()
                return
            }
        }
        _state.value = State.Error(resId)
    }

    override fun onComplete(task: Task<AuthResult>) {
        if (task.isSuccessful) {
            Storage.putUser(auth.currentUser?.uid ?: Storage.ANONYMOUS)
            _state.value = State.Finished
        } else {
            _state.value = State.Error(R.string.failed_sign_in)
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this)
    }


    fun firebaseAuthWithGoogle(googleAccount: GoogleSignInAccount) {
        googleAccount.idToken?.let { idToken ->
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential).addOnCompleteListener {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(credential).addOnCompleteListener(this)
                if (it.isSuccessful) {
                    _state.value = State.Finished
                } else {
                    _state.value = State.Error(R.string.failed_sign_in)
                }
            }
        }
    }

    fun signInAsAnonymous() {
        auth.signInAnonymously().addOnCompleteListener(this)
    }
}