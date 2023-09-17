package com.novikov.blubb.presentation.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.novikov.blubb.R

class LoadingDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        var inflater = requireActivity().layoutInflater

        var view = inflater.inflate(R.layout.dialog_loading, null)
        builder.setView(view)

        return builder.create()
    }

}