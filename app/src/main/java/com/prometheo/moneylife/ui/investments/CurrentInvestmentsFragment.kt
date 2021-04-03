package com.prometheo.moneylife.ui.investments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prometheo.moneylife.R
import com.prometheo.moneylife.databinding.FragmentCurrentnvestmentsBinding
import com.prometheo.moneylife.databinding.FragmentLoginBinding

class CurrentInvestmentsFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentInvestmentsFragment()
    }

    private var _binding: FragmentCurrentnvestmentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CurrentnvestmentsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrentnvestmentsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentnvestmentsViewModel::class.java)

    }

}