package site.saba123.command

import site.saba123.Main
import site.saba123.command.commands.StatusCommand

class CommandManager {
    companion object {
        fun registerAll(plugin: Main) {
            // デフォルトコマンドの無効化
            val disableCommands: String = plugin.config.getString("Command.Disable")
            if (disableCommands.isNotEmpty()) {
                val commands = disableCommands.split("\n")
                for (target in commands) {
                    plugin.server.commandMap.commands.remove(target)
                    if (commands.isEmpty()) return
                }
            }

            // 登録
            val map = plugin.server.commandMap
            map.register("status", StatusCommand())
            // map.register("warp", WarpCommand(plugin))
        }

        fun hasPermission(command: String, rankId: Int): Boolean {
            val config = Main().config
            val permission = config.get("Command.Alias.$command").toString().toInt()
            return rankId >= permission
        }
    }
}