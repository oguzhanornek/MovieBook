package oguzhan.ornek.moviebook.model

data class Response(
    val dates: Dates,
    val page: Int,
    val results: List<Upcoming>,
    val total_pages: Int,
    val total_results: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)

