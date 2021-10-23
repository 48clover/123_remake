package site.saba123.model

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import kotlin.arrayOf as arrayOf

object Statuses: Table() {
    val xuid = varchar("xuid", 20)
    val name = varchar("name", 20)
    val money = integer("money").default(0)
    val rank = integer("rank").default(0)
    val playTime = integer("play_time").default(0)

    override val primaryKey = PrimaryKey(xuid)
}

data class Status(
    val xuid: String,
    val name: String,
    val money: Int,
    val rank: Int,
    val playTime: Int
)

class StatusDTO {
    companion object {
        fun decode(result: ResultRow) = Status(
            result[Statuses.xuid],
            result[Statuses.name],
            result[Statuses.money],
            result[Statuses.rank],
            result[Statuses.playTime]
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

    fun add(amount: Int): Boolean {
        if(amount < 0) return false
        money += amount
        return true
    }

    fun sub(amount: Int): Boolean {
        if(amount > money || amount < 0) return false
        money -= amount
        return true
    }
}