package kz.android.tron.presentation.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kz.android.tron.App
import kz.android.tron.R
import kz.android.tron.databinding.FragmentSignupBinding
import kz.android.tron.presentation.adapters.movie_adapter.MovieAdapter
import kz.android.tron.presentation.util.showSnackbar
import kz.android.tron.presentation.util.value
import kz.android.tron.presentation.viewmodel.GenreContentViewModel
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import javax.inject.Inject


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var viewModelFactory: MovieModelFactory
    private val component by lazy { (requireActivity().application as App).component }
    private val viewModel: SignUpViewModel by viewModels { viewModelFactory }
    private lateinit var onStartActivity: OnStartActivity

    override fun onAttach(context: Context) {
        component.inject(this)
        onStartActivity = context as OnStartActivity
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        _binding = FragmentSignupBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { state: State ->
            when (state) {
                State.Success -> {
                    val email: String = binding.email.value
                    val password: String = binding.password.value
                    viewModel.createUserWithEmailAndPassword(email, password)
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

        binding.btnSignUp.setOnClickListener {
            showProgress()
            viewModel.signUp(binding.email.value, binding.password.value, binding.username.value)
        }
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