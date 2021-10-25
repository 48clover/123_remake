package site.saba123.model

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

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
    val name: Name,
    val money: Money,
    val rank: Rank,
    val playMinute: PlayMinute
)

class StatusDTO {
    companion object {
        fun decode(result: ResultRow) = Status(
            result[Statuses.xuid],
            Name(result[Statuses.name]),
            Money(result[Statuses.money]),
            Rank(result[Statuses.rank]),
            PlayMinute(result[Statuses.playMinute])
        )
    }
}

class Rank(var rankId: Int) {
    init {
        require(rankId in 0..5)
    }

    companion object {
        val textArray = arrayOf(
            "観光",
            "住民",
            "信任",
            "管理",
            "パイ",
            "主"
        )
    }

    fun toText(): String {
        return textArray[rankId]
    }

    fun update(value: Int) {
        if (value !in 0..5) return
        rankId = value
    }
}

class Money(var amount: Int) {
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
}

class PlayMinute(var amount: Int) {
    init {
        require(0 <= amount)
    }

    fun toDate(): Map<String, Int> {
        val hour = amount / 60
        val minute = amount % 60
        return mapOf("hour" to hour, "minute" to minute)
    }
}