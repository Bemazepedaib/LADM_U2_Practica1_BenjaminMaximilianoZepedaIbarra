package mx.edu.ittepic.ladm_u2_practica1_benjaminmaximilianozepedaibarra

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random

class CopoDeNieve(l:Lienzo, tam:Float) {
    val l = l
    var x = 0f
    var y = 0f
    var viento = 0f //Movimiento en X
    var caida = 0f //Movimiento en Y
    var tamano = tam
    var color = Color.WHITE

    init {
        x = rand(1080)
        y = rand(1920)
        caida = rand(6)+2
        viento = rand(6)+2
    }
    fun viento(Veces: Int){
        var cont=0
        while (cont < Veces){
            viento+=5
            cont++
        }
    }
    fun caida(Veces : Int) {
        var cont=0
        while (cont < Veces){
            caida+=5f
            cont++
        }

    }
    fun mover(){
        x+=viento
        y+=caida
        if(y>1920) y=0f
        if(x>1080) x=0f
        if(x<0) x=1080f
    }
    fun pintar(canvas : Canvas){
        var p = Paint()
        p.color = color
        canvas.drawCircle(x,y,tamano,p)
    }
    private fun rand(hasta:Int) : Float {
        return Random.nextInt(hasta).toFloat()
    }
}