package site.saba123.model

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Jobs: Table("jobs") {
    val xuid = varchar("xuid", 20)
    val name = varchar("name", 20)
    val currentJob = integer("current_job").default(0)
    val beginnerExp = long("beginner_exp").default(0L)
    val fighterExp = long("fighter_exp").default(0L)
    val therapistExp = long("therapist_exp").default(0L)
    val builderExp = long("builder_exp").default(0L)
    val organizerExp = long("organizer_exp").default(0L)

    override val primaryKey = PrimaryKey(xuid)
}

data class Job(
    val xuid: String,
    val name: Name,
    val currentJob: CurrentJob,
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
            Name(result[Jobs.name]),
            CurrentJob(result[Jobs.currentJob]),
            Exp(result[Jobs.beginnerExp]),
            Exp(result[Jobs.fighterExp]),
            Exp(result[Jobs.therapistExp]),
            Exp(result[Jobs.builderExp]),
            Exp(result[Jobs.organizerExp])
        )
    }
}

class CurrentJob(var jobId: Int) {
    init {
        require(jobId in 0..4)
    }

    companion object {
        val textArray = arrayOf(
            "§f初心者",
            "§c闘技士",
            "§b癒し",
            "§a建築士",
            "§d開催者"
        )
    }

    fun toText(): String {
        return textArray[jobId]
    }

    fun update(value: Int) {
        if (value !in 0..4) return
        jobId = value
    }
}

class Exp(var amount: Long) {
    init {
        require(0 <= amount)
    }

    fun add(value: Int) {
        if (value < 0) return
        amount += value
    }

    fun sub(value: Int) {
        if (value > amount || value < 0) return
        amount -= value
    }

    fun getLevel(): Int {
        val threshold = 50
        for (i in 1..100) {
            if (amount >= (i - 1) * threshold) continue
            return i
        }
        return 100
    }
}