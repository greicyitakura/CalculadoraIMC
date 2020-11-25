package br.com.greicyitakura.calcimcapplication

import android.content.Context
import android.icu.text.DecimalFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalcular.setOnClickListener {
            if(etPeso.text.toString() != "" && etAltura.text.toString() != "" ) {
                val imc = calcIMC(etPeso.text.toString(), etAltura.text.toString())
                val df = DecimalFormat("#.00")
                val imcResp = "Seu IMC é: " + df.format(imc) + "\n" + checkIMC(imc)
                tvResp.text = imcResp
            }
            else{
                tvResp.text = "Digite seu peso e altura"
            }
            it.hideKeyboard()
        }
    }
    private fun calcIMC(peso: String, altura: String): Double  = peso.toDouble() / (altura.toDouble() * altura.toDouble())

    private fun checkIMC(db: Double): String{
        return when(db) {
            in 0..17 ->  " Você está muito abaixo do peso."
            in 17.1..18.49 ->  " Você está abaixo do peso."
            in 18.5..24.99 ->  " Você está no peso normal."
            in 25.0..29.99 ->  " Você está acima do peso."
            in 30.0..34.99 ->  "Você está com Obesidade I."
            in 35.0..39.99 -> "  Você está com Obesidade II (severa)."
            else ->  " Você está com Obesidade III (mórbida)."
        }
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}