package com.example.fragments

import android.content.res.Configuration
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : FragmentActivity(), MovieTitlesFragment.OnFragmentInteractionListener {

    private lateinit var images: TypedArray
    private lateinit var descriptions: Array<String>
    private lateinit var titles: Array<String>

    private lateinit var previous_button: Button
    private lateinit var next_button: Button

    private lateinit var image: Drawable
    private lateinit var description: String
    private lateinit var title: String
    private lateinit var imageFragment: MovieDescriptionFragment

    private var clicked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setOrientation(resources.configuration)


        images = resources.obtainTypedArray(R.array.images)
        descriptions = resources.getStringArray(R.array.movie_descriptions)
        titles = resources.getStringArray(R.array.movie_titles)

        previous_button = findViewById<Button>(R.id.previous_button)
        next_button = findViewById<Button>(R.id.next_button)
        previous_button.isEnabled = false
        next_button.isEnabled = false

        initializeButton(previous_button)
        initializeButton(next_button)

        imageFragment = supportFragmentManager.findFragmentById(R.id.movie_description_fragment) as MovieDescriptionFragment


    }

    fun initializeButton(button: Button) {
        if(button == previous_button) {
            button.setOnClickListener() {
                var counter = 0
                for(d in descriptions) {
                    if(d == description) {
                        if(counter == 0) {
                            onFragmentInteraction(images?.getDrawable(descriptions.size - 1), descriptions[descriptions.size - 1], titles[descriptions.size - 1])
                        }else {
                            onFragmentInteraction(images?.getDrawable(counter - 1), descriptions[counter - 1], titles[counter - 1])
                        }
                        break
                    }
                    counter++
                }
            }
        }else if(button == next_button) {
            button.setOnClickListener() {
                var counter = 0
                for(d in descriptions) {
                    if(d == description) {
                        if(counter + 1 == descriptions.size) {
                            onFragmentInteraction(images?.getDrawable(0), descriptions[0], titles[0])
                        }else {
                            onFragmentInteraction(images?.getDrawable(counter + 1), descriptions[counter + 1], titles[counter + 1])
                        }
                        break
                    }
                    counter++
                }
            }
        }
    }

    override fun onFragmentInteraction(image: Drawable?, description: String?, title: String?) {
        if (clicked == 0) {
            previous_button.isEnabled = true
            next_button.isEnabled = true
            clicked++
        }

        if (image != null && description != null && title != null) {
            this.image = image
            this.description = description
            this.title = title
        }

        imageFragment.changeContent(this.image, this.description, this.title)
    }




}