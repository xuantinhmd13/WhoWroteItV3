package com.myour.whowroteitv3.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.myour.whowroteitv3.data.model.local.GBookEntity
import com.myour.whowroteitv3.databinding.ItemBookBinding
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class GBookAdapter @Inject constructor(
    private val glide: RequestManager,
) :
    RecyclerView.Adapter<GBookAdapter.ViewHolder>() {

    var mBooks: List<GBookEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var mClickListener: ClickListener

    interface ClickListener {
        fun onItemLongClick(v: View?, position: Int): Boolean
    }

    fun setOnItemLongClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

    inner class ViewHolder(private val bd: ItemBookBinding) : RecyclerView.ViewHolder(bd.root),
        View.OnLongClickListener {
        fun bindData(currentBook: GBookEntity) {
            //view
            bd.textViewTitle.text = currentBook.title
            bd.textViewAuthor.text = currentBook.authors
            bd.textViewPublishedDate.text = currentBook.publishedDate
            glide
                .load(currentBook.smallThumbnail)
                .into(bd.imageViewThumbnail)

            //event
            bd.root.rootView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            return mClickListener.onItemLongClick(v, adapterPosition)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bd = ItemBookBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(bd)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mBooks?.let { holder.bindData(it[position]) }
    }

    override fun getItemCount() = mBooks?.size ?: 0
}