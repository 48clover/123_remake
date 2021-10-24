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
    val beginnerExp: Exp,
    val fighterExp: Exp,
    val therapistExp: Exp,
    val builderExp: Exp,
    val organizerExp: Exp
)

class JobDTO {
    companion object {
        fun decode(result: ResultRow) = Job(
            result[Jobs.xuid],
            Exp(result[Jobs.beginnerExp]),
            Exp(result[Jobs.fighterExp]),
            Exp(result[Jobs.therapistExp]),
            Exp(result[Jobs.builderExp]),
            Exp(result[Jobs.organizerExp])
        )
    }
}

class Exp(var exp: Long) {
    init {
        require(0 <= exp)
    }

    fun add(amount: Int) {
        if(amount < 0) return
        exp += amount
        return
    }

    fun sub(amount: Int) {
        if (amount > exp || amount < 0) return
        exp -= amount
        return
    }

    fun getLevel(): Int {
        val threshold = 50
        for(i in 1..100) {
            if(exp >= (i - 1) * threshold) continue
            return i
        }
        return 100
    }
}