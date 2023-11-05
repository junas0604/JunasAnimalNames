package com.example.junasanimalnames.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.junasanimalnames.AnimalNameActivity
import com.example.junasanimalnames.databinding.BlockItemAnimalBinding
import com.example.junasanimalnames.model.Animal

class BlockedAnimalAdapter(private val activity: Activity, private val animal: ArrayList<Animal>): RecyclerView.Adapter<BlockedAnimalAdapter.AnimalViewHolder>(){

    class AnimalViewHolder(val binding: BlockItemAnimalBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        return AnimalViewHolder(BlockItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return animal.size
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.binding.name.text = animal[position].name.toString()
        holder.binding.unblock.setOnClickListener {
            val animalIndex = AnimalNameActivity.animals.indexOfFirst {
                it.name == animal[position].name.toString()
            }
            if(animalIndex != -1) {
                AnimalNameActivity.animals[animalIndex].blocked = false
            }

            val updatedAnimalListJson = AnimalNameActivity.gson.toJson(AnimalNameActivity.animals)

            val editor = AnimalNameActivity.sharedPreferences!!.edit()
            editor.putString("animalList", updatedAnimalListJson)
            editor.apply()

            activity.startActivity(Intent(activity, AnimalNameActivity::class.java))
            activity.finish()
        }
    }
}