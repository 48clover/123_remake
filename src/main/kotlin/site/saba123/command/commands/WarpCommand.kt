package site.saba123.command.commands

import cn.nukkit.Player
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.lang.TranslationContainer
import cn.nukkit.utils.Config


class WarpCommand(val config: Config): Command("warp") {
    init {
        description = "ワープ地点にワープします"
        usageMessage = "/warp point名"
    }

    override fun execute(sender: CommandSender?, commandLabel: String?, args: Array<out String>?): Boolean {
        // コンソールは弾く
        if(sender !is Player) {
            sender?.server?.logger?.alert("コンソールからは実行できません")
            return true
        }
        if (args?.isEmpty() == true) {
            sender.sendMessage(TranslationContainer("commands.generic.usage", usageMessage))
            return false
        }
        val path = "WarpData.${args[0]}."
        val data: Map<String, Float> = mapOf(
            "x" to config.get(path + "x")
            "y" to config.get(path + "y")
            "z" to config.get(path + "z")
        )
        sender.teleport(data?.)
        return true
    }
}