package com.prometheo.moneylife.ui.investments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prometheo.moneylife.databinding.FragmentCurrentInvestmentsBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AvailableInvestmentsFragment : Fragment() {

    companion object {
        fun newInstance() = AvailableInvestmentsFragment()
    }

    private val vm: AvailableInvestmentsViewModel by viewModels()
    private val adapter = GroupAdapter<GroupieViewHolder>()
    private var _binding: FragmentCurrentInvestmentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentInvestmentsBinding.inflate(inflater, container, false).apply {
            title.text = "Inversiones Disponibles"
            fabNewInvestment.isVisible = false
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.loadData()

        vm.state.observe(viewLifecycleOwner, Observer { state ->
            binding.loadingIndicator.isVisible = state.loading
            if (state.transactionComplete) {
                parentFragmentManager.popBackStack()
            }
            if (state.error) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })

        vm.availableInvestments.observe(viewLifecycleOwner, Observer { availableInvestments ->
            binding.emptyMessage.isVisible = availableInvestments.isEmpty()
            adapter.update(availableInvestments.map { investment ->

                AvailableInvestmentItem(
                    investmentName = investment.name,
                    category = investment.type,
                    range = investment.range,
                    onClickListener = {
                        showInvestmentDialog(investmentId = investment.id, investmentName = investment.name )
                    }
                )
            })
        })
    }

    private fun showInvestmentDialog(investmentId: Int, investmentName: String) {
         NewInvestmentDialogFragment(
            name = investmentName,
            onConfirmListener = { amount ->
                vm.newInvestment(investmentId, amount)
            }
        ).show(parentFragmentManager, null)
    }
}