package com.ipial.jhostyn.tempodoro.activities

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.ipial.jhostyn.tempodoro.databinding.ActivityPomodoroBinding
import com.ipial.jhostyn.tempodoro.utils.NotificationHelper



class PomodoroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPomodoroBinding

    private var timer: CountDownTimer? = null

    private var tiempoPomodoro = 25 * 60 * 1000L
    private var tiempoDescanso = 5 * 60 * 1000L

    private var tiempoRestante = tiempoPomodoro

    private var esDescanso = false
    private var pausado = false

    private var completados = 0
    private var estimados = 1

    private var tiempoPomodoroMinutos = 25
    private var tiempoDescansoMinutos = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPomodoroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titulo = intent.getStringExtra("titulo") ?: "Pomodoro"

        completados = intent.getIntExtra("completados", 0)
        estimados = intent.getIntExtra("estimados", 1)

        binding.txtTitulo.text = titulo
        binding.txtProgreso.text = "$completados/$estimados"

        mostrarTiempo()

        binding.btnIniciar.setOnClickListener {
            iniciarTemporizador()
        }

        binding.btnPausar.setOnClickListener {
            timer?.cancel()
            pausado = true
        }


        binding.btnReiniciar.setOnClickListener {

            timer?.cancel()

            tiempoRestante =
                if (esDescanso)
                    tiempoDescanso
                else
                    tiempoPomodoro

            mostrarTiempo()
        }
        binding.btnVolver.setOnClickListener {
            finish()
        }
        binding.btnGuardarConfiguracion.setOnClickListener {

            val pomodoro =
                binding.edtPomodoro.text.toString()

            val descanso =
                binding.edtDescanso.text.toString()

            if (pomodoro.isNotEmpty()) {
                tiempoPomodoroMinutos =
                    pomodoro.toInt()
            }

            if (descanso.isNotEmpty()) {
                tiempoDescansoMinutos =
                    descanso.toInt()
            }

            tiempoPomodoro =
                tiempoPomodoroMinutos * 60 * 1000L

            tiempoDescanso =
                tiempoDescansoMinutos * 60 * 1000L

            tiempoRestante =
                if (esDescanso)
                    tiempoDescanso
                else
                    tiempoPomodoro

            mostrarTiempo()
        }
        binding.btnSaltar.setOnClickListener {

            timer?.cancel()

            if (esDescanso) {

                esDescanso = false
                tiempoRestante = tiempoPomodoro

            } else {

                esDescanso = true
                tiempoRestante = tiempoDescanso
            }

            mostrarTiempo()
        }
    }

    private fun iniciarTemporizador() {

        timer?.cancel()

        timer = object : CountDownTimer(
            tiempoRestante,
            1000
        ) {

            override fun onTick(millisUntilFinished: Long) {

                tiempoRestante = millisUntilFinished

                mostrarTiempo()
            }

            override fun onFinish() {

                if (!esDescanso) {

                    completados++

                    binding.txtProgreso.text =
                        "$completados/$estimados"

                    NotificationHelper(this@PomodoroActivity)
                        .mostrarNotificacion(
                            "Pomodoro terminado",
                            "Comienza tu descanso de 5 minutos."
                        )

                    if (completados >= estimados) {

                        NotificationHelper(this@PomodoroActivity)
                            .mostrarNotificacion(
                                "Tarea completada",
                                "Has completado todos los pomodoros."
                            )
                        finish()

                    } else {

                        esDescanso = true
                        tiempoRestante = tiempoDescanso
                        mostrarTiempo()
                    }

                } else {

                    NotificationHelper(this@PomodoroActivity)
                        .mostrarNotificacion(
                            "Descanso terminado",
                            "Es momento de volver al estudio."
                        )

                    esDescanso = false
                    tiempoRestante = tiempoPomodoro
                    mostrarTiempo()
                }
            }
        }

        timer?.start()
    }

    private fun mostrarTiempo() {

        val minutos =
            (tiempoRestante / 1000) / 60

        val segundos =
            (tiempoRestante / 1000) % 60

        binding.txtTiempo.text =
            String.format(
                "%02d:%02d",
                minutos,
                segundos
            )

        binding.txtEstado.text =
            if (esDescanso)
                "Descanso"
            else
                "Pomodoro"
    }
}