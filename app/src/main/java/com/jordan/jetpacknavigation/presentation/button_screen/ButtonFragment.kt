package com.jordan.jetpacknavigation.presentation.button_screen

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.jordan.jetpacknavigation.MainActivity
import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.databinding.FragmentButtonBinding
import com.jordan.jetpacknavigation.domain.model.UserItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ButtonFragment : Fragment(R.layout.fragment_button) {

    private var _binding: FragmentButtonBinding? = null
    private val binding: FragmentButtonBinding
        get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentButtonBinding.inflate(inflater, container, false)

        // notification
        createNotificationChannel()

        val intent = Intent(requireContext(), MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(requireContext()).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setContentTitle("Awesome notification")
            .setContentText("This is content text")
            .setSmallIcon(R.drawable.ic_home)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = NotificationManagerCompat.from(requireContext())
        // ------------------------------------------------------------------------

        // dialog
        val addContactDialog = AlertDialog.Builder(requireContext())
            .setTitle("Add contact")
            .setMessage("Do you want to add Mr. Jordan to your contacts list")
            .setIcon(R.drawable.ic_home)
            .setPositiveButton("Yes") { _, _ ->
                showToast("You added Mr. Jordan to your contact list")
            }
            .setNegativeButton("No") { _, _ ->
                showToast("You didn't add Mr. Jordan to your contact list")
            }
            .create()

        val options = arrayOf("First Item", "Second Item", "Third Item")
        val singleChoiceDialog = AlertDialog.Builder(requireContext())
            .setTitle("Choose one of this options")
            .setSingleChoiceItems(options, 0) { _, i ->
                showToast(options[i])
            }
            .setPositiveButton("Accept") { _, _ ->
                showToast("You accepted the SingleChoiceDialog")
            }
            .setNegativeButton("Decline") { _, _ ->
                showToast("You declined the SingleChoiceDialog")
            }
            .create()

        val multiChoiceDialog = AlertDialog.Builder(requireContext())
            .setTitle("Choose one of this options")
            .setMultiChoiceItems(options, booleanArrayOf(false, false, false)) { _, i, isChecked ->
                if (isChecked) {
                    showToast("You checked ${options[i]}")
                } else {
                    showToast("You unchecked ${options[i]}")
                }
            }
            .setPositiveButton("Accept") { _, _ ->
                showToast("You accepted the MultiChoiceDialog")
            }
            .setNegativeButton("Decline") { _, _ ->
                showToast("You declined the MultiChoiceDialog")
            }
            .create()
        // ------------------------------------------------------------------------

        // data binding
        val myUser = UserItem("John", "Doe", 25, true)
        binding.user = myUser

        binding.outlinedButton.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID, notification)
        }

        binding.toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnAndroid -> addContactDialog.show()
                    R.id.btnFlutter -> singleChoiceDialog.show()
                    R.id.btnWeb -> multiChoiceDialog.show()
                }
            } else {
                if (toggleButtonGroup.checkedButtonId == View.NO_ID) {
                    showToast("Nothing Selected")
                }
            }
        }

        return binding.root
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun showToast(str: String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val CHANNEL_ID = "channel_id"
        const val CHANNEL_NAME = "channel_name"
        const val NOTIFICATION_ID = 0
    }
}