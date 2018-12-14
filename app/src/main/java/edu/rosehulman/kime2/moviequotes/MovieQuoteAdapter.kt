package edu.rosehulman.kime2.moviequotes

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.add_dialog.view.*
import kotlinx.android.synthetic.main.row_view.view.*

class MovieQuoteAdapter(var context: Context): RecyclerView.Adapter<MovieQuoteViewHolder>() {
    val movieQuotes = ArrayList<MovieQuote>()

    override fun getItemCount() = movieQuotes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieQuoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_view, parent, false)
        return MovieQuoteViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: MovieQuoteViewHolder, position: Int) {
        holder.bind(movieQuotes[position])
    }

    fun add(movieQuote: MovieQuote) {
        movieQuotes.add(movieQuote)
    }

    fun edit(position: Int, quote: String, movie: String) {
        var movieQuote = movieQuotes[position]
        movieQuote.quote = quote
        movieQuote.movie = movie
        notifyItemChanged(position)
    }

    fun showAddEditDialog(position: Int = -1) {
        var builder = AlertDialog.Builder(context)
        // Configure it. Title. Buttons (pos/neg/neutral). Icon. Message or Custom View or List.
        builder.setTitle(R.string.add_dialog_title)
        val view = LayoutInflater.from(context).inflate(R.layout.add_dialog, null, false)
        builder.setView(view)
        if (position >= 0) {
            // Edit
            var movieQuote = movieQuotes[position]
            view.add_dialog_quote_edit_text.setText(movieQuote.quote)
            view.add_dialog_movie_edit_text.setText(movieQuote.movie)
        }

        builder.setPositiveButton(android.R.string.ok, { _, _ ->
            val quote = view.add_dialog_quote_edit_text.text.toString()
            val movie = view.add_dialog_movie_edit_text.text.toString()
            if (position >= 0) {
                edit(position, quote, movie)
            } else {
                add(MovieQuote(quote, movie))
            }
        })
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.create().show()
    }

    fun selectMovieQuote(position: Int) {
        movieQuotes[position].showDark = !movieQuotes[position].showDark
        notifyItemChanged(position)
    }
}