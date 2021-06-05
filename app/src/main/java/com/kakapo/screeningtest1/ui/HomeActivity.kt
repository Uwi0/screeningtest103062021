package com.kakapo.screeningtest1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
        methodPalindrome()
    }

    private fun methodPalindrome(){
        mBinding.btnNext.setOnClickListener{
            mBinding.btnNext.setBackgroundResource(R.drawable.btn_signup_selected)
            val nama = mBinding.etName.text.toString()
            if (nama.isEmpty()){
                Toast.makeText(
                    this,
                    "masukkan nama anda",
                    Toast.LENGTH_SHORT
                ).show()
                mBinding.btnNext.setBackgroundResource(R.drawable.btn_signup_normal)
            }else{
                when {
                    nama.equals("kasur rusak", true) -> {
                        Toast.makeText(
                            this,
                            "it's palindrome",
                            Toast.LENGTH_SHORT
                        ).show()
                        setupNewActivityWithDelay(nama)
                    }
                    nama.equals("step on no pets", true) -> {
                        Toast.makeText(
                            this,
                            "it's palindrome",
                            Toast.LENGTH_SHORT
                        ).show()
                        setupNewActivityWithDelay(nama)
                    }
                    nama.equals("put it up", true) -> {
                        Toast.makeText(
                            this,
                            "it's palindrome",
                            Toast.LENGTH_SHORT
                        ).show()
                        setupNewActivityWithDelay(nama)
                    }
                    nama.equals("suitmedia", true) -> {
                        Toast.makeText(
                            this,
                            "not palindrome",
                            Toast.LENGTH_SHORT
                        ).show()
                        setupNewActivityWithDelay(nama)
                    }
                    else -> {
                        setupNewActivity(nama)
                    }
                }
            }
        }
    }

    private fun setupNewActivity(name: String){
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra(NAME, name)
        startActivity(intent)
        mBinding.etName.text.clear()
    }

    private fun setupNewActivityWithDelay(name: String){
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra(NAME, name)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            mBinding.etName.text.clear()
        }, 1500)
    }
}