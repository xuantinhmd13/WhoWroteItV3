package com.myour.whowroteitv3.feature.showbook.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.myour.whowroteitv3.R
import com.myour.whowroteitv3.core.view.ItemLongClickListener
import com.myour.whowroteitv3.databinding.ActivityShowBookBinding
import com.myour.whowroteitv3.feature.addbook.view.AddBookActivity
import com.myour.whowroteitv3.feature.showbook.adapter.GBookAdapter
import com.myour.whowroteitv3.feature.showbook.viewmodel.ShowBookViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowBookActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    @Inject
    lateinit var adapter: GBookAdapter
    private var bd: ActivityShowBookBinding? = null
    private val mShowBookViewModel: ShowBookViewModel by viewModels()
    private var mPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bd = ActivityShowBookBinding.inflate(layoutInflater)
        setContentView(bd!!.root)

        setInit()
        setRecyclerView()
        setObserveData()
        setEvent()
    }

    private fun setRecyclerView() {
        bd!!.contentShowBook.recyclerViewBook.adapter = adapter
        bd!!.contentShowBook.recyclerViewBook.layoutManager = LinearLayoutManager(this)
    }

    private fun setObserveData() {
        mShowBookViewModel.getAllBooks().observe(this,
            { data -> adapter.mBooks = data })
    }

    private fun setEvent() {
        bd!!.fab.setOnClickListener { clickFab() }
        adapter.setOnItemLongClickListener(object : ItemLongClickListener {
            override fun onItemLongClick(v: View?, position: Int): Boolean {
                mPosition = position
                showPopup(v)
                return true
            }
        })
    }

    private fun clickFab() {
        startActivity(Intent(this, AddBookActivity::class.java))
    }

    private fun setInit() {
        setSupportActionBar(bd!!.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clear_all -> {
                mShowBookViewModel.deleteAllBooks()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showPopup(v: View?) {
        val popup = PopupMenu(this, v!!)
        popup.menuInflater.inflate(R.menu.menu_popup_item, popup.menu)
        popup.setOnMenuItemClickListener(this)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.popup_menu_delete -> {
                adapter.mBooks?.get(mPosition)?.let {
                    mShowBookViewModel.deleteBook(it)
                    Toast.makeText(this,
                        getString(R.string.deleted_label) + " " + it.title,
                        Toast.LENGTH_SHORT)
                        .show()
                }
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bd = null
    }
}