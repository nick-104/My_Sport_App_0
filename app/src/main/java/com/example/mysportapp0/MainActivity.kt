package com.example.mysportapp0

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
    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // счетчик
        var count: Int = 0

        // вью секунд
        val seconds: TextView = findViewById(R.id.seconds)

        // вью счетчика
        val countView: TextView = findViewById(R.id.count)
        countView.text = count.toString()

        val btn: Button = findViewById(R.id.button)
        btn.setOnClickListener {
            // увеличиваем счетчик, присваиваем значение вью счетчика
            count++
            countView.text = count.toString()

            // делаем кнопку неактивной
            btn.isEnabled = false

            // запускаем таймер
            object: CountDownTimer(20000, 1000) {
                override fun onTick(p0: Long) {
                    // присваиваем вью секунд значение количества оставшихся секунд
                    seconds.text = (p0 / 1000).toString()
                }

                // после завершения таймера
                override fun onFinish() {
                    // делаем кнопку активной
                    btn.isEnabled = true
                    // обнуляем значение вью секунд (вью исчезает)
                    seconds.text = ""
                    /*
                    // Тоны (стандартные звуки)
                    val tonGen: ToneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 1000)
                    tonGen.startTone(ToneGenerator.TONE_DTMF_0, 300)
                     */

                    // проигрываем звук гонга
                    playSound()
                }

            }.start()
        }
    }

    // при остановке приложения
    override fun onStop() {
        super.onStop()
        // обнуляем плеер
        if (mMediaPlayer != null) {
            mMediaPlayer?.stop()
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }

    // проигрываем звук гонга
    fun playSound() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.gong)
        }
        mMediaPlayer?.start()
    }
}