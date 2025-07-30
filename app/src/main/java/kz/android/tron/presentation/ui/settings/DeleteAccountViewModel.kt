package kz.android.tron.presentation.ui.settings

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kz.android.tron.R
import javax.inject.Inject

class DeleteAccountViewModel @Inject constructor() : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _state = MutableLiveData<DeleteAccountState>()
    val state: LiveData<DeleteAccountState> = _state

    fun deleteAccount(password: String? = null, googleIdToken: String? = null) {
        _state.value = DeleteAccountState.Loading

        viewModelScope.launch {
            val user = auth.currentUser
            if (user == null) {
                _state.value = DeleteAccountState.Error(R.string.user_is_null)
                return@launch
            }

            val reAuth = createReAuthProvider(user, password, googleIdToken)

            if (reAuth == null) {
                _state.value = DeleteAccountState.Error(R.string.reauth_provider_not_found)
                return@launch
            }

            try {
                reAuth.reauthenticate(user)
                user.delete().await()
                _state.value = DeleteAccountState.Success
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                _state.value = DeleteAccountState.Error(R.string.wrong_password)
            } catch (e: FirebaseAuthRecentLoginRequiredException) {
                _state.value = DeleteAccountState.Error(R.string.recent_login_required)
            } catch (e: Exception) {
                _state.value = DeleteAccountState.Error(R.string.delete_failed)
            }
        }
    }
}

sealed interface ReAuthProvider {
    suspend fun reauthenticate(user: FirebaseUser)
}

class PasswordReAuth(private val email: String, private val password: String) : ReAuthProvider {
    override suspend fun reauthenticate(user: FirebaseUser) {
        val credential = EmailAuthProvider.getCredential(email, password)
        user.reauthenticate(credential).await()
    }
}

class GoogleReAuth(private val idToken: String) : ReAuthProvider {
    override suspend fun reauthenticate(user: FirebaseUser) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        user.reauthenticate(credential).await()
    }
}

class FirebaseReAuth : ReAuthProvider {
    override suspend fun reauthenticate(user: FirebaseUser) {

    }
}

fun createReAuthProvider(user: FirebaseUser, password: String?, googleIdToken: String?): ReAuthProvider? {
    val providers = user.providerData.map { it.providerId }

    return when {
        "password" in providers && !password.isNullOrBlank() -> PasswordReAuth(user.email.orEmpty(), password)
        "google.com" in providers && !googleIdToken.isNullOrBlank() -> GoogleReAuth(googleIdToken)
        "firebase" in providers -> FirebaseReAuth()
        else -> null
    }
}


sealed class DeleteAccountState {
    object Loading : DeleteAccountState()
    object Success : DeleteAccountState()
    class Error(@StringRes val messageRes: Int) : DeleteAccountState()
}