package site.saba123.command.commands

import cn.nukkit.Player
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender

class IdkCommand: Command("idk") {
    init {
        description = "プレイ時間を確認できます"
    }

    override fun execute(sender: CommandSender?, commandLabel: String?, args: Array<out String>?): Boolean {
        if(sender !is Player) return false
        TODO("プレイ時間の計算と通知")
    }
}