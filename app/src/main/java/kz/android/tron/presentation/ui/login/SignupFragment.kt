package kz.android.tron.presentation.ui.login

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kz.android.tron.presentation.adapter.TronSharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kz.android.tron.R
import kz.android.tron.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private val auth: FirebaseAuth = Firebase.auth
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var onStartActivity: LoginFragment.IOStartActivity

    override fun onAttach(context: Context) {
        onStartActivity = context as LoginFragment.IOStartActivity
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        _binding = FragmentSignupBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            showProgress()

            when {
                TextUtils.isEmpty(binding.email.text.toString().trim { it <= ' ' }) -> {
                    showToast(R.string.email_error)
                    hideProgress()

                }
                TextUtils.isEmpty(binding.password.text.toString().trim { it <= ' ' }) -> {
                    showToast(R.string.password_error)
                    hideProgress()

                }

                binding.password.length() < 6 -> {
                    binding.password.error = getString(R.string.password_error)
                    hideProgress()
                }

                TextUtils.isEmpty(binding.username.text.toString().trim { it <= ' ' }) -> {
                    showToast(R.string.please_enter_your_name)
                    hideProgress()

                }

                else -> {
                    showProgress()
                    val email: String = binding.email.text.toString().trim { it <= ' ' }
                    val password: String = binding.password.text.toString().trim { it <= ' ' }

                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task ->
                            //если регистрация прошла успешна
                            if (task.isSuccessful) {
                               // val userId = auth.currentUser?.uid.toString()
                                showToast(R.string.successful_sign_up)
                                TronSharedPreferences.initial(requireContext())
                                TronSharedPreferences.putUser(auth.currentUser?.uid.toString())
                                launchToMainActivity()

                            } else {
                                showToast(R.string.failed_sign_in)
                                hideProgress()
                            }
                        }
                }
            }
        }
    }

    private fun showToast(message: Int) {
        Toast.makeText(requireContext(), getString(message), Toast.LENGTH_LONG).show()
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