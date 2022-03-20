package mx.edu.ittepic.ladm_u2_practica1_benjaminmaximilianozepedaibarra

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class Lienzo(este:MainActivity) : View(este) {
    val este = este
    var cTit = ""
    var NieveLigera = Array<CopoDeNieve>(300, { CopoDeNieve(this, 15f) })
    var NieveNormal = Array<CopoDeNieve>(300, {CopoDeNieve(this, 25f)})
    var NievePesada = Array<CopoDeNieve>(300, {CopoDeNieve(this, 35f)})
    var fondo = Color.rgb(16,44,84)
    var climaOpcion=-1
    var contador=0
    val coroutina = GlobalScope.launch{
        while (true){
            este.runOnUiThread{
                invalidate()
            }
            delay(5L)
        }
    }
    fun clima() : Int{
        if(rand(100)>66) return 0
        if(rand(100)>33 && rand(100)<66) return 1
        if(rand(100)<33) return 2
        return -1
    }
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        este.setTitle("Clima: ${cTit}")
        c.drawColor(fondo)
        var p = Paint()
        //////////////////////////////////////////////////
        //Suelo
        p.color = Color.rgb(200,224,228)
        c.drawRect(0f, 800f, width.toFloat(), height.toFloat(), p)
        //Arbol 1
        p.color = Color.rgb(128, 64, 0)
        c.drawRect(125f, 850f, 175f, 1150f, p)
        p.color = Color.rgb(0, 130, 127)
        c.drawCircle(150f,850f,125f,p)
        c.drawCircle(150f,725f,100f,p)
        c.drawCircle(150f,625f,75f,p)
        //Arbol 2
        p.color = Color.rgb(128, 64, 0)
        c.drawRect(910f, 750f, 945f, 975f, p)
        p.color = Color.rgb(0, 130, 127)
        c.drawCircle(925f,750f,100f,p)
        c.drawCircle(925f,650f,75f,p)
        c.drawCircle(925f,575f,50f,p)
        //Casa
            //Paredes
        p.color = Color.YELLOW
        c.drawRect(200f,1150f,900f,1550f,p)
        p.color = Color.rgb(204,204,0)
        c.drawRect(200f,1150f,900f,1175f,p)
        p.color = Color.BLACK
        p.strokeWidth = 5f
        c.drawLine(200f,1550f,900f,1550f,p)
            //Techo
        p.color = Color.rgb(237,135,45)
        c.drawRect(150f,1025f,950f,1150f,p)
        p.color = Color.BLACK
        p.strokeWidth = 5f
        c.drawLine(150f,1150f,950f,1150f,p)
            //Chimenea
        p.color = Color.rgb(188,74,60)
        c.drawRect(300f,900f,450f,1025f,p)
        p.color = Color.BLACK
        p.strokeWidth = 5f
        c.drawLine(300f,1025f,450f,1025f,p)
                //Humo
        p.color = Color.rgb(200,224,228)
        c.drawCircle(530f, 520f, 50f, p)
        c.drawCircle(480f, 570f, 50f, p)
        c.drawCircle(410f, 620f, 50f, p)
            //Puerta
        p.color = Color.rgb(128, 64, 0)
        c.drawRect(675f,1250f,850f,1550f,p)
        p.color = Color.BLACK
        c.drawCircle(825f,1400f,10f,p)
            //Ventana
        p.color = Color.rgb(153,255,255)
        c.drawRect(250f, 1225f, 625f,1450f,p)
        p.color = Color.BLACK
        p.strokeWidth = 2f
        c.drawLine(250f,1225f,625f,1225f,p)
        c.drawLine(250f,1450f,625f,1450f,p)
        c.drawLine(250f,1225f,250f,1450f,p)
        c.drawLine(625f,1225f,625f,1450f,p)
        c.drawLine(437.5f,1225f,437.5f,1450f,p)
        c.drawLine(250f,1337.5f,625f,1337.5f,p)
        //////////////////////////////////////////////////
        if (climaOpcion==-1){
            climaOpcion=clima()
        }
        else {
            if (contador>100000){
                climaOpcion=-1
                contador=0
            } else {
                when(climaOpcion){
                    0->{
                        for(copo in NieveLigera){
                            copo.mover()
                            copo.pintar(c)
                            contador++
                            cTit="Normal"
                        }
                    }
                    1->{
                        for(copo in NieveNormal){
                            copo.mover()
                            copo.pintar(c)
                            copo.viento = 20f
                            contador++
                            cTit="Ventisca"
                        }
                    }
                    2->{
                        for(copo in NievePesada){
                            copo.mover()
                            copo.pintar(c)
                            copo.caida = 20f
                            contador++
                            cTit="Nevada"
                        }
                    }
                }
            }
        }
    }
    private fun rand(hasta:Int) : Float {
        return Random.nextInt(hasta).toFloat()
    }
}