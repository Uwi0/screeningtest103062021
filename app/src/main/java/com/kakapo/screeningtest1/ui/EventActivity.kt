package com.kakapo.screeningtest1.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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

    private fun setupToolbar(){
        setSupportActionBar(mBinding.toolbarEvent)
        supportActionBar?.let {
            it.title = resources.getString(R.string.event)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.toolbarEvent.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }
    }

    private fun initialiseAdapter(){
        mBinding.rvEvent.layoutManager = LinearLayoutManager(this)
        mEvent = EventAdapter(this)
        mBinding.rvEvent.adapter = mEvent
        mBinding.rvEvent.setHasFixedSize(true)
        mBinding.rvEvent.
        addItemDecoration(DividerItemDecoration(this.applicationContext, DividerItemDecoration.VERTICAL))
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