package site.saba123

import cn.nukkit.plugin.PluginBase
import site.saba123.usecase.DataBaseUseCase

class Main: PluginBase() {
    override fun onEnable() {
        // config
        saveDefaultConfig()

        // db
        DataBaseUseCase.init(dataFolder.path)

        // EventListener
        this.server.pluginManager.registerEvents(EventListener(), this)
    }
}