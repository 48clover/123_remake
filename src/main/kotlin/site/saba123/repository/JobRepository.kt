package site.saba123.repository

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import site.saba123.model.Job
import site.saba123.model.JobDTO
import site.saba123.model.Jobs

class JobRepository {
    companion object {
        fun add(_xuid: String, _name: String) = transaction {
            Jobs.insert {
                it[xuid] = _xuid
                it[name] = _name
            }
        }

        fun find(_xuid: String): Job? = Jobs.select { Jobs.xuid eq _xuid }.firstOrNull()?.let {
            JobDTO.decode(it)
        }

        fun update(job: Job) = transaction {
            Jobs.update({ Jobs.xuid eq job.xuid }) {
                it[name] = job.name.text
                it[currentJob] = job.currentJob.jobId
                it[beginnerExp] = job.beginnerExp.amount
                it[fighterExp] = job.fighterExp.amount
                it[therapistExp] = job.therapistExp.amount
                it[builderExp] = job.builderExp.amount
                it[organizerExp] = job.organizerExp.amount
            }
        }
    }
}