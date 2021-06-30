package paul.barthuel.testble

import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import paul.barthuel.testble.databinding.ViewHolderBinding

class Adapter(private val listener: (ScanResult) -> Unit) : ListAdapter<ScanResult, Adapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
    
    class ViewHolder(private val binding: ViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(scanResult: ScanResult, listener: (ScanResult) -> Unit) {
            
            binding.viewHolderTextView.text = scanResult.device.address
            
            itemView.setOnClickListener { listener(scanResult) }
        }
    }
    
    private class DiffCallback : DiffUtil.ItemCallback<ScanResult>() {
        override fun areItemsTheSame(oldItem: ScanResult, newItem: ScanResult): Boolean {
            return oldItem.device.name == newItem.device.name
        }
        
        override fun areContentsTheSame(oldItem: ScanResult, newItem: ScanResult): Boolean {
            return oldItem == newItem
        }
    }
}