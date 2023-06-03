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
            .registerAlarmType(AlarmsType.SET_ALARM_CLOCK)
            .registerAlarmManager(this)
            .registerBroadcastReceiverAlarm(AlarmReceiver(), this)
            .calendarInTime(Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 2)
                set(Calendar.MINUTE, 9)
            })
            .setAlarm()
            .builder()
    }
}


 class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("Alarm", "Alarm")
        Toast.makeText(context, "Alarm", Toast.LENGTH_SHORT).show()
    }
}