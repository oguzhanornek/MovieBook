package oguzhan.ornek.moviebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import oguzhan.ornek.moviebook.databinding.SimilarItemBinding
import oguzhan.ornek.moviebook.model.SimilarMovie

class SimilarMovieListAdapter :
    RecyclerView.Adapter<SimilarMovieListAdapter.SimilarMovieViewHolder>() {
    private val similarMovieList: MutableList<SimilarMovie> = mutableListOf()

    fun setSimilarMovie(list: MutableList<SimilarMovie>) {
        similarMovieList.clear()
        similarMovieList.addAll(list)
    }

    class SimilarMovieViewHolder(val view: SimilarItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(itemSimilar: SimilarMovie) {
            view.apply {
                item = itemSimilar
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SimilarItemBinding.inflate(inflater, parent, false)
        return SimilarMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        holder.bind(similarMovieList[position])
    }

    override fun getItemCount(): Int {
        return similarMovieList.size
    }
}