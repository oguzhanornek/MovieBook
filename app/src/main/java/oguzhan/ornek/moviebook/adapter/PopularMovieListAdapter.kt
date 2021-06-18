package oguzhan.ornek.moviebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import oguzhan.ornek.moviebook.databinding.PopularItemBinding
import oguzhan.ornek.moviebook.model.Popular
import oguzhan.ornek.moviebook.model.Upcoming


class PopularMovieListAdapter :
    RecyclerView.Adapter<PopularMovieListAdapter.PopularMovieViewHolder>() {
    private val popularNovieList: MutableList<Popular> = mutableListOf()
    fun setPopularMovie(list: MutableList<Popular>) {
        popularNovieList.clear()
        popularNovieList.addAll(list)
    }

    class PopularMovieViewHolder(val view: PopularItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(itemPop: Popular) {
            view.apply {
                item = itemPop
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PopularItemBinding.inflate(inflater, parent, false)
        return PopularMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.bind(popularNovieList[position])
    }

    override fun getItemCount(): Int {
        return popularNovieList.size
    }
}