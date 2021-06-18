package oguzhan.ornek.moviebook.model

data class PopularResponse(
    val page : Int,
    val results : List<Popular>,
    val total_results : Int,
    val total_pages : Int
)