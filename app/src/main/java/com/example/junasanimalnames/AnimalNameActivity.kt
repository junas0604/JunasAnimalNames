package com.example.junasanimalnames

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.junasanimalnames.adapter.AnimalAdapter
import com.example.junasanimalnames.databinding.ActivityAnimalNameBinding
import com.example.junasanimalnames.model.Animal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AnimalNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalNameBinding

    companion object {
        val blockedAnimal = ArrayList<Animal>()
        var sharedPreferences: SharedPreferences? = null
        val gson = Gson()

        fun retrieveData (): ArrayList<Animal> {
            val animalListJson = sharedPreferences!!.getString("animalList", null)
            return if (animalListJson != null) {
                val type = object : TypeToken<ArrayList<Animal>>() {}.type
                gson.fromJson(animalListJson, type)
            } else {
                ArrayList()
            }
        }

        fun block(animalToBlock: String) {
            blockedAnimal.add(Animal(blockedAnimal.toString()))
            val animalListJson = gson.toJson(blockedAnimal)
            val editor = sharedPreferences!!.edit()
            editor.putString("animalList", animalListJson)
            editor.apply()
        }
        val animals = arrayListOf(
            Animal("Antelope", "Antelopes are herbivorous mammals known for their fast running abilities.", false),
            Animal("Bear", "Bears are large, strong mammals that are found in various habitats worldwide.", false),
            Animal("Cheetah", "Cheetahs are the fastest land animals, known for their speed and agility.", false),
            Animal("Dolphin", "Dolphins are highly intelligent marine mammals known for their playful behavior.", false),
            Animal("Elephant", "Elephants are the largest land animals and known for their long trunks.", false),
            Animal("Fox", "Foxes are small carnivorous mammals with bushy tails.", false),
            Animal("Giraffe", "Giraffes are known for their long necks and spotted coats.", false),
            Animal("Hippopotamus", "Hippos are large, mostly herbivorous mammals found near water.", false),
            Animal("Iguana", "Iguanas are reptiles known for their colorful scales.", false),
            Animal("Jaguar", "Jaguars are big cats known for their powerful jaws.", false),
            Animal("Kangaroo", "Kangaroos are marsupials known for their hopping locomotion.", false),
            Animal("Lion", "Lions are large cats, often referred to as 'the king of the jungle.'", false),
            Animal("Monkey", "Monkeys are primates known for their intelligence and agility.", false),
            Animal("Newt", "Newts are small, aquatic amphibians with vibrant colors.", false),
            Animal("Ostrich", "Ostriches are flightless birds known for their long legs.", false),
            Animal("Panda", "Pandas are bears native to China, known for their black and white fur.", false),
            Animal("Quokka", "Quokkas are small marsupials known for their friendly appearance.", false),
            Animal("Rhinoceros", "Rhinoceroses are large herbivorous mammals with thick skin.", false),
            Animal("Sloth", "Sloths are slow-moving mammals known for their tree-dwelling lifestyle.", false),
            Animal("Tiger", "Tigers are big cats known for their striped patterns.", false),
            Animal("Uakari", "Uakaris are primates with bald faces and red faces.", false),
            Animal("Vulture", "Vultures are scavenging birds known for their bald heads.", false),
            Animal("Walrus", "Walruses are marine mammals known for their tusks.", false),
            Animal("X-ray Tetra", "X-ray Tetras are small fish with transparent bodies.", false),
            Animal("Yak", "Yaks are domesticated bovids native to the Himalayas.", false),
            Animal("Zebra", "Zebras are known for their black and white stripes.", false)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("animals", Context.MODE_PRIVATE)
        sharedPreferences = getSharedPreferences("blockedAnimals", Context.MODE_PRIVATE)
        binding.animalList.layoutManager = LinearLayoutManager(this)

        if(retrieveData().isEmpty()) {
            val gson = Gson()
            val animalListJson = gson.toJson(animals)
            val editor = sharedPreferences!!.edit()
            editor.putString("animalList", animalListJson)
            editor.apply()
        }

        val validAnimalList = ArrayList<Animal>()

        for (animal in retrieveData()) {
            if(animal.blocked != true) {
                validAnimalList.add(animal)
            }
        }

        binding.animalList.adapter = AnimalAdapter(this, validAnimalList)

        binding.toBlock.setOnClickListener {
            val intent = Intent(this, ManageBlockActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
