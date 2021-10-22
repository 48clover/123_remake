package site.saba123

import cn.nukkit.plugin.PluginBase

class Main: PluginBase() {
    override fun onEnable() {
        this.server.pluginManager.registerEvents(EventListener(), this)
    }
}