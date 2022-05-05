package com.example.trainingground

import android.app.Activity
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingground.databinding.ActivityTrainingGroundBinding
import com.example.trainingground.databinding.ItemTrainingGroundBinding

class TrainingGroundActivity : AppCompatActivity() {
    private var binding: ActivityTrainingGroundBinding? = null
    private var list: List<String> = TRAINING_GROUND_ITEM

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, TrainingGroundActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_training_ground)
        setSupportActionBar(binding!!.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding!!.trainingRv.apply {
            adapter = TrainingAdapter(list)
            layoutManager = LinearLayoutManager(this@TrainingGroundActivity)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    //------------
    class TrainingAdapter(private val list: List<String>) :
        RecyclerView.Adapter<TrainingAdapter.VH>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ItemTrainingGroundBinding>(
                inflater,
                R.layout.item_training_ground,
                parent,
                false
            )
            return VH(binding)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount(): Int = list.size

        class VH(private val binding: ItemTrainingGroundBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(content: String) {
                binding.tvItem.text = content
            }
        }
    }
}