package covid.washer.covidwasher;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import covid.washer.covidwasher.R;

public class NotiReci extends BroadcastReceiver {
    private final String CHANNEL_ID = "Covid Notifications";
    private final int NOTIFICATION_ID = 001;
    public boolean noti = false;

    @Override
    public void onReceive (Context context, Intent intent) {

            createNotificationChannel(context);
            NotificationManager notiMan = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


            Intent repeatingIntent = new Intent(context, MainActivity.class);
            repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //MainActivity will show up

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Time to wash your hands!")
                .setContentText("It has been 2 hours since you last washed your hands")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true); //make the notification invisible when user swipes*/

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());


    }
    public void cancelNoti()
    {
        noti = false;
    }

    public void startNoti()
    {
        noti = true;
    }
    private void createNotificationChannel(Context context) {  //for devices w API version 26+ and Android 8+ hence the need for notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = "Covid Notifications";
            String description = "Notification for Covid Washer Timer";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(notificationChannel);



        }
    }

}
