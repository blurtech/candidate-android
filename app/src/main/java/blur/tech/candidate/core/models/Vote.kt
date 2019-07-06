package blur.tech.candidate.core.models

class Vote (
    val id: String,
    val vote: String
){
    enum class voteTypes{Like, Dislike, Superlike}
}