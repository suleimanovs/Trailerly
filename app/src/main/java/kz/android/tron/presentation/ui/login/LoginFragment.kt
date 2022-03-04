package kz.android.tron.presentation.ui.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kz.android.tron.R
import kz.android.tron.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private val auth: FirebaseAuth = Firebase.auth
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var onStartActivity: IOStartActivity
    private lateinit var googleSignInClient: GoogleSignInClient


    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    try {
                        task.getResult(ApiException::class.java)?.let { firebaseAuthWithGoogle(it) }
                    } catch (e: ApiException) {
                        hideProgress()
                    }
                } else hideProgress()

            }
        }

    override fun onAttach(context: Context) {
        onStartActivity = context as IOStartActivity
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linkSignUp.setOnClickListener {
            launchToSignupFragment()
        }

        val googleSignOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignOptions)



        binding.btnGoogle.setOnClickListener {
            showProgress()
            resultLauncher.launch(googleSignInClient.signInIntent)
        }

        signInAsAnonymousListener()

        signInWithEmailListener()

    }

    private fun signInWithEmailListener() {
        binding.btnSignIn.setOnClickListener {
            showProgress()
            when {
                binding.email.text.isEmpty() -> {
                    showToast(R.string.email_error)
                    hideProgress()

                }
                binding.password.text.isEmpty() -> {
                    showToast(R.string.password_error)
                    hideProgress()

                }

                binding.password.length() < 6 -> {
                    binding.password.error = getString(R.string.password_error)
                    hideProgress()
                }


                else -> {
                    binding.progress.visibility = View.VISIBLE
                    val email: String = binding.email.text.toString().trim { it <= ' ' }
                    val password: String = binding.password.text.toString().trim { it <= ' ' }

                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task ->

                            if (task.isSuccessful) {
                                Storage.initial(requireContext())
                                Storage.putUser(auth.currentUser?.uid.toString())
                                launchToMainActivity()
                            } else {
                                hideProgress()
                                showToast(R.string.failed_sign_in)
                            }
                        }
                }
            }
        }
    }

    private fun signInAsAnonymousListener() {
        binding.anonymous.setOnClickListener {
            showProgress()
            auth.signInAnonymously().addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    Storage.initial(requireContext())
                    Storage.putUser(user?.providerId ?: ANONYMOUS)
                    launchToMainActivity()
                } else {
                    showToast(R.string.failed_sign_in)
                    hideProgress()
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(googleAccount: GoogleSignInAccount) {
        googleAccount.idToken?.let {
            val credential = GoogleAuthProvider.getCredential(it, null)
            auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Storage.initial(requireContext())
                    Storage.putUser(auth.currentUser?.uid.toString())
                    launchToMainActivity()
                } else hideProgress()
            }
        }

    }

    companion object {
        const val ANONYMOUS = "anonymous"
    }

    private fun showToast(message: Int) {
        Toast.makeText(requireContext(), getString(message), Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        this.binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        this.binding.progress.visibility = View.GONE
    }

    private fun launchToMainActivity() {
        onStartActivity.onStartActivity()
    }

    private fun launchToSignupFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
    }

    interface IOStartActivity {
        fun onStartActivity()
    }

    fun Editable?.isEmpty(): Boolean {
        return TextUtils.isEmpty(this.toString().trim { it <= ' ' })
    }
}











