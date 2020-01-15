package com.andyshon.flickrapi.ui.widget.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.Window
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.andyshon.flickrapi.R

class ProgressDialog : DialogFragment() {
    private var progressTxt: TextView? = null

    val isShowing: Boolean
        get() = dialog != null && dialog?.isShowing?:false

    fun show(manager: FragmentManager) {
        show(manager, this.javaClass.name)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (dialog.window != null) {
            dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val window = dialog?.window ?: return
        window.setLayout(MATCH_PARENT, MATCH_PARENT)
        window.setBackgroundDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.color.colorPrimaryTransparent
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_progress, container, false)
    }

    fun setDescription(description: String) {
        progressTxt!!.text = description
    }

    companion object {

        private const val KEY_DESCRIPTION = "KEY.DESCRIPTION"

        fun newInstance(description: String): ProgressDialog {
            val fragment = ProgressDialog()
            val args = Bundle()
            args.putString(KEY_DESCRIPTION, description)
            fragment.arguments = args
            return fragment
        }
    }
}