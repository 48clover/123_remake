package site.saba123.command.commands

import cn.nukkit.Server
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.level.Location
import site.saba123.store.PermissionStore

class OtuCommand : Command("otu") {
    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) return false
        val name = args[0]
        val target = sender.server.getPlayer(name)
        val disabledCommandList = PermissionStore.getByName(name).disabledCommand
        val config = sender.server.config
        val jailLocation = Location(
            config.getDouble("Jail.x"),
            config.getDouble("Jail.y"),
            config.getDouble("Jail.z"),
            Server.getInstance().getLevelByName(config.getString("Jail.level"))
        )
        // otu者を牢屋にtp
        target.teleport(jailLocation)
        // warpを制限
        disabledCommandList.add("warp")
        return true
    }
}