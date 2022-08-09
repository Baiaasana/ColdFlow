package com.example.adtajstumag

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

class ADTAJSTUMAG {

    fun adtajstumag(url: String, image: ImageView){

        Glide
            .with(image.context)
            .load(url)
            .placeholder(R.mipmap.place_holder)
            .centerCrop()
            .into(image)

    }

}