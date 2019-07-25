package com.jay.storymirror.ui.storyread

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jay.storymirror.R

class StoryReadActivity : AppCompatActivity() {

    private lateinit var storyReadViewModel: StoryReadViewModel
    private lateinit var buttonPrev: ImageView
    private lateinit var buttonNext: ImageView
    private lateinit var titleTV: TextView
    private lateinit var htmlText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_read)

        attachViewModel()
        initUi()
        observeData()
        listeners()
    }

    private fun listeners() {
        buttonPrev.setOnClickListener {
            storyReadViewModel.handlePreviousButtonClicked()
        }

        buttonNext.setOnClickListener {
            storyReadViewModel.handleNextButtonClicked()
        }
    }

    private fun attachViewModel() {
        storyReadViewModel = ViewModelProviders.of(this).get(StoryReadViewModel::class.java)
    }

    private fun observeData() {
        storyReadViewModel.title.observe(this, Observer {
            titleTV.text = it
        })

        storyReadViewModel.content.observe(this, Observer {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                htmlText.text = Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT)
            } else {
                htmlText.text = Html.fromHtml(it)
            }
        })

        storyReadViewModel.prevButtonVisibility.observe(this, Observer {
            if (it) buttonPrev.visibility = View.VISIBLE else buttonPrev.visibility = View.INVISIBLE
        })

        storyReadViewModel.nextButtonVisibility.observe(this, Observer {
            if (it) buttonNext.visibility = View.VISIBLE else buttonNext.visibility = View.INVISIBLE
        })

        storyReadViewModel.finishActivity.observe(this, Observer {
            if (it) finish()
        })
    }

    private fun initUi() {
        supportActionBar?.hide()
        buttonPrev = findViewById(R.id.button_prev)
        buttonNext = findViewById(R.id.button_next)
        titleTV = findViewById(R.id.text_view_title)
        htmlText = findViewById(R.id.text_view_chapter_html_text)
        val storyId = intent.getLongExtra("storyId", -1)
        storyReadViewModel.getStory(storyId)
    }
}
