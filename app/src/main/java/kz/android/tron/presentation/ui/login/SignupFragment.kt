package kz.android.tron.presentation.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kz.android.tron.R
import kz.android.tron.databinding.FragmentSignupBinding
import kz.android.tron.presentation.util.getValue
import kz.android.tron.presentation.util.isEmpty


class SignupFragment : Fragment() {

    private val auth: FirebaseAuth = Firebase.auth
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var onStartActivity: OnStartActivity

    override fun onAttach(context: Context) {
        onStartActivity = context as OnStartActivity
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        _binding = FragmentSignupBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {

            when {
                binding.email.text.isEmpty() -> showSnackbar(R.string.email_error)

                binding.password.text.isEmpty() -> showSnackbar(R.string.password_error)

                binding.password.length() < 6 -> showSnackbar(R.string.password_error)

                binding.username.text.isEmpty() -> showSnackbar(R.string.please_enter_your_name)


                else -> {
                    showProgress()
                    val email: String = binding.email.getValue()
                    val password: String = binding.password.getValue()

                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task ->
                            //если регистрация прошла успешна
                            if (task.isSuccessful) {
                                val userId = auth.currentUser?.uid.toString()
                                showSnackbar(R.string.successful_sign_up)
                                Storage.putUser(userId)
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


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}