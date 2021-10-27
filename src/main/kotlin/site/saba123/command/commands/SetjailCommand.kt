package site.saba123.command.commands

import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender

class SetjailCommand : Command("setjail") {
    init {
        description = "[管理] 現在地点に檻の位置を設定します"
        usage = "/setjail"
    }

    override fun execute(sender: CommandSender?, commandLabel: String?, args: Array<out String>?): Boolean {
        // TODO: configのJailを書き換える
        return true
    }
}