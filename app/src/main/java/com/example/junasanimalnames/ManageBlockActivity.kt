package com.example.junasanimalnames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.junasanimalnames.adapter.BlockedAnimalAdapter
import com.example.junasanimalnames.databinding.ActivityManageBlockBinding
import com.example.junasanimalnames.model.Animal


class ManageBlockActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageBlockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageBlockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.blockedAnimalList.layoutManager = LinearLayoutManager(this)

        val blockedAnimal = ArrayList<Animal>()

        for (animal in AnimalNameActivity.retrieveData()) {
            if(animal.blocked != false) {
                blockedAnimal.add(animal)
            }
        }
        binding.blockedAnimalList.adapter = BlockedAnimalAdapter(this, blockedAnimal)

    }
}