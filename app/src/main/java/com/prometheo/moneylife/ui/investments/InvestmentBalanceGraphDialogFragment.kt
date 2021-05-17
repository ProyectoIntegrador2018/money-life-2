package com.prometheo.moneylife.ui.investments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.prometheo.moneylife.data.models.UserInvestment
import com.prometheo.moneylife.databinding.FragmentDialogInvestmentBalanceGraphBinding

class InvestmentBalanceGraphDialogFragment(
    val investmentItemsBalance: List<UserInvestment>
): DialogFragment() {

    private var _binding: FragmentDialogInvestmentBalanceGraphBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentDialogInvestmentBalanceGraphBinding.inflate(LayoutInflater.from(context)).apply {

            //Add points into the graph
            graph.addSeries(createPointSeries())

            //Set scrollable atribbutes
            graph.viewport.setScalableY(true)
            graph.viewport.isYAxisBoundsManual = true
            graph.viewport.setMinY(0.0)

            //Set labels atribbutes
            graph.title = investmentItemsBalance.first().name //TODO: Change harcoded string when Fragment Send Info
            graph.gridLabelRenderer.verticalAxisTitle = "Precio"
            graph.gridLabelRenderer.horizontalAxisTitle = "Turno"
            graph.legendRenderer.isVisible = true
            graph.legendRenderer.align = LegendRenderer.LegendAlign.TOP
        }

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun createPointSeries(): LineGraphSeries<DataPoint> {

        var dataPointsArray = arrayOf<DataPoint>()
        investmentItemsBalance.map {
            dataPointsArray += DataPoint(it.turnNumber?.toDouble()!!,it.currentBalance.toDouble())
        }
        val investmentLineGrapSeries = LineGraphSeries(dataPointsArray)
        val series: LineGraphSeries<DataPoint> = investmentLineGrapSeries

        //Set atribbutes for series object
        series.setAnimated(true)
        series.title = "Balance"

        return series
    }
}