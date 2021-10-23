package site.saba123.model

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Jobs: Table("jobs") {
    val xuid = varchar("xuid", 20)
    val beginnerExp = long("beginner_exp").default(0)
    val fighterExp = long("fighter_exp").default(0)
    val therapistExp = long("therapist_exp").default(0)
    val builderExp = long("builder_exp").default(0)
    val organizerExp = long("organizer_exp").default(0)

    override val primaryKey = PrimaryKey(xuid)
}

data class Job(
    val xuid: String,
    val beginnerExp: Long,
    val fighterExp: Long,
    val therapistExp: Long,
    val builderExp: Long,
    val organizerExp: Long
)

class JobDTO {
    companion object {
        fun decode(result: ResultRow) = Job(
            result[Jobs.xuid],
            result[Jobs.beginnerExp],
            result[Jobs.fighterExp],
            result[Jobs.therapistExp],
            result[Jobs.builderExp],
            result[Jobs.organizerExp]
        )
    }
}

class Exp(val exp: Long) {
    init {
        require(0 <= exp)
    }
}