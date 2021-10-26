package site.saba123.model

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Permissions : Table() {
    val xuid = varchar("xuid", 20)
    val name = varchar("xuid", 20)
    val enabledCommand = varchar("enabled_command", 255).default("")
    val disabledCommand = varchar("disabled_command", 255).default("")
    val canEditLevel = varchar("disabled_command", 255).default("")

    override val primaryKey = PrimaryKey(xuid)
}

data class Permission(
    val xuid: String,
    val name: Name,
    val enabledCommand: MutableList<String>,
    val disabledCommand: MutableList<String>,
    val canEditLevel: MutableList<String>
)

class PermissionDTO {
    companion object {
        fun decode(result: ResultRow) = Permission(
            result[Permissions.xuid],
            Name(result[Permissions.name]),
            result[Permissions.enabledCommand].split(",").toMutableList(),
            result[Permissions.disabledCommand].split(",").toMutableList(),
            result[Permissions.canEditLevel].split(",").toMutableList()
        )
    }
}