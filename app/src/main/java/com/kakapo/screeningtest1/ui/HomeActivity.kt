package com.kakapo.screeningtest1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kakapo.screeningtest1.R
import com.kakapo.screeningtest1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    companion object{
        const val NAME = "nama"
    }

    private lateinit var mBinding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupToolbar()
        setupIntent()
    }

    private fun setupToolbar(){
        setSupportActionBar(mBinding.toolbarMenu)
        supportActionBar?.let {
            it.title = resources.getString(R.string.home)
        }
    }

    private fun setupIntent(){
        mBinding.btnNext.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            val nama = mBinding.etName.text.toString()
            if (nama.isEmpty()){
                Toast.makeText(
                    this,
                    "masukkan nama anda",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                intent.putExtra(NAME, nama)
                startActivity(intent)
            }
        }
    }
}