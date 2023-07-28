package com.example.mysportapp0

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TimerActivity: AppCompatActivity() {
    var mMediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val seconds: TextView = findViewById(R.id.seconds)

        // ролучаем значение count из интента из MainActivity
        val extraCount = intent?.extras?.getInt("extraCount")

        // запускаем таймер
        object: CountDownTimer(20000, 1000) {
            override fun onTick(p0: Long) {
                // присваиваем вью секунд значение количества оставшихся секунд
                seconds.text = (p0 / 1000).toString()
            }

            // после завершения таймера
            override fun onFinish() {
                // проигрываем звук гонга
                playSound()

                // интентом передаем значение count из MainActivity в MainActivity
                val intent = Intent(this@TimerActivity, MainActivity::class.java)
                intent.putExtra("extraCount", extraCount)
                startActivity(intent)
            }

        }.start()
    }

    // проигрываем звук гонга
    fun playSound() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.gong)
        }
        mMediaPlayer?.start()

        // после завершения проигрывания записи
        mMediaPlayer?.setOnCompletionListener {
            // останавливаем и обнуляем плеер
            mMediaPlayer?.stop()
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }
}