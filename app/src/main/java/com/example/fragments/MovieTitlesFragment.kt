package com.example.fragments

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import java.io.IOException

class MovieTitlesFragment() : ListFragment() {
    private var titles: Array<String> = arrayOf()
    private var images: TypedArray? = null
    private var descriptions: Array<String> = arrayOf()
    private lateinit var selected_title: String
    private var mListener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            titles = resources.getStringArray(R.array.movie_titles)
            images = resources.obtainTypedArray(R.array.images)
            descriptions = resources.getStringArray(R.array.movie_descriptions)
            listAdapter = activity?.let {
                ArrayAdapter(it, android.R.layout.simple_list_item_1, android.R.id.text1, titles)
            }
        }catch (e: IOException) {
            e.printStackTrace()
            Log.d("ERROR", "Could not fetch some of the resources requested.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.movies_titles_fragment, container, false)
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(image: Drawable?, description: String?, title: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            activity as OnFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$activity must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)

        val selected_title = titles?.get(position)
        Log.d("TITLE", selected_title)
        val selected_image = images?.getDrawable(position) as Drawable
        Log.d("IMAGE", selected_image.toString())
        val selected_description = descriptions?.get(position)
        Log.d("DESCRIPTION", selected_description)
        mListener?.onFragmentInteraction(selected_image, selected_description, selected_title)
    }
}