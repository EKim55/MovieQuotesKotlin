package edu.rosehulman.kime2.moviequotes

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog.*
import kotlinx.android.synthetic.main.add_dialog.view.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MovieQuoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        adapter = MovieQuoteAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter

        fab.setOnClickListener { view ->
            Log.d(Constants.TAG, "Pressed the floating action button.")
//            updateQuote(MovieQuote("I am your father", "The Empire Strikes Back"))
            showAddDialog()
        }
    }

    private fun updateQuote(movieQuote: MovieQuote) {
//        quote_text_view.text = movieQuote.quote
//        movie_text_view.text = movieQuote.movie
    }

    private fun showAddDialog() {
        var builder = AlertDialog.Builder(this)
        // Configure it. Title. Buttons (pos/neg/neutral). Icon. Message or Custom View or List.
        builder.setTitle(R.string.add_dialog_title)
        val view = LayoutInflater.from(this).inflate(R.layout.add_dialog, null, false)
        builder.setView(view)
        builder.setPositiveButton(android.R.string.ok, { _, _ ->
            val quote = view.add_dialog_quote_edit_text.text.toString()
            val movie = view.add_dialog_movie_edit_text.text.toString()
            updateQuote(MovieQuote(quote, movie))
        })
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                //startActivity(Intent(Settings.ACTION_SETTINGS))
                getWhichSettings()
                true
            }
            R.id.action_increase_font_size -> {
                changeFontSize(4)
                true
            }
            R.id.action_clear_quote -> {
                clearQuote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getWhichSettings() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.which_settings_title))
        builder.setItems(R.array.settings_options, { _, position ->
            val settingsType = when (position) {
                0 -> Settings.ACTION_SOUND_SETTINGS
                1 -> Settings.ACTION_SEARCH_SETTINGS
                else -> Settings.ACTION_SETTINGS
            }
            startActivity(Intent(settingsType))
        })
        builder.create().show()
    }

    private fun changeFontSize(delta: Int) {
//        var currentSize = quote_text_view.textSize / resources.displayMetrics.scaledDensity
//        currentSize += delta
//        quote_text_view.textSize = currentSize
//        movie_text_view.textSize = currentSize
    }

    private fun clearQuote() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.clear_quote)
        builder.setPositiveButton(android.R.string.ok, { _, _ ->
            updateQuote(MovieQuote(getString(R.string.quote), getString(R.string.movie)))
        })
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.create().show()
    }
}
