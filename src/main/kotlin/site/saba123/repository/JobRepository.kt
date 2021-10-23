package site.saba123.repository

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import site.saba123.model.Job
import site.saba123.model.JobDTO
import site.saba123.model.Jobs

class JobRepository {
    companion object {
        fun add(_xuid: String) = transaction {
            Jobs.insert {
                it[xuid] = _xuid
            }
        }

        fun find(_xuid: String): Job? = Jobs.select{Jobs.xuid eq _xuid}.firstOrNull()?.let {
            JobDTO.decode(it)
        }
    }
}