package com.mishbanya.effectivemobiletest.presentation.adapters

import android.annotation.SuppressLint
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
import com.mishbanya.effectivemobiletest.domain.vacancies.entity.VacancyModel
import com.mishbanya.effectivemobiletest.domain.vacancies.usecases.IOnVacancyClickListener

class VacanciesAdapter (
    private val context: Context,
    private val listener: IOnVacancyClickListener
) : ListAdapter<VacancyModel, VacanciesAdapter.VacancyViewHolder>(VacancyDiffCallback()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VacanciesAdapter.VacancyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.vacancy_item, parent, false)
        return VacancyViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
    inner class VacancyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lookingNumber : TextView = itemView.findViewById(R.id.vacancy_looking_number)
        private val title : TextView = itemView.findViewById(R.id.vacancy_title)
        private val town : TextView = itemView.findViewById(R.id.vacancy_town)
        private val company : TextView = itemView.findViewById(R.id.vacancy_company)
        private val experience : TextView = itemView.findViewById(R.id.vacancy_experience)
        private val publishedDate : TextView = itemView.findViewById(R.id.vacancy_published_date)

        private val vacancyButton : Button = itemView.findViewById(R.id.vacancy_button)
        private val isFavorite : ImageView = itemView.findViewById(R.id.vacancy_is_favorite)

        init {
            vacancyButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onVacancyClick(position)
                }
            }
            isFavorite.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onIsFavoriteClick(position)
                }
            }
        }

        fun bind(data: VacancyModel){
            title.text = data.title
            town.text = data.addressModel.town
            company.text = data.company
            experience.text = data.experienceModel.previewText

            lookingNumber.text = "Сейчас просматривает ${data.lookingNumber} человек"
            publishedDate.text = "Опубликовано ${data.publishedDate}"
        }
    }

    class VacancyDiffCallback : DiffUtil.ItemCallback<VacancyModel>()  {
        override fun areItemsTheSame(oldItem: VacancyModel, newItem: VacancyModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: VacancyModel, newItem: VacancyModel): Boolean {
            return oldItem == newItem
        }
    }

    fun reload(data: List<VacancyModel>){
        submitList(data)
    }
}