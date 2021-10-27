package site.saba123.command.commands

import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender

class BlackCommand : Command("black") {
    init {
        description = "[管理] 対象プレイヤーをblackします"
        usage = "/black <player> [reason]"
    }

    override fun execute(sender: CommandSender?, commandLabel: String?, args: Array<out String>?): Boolean {
        // TODO: black実装
        return true
    }
}