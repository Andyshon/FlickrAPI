package com.andyshon.flickrapi.ui.search

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import com.andyshon.flickrapi.R
import com.andyshon.flickrapi.data.entity.SearchItem
import com.andyshon.flickrapi.ui.base.BaseContract
import com.andyshon.flickrapi.ui.base.inject.BaseInjectActivity
import com.andyshon.flickrapi.ui.base.recycler.ItemClickListener
import com.andyshon.flickrapi.ui.search.adapter.PhotosAdapter
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.alert
import javax.inject.Inject
import android.os.Vibrator
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.andyshon.flickrapi.utils.extensions.*
import org.jetbrains.anko.longToast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.textColor

class SearchActivity : BaseInjectActivity(), SearchContract.View {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    @Inject lateinit var presenter: SearchPresenter
    override fun getPresenter(): BaseContract.Presenter<*>? = presenter

    private var adapter: PhotosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        presentationComponent.inject(this)
        presenter.attachToView(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        initListeners()
        setupPhotosList()
        presenter.loadHistory()
    }

    private fun initListeners() {
        btnSearch.setOnClickListener {
            search(etSearchInput.fetchText())
        }

        btnClearInput.setOnClickListener {
            etSearchInput.setText("")
            btnClearInput.hide()
        }

        etSearchInput.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                search(etSearchInput.fetchText())
                true
            } else {
                false
            }
        }

        etSearchInput.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(text: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (text?.toString()?.trim()?.isEmpty()!!) btnClearInput.hide()
                else btnClearInput.show()
            }
        })
    }

    private fun search(query: String) {
        etSearchInput.hideKeyboard()
        etSearchInput.clearFocus()
        if (query.isNotEmpty()) {
            btnClearInput.show()
            presenter.search(query)
        }
    }

    private fun setupPhotosList() {
        adapter = PhotosAdapter(presenter.photos, setClickListener())
        searchResults.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(this, (searchResults.layoutManager as LinearLayoutManager).orientation)
        ContextCompat.getDrawable(this,R.drawable.bg_divider_color)?.let { dividerItemDecoration.setDrawable(it) }
        searchResults.addItemDecoration(dividerItemDecoration)
    }

    private fun setClickListener(): ItemClickListener<SearchItem> {
        return object : ItemClickListener<SearchItem> {
            override fun onItemClick(view: View, pos: Int, item: SearchItem) {
                vibrate()
                prepareToDelete(pos)
            }
        }
    }

    fun prepareToDelete(pos: Int) {
        alert(resources.getString(R.string.ask_to_delete_photo)) {
            also {
                ctx.setTheme(R.style.CustomAlertDialogAnko)
            }
            isCancelable = true
            positiveButton(resources.getString(R.string.delete)) {
                presenter.deleteItem(pos)
                adapter?.removeObject(pos)
            }
            negativeButton(resources.getString(R.string.dialog_action_cancel)) { it.dismiss() }
        }.show().apply {
            getButton(AlertDialog.BUTTON_POSITIVE)?.let {
                it.textColor = ContextCompat.getColor(this@SearchActivity, R.color.colorPrimaryDark)
            }
            getButton(AlertDialog.BUTTON_NEGATIVE)?.let {
                it.textColor = ContextCompat.getColor(this@SearchActivity, R.color.colorPrimaryDark)
            }
        }
    }

    override fun searched() {
        adapter?.notifyItemInserted(presenter.photos.size)
        searchResults.scrollToPosition(presenter.photos.size-1)
    }

    override fun empty() {
        longToast(resources.getString(R.string.no_photos_found))
    }

    override fun error(s: String) {
        longToast(resources.getString(R.string.error_text, s))
    }

    private fun vibrate() {
        val vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibe.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.EFFECT_HEAVY_CLICK))
        } else {
            vibe.vibrate(200)
        }
    }
}
