package com.example.bime.classes

import android.app.Activity
import android.app.AlertDialog

class DeleteAlert(private val activity: Activity?, private val onEntryDelete: () -> Unit) {

    init {
        val dialogBuilder = AlertDialog.Builder(activity)

        dialogBuilder.setMessage("Are you sure you want to delete this entry?")

            .setCancelable(true)
            .setPositiveButton("Proceed") { dialog, id ->
                onEntryDelete()
            }
            .setNegativeButton("Cancel") { dialog, id ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Delete Entry")
        alert.show()
    }

}