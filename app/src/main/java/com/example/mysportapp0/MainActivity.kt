package com.example.mysportapp0

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // счетчик
        var count: Int

        // получаем значение для count из интента
        val extraCount = intent?.extras?.getInt("extraCount")

        // если значение из интента не нулевое, присваиваем его count
        if (extraCount != null) {
            count = extraCount
        } else {
            count = 0
        }

        // вью счетчика
        val countView: TextView = findViewById(R.id.count)
        countView.text = count.toString()

        val btn: Button = findViewById(R.id.button)
        btn.setOnClickListener {
            // увеличиваем счетчик, присваиваем значение вью счетчика
            count++
            countView.text = count.toString()

            // передаем интентом значение count в активити TimerActivity
            // для того чтобы из TimerActivity передать его обратно и
            // сохранить значение count.
            val intent = Intent(this, TimerActivity::class.java)
            intent.putExtra("extraCount", count)
            startActivity(intent)
        }
    }
}