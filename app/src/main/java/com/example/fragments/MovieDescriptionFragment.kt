package com.example.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class MovieDescriptionFragment() : Fragment() {

    private var clicked = 0

    private var descriptions: Array<String> = arrayOf()
    private var titles: Array<String> = arrayOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        return inflater.inflate(R.layout.movie_description_fragment, container, false)
    }

    fun changeContent(image: Drawable?, description: String?, title: String?) {
        Log.d("CLICKED", "A TITLE HAS BEEN CLICKED")
        /*
        if(clicked == 0) {
            Log.d("CLICKED", "NOTHING CLICKED YET")
            val info_text = requireView().findViewById<TextView>(R.id.clickInfoText)
            info_text.visibility = View.INVISIBLE
            if(info_text.visibility == View.INVISIBLE) {
                Log.d("INFO_TEXT", "INVISIBLEEEE")
            }else {
                Log.d("INFO_TEXT", "VISIBLEEEE")
            }
            clicked++


        }

         */
        /*
        Log.d("TITLE", title.toString())
        requireView().findViewById<TextView>(R.id.title).text = title

         */
        Log.d("IMAGE TO BE DISPLAYED", image.toString())
        val imageView = requireView().findViewById<ImageView>(R.id.image).setImageDrawable(image)


        Log.d("DESCRIPTION DISPLAYED", description.toString())
        val textView = requireView().findViewById<TextView>(R.id.description).setText(description.toString())


    }
}