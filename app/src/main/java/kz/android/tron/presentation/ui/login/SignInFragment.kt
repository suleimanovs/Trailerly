package kz.android.tron.presentation.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kz.android.tron.App
import kz.android.tron.R
import kz.android.tron.databinding.FragmentSigninBinding
import kz.android.tron.presentation.util.showSnackbar
import kz.android.tron.presentation.util.value
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import javax.inject.Inject


class SignInFragment : Fragment(), ActivityResultCallback<ActivityResult> {


    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    private lateinit var onStartActivity: OnStartActivity
    @Inject lateinit var viewModelFactory: MovieModelFactory

    private val component by lazy { (requireActivity().application as App).component }
    private val viewModel: SignInViewModel by viewModels { viewModelFactory }
    private var resultLauncher = registerForActivityResult(StartActivityForResult(), this)

    override fun onAttach(context: Context) {
        component.inject(this)
        onStartActivity = context as OnStartActivity
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        _binding = FragmentSigninBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

        viewModel.state.observe(viewLifecycleOwner) { state: State ->
            when (state) {
                State.Success -> {
                    val email = binding.email.value
                    val password = binding.password.value
                    viewModel.signInWithEmailAndPassword( email, password)
                }
                is State.Error -> {
                    hideProgress()
                    showSnackbar(state.resId)
                }
                is State.Finished -> {
                    launchToMainActivity()
                }
            }
        }

    }

    private fun setupListeners(){

        binding.linkSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.btnGoogle.setOnClickListener {
            showProgress()
            resultLauncher.launch(googleSignInIntent())
        }

        binding.anonymous.setOnClickListener {
            showProgress()
            viewModel.signInAsAnonymous()
        }

        binding.btnSignIn.setOnClickListener {
            showProgress()
            viewModel.signIn(binding.email.value, binding.password.value)
        }
    }

    override fun onActivityResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            if (task.isSuccessful) {
                try {
                    task.getResult(ApiException::class.java)
                        ?.let { viewModel.firebaseAuthWithGoogle(it) }
                } catch (_: ApiException) {
                }
            }
        }
    }

    private fun googleSignInIntent(): Intent {
        val googleSignOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(requireActivity(), googleSignOptions).signInIntent
    }

    private fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    private fun launchToMainActivity() {
        onStartActivity.onStartActivity()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}











