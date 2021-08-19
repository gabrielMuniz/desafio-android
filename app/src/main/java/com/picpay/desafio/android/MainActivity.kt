package com.picpay.desafio.android

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.UserViewModel.*
import com.picpay.desafio.android.UserViewModel.State.OnLoading
import com.picpay.desafio.android.UserViewModel.State.OnSuccess
import com.picpay.desafio.android.adapters.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

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
                    adapter.users = it.userEntities
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
