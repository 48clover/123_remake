package site.saba123.command.commands

import cn.nukkit.Player
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import site.saba123.model.Rank
import site.saba123.store.StatusStore

class RankCommand : Command("rank") {
    init {
        description = "[管理] プレイヤーの役職を設定します"
        usage = "/rank [player] [0：観光|1：住民|2：信任|3：管理]"
    }

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        if (args.size < 2 || sender !is Player) return false
        val senderName = sender.name
        val targetName = args[0]
        val status = StatusStore.getByName(targetName)

        status.rank.update(args[1].toInt())
        val rank = Rank(args[1].toInt())

        sender.server.broadcastMessage("§a[INFO] §r$senderName §aが §r$targetName §aを §r[${rank.toText()}] にしました！")
        return true
    }
}