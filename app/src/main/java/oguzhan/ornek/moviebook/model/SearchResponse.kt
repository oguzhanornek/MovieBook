package oguzhan.ornek.moviebook.model

data class SearchResponse(
    val page: Int,
    val results: List<SearchMovieData>,
    val total_results: Int,
    val total_pages: Int
)
