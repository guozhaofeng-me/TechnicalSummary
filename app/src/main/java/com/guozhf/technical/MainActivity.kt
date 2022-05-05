package com.guozhf.technical

import activity.ActivityUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.trainingground.TrainingGroundActivity
import com.guozhf.common.log.LogConfigActivity
import com.guozhf.technical.databinding.ActivityMainBinding
import log.ZLog
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.tvLogConfig.setOnClickListener {
            LogConfigActivity.start(this)
        }
        binding.tvTest.setOnClickListener {
            try {
                var name: String? = null
                print(name!!.length)
            } catch (ex: Exception) {
                ZLog.d(message = LOG_TEST, throwable = ex)
            }
//            ZLog.d(message = LOG_TEST)
        }
        binding.tvTrainingGround.setOnClickListener {
//            TrainingGroundActivity.start(this)
            ActivityUtils.startActivity(this, null, TrainingGroundActivity::class.java)
        }
    }
}