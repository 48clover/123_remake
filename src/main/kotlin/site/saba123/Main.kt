package site.saba123

import cn.nukkit.event.Listener
import cn.nukkit.plugin.PluginBase

class Main: PluginBase(), Listener {
    override fun onEnable() {
        this.server.pluginManager.registerEvents(EventListener(), this)
        saveDefaultConfig()
    }
}