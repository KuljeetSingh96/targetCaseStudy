package com.target.targetcasestudy.databinding

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.StrikethroughSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        @BindingAdapter("productAvatarUrl")
        @JvmStatic
        fun setProductAvatarUrl(imageView: ImageView, productAvatarUrl: String) {
            val context = imageView.context
            if (!TextUtils.isEmpty(productAvatarUrl)) {
                Picasso.with(context)
                    .load(productAvatarUrl)
                    .centerCrop()
                    .fit()
                    .into(imageView)
            }
        }

        @BindingAdapter("regularPriceText")
        @JvmStatic
        fun setRegularPriceText(textView: TextView, regularPriceText: String) {
            val content = "Reg: $regularPriceText"
            val spannableString = SpannableString(content)
            spannableString.setSpan(
                StrikethroughSpan(),
                5,
                content.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.text = spannableString
        }
    }
}
