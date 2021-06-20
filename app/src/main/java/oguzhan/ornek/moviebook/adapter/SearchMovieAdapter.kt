package oguzhan.ornek.moviebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import oguzhan.ornek.moviebook.databinding.SearchedMovieItemBinding
import oguzhan.ornek.moviebook.model.SearchMovieData

class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder> (){
    private val searchedMovieList: MutableList<SearchMovieData> = mutableListOf()
    var movieClickListener : (Int)-> Unit = {}
    fun setSearchMovie(list: MutableList<SearchMovieData>) {
        searchedMovieList.clear()
        searchedMovieList.addAll(list)
    }
    class SearchMovieViewHolder(val view : SearchedMovieItemBinding ) : RecyclerView.ViewHolder(view.root){

        fun bind(itemSearch : SearchMovieData, movieClickListener : (Int)-> Unit){
            view.apply {
                item = itemSearch
            }
            itemView.setOnClickListener {
                movieClickListener.invoke(itemSearch.id)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val binding = SearchedMovieItemBinding.inflate(inflater,parent,false)
        return SearchMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
       holder.bind(searchedMovieList[position],movieClickListener)
    }

    override fun getItemCount(): Int {
       return searchedMovieList.size
    }
}