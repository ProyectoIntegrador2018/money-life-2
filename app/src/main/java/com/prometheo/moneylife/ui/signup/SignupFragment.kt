package com.prometheo.moneylife.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.prometheo.moneylife.databinding.FragmentSignupBinding
import com.prometheo.moneylife.ui.login.LoginFragment
import com.prometheo.moneylife.ui.login.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private val vm: SignupViewModel by viewModels()

    private var _binding: FragmentSignupBinding? = null
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
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.uiModel.observe(viewLifecycleOwner, Observer {
            if (it.goToApp) {
                goToApp()
            }

            if (it.goToLogin) {
                goToLogin()
            }

            binding.errorMessage.isVisible = it.showError
            binding.loadingIndicator.isVisible = it.showLoading
        })

        binding.signupButton.setOnClickListener {
            vm.signup(binding.emailField.text.toString(), binding.passwordField.text.toString())
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

    private fun goToLogin() {
        callback.remove()
        parentFragmentManager.commit {
            replace(android.R.id.content, LoginFragment.newInstance())
        }
    }


    companion object {
        fun newInstance(): SignupFragment {
            return SignupFragment()
        }
    }
}