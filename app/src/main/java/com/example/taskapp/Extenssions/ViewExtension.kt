package com.example.taskapp.Extenssions

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.w3c.dom.Text

fun View.loadImage(url:String){
    Glide.with(this)
        .load(url)
        .circleCrop()
        .into(this as ImageView);
}
fun Fragment.showToast(msg:String){
    Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
}
fun EditText.showName(name:String){
    TextView.ALPHA.toString()
}