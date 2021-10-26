package site.saba123

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.block.BlockBreakEvent
import cn.nukkit.event.player.PlayerCommandPreprocessEvent
import cn.nukkit.event.player.PlayerJoinEvent
import cn.nukkit.event.player.PlayerQuitEvent
import site.saba123.model.Job
import site.saba123.model.Permission
import site.saba123.model.Status
import site.saba123.repository.JobRepository
import site.saba123.repository.PermissionRepository
import site.saba123.repository.StatusRepository
import site.saba123.store.JobStore
import site.saba123.store.PermissionStore
import site.saba123.store.StatusStore

class EventListener: Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val name = player.name
        val xuid = player.loginChainData.xuid

        // 既参加
        if (player.hasPlayedBefore()) {
            val status: Status = StatusRepository.find(name)!!
            val job: Job = JobRepository.find(name)!!
            val permission: Permission = PermissionRepository.find(name)!!

            // storeに登録
            StatusStore.add(name, status)
            JobStore.add(name, job)
            PermissionStore.add(name, permission)

            // mcid変更時：store内のnameを更新
            if (name != status.name.text) {
                // storeのnameを更新
                status.name.shiftName(name)
                job.name.shiftName(name)
                permission.name.shiftName(name)
            }
        }

        // 初参加
        // DBにプレイヤーデータを登録
        StatusRepository.add(xuid, name)
        JobRepository.add(xuid, name)
        PermissionRepository.add(xuid, name)
    }


    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val player = event.player
        val name = player.name
        val status = StatusStore.getByName(name)
        val job = JobStore.getByName(name)
        val permission = PermissionStore.getByName(name)

        // DBの値を更新
        StatusRepository.update(status)
        JobRepository.update(job)
        PermissionRepository.update(permission)

        // Storeからstatusを削除
        StatusStore.delete(name)
        JobStore.delete(name)
        PermissionStore.delete(name)
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val player = event.player
        val name = player.name

        // storeにmoneyを加算
        val status = StatusStore.getByName(name)
        status.money.add(1)
        StatusStore.update(name, status)

        // storeにexpを加算
        val job = JobStore.getByName(name)
        when (job.currentJob.jobId) {
            0 -> {
                job.beginnerExp.add(1)
            }
            1 -> {
                job.fighterExp.add(1)
            }
            2 -> {
                job.therapistExp.add(1)
            }
            3 -> {
                job.builderExp.add(1)
            }
            4 -> {
                job.organizerExp.add(1)
            }
        }
    }

    @EventHandler
    fun onPlayerCommandPreprocess(event: PlayerCommandPreprocessEvent) {
        val player = event.player
        val name = player.name
        val status = StatusStore.getByName(name)
        val command = event.message.split(" ")[0]
        val permission: Permission = PermissionStore.getByName(name)

        if (!permission.enabledCommand.contains(command) &&
            (!status.rank.hasPermission(command) ||
                    permission.disabledCommand.contains(command))
        ) {
            player.sendMessage("コマンドの実行権限がありません")
            event.setCancelled()
        }
    }
}