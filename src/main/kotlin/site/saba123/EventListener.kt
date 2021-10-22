package site.saba123

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerJoinEvent

class EventListener: Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        //TODO: プレイヤーのステータスをDBに登録
    }
}