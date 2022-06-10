package com.las.stridechallenger.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.las.stridechallenger.BASE_URL
import com.las.stridechallenger.R
import com.las.stridechallenger.model.Character
import com.las.stridechallenger.model.CharacterResponse
import com.las.stridechallenger.network.Endpoint
import com.las.stridechallenger.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var charactersRecycleView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter
    var items: List<Character> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
        initViews()
    }

    private fun initViews() {
        charactersRecycleView = findViewById(R.id.character_recyclerview)
    }

    private fun fetchItemsOnAdapter(items: List<Character>) {
        val layoutManager = LinearLayoutManager(applicationContext)
        characterAdapter = CharacterAdapter(items)
        charactersRecycleView.layoutManager = layoutManager
        charactersRecycleView.adapter = characterAdapter
    }

    private fun getData(){
        val retrofitClient = NetworkManager.getRetrofitInstance(BASE_URL)
        retrofitClient.create(Endpoint::class.java).run {
            getCharacters().enqueue(object: Callback<CharacterResponse> {
                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {
                    val result = response.body()
                    result?.let {
                        items = it.results
                        fetchItemsOnAdapter(items)
                    }
                }

                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}