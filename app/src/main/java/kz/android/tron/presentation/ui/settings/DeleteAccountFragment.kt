package kz.android.tron.presentation.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import dev.androidbroadcast.vbpd.viewBinding
import kz.android.tron.R
import kz.android.tron.databinding.FragmentDeleteAccountBinding
import kz.android.tron.presentation.TrailerlyApplication
import kz.android.tron.presentation.ui.SetupActionbar
import kz.android.tron.presentation.ui.login.AuthorizationActivity
import kz.android.tron.presentation.ui.login.Storage
import kz.android.tron.presentation.util.showErrorSnackbar
import kz.android.tron.presentation.util.showSuccessSnackbar
import kz.android.tron.presentation.viewmodel.MovieModelFactory
import javax.inject.Inject
import kotlin.properties.Delegates

class DeleteAccountFragment : Fragment(R.layout.fragment_delete_account) {

    private val binding by viewBinding(FragmentDeleteAccountBinding::bind)

    @Inject lateinit var viewModelFactory: MovieModelFactory
    private val component by lazy { (requireActivity().application as TrailerlyApplication).component }
    private val viewModel: DeleteAccountViewModel by viewModels { viewModelFactory }
    private var setupActionbar by Delegates.notNull<SetupActionbar>()

    override fun onAttach(context: Context) {
        component.inject(this)
        setupActionbar = context as SetupActionbar
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionbar.setTitle(getString(R.string.delete_account_title))
        setupListeners()
        observeState()
    }

    private fun setupListeners() {
        binding.deleteButton.setOnClickListener {
            val password = binding.passwordInput.text?.toString()?.takeIf { it.isNotBlank() }
            val googleIdToken = Storage.user()
            viewModel.deleteAccount(password = password, googleIdToken = googleIdToken)
        }
        FirebaseAuth.getInstance().currentUser?.providerData?.any { it.providerId == "password" }?.let { isEmailPassword ->
            binding.passwordLayout.isVisible = isEmailPassword
        }
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DeleteAccountState.Loading -> {
                    showProgress(true)
                    binding.deleteButton.isEnabled = false
                }

                is DeleteAccountState.Success -> {
                    showProgress(false)
                    showSuccessSnackbar(getString(R.string.account_deleted))
                    FirebaseAuth.getInstance().signOut()
                    Storage.putUser(null)
                    startActivity(Intent(requireContext(), AuthorizationActivity::class.java))
                    requireActivity().finish()
                }

                is DeleteAccountState.Error -> {
                    showProgress(false)
                    binding.deleteButton.isEnabled = true
                    showErrorSnackbar(getString(state.messageRes))
                }
            }
        }
    }

    private fun showProgress(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }
}