package com.github.tumusx.easy_background_tasks.alarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.github.tumusx.easy_background_tasks.typeAlarms.AlarmsType

class EasyTaskAlarm {

    class EasyAlarmTaskBuilder {
        var typeAlarm: AlarmsType = AlarmsType.SET_EXACT
            private set

        var context: Context? = null
            private set

        fun registerContext(context: Context) = apply { this.context = context }

        fun registerAlarmType(alarmsType: AlarmsType) = apply { typeAlarm = alarmsType }

        fun registerAlarmManager(): AlarmManager =
            context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        private fun registerPendingIntentService(service: Service) = apply {
            PendingIntent.getService(
                context,
                0,
                Intent(context, service::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
        }

        fun registerBroadcastReceiverAlarm(broadcastReceiver: BroadcastReceiver) = apply {
            PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, broadcastReceiver::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
        }

        fun builder() = EasyTaskAlarm()

    }
}