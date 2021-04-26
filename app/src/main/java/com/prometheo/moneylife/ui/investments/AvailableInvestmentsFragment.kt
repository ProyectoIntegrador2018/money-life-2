package com.prometheo.moneylife.ui.investments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prometheo.moneylife.databinding.FragmentCurrentnvestmentsBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class AvailableInvestmentsFragment : Fragment() {

    companion object {
        fun newInstance() = AvailableInvestmentsFragment()
    }

    private val vm: AvailableInvestmentsViewModel by viewModels()
    private val adapter = GroupAdapter<GroupieViewHolder>()
    private var _binding: FragmentCurrentnvestmentsBinding? = null
    private val binding get() = _binding!!

    private var currentEditingInvestmentPosition: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrentnvestmentsBinding.inflate(inflater, container, false).apply {
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.loadData()

        vm.state.observe(viewLifecycleOwner, Observer { state ->
            if (state.transactionComplete) {
                parentFragmentManager.popBackStack()
            }

            adapter.update(state.availableInvestments.map { investment ->

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
            onConfirmListener = {amount ->
                vm.newInvestment(investmentId, amount)
            }
        ).show(parentFragmentManager, null)
    }
}