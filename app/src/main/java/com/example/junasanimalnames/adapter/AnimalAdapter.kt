package com.example.junasanimalnames.adapter
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.junasanimalnames.AnimalDetailActivity
import com.example.junasanimalnames.constants.Constants
import com.example.junasanimalnames.databinding.ItemAnimalBinding
import com.example.junasanimalnames.model.Animal

class AnimalAdapter (private val activity: Activity, private val animal: ArrayList<Animal>): RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>(){


class AnimalViewHolder(private val activity: Activity, private val binding:ItemAnimalBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(animal: Animal) {
        binding.name.text = animal.name
        binding.row.setOnClickListener {

            val intent = Intent(activity, AnimalDetailActivity::class.java)
            intent.putExtra(Constants.PARAM_NAME, animal.name)
            intent.putExtra(Constants.PARAM_DETAIL, animal.detail)

            activity.startActivity(intent)
        }
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(activity, binding)
    }

    override fun getItemCount() = animal.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(animal[position])
    }

}