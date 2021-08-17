package com.picpay.desafio.android

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.UserViewModel.*
import com.picpay.desafio.android.UserViewModel.State.OnLoading
import com.picpay.desafio.android.UserViewModel.State.OnSuccess
import com.picpay.desafio.android.adapters.UserListAdapter
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.data.services.PicPayService
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val userViewModel: UserViewModel by viewModel()

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE

        observeData()

        userViewModel.getUsers()
    }

    private fun observeData() {
        userViewModel.state.observe(this) {
            when (it) {
                is OnSuccess -> {
                    progressBar.visibility = View.GONE
                    adapter.users = it.users
                }
                State.OnError -> {
                    val message = getString(R.string.error)

                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }
                OnLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}
