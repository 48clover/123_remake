package site.saba123.command.commands

import cn.nukkit.Player
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
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
        val name = sender.name
        val status = StateRepository.find(sender.loginChainData.xuid) ?: return true
        val money = status.money.money
        val rank = status.rank.toText()
        val playTime = status.playMinute.toDate()
        sender.sendMessage("""
            $name：
            ランク：$rank
            所持金：$money
            プレイ時間：${playTime["hour"]}時間 ${playTime["minute"]}分
        """.trimIndent())
        return true
    }
}