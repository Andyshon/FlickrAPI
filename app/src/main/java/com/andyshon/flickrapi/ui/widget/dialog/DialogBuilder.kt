package com.andyshon.flickrapi.ui.widget.dialog

import android.content.Context
import android.content.DialogInterface
import android.view.ContextThemeWrapper
import android.view.View
import android.view.Window
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import com.andyshon.flickrapi.R

@Suppress("UNUSED_EXPRESSION")
class DialogBuilder() {

    private constructor(init: DialogBuilder.() -> Unit) : this() {
        init()
    }

    companion object {
        fun createAlertDialog(context: Context, builder: DialogBuilder.() -> Unit): AlertDialog {
            return DialogBuilder(builder).buildWith(context)
        }
    }

    private var title: TextMessage? = null
    private var message: TextMessage? = null
    private var contentView: ContentView? = null
    private var positiveButton: PositiveButton? = null
    private var negativeButton: NegativeButton? = null
    private var neutralButton: NeutralButton? = null

    fun title(init: TextMessage.() -> Unit) {
        title = TextMessage().apply { init() }
    }

    fun message(init: TextMessage.() -> Unit) {
        message = TextMessage().apply { init() }
    }

    fun contentView(init: ContentView.() -> Unit) {
        contentView = ContentView().apply { init() }
    }

    fun positiveButton(init: PositiveButton.() -> Unit) {
        positiveButton = PositiveButton().apply { init() }
    }

    fun negativeButton(init: NegativeButton.() -> Unit) {
        negativeButton = NegativeButton().apply { init() }
    }

    fun neutralButton(init: NeutralButton.() -> Unit) {
        neutralButton = NeutralButton().apply { init() }
    }

    fun buildWith(context: Context, @StyleRes theme: Int = R.style.AlertDialog): AlertDialog {

        val dialog = AlertDialog.Builder(ContextThemeWrapper(context, theme))

        title?.apply {
            if (textId != -1) {
                dialog.setTitle(context.getString(textId))
            }
        }

        message?.apply {
            if (text.isNotEmpty()) {
                dialog.setMessage(text)
            } else if (textId != -1) {
                dialog.setMessage(context.getString(textId))
            }
        }

        positiveButton?.apply {
            if (textId != -1) {
                dialog.setPositiveButton(context.getString(textId), callback)
            } else {
                dialog.setPositiveButton("", callback)
            }
        }

        negativeButton?.apply {
            if (textId != -1) {
                dialog.setNegativeButton(context.getString(textId), callback)
            } else {
                dialog.setNegativeButton("", callback)
            }
        }

        neutralButton?.apply {
            if (textId != -1) {
                dialog.setNeutralButton(context.getString(textId), callback)
            } else {
                dialog.setNeutralButton("", callback)
            }
        }

        contentView?.apply {
            if (layoutResId != -1) {
                dialog.setView(layoutResId)
            } else if (layoutView!=null){
                dialog.setView(layoutView)
            }
        }

        val alert = dialog.create()
        if (title == null) {
            alert.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }

        return alert
    }

    class TextMessage {
        var textId: Int = -1
        var text: String = ""
    }

    class ContentView {
        var layoutResId: Int = -1
        var layoutView: View? = null
    }

    class PositiveButton {
        var textId: Int = -1
        var callback: DialogInterface.OnClickListener? = null
    }

    class NegativeButton {
        var textId: Int = -1
        var callback: DialogInterface.OnClickListener? = null
    }

    class NeutralButton {
        var textId: Int = -1
        var callback: DialogInterface.OnClickListener? = null
    }
}