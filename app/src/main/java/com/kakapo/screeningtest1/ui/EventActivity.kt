package com.kakapo.screeningtest1.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kakapo.screeningtest1.R
import com.kakapo.screeningtest1.adapter.EventAdapter
import com.kakapo.screeningtest1.databinding.ActivityEventBinding
import com.kakapo.screeningtest1.utils.Constant
import com.kakapo.screeningtest1.viewmodel.EventViewModel
import com.kakapo.screeningtest1.viewmodel.EventViewModelFactory

class EventActivity : AppCompatActivity() {

    private lateinit var viewModel: EventViewModel
    private lateinit var mBinding: ActivityEventBinding
    private lateinit var mEvent: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupToolbar()
        val factory = EventViewModelFactory()
        viewModel = ViewModelProvider(this,factory).get(EventViewModel::class.java)
        addData()
        initialiseAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_ivent, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search -> {
                Toast.makeText(
                    this,
                    "Search Icon",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.action_new_media_event -> {
                item.setIcon(R.drawable.btn_new_media_article_selected)
                Toast.makeText(
                    this,
                    "New Media",
                    Toast.LENGTH_SHORT
                ).show()
                item.setIcon(R.drawable.btn_newmediaarticle_normal)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar(){
        setSupportActionBar(mBinding.toolbarEvent)
        supportActionBar?.let {
            it.title = resources.getString(R.string.event)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.toolbarEvent.setNavigationIcon(R.drawable.btn_backarticle_normal)
        mBinding.toolbarEvent.setNavigationOnClickListener {
            mBinding.toolbarEvent.setNavigationIcon(R.drawable.btn_backarticle_selected)
            onBackPressed()
            finish()
        }
    }

    private fun initialiseAdapter(){
        mBinding.rvEvent.layoutManager = LinearLayoutManager(this)
        mEvent = EventAdapter(this)
        mBinding.rvEvent.adapter = mEvent
        mBinding.rvEvent.setHasFixedSize(true)
        viewModel.list.observe(this, {
            Log.i("data", it.toString())
            mEvent.eventList(it)
        })
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun addData(){
        val listEvent = Constant.eventList()
        listEvent.forEach{
            viewModel.add(it)
            mBinding.rvEvent.adapter?.notifyDataSetChanged()
        }
    }
}