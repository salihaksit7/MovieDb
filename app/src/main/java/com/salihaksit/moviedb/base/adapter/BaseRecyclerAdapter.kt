package com.salihaksit.moviedb.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.salihaksit.moviedb.utils.OnBottomReachedListener

class BaseRecyclerAdapter<T : LayoutModel> :
    RecyclerView.Adapter<BaseRecyclerAdapter<T>.ViewHolder> {

    private lateinit var itemList: MutableList<T>
    private var listener: ItemClickListener<T>? = null
    private var onBottomReachedListener: OnBottomReachedListener? = null


    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    constructor() : super()

    constructor(
        listener: ItemClickListener<T>,
        onBottomReachedListener: OnBottomReachedListener
    ) : super() {
        this.listener = listener
        this.onBottomReachedListener = onBottomReachedListener
    }

    constructor(list: MutableList<T>, listener: ItemClickListener<T>) : super() {
        this.itemList = list
        this.listener = listener
    }

    constructor(list: MutableList<T>) : super() {
        this.itemList = list
        this.listener = null
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].layoutId()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, viewGroup, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItem(position) != null) {
            if (position == itemCount - 1) {
                onBottomReachedListener?.onBottomReached(position)
            }
            val item = getItem(position)
//        todo uncomment
//            holder.binding.setVariable(obj, item)
//            if (listener != null)
//                holder.binding.setVariable(clickListener, listener)

            holder.binding.executePendingBindings()
        }
    }


    override fun getItemCount(): Int {
        return if (::itemList.isInitialized) itemList.size else 0
    }

    fun getItem(index: Int): T? {
        return if (index < itemList.size) itemList[index] else null
    }

    fun setList(list: MutableList<T>) {
        itemList = list
        notifyDataSetChanged()
    }

}