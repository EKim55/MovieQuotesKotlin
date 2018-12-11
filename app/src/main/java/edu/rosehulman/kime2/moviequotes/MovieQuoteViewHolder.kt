package edu.rosehulman.kime2.moviequotes

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class MovieQuoteViewHolder(itemView: View, var adapter: MovieQuoteAdapter): RecyclerView.ViewHolder(itemView) {
    val quoteTextView = itemView.findViewById<TextView>(R.id.quote_text_view)
    val movieTextView = itemView.findViewById<TextView>(R.id.movie_text_view)

    init {
        itemView.setOnClickListener {
            adapter.showAddEditDialog(adapterPosition)
        }
    }

    fun bind(movieQuote: MovieQuote) {
        quoteTextView.text = movieQuote.quote
        movieTextView.text = movieQuote.movie
    }
}