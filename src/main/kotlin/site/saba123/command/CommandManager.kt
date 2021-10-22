package site.saba123.command

import site.saba123.Main
import site.saba123.command.commands.StatusCommand

class CommandManager {
    companion object {
        fun registerAll(plugin: Main) {
            // 無効化
            val disableCommands: String = plugin.config.getString("Command.Disable")
            if (disableCommands.isNotEmpty()) {
                val commands = disableCommands.split(",").dropLastWhile { it.isEmpty() }.toTypedArray()
                if (commands.isNotEmpty()) {
                    for (target in commands) {
                        plugin.server.commandMap.commands.remove(target)
                    }
                }
            }

            //登録
            val map = plugin.server.commandMap
            map.register("status", StatusCommand())
        }
    }
}