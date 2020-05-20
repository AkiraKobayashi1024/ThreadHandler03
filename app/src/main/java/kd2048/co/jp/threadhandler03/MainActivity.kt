package kd2048.co.jp.threadhandler03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val handler :Handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what) {
                1 -> {
                    Log.d("tetsu", msg.obj.toString())
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val text = findViewById<TextView>(R.id.text)

        Log.d("tetsu", "thread ${mainLooper.thread}")

        // 
        thread{
            Log.d("tetsu", "${Thread.currentThread()} thread sleep")
            Thread.sleep(10000)
            val r = Runnable {
                Log.d("tetsu", "${Thread.currentThread()} Runnable")
                val str = "RunnnaableをUIスレッドで実行して表示更新"
                text.setText(str)
            }
            handler.post(r)
        }

        thread{
            Log.d("tetsu", "${Thread.currentThread()} Message thread")
            Thread.sleep(6000)
            val msg = handler.obtainMessage(1, "test")
            handler.handleMessage(msg)
        }
    }
}
