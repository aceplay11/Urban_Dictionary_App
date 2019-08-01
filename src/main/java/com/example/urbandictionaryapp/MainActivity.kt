package com.example.urbandictionaryapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urbandictionaryapp.model.datasource.UrbanDictionary.ResponseList
import com.example.urbandictionaryapp.model.datasource.UrbanDictionary.UrbanDictionaryResponse
import com.example.urbandictionaryapp.model.datasource.remote.AtlUrbanDictionaryApiService
import com.example.urbandictionaryapp.model.datasource.remote.BASE_URL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    lateinit var list: List<ResponseList>
    var sortFlag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    //Onclick methods for buttons
    fun onClick(view: View) =
        when (view.id) {
            R.id.btnSubmit -> {
                btnThumbSort.visibility = View.VISIBLE
                pgProgress.visibility = View.VISIBLE
                if (etUserInput.text.isNotBlank()) {
                    etUserInput.setBackgroundColor(Color.TRANSPARENT)
                    var entry = etUserInput.text.toString()
                    tvDefinedWord.text = entry
                    getUrbanDefinition(entry)
                } else {
                    etUserInput.setBackgroundColor(Color.RED)
                    Toast.makeText(this, "Input field is empty!", Toast.LENGTH_SHORT).show()
                }

            }

            R.id.btnThumbSort ->{
                when (sortFlag) {
                    0 -> {
                        var thumbUpList = list.sortedByDescending { list -> list.thumbsUp }
                        populateRecyclerview(thumbUpList)

                        sortFlag = 1
                        view.btnThumbSort.setImageResource(R.drawable.ic_thumbs_down)

                    }
                    1 -> {

                        var thumbDownList = list.sortedByDescending { list -> list.thumbsDown }

                        sortFlag = 0
                        view.btnThumbSort.setImageResource(R.drawable.ic_thumbs_up)
                        populateRecyclerview(thumbDownList)

                    }
                    else -> {}
                }

            }
            else -> {}
        }

                private fun getUrbanDefinition(userInput: String){
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val response = retrofit.create(AtlUrbanDictionaryApiService::class.java)

        val callUrbanDictionary = response.userEntry(userInput)
        callUrbanDictionary.enqueue(object: Callback<UrbanDictionaryResponse> {
            override fun onFailure(call: Call<UrbanDictionaryResponse>, t: Throwable) {
                Log.e("TAG", t.message, t)
                Toast.makeText(this@MainActivity, "Error ${t.message}. Press Define Again", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UrbanDictionaryResponse>, response: Response<UrbanDictionaryResponse>) {
                val urbanResponse = response.body()
                if (urbanResponse?.list?.size!! > 0){

                    list = urbanResponse.list
                    populateRecyclerview(list)


                }else{
                    etUserInput.setBackgroundColor(Color.RED)
                    btnThumbSort.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Invalid input. Try with different word", Toast.LENGTH_SHORT).show()
                }
                pgProgress.visibility = View.GONE

            }

        })

    }

    fun populateRecyclerview(list: List<ResponseList>){
        rvDefinitions.layoutManager = LinearLayoutManager(applicationContext)
        rvDefinitions.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
        rvDefinitions.adapter = UrbanDictionaryRecyclerviewAdapter(list, applicationContext)
    }
}

