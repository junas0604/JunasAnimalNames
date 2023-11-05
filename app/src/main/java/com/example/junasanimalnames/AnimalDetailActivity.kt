package com.example.junasanimalnames

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.junasanimalnames.constants.Constants
import com.example.junasanimalnames.databinding.ActivityAnimalDetailBinding

class AnimalDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(Constants.PARAM_NAME)
        val detail = intent.getStringExtra(Constants.PARAM_DETAIL)

        binding.name.text = name
        binding.detail.text = detail

        binding.block.setOnClickListener {
            val animalIndex = AnimalNameActivity.animals.indexOfFirst {
                it.name == name
            }
            if(animalIndex != -1) {
                AnimalNameActivity.animals[animalIndex].blocked = true
            }

            val updatedAnimalListJson = AnimalNameActivity.gson.toJson(AnimalNameActivity.animals)

            val editor = AnimalNameActivity.sharedPreferences!!.edit()
            editor.putString("animalList", updatedAnimalListJson)
            editor.apply()

            finish()
            startActivity(Intent(this, AnimalNameActivity::class.java))
        }

    }
}

