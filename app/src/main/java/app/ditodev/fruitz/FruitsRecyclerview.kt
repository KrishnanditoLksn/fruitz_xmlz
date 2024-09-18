package app.ditodev.fruitz

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.ditodev.fruitz.adapter.FruitListsAdapter
import app.ditodev.fruitz.data.Fruit

class FruitsRecyclerview : AppCompatActivity() {
    private  lateinit var fruitRv : RecyclerView
    private var list = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fruits_recyler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fruitRv = findViewById(R.id.rv_fruits)
        Log.d("MainActivity" , fruitRv.toString())
        fruitRv.setHasFixedSize(true)

        Utils.changeStatusBarColor(window , "#3498db")
        list.addAll(getFruitLists())
        showLists()
    }

    @SuppressLint("Recycle")
    private fun getFruitLists():ArrayList<Fruit>{
        val datasName = resources.getStringArray(R.array.data_name)
        val datasDescription = resources.getStringArray(R.array.data_description)
        val datasPhotos = resources.obtainTypedArray(R.array.data_photos)
        val listsFruits =  ArrayList<Fruit>()

        for (i in datasName.indices){
            val fruits = Fruit(datasName[i] , datasDescription[i] , datasPhotos.getResourceId(i , i - 1))
            listsFruits.add(fruits)
        }

        return listsFruits
    }

    private fun showLists(){
        fruitRv.layoutManager = LinearLayoutManager(this)
        val listAdapter  = FruitListsAdapter(list)
        fruitRv.adapter = listAdapter
    }
}