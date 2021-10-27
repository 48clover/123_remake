package site.saba123

import cn.nukkit.plugin.PluginBase
import site.saba123.usecase.DataBaseUseCase
import site.saba123.util.ConfigPool

class Main: PluginBase() {
    override fun onEnable() {
        // config
        saveDefaultConfig()
        ConfigPool.add("config", config)

        // db
        DataBaseUseCase.init(dataFolder.path)

        // EventListener
        this.server.pluginManager.registerEvents(EventListener(), this)
    }
}