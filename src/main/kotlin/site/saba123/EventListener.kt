package site.saba123

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.block.BlockBreakEvent
import cn.nukkit.event.player.PlayerJoinEvent
import cn.nukkit.event.player.PlayerQuitEvent
import site.saba123.model.Status
import site.saba123.repository.StatusRepository
import site.saba123.store.StatusStore

class EventListener: Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val name = player.name
        val xuid = player.loginChainData.xuid

        // 既参加
        if (player.hasPlayedBefore()) {
            // storeにstatusを登録
            val status: Status = StatusRepository.find(name)!!
            StatusStore.add(name, status)
            return

            // TODO: storeにjobを登録
        }

        // mcid変更
        // TODO: status内のnameを更新


        // 初参加
        // DBにプレイヤーデータを登録
        StatusRepository.add(xuid, name)
    }


    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val player = event.player
        val name = player.name

        // DBの値を更新
        val status = StatusStore.getByName(name)
        StatusRepository.update(name, status)

        // Storeからstatusを削除
        StatusStore.delete(name)
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val player = event.player
        val name = player.name

        // storeにmoneyを加算
        val status = StatusStore.getByName(name)
        status.money.add(1)
        StatusStore.update(name, status)

        // TODO: storeにexpを加算
    }
}