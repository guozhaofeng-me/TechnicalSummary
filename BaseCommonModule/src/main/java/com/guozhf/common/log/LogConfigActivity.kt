package com.guozhf.common.log

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.basecommonmodule.R
import com.example.basecommonmodule.databinding.ActivityLogConfigBinding
import log.ZLog

class LogConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogConfigBinding

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, LogConfigActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_log_config
        )
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.switchLogSwitch.apply {
            isChecked = ZLog.isDebuggable()
            setOnCheckedChangeListener { _, isChecked ->
                ZLog.setDebuggable(isChecked)
            }
        }

        binding.switchSaveToFile.apply {
            isChecked = ZLog.isSaveToFile()
            setOnCheckedChangeListener { _, isChecked ->
                ZLog.setSaveToFile(isChecked)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}