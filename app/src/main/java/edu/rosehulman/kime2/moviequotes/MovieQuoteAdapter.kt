package edu.rosehulman.kime2.moviequotes

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class MovieQuoteAdapter(var context: Context): RecyclerView.Adapter<MovieQuoteViewHolder>() {
    val movieQuotes = ArrayList<MovieQuote>()

    override fun getItemCount() = movieQuotes.size

    override fun onBindViewHolder(holder: MovieQuoteViewHolder, position: Int) {
        holder.bind(movieQuotes[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieQuoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_view, parent, false)
        return MovieQuoteViewHolder(view)
    }

    fun add(movieQuote: MovieQuote) {
        movieQuotes.add(movieQuote)
    }
}