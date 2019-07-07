package blur.tech.candidate.core.models

class OrgInitiative(
    val _id: String,
    val rating: Int,
    val creator: String,
    val title: String,
    val describe: String,
    val voting: ArrayList<Voting>,
    val geo: Geo
)