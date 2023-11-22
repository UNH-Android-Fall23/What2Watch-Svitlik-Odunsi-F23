package com.example.what2watch_svitlik_odunsi_f23.ui.notifications
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

public class PushNotifications : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            val title = remoteMessage.notification?.title
            val body = remoteMessage.notification?.body

            // Call a method to display the notification
            showNotification(title, body)
        }
    }

    private fun showNotification(title: String?, body: String?) {
        // Implement your notification display logic here
        // This can be similar to the showNotification function in the previous response
    }
}
