package site.saba123.command.commands

import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender

class StatusCommand: Command("status") {
    init {
        description = "ステータスを確認します"
    }

    override fun execute(sender: CommandSender?, commandLabel: String?, args: Array<out String>?): Boolean {
        //TODO: DBからステータスを入手し表示
        return true
    }
}