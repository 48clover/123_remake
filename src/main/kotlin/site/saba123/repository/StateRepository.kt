package site.saba123.repository

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import site.saba123.model.State
import site.saba123.model.StateDTO
import site.saba123.model.Status

class StateRepository {
    companion object {
        fun add(_xuid: String, _name: String) = transaction {
            Status.insert {
                it[name] = _name
                it[xuid] = _xuid
            }
        }

        fun find(_xuid: String): State? = Status.select{ Status.xuid.eq(_xuid) }.firstOrNull()?.let {
            StateDTO.decode(it)
        }
    }
}