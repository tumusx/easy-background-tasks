package com.github.tumusx.easy_background_tasks.alarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.github.tumusx.easy_background_tasks.typeAlarms.AlarmsType
import java.util.Calendar
import java.util.Date

class EasyTaskAlarm {

    class EasyAlarmTaskBuilder {
        var typeAlarm: AlarmsType = AlarmsType.SET_EXACT
            private set

        var alarmManager: AlarmManager? = null
            private set

        var pendingIntent: PendingIntent? = null
            private set

        var action: PendingIntent? = null
            private set

        var time: Long = 0
            private set



        fun registerAlarmType(alarmsType: AlarmsType) = apply { typeAlarm = alarmsType }

        fun actionAlarmClock(context: Context, intent: Intent) = apply {
            action = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        }

        fun registerAlarmManager(context: Context) = apply {
            alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        }

         fun registerPendingIntentService(service: Service, context: Context) = apply {
            pendingIntent = PendingIntent.getService(
                context,
                0,
                Intent(context, service::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
        }

        fun registerBroadcastReceiverAlarm(broadcastReceiver: BroadcastReceiver, context: Context) = apply {
            pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, broadcastReceiver::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
        }

        fun calendarInTime(calendar: Calendar) = apply {
            Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY))
                set(Calendar.MINUTE, calendar.get(Calendar.MINUTE))
                this@EasyAlarmTaskBuilder.time = timeInMillis
            }
        }

        fun setAlarm(typeCurrentMilli: Int = AlarmManager.RTC_WAKEUP) = apply {
            when(typeAlarm){
                AlarmsType.SET -> alarmManager?.set(typeCurrentMilli, time, pendingIntent)
                AlarmsType.SET_EXACT -> alarmManager?.setExact(typeCurrentMilli, time, pendingIntent)
                AlarmsType.SET_AND_ALLOW_WHILE_IDLE -> alarmManager?.setAndAllowWhileIdle(typeCurrentMilli, time, pendingIntent)
                AlarmsType.SET_EXACT_AND_ALLOW_WHILE_IDLE -> alarmManager?.setExactAndAllowWhileIdle(typeCurrentMilli, time, pendingIntent)
                AlarmsType.SET_WINDOW -> alarmManager?.setWindow(typeCurrentMilli, time, 0, pendingIntent)
                AlarmsType.SET_ALARM_CLOCK -> alarmManager?.setAlarmClock(AlarmManager.AlarmClockInfo(time, action), pendingIntent)
            }
        }

        fun builder() = EasyTaskAlarm()

    }
}