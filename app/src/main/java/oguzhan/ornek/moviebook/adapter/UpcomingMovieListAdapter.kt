package oguzhan.ornek.moviebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import oguzhan.ornek.moviebook.databinding.MovieListRecyclerItemBinding
import oguzhan.ornek.moviebook.model.MovieDetail
import oguzhan.ornek.moviebook.model.Upcoming
import oguzhan.ornek.moviebook.view.HomeFragmentDirections

class UpcomingMovieListAdapter :
    RecyclerView.Adapter<UpcomingMovieListAdapter.UpcomingMovieViewHolder>() {
    private val movieList: MutableList<Upcoming> = mutableListOf()
    var movieClickListener : (Int)-> Unit = {

    }
    fun setUpcomingMovies(list: MutableList<Upcoming>) {
        movieList.clear()
        movieList.addAll(list)
    }

    class UpcomingMovieViewHolder(val view: MovieListRecyclerItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(itemUp: Upcoming,movieClickListener : (Int)-> Unit) {
            view.apply {
                item = itemUp
            }
            itemView.setOnClickListener {
                movieClickListener.invoke(itemUp.id)

            }
        }
    }
    


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieListRecyclerItemBinding.inflate(inflater, parent, false)
        return UpcomingMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingMovieViewHolder, position: Int) {
        holder.bind(movieList[position],movieClickListener)

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}