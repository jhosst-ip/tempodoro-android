package com.ipial.jhostyn.tempodoro.activities

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.ipial.jhostyn.tempodoro.databinding.ActivityPomodoroBinding
import com.ipial.jhostyn.tempodoro.utils.NotificationHelper
class PomodoroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPomodoroBinding

    private var timer: CountDownTimer? = null

    private var tiempoRestante = 25 * 60 * 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPomodoroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mostrarTiempo()

        binding.btnIniciar.setOnClickListener {
            iniciarPomodoro()
        }

        binding.btnReiniciar.setOnClickListener {
            reiniciarPomodoro()
        }
    }

    private fun iniciarPomodoro() {

        timer?.cancel()

        timer = object : CountDownTimer(tiempoRestante,1000){

            override fun onTick(millisUntilFinished: Long) {

                tiempoRestante = millisUntilFinished

                mostrarTiempo()

            }

            override fun onFinish() {

                binding.txtTiempo.text="00:00"

                NotificationHelper(this@PomodoroActivity)
                    .mostrarNotificacion("¡Tiempo terminado!", "Tu sesión de Pomodoro ha finalizado.")

            }

        }

        timer?.start()

    }

    private fun reiniciarPomodoro(){

        timer?.cancel()

        tiempoRestante=25*60*1000L

        mostrarTiempo()

    }

    private fun mostrarTiempo(){

        val minutos=(tiempoRestante/1000)/60
        val segundos=(tiempoRestante/1000)%60

        binding.txtTiempo.text=String.format("%02d:%02d",minutos,segundos)

    }

}