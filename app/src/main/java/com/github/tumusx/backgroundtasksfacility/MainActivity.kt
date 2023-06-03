package com.github.tumusx.backgroundtasksfacility

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.tumusx.easy_background_tasks.alarmManager.EasyTaskAlarm
import com.github.tumusx.easy_background_tasks.typeAlarms.AlarmsType
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EasyTaskAlarm.EasyAlarmTaskBuilder()
            .registerAlarmType(AlarmsType.SET_EXACT)
            .registerAlarmManager(this)
            .registerBroadcastReceiverAlarm(AlarmReceiver(), this)
            .calendarInTime(Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 1)
                set(Calendar.MINUTE, 59)
                set(Calendar.AM_PM, Calendar.AM)
                set(Calendar.DAY_OF_MONTH, 3)
                set(Calendar.MONTH, 6)
                set(Calendar.YEAR, 2023)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            })
            .setAlarm()
            .builder()
    }

    inner class AlarmReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Alarm", Toast.LENGTH_SHORT).show()
            Log.d("Alarm", "Alarm")
        }
    }
}