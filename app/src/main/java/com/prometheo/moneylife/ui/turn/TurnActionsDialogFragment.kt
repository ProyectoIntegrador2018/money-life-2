package com.prometheo.moneylife.ui.turn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prometheo.moneylife.data.models.TurnAction
import com.prometheo.moneylife.data.models.TurnActionCategory
import com.prometheo.moneylife.databinding.DialogTurnActionBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class TurnActionsDialogFragment(
    private val turnActions: List<TurnAction>,
    private val onItemClickListener: (Int, TurnActionCategory) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: DialogTurnActionBinding? = null
    private val binding get() = _binding!!

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogTurnActionBinding.inflate(inflater)
        binding.rvTurnActions.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTurnActions.adapter = adapter
        adapter.update(turnActions.map { item ->
            TurnActionGroupieItem(item) { actionId, category ->
                onItemClickListener(actionId, category)
                dismiss()
            }
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG = TurnActionsDialogFragment::class.java.simpleName
    }
}