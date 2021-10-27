package site.saba123.usecase

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import site.saba123.model.Jobs
import site.saba123.model.Permissions
import site.saba123.model.Statuses
import java.sql.Connection

class DataBaseUseCase {
    companion object {
        fun init(path: String) {
            Database.connect("jdbc:sqlite:" + path + "123saba.db", "org.sqlite.JDBC")
            TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

            transaction {
                create(Statuses)
                create(Jobs)
                create(Permissions)
            }
        }
    }
}