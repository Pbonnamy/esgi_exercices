package com.esgi.android.exercices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO Changer cette ligne en fonction des layouts
        setContentView(R.layout.list_activity)

        val bookings = mutableListOf<Booking>();

        val booking = Booking(
            "John Doe",
            "1 rue de la paix",
            BookingStatus.ON_PROCESS,
            Date(),
            Date()
        )

        for (i in 0..5) {
            bookings.add(booking)
        }

        findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = LinearLayoutManager(this@MainActivity,)
            adapter = ListAdapter(bookings)
        }
    }

    class ListAdapter(private val bookings: List<Booking>) : RecyclerView.Adapter<BookingViewHolder>() {

        override fun getItemCount(): Int = bookings.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
            return BookingViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item, parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
            holder.updateBooking(
                bookings[position]
            )
        }
    }

    class BookingViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val titleTextView = v.findViewById<TextView>(R.id.title)
        private val subtitleTextView = v.findViewById<TextView>(R.id.subtitle)

        fun updateBooking(booking: Booking) {
            titleTextView.text = booking.name
            subtitleTextView.text = booking.streetAddress
        }
    }
}