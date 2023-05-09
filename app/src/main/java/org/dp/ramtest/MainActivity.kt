package org.dp.ramtest

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.dp.ramtest.R.id.helloTextView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val helloTextView = findViewById<TextView>(helloTextView);
        helloTextView.text = "Total memory: " + bytesToHuman(getAvailableMemory().totalMem);
    }

    private fun getAvailableMemory(): ActivityManager.MemoryInfo {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return ActivityManager.MemoryInfo().also { memoryInfo ->
            activityManager.getMemoryInfo(memoryInfo)
        }
    }

    private fun bytesToHuman(size: Long): String? {
        val kb: Long = 1024
        val mb = kb * 1024
        val gb = mb * 1024
        val tb = gb * 1024
        val pb = tb * 1024
        val eb = pb * 1024
        if (size < kb) return floatForm(size.toDouble()) + " byte"
        if (size in kb until mb) return floatForm(size.toDouble() / kb) + " KB"
        if (size in mb until gb) return floatForm(size.toDouble() / mb) + " MB"
        if (size in gb until tb) return floatForm(size.toDouble() / gb) + " GB"
        if (size in tb until pb) return floatForm(size.toDouble() / tb) + " TB"
        if (size in pb until eb) return floatForm(size.toDouble() / pb) + " Pb"
        return "" + size.toDouble() / eb + " Eb"
    }

    private fun floatForm(d: Double): String? {
        return String.format(Locale.US, "%.2f", d)
    }
}