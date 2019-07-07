package blur.tech.candidate.core.models

class Initiative(
    val _id: String,
    val rating: Int,
    val creator: String,
    val title: String,
    val describe: String,
    val geo: Geo
)