package oguzhan.ornek.moviebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import oguzhan.ornek.moviebook.databinding.MovieListRecyclerItemBinding
import oguzhan.ornek.moviebook.model.Upcoming

class UpcomingMovieListAdapter() : RecyclerView.Adapter<UpcomingMovieListAdapter.UpcomingMovieViewHolder>() {
    private val movieList : MutableList<Upcoming> = mutableListOf()
    fun setUpcomingMovies(list: MutableList<Upcoming>) {
        movieList.clear()
        movieList.addAll(list)
    }

    class UpcomingMovieViewHolder (val view : MovieListRecyclerItemBinding) : RecyclerView.ViewHolder(view.root){
        fun bind ( itemUp : Upcoming){
            view.apply {
                item = itemUp
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieListRecyclerItemBinding.inflate(inflater,parent,false)
        return UpcomingMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingMovieViewHolder, position: Int) {
       holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
       return movieList.size
    }

}