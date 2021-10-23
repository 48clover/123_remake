package site.saba123

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerJoinEvent
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import site.saba123.model.Jobs
import site.saba123.model.Statuses

class EventListener: Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        if(player.hasPlayedBefore()) {
            //TODO: タイトルとかメッセージとか表示
            return
        }

        // DBにプレイヤーデータを登録
        transaction {
            Statuses.insert {
                it[xuid] = player.loginChainData.xuid
                it[name] = player.name
                it[rankId] = 0
                it[playTime] = 0
            }
            Jobs.insert {
                it[xuid] = player.loginChainData.xuid
                it[beginnerExp] = 0
                it[fighterExp] = 0
                it[therapistExp] = 0
                it[builderExp] = 0
                it[organizerExp] = 0
            }
        }
    }
}