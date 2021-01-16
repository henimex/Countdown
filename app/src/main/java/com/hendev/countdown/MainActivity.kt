package com.hendev.countdown

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var numara = 0
    var dk = 0
    var runable : Runnable = Runnable {  }
    var handeler = Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun selfcd(view: View) {
        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txtCD.text = "Kalan : ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                txtCD.text = "Kalan : 0"
            }
        }.start()
    }

    fun start(view: View) {

        runable = object :Runnable{
            override fun run() {
                numara+=1
                txtZaman.text = "Saniye : $numara"
                handeler.postDelayed(runable,100)
                if (numara %10 == 0){
                    dk+=1
                    txtDk.text ="Dakika : $dk"
                    if (numara == 60){
                        numara = 0
                        Toast.makeText(this@MainActivity,"60 Oldu",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        handeler.post(runable)

        /*while (numara < 10000){
            numara +=1
            txtZaman.text = "Sayaç $numara"
        }
        //Thread.sleep(1000)*/
    }

    fun stop(view: View) {
        handeler.removeCallbacks(runable)
    }
}