package com.kakapo.screeningtest1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kakapo.screeningtest1.R
import com.kakapo.screeningtest1.adapter.EventAdapter
import com.kakapo.screeningtest1.adapter.GuestAdapter
import com.kakapo.screeningtest1.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    companion object{
        lateinit var nameStatic: String
    }

    private lateinit var mBinding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupToolbar()
        setupView()
    }

    private fun setupToolbar(){
        setSupportActionBar(mBinding.toolbarMenu)
        supportActionBar?.let {
            it.title = resources.getString(R.string.menu)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.toolbarMenu.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupView(){
        if (intent.hasExtra(EventAdapter.EVENT)){
            val name = intent.getStringExtra(EventAdapter.EVENT)
            mBinding.btnEvent.text = name
        }
        if (intent.hasExtra(HomeActivity.NAME)){
            val name = intent.getStringExtra(HomeActivity.NAME)
            val nameValue = resources.getString(R.string.name, name)
            mBinding.tvNama.text = nameValue
            nameStatic = nameValue
        }else{
            mBinding.tvNama.text = nameStatic
        }
        if (intent.hasExtra(GuestAdapter.GUEST_NAME)){
            val name = intent.getStringExtra(GuestAdapter.GUEST_NAME)
            mBinding.btnGuest.text = name
        }
        if (intent.hasExtra(GuestAdapter.GUEST_DATE)){
            var numberValue = intent.getStringExtra(GuestAdapter.GUEST_DATE)
            val regrex = "0"
            numberValue = numberValue?.replace(regrex, "")
            val number = numberValue!!.toInt()
            if (number % 2 == 0){
                Toast.makeText(
                    this,
                    "blackberry",
                    Toast.LENGTH_SHORT
                ).show()
            }else if(number % 3 == 0){
                Toast.makeText(
                    this,
                    "android",
                    Toast.LENGTH_SHORT
                ).show()
            }else if(number % 2 == 0 && number % 3 == 0){
                Toast.makeText(
                    this,
                    "IOS",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(
                    this,
                    "phone",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        mBinding.btnEvent.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }
        mBinding.btnGuest.setOnClickListener {
            val intent = Intent(this, GuestActivity::class.java)
            startActivity(intent)
        }

    }
}