package kz.android.tron.presentation.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kz.android.tron.R
import javax.inject.Inject

class SignUpViewModel @Inject constructor() : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    fun signUp(email: String, password: String, username: String) {
        checkValidate(email, password, username) {
            _state.value = State.Success
        }
    }

    private fun checkValidate(email: String, password: String, username: String, call: () -> Unit) {
        val resId = when {
            email.isBlank() || Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                R.string.email_error
            }
            username.isBlank() -> {
                R.string.please_enter_your_name
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

    fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Storage.putUser(auth.currentUser?.uid ?: Storage.ANONYMOUS)
                    _state.value = State.Finished
                } else {
                    _state.value = State.Error(R.string.failed_sign_in)
                }
            }
    }

}