package com.myour.whowroteitv3.feature.addbook.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myour.whowroteitv3.R
import com.myour.whowroteitv3.databinding.ActivityAddBookBinding
import com.myour.whowroteitv3.core.api.Response
import com.myour.whowroteitv3.core.util.SystemUtils
import com.myour.whowroteitv3.feature.addbook.viewmodel.AddBookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookActivity : AppCompatActivity() {

    private lateinit var bd: ActivityAddBookBinding

    private val mAddBookViewModel: AddBookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bd = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(bd.root)

        setObserveData()
        setEvent()
    }

    private fun setObserveData() {
        mAddBookViewModel.getOneBookBySearchAndAddLiveData.observe(this, {
            when (it) {
                is Response.Loading -> {
                    setBookInfo("", getString(R.string.loading))
                }

                is Response.Success -> {
                    Toast.makeText(applicationContext,
                        getString(R.string.message_success),
                        Toast.LENGTH_SHORT)
                        .show()
                    val gBookRepoModel = it.data
                    if (gBookRepoModel != null) {
                        setBookInfo(gBookRepoModel.title, gBookRepoModel.authors)
                    } else setBookInfo(getString(R.string.no_results), "")
                }

                is Response.Error -> {
                    val messageError = getString(R.string.message_error) + it.message
                    Toast.makeText(applicationContext,
                        messageError,
                        Toast.LENGTH_SHORT)
                        .show()
                    setBookInfo(getString(R.string.no_results), messageError)
                }
            }
        })
    }

    private fun setBookInfo(title: String, authors: String) {
        bd.textViewTitle.text = title
        bd.textViewAuthor.text = authors
    }

    private fun setEvent() {
        bd.buttonSearch.setOnClickListener { clickButtonSearchAndAdd() }
    }

    private fun clickButtonSearchAndAdd() {
        SystemUtils.hideKeyboard(this, bd.root)
        val queryString = bd.editTextBookInput.text.toString().trim()

        if (queryString.isNotEmpty()) {
            mAddBookViewModel.getOneBookBySearchAndAdd(queryString)
        } else {
            setBookInfo(getString(R.string.no_search_term), "")
        }
    }
}