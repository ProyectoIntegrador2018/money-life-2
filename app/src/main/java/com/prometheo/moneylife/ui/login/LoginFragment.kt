package com.prometheo.moneylife.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.prometheo.moneylife.databinding.FragmentLoginBinding
import com.prometheo.moneylife.ui.signup.SignupFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val vm: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var callback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Ignore back button.
        }
        callback.isEnabled = true
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.uiModel.observe(viewLifecycleOwner, Observer {
            if (it.goToApp) {
                goToApp()
            }

            if (it.goToSignup) {
                goToSignup()
            }

            binding.errorMessage.isVisible = it.showError
            binding.loadingIndicator.isVisible = it.showLoading
        })

        binding.loginButton.setOnClickListener {
            vm.login(binding.emailField.text.toString(), binding.passwordField.text.toString())
        }

        binding.goToSignup.setOnClickListener {
            vm.goToSignup()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        if (_binding != null) {
            _binding = null
        }
    }

    private fun goToApp() {
        callback.remove()
        requireActivity().onBackPressed()
    }

    private fun goToSignup() {
        callback.remove()
        parentFragmentManager.commit {
            replace(android.R.id.content, SignupFragment.newInstance())
        }
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}