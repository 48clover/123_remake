package site.saba123

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerJoinEvent
import site.saba123.repository.StateRepository

class EventListener: Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        if(player.hasPlayedBefore()) {
            //TODO: タイトルとかメッセージとか表示
            return
        }
        // DBにプレイヤーデータを登録
        val xuid = player.loginChainData.xuid
        val name = player.name
        StateRepository.add(xuid, name)
    }
}