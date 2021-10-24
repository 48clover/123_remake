package site.saba123.model

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import kotlin.arrayOf as arrayOf

object Statuses: Table() {
    val xuid = varchar("xuid", 20)
    val name = varchar("name", 20)
    val money = integer("money").default(0)
    val rank = integer("rank").default(0)
    val playMinute = integer("play_time").default(0)

    override val primaryKey = PrimaryKey(xuid)
}

data class Status(
    val xuid: String,
    val name: String,
    val money: Money,
    val rank: Rank,
    val playMinute: PlayMinute
)

class StatusDTO {
    companion object {
        fun decode(result: ResultRow) = Status(
            result[Statuses.xuid],
            result[Statuses.name],
            Money(result[Statuses.money]),
            Rank(result[Statuses.rank]),
            PlayMinute(result[Statuses.playMinute])
        )
    }
}

class Rank(val rank: Int) {
    init {
        require(rank in 0..5)
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
        return textList[rank]
    }
}

class Money(var money: Int) {
    init {
        require(0 <= money)
    }

    fun add(amount: Int) {
        if(amount < 0) return
        money += amount
    }

    fun sub(amount: Int) {
        if(amount > money || amount < 0) return
        money -= amount
    }
}

class PlayMinute(var playMinute: Int) {
    init {
        require(0 <= playMinute)
    }

    fun toDate(): Map<String, Int> {
        val hour = playMinute / 60
        val minute = playMinute % 60
        return mapOf("hour" to hour, "minute" to minute)
    }
}