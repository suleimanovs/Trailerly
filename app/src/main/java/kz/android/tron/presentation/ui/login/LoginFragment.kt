package kz.android.tron.presentation.ui.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kz.android.tron.R
import kz.android.tron.databinding.FragmentLoginBinding
import kz.android.tron.presentation.ui.login.Storage.ANONYMOUS
import kz.android.tron.presentation.util.getValue
import kz.android.tron.presentation.util.isEmpty


class LoginFragment : Fragment() {

    private val auth: FirebaseAuth = Firebase.auth
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var onStartActivity: OnStartActivity
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

            } else hideProgress()
        }

    override fun onAttach(context: Context) {
        onStartActivity = context as OnStartActivity
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val googleSignOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignOptions)



        binding.linkSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.btnGoogle.setOnClickListener {
            showProgress()
            resultLauncher.launch(googleSignInClient.signInIntent)
        }

        signInAsAnonymousListener()
        signInWithEmailListener()

    }


    private fun signInWithEmailListener() {
        binding.btnSignIn.setOnClickListener {
            when {
                binding.email.text.isEmpty() -> showSnackbar(R.string.email_error)

                binding.password.text.isEmpty() -> showSnackbar(R.string.password_error)

                binding.password.length() < 6 -> showSnackbar(R.string.password_error)

                else -> {
                    showProgress()
                    val email: String = binding.email.getValue()
                    val password: String = binding.password.getValue()

                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task ->

                            if (task.isSuccessful) {
                                Storage.putUser(auth.currentUser?.uid.toString())
                                launchToMainActivity()
                            } else {
                                hideProgress()
                                showSnackbar(R.string.failed_sign_in)
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
                    Storage.putUser(user?.providerId ?: ANONYMOUS)
                    launchToMainActivity()
                } else {
                    showSnackbar(R.string.failed_sign_in)
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
                    Storage.putUser(auth.currentUser?.uid.toString())
                    launchToMainActivity()
                } else {
                    hideProgress()
                }
            }
        }

    }


    private fun showSnackbar(@StringRes stringRes: Int) = Snackbar
        .make(
            requireContext(),
            binding.root,
            getString(stringRes),
            Snackbar.LENGTH_SHORT
        ).show()

    private fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    private fun launchToMainActivity() {
        onStartActivity.onStartActivity()
    }


}











