package site.saba123.model

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import kotlin.arrayOf as arrayOf

object Statuses: Table() {
    val xuid = varchar("xuid", 20)
    val name = varchar("name", 20)
    val money = integer("money").default(0)
    val rankId = integer("rank").default(0)
    val playTime = integer("play_time").default(0)

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

class Rank(val rankId: Int) {
    init {
        require(rankId in 0..5)
    }

    companion object {
        val textList = arrayOf("観光",
            "住民",
            "信任",
            "管理",
            "パイ",
            "主")
    }
    fun toText(): String {
        return textList[rankId]
    }
}