    package com.example.p11_retrovidapp

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.util.Log
    import android.widget.Toast
    import androidx.recyclerview.widget.LinearLayoutManager
    import com.example.p11_retrovidapp.databinding.ActivityMainBinding
    import com.example.p11_retrovidapp.model.News
    import com.example.p11_retrovidapp.network.ApiClient
    import retrofit2.Call

    class MainActivity : AppCompatActivity() {

        private val TAG : String = "NGETEST"
        val binding by lazy {
            ActivityMainBinding.inflate(layoutInflater)
        }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)
            val client = ApiClient.getInstance()
            val response = client.getAllNews()

            response.enqueue(object : retrofit2.Callback<List<News>>{
                override fun onResponse(call: Call<List<News>>, response: retrofit2.Response<List<News>>) {
                    if (response.isSuccessful){
                        val result = response.body()
                        printLog(result.toString())
                        val adapterNews = NewsAdapter(result){
                                news ->
                            Toast.makeText(this@MainActivity,"Hei! you clicked an ${news.title}" ,
                                Toast.LENGTH_SHORT).show()
                        }
                        binding.recyclerView.adapter = adapterNews
                        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }

                override fun onFailure(call: Call<List<News>>, t: Throwable) {
                    printLog(t.toString())
                }
            })


        }

        private fun printLog(message: String ){
            Log.d(TAG, message)

        }
    }