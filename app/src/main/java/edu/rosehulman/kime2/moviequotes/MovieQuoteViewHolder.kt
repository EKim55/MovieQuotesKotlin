package edu.rosehulman.kime2.moviequotes

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class MovieQuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val quoteTextView = itemView.findViewById<TextView>(R.id.quote_text_view)
    val movieTextView = itemView.findViewById<TextView>(R.id.movie_text_view)

    fun bind(movieQuote: MovieQuote) {
        quoteTextView.text = movieQuote.quote
        movieTextView.text = movieQuote.movie
    }
}