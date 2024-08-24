package io.github.thwisse.kekodappnavigation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController

class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var textViewTitle: TextView
    private var textViewDescription: TextView

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.view_custom, this, true)

        textViewTitle = findViewById(R.id.textViewTitle)
        textViewDescription = findViewById(R.id.textViewDescription)

        // Default değerleri ayarla
        textViewTitle.text = "Custom View Title"
        textViewDescription.text = "This is a custom description."

        // Eger attrs ile ozellestirilebilir yapmak isterseniz:
        attrs?.let {
//            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomView, 0, 0)
//            val title = typedArray.getString(R.styleable.CustomView_titleText)
//            val description = typedArray.getString(R.styleable.CustomView_descriptionText)

//            title?.let { textViewTitle.text = it }
//            description?.let { textViewDescription.text = it }

//            typedArray.recycle()
        }

        //TODO-5 findNavController() ile navController'a ulas.
        val navController = findNavController()
    }
}