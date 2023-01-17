package com.example.bime

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class CustomPieChart(pieChart: PieChart, values: Array<Double>, labels: Array<String>, colorSet: Array<Int>) {

    init {
        populatePieChart(pieChart, values, labels, colorSet)
    }

    private fun populatePieChart(pieChart: PieChart, values: Array<Double>, labels: Array<String>, colorSet: Array<Int>) {
        //an array to store the pie slices entry
        val ourPieEntry = ArrayList<PieEntry>()
        var i = 0

        for (entry in values) {
            var value = values[i].toFloat()
            var label = labels[i]
            ourPieEntry.add(PieEntry(value, label))
            i++
        }

        val ourSet = PieDataSet(ourPieEntry, "")
        val data = PieData(ourSet)

        data.setValueFormatter(HourFormatter())

        ourSet.sliceSpace = 1f

        ourSet.colors = colorSet.toMutableList()
        pieChart.data = data

        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(20f)

        pieChart.centerText = generateCenterSpannableText("Last 5 days", "day to day");
        pieChart.setDrawCenterText(true);

        pieChart.isDrawHoleEnabled = true
        pieChart.holeRadius = 58f;
        pieChart.transparentCircleRadius = 63f;

        pieChart.animateY(1400, Easing.EaseInOutQuad)

        pieChart.description.isEnabled = false

        pieChart.legend.isEnabled = true
        pieChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        pieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        pieChart.legend.isWordWrapEnabled = true

        pieChart.legend.textSize = 15f

        pieChart.setDrawEntryLabels(false)
        pieChart.invalidate()
    }


    private fun generateCenterSpannableText(title: String, subtitle:String): SpannableString? {
        val s = SpannableString(title + "\n" + subtitle)
        s.setSpan(RelativeSizeSpan(2f), 0, title.length, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), title.length, s.length, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), title.length, s.length, 0)
        s.setSpan(RelativeSizeSpan(1.5f), title.length, s.length, 0)
        return s
    }
}