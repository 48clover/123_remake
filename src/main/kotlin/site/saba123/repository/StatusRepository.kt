package site.saba123.repository

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import site.saba123.model.Status
import site.saba123.model.StatusDTO
import site.saba123.model.Statuses

class StatusRepository {
    companion object {
        fun add(_xuid: String, _name: String) = transaction {
            Statuses.insert {
                it[xuid] = _xuid
                it[name] = _name
            }
        }

        fun find(_xuid: String): Status? = Statuses.select { Statuses.xuid eq _xuid }.firstOrNull()?.let {
            StatusDTO.decode(it)
        }

        fun update(status: Status) = transaction {
            Statuses.update({ Statuses.xuid eq status.xuid }) {
                it[name] = status.name.text
                it[money] = status.money.amount
                it[rank] = status.rank.rankId
                it[playMinute] = status.playMinute.amount
            }
        }
    }
}