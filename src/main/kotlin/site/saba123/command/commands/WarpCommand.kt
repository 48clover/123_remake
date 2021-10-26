package site.saba123.command.commands

import cn.nukkit.Player
import cn.nukkit.Server
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.lang.TranslationContainer
import cn.nukkit.level.Location
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
        val path = "WarpData.${args?.get(0)}."
        val position = Location(
            config.get(path + "x").toString().toDouble(),
            config.get(path + "y").toString().toDouble(),
            config.get(path + "z").toString().toDouble(),
            sender.yaw,
            sender.pitch,
            Server.getInstance().getLevelByName(
                config.get(path + "level").toString()
            )
        )
        sender.teleport(position)
        sender.sendMessage(">${args?.get(0)} にワープしました")
        return true
    }
}