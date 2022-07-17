package com.jordan.jetpacknavigation.presentation.home_screen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.jordan.jetpacknavigation.MainActivity
import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.databinding.FragmentHomeBinding
import com.jordan.jetpacknavigation.presentation.pager_screen.PagerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

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

        binding.calculateButton.setOnClickListener {

            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }

            val isChecked = binding.roundUpSwitch.isChecked

            mainViewModel.calculateTip(
                binding.costOfServiceEditText.text.toString(),
                tipPercentage,
                isChecked
            )

            notificationManager.notify(NOTIFICATION_ID, notification)
        }

        binding.toPagerActivityButton.setOnClickListener {
            val intent = Intent(context, PagerActivity::class.java)
            startActivity(intent)
        }

        subscribeToObservables()
        return binding.root
    }

    private fun subscribeToObservables() {
        mainViewModel.tipResult.observe(viewLifecycleOwner) {
            binding.tipResult.text = it
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CHANNEL_ID = "channel_id"
        const val CHANNEL_NAME = "channel_name"
        const val NOTIFICATION_ID = 0
    }
}