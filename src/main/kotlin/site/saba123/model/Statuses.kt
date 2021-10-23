package site.saba123.model

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Statuses: Table() {
    val xuid = varchar("xuid", 20)
    val name = varchar("name", 20)
    val money = integer("money")
    val rankId = integer("rank")
    val playTime = integer("play_time")

    override val primaryKey = PrimaryKey(xuid)
}

data class Status(
    val xuid: String,
    val name: String,
    val money: Int,
    val rankId: Int,
    val playTime: Int
)

class StatusDTO {
    companion object {
        fun decode(result: ResultRow) = Status(
            result[Statuses.xuid],
            result[Statuses.name],
            result[Statuses.money],
            result[Statuses.rankId],
            result[Statuses.playTime]
        )
    }
}
