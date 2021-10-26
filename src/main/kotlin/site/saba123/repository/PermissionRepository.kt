package site.saba123.repository

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import site.saba123.model.Permission
import site.saba123.model.PermissionDTO
import site.saba123.model.Permissions

class PermissionRepository {
    companion object {
        fun add(_xuid: String, _name: String) = transaction {
            Permissions.insert {
                it[xuid] = _xuid
                it[name] = _name
            }
        }

        fun find(_xuid: String): Permission? = Permissions.select { Permissions.xuid eq _xuid }.firstOrNull()?.let {
            PermissionDTO.decode(it)
        }

        fun update(permission: Permission) = transaction {
            Permissions.update({ Permissions.xuid eq permission.xuid }) {
                it[name] = permission.name.text
                it[enabledCommand] = permission.enabledCommand.joinToString(",")
                it[disabledCommand] = permission.disabledCommand.joinToString(",")
            }
        }
    }
}