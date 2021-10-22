package site.saba123

import cn.nukkit.plugin.PluginBase
import site.saba123.usecase.DataBaseUseCase

class Main: PluginBase() {
    override fun onEnable() {
        DataBaseUseCase.init(dataFolder.path)
        this.server.pluginManager.registerEvents(EventListener(), this)
    }
}