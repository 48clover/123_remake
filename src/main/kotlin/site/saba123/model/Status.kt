package site.saba123.model

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Status: Table() {
    val xuid = varchar("xuid", 20)
    val name = varchar("name", 20)
}

data class State(
    val xuid: String,
    val name: String
)

class StateDTO {
    companion object {
        fun decode(result: ResultRow) = State(
            result[Status.xuid],
            result[Status.name]
        )
    }
}
