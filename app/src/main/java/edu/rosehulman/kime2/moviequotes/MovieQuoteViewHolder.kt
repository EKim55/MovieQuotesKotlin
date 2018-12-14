package edu.rosehulman.kime2.moviequotes

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.row_view.view.*

class MovieQuoteViewHolder(itemView: View, var adapter: MovieQuoteAdapter): RecyclerView.ViewHolder(itemView) {
    val quoteTextView = itemView.findViewById<TextView>(R.id.quote_text_view)
    val movieTextView = itemView.findViewById<TextView>(R.id.movie_text_view)
    val cardView = itemView.card_view

    init {
        itemView.setOnClickListener {
            adapter.showAddEditDialog(adapterPosition)
        }

        itemView.setOnLongClickListener {
            adapter.selectMovieQuote(adapterPosition)
            true
        }
    }

    fun bind(movieQuote: MovieQuote) {
        quoteTextView.text = movieQuote.quote
        movieTextView.text = movieQuote.movie

        if (movieQuote.showDark) {
            cardView.setCardBackgroundColor(ContextCompat.getColor(adapter.context, R.color.colorAccent))
        } else {
            cardView.setCardBackgroundColor(Color.WHITE)
        }
    }
}