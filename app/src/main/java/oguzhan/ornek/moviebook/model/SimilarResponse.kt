package oguzhan.ornek.moviebook.model

data class SimilarResponse (
    val page : String,
    val results : List<SimilarMovie>,
    val total_pages : Int,
    val total_results : Int
    )
