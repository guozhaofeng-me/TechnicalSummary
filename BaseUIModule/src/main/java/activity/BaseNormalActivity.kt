package activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseNormalActivity<VB : ViewBinding> : AppCompatActivity() {
    private lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIntent(intent)
        binding = setupViewBinding()
        initView()
        initListener()
        initData()
    }

    open fun initData() {
    }

    open fun initListener() {

    }

    open fun initView() {

    }

    abstract fun setupViewBinding(): VB

    protected open fun initIntent(intent: Intent) {

    }
}