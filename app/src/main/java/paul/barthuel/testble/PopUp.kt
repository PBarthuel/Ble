package paul.barthuel.testble

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import paul.barthuel.testble.databinding.PopupBinding

class PopUp(
    context: Context,
    private val title: String,
    private val content: String?,
    private val validationButtonTitle: String,
    private val completion: (() -> Unit)? = null
) : AlertDialog(context, R.style.InformationPopupDialogTheme) {
    
    private var _binding: PopupBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = PopupBinding.inflate(layoutInflater).apply { setContentView(root) }
        setupUI()
    }
    
    private fun setupUI() {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        with(binding) {
            titleTextView.text = title
            
            subtitleTextView.text = content
            
            validationButton.text = validationButtonTitle
            
            validationButton.setOnClickListener {
                completion?.invoke()
                dismiss()
            }
        }
    }
}