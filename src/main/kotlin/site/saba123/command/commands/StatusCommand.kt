package site.saba123.command.commands

import cn.nukkit.Player
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import site.saba123.model.Rank
import site.saba123.repository.StateRepository

class StatusCommand: Command("status") {
    init {
        description = "ステータスを確認します"
        aliases = arrayOf("stat", "status")
    }

    override fun execute(sender: CommandSender?, commandLabel: String?, args: Array<out String>?): Boolean {
        // コンソールは弾く
        if(sender !is Player) {
            sender?.server?.logger?.alert("コンソールからは実行できません")
            return true
        }
        val status = StateRepository.find(sender.loginChainData.xuid)
        //TODO: プレイ時間のフォーマット実装
        val name = sender.name
        val playTime = status?.playTime
        val rank = Rank.format(status!!.rankId)
        sender.sendMessage("""
                        $name：
                        ランク：$rank
                        プレイ時間：$playTime 秒
        """.trimIndent())
        return true
    }
}