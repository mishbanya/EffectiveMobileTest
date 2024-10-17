package com.mishbanya.effectivemobiletest.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mishbanya.effectivemobiletest.R
import com.mishbanya.effectivemobiletest.domain.offers.entity.OfferModel
import com.mishbanya.effectivemobiletest.domain.offers.usecases.IOnOfferClickListener

class OffersAdapter(
    private val context: Context,
    private val listener: IOnOfferClickListener
) : ListAdapter<OfferModel, OffersAdapter.OfferViewHolder>(OfferDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OfferViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.offer_item, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OffersAdapter.OfferViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val offerIcon: ImageView = itemView.findViewById(R.id.offer_icon)
        private val offerText: TextView = itemView.findViewById(R.id.offer_text)

        private val offerInfo: TextView = itemView.findViewById(R.id.offer_info)
        private val offerButton: Button = itemView.findViewById(R.id.offer_button)

        init {
            offerButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onOfferClick(position)
                }
            }
        }

        fun bind(data: OfferModel){
            offerText.text = data.title
            offerInfo.text = data.buttonModel?.text
            when(data.id){
                "near_vacancies" ->
                    offerIcon.setImageResource(R.drawable.offer_near_vacancies)
                "level_up_resume" ->
                    offerIcon.setImageResource(R.drawable.offer_level_up_resume)
                "temporary_job" ->
                    offerIcon.setImageResource(R.drawable.offer_temporary_job)
            }
        }
    }

    class OfferDiffCallback : DiffUtil.ItemCallback<OfferModel>() {
        override fun areItemsTheSame(oldItem: OfferModel, newItem: OfferModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: OfferModel, newItem: OfferModel): Boolean {
            return oldItem == newItem
        }
    }

    fun reload(data: List<OfferModel>){
        submitList(data)
    }
}