package site.saba123.util

import cn.nukkit.utils.Config

class ConfigPool {
    companion object {
        private val configMap: MutableMap<String, Config> = mutableMapOf()
        fun add(fileName: String, config: Config) {
            if (isExist(fileName)) return
            configMap[fileName] = config
        }

        fun delete(fileName: String) {
            if (!isExist(fileName)) return
            configMap.remove(fileName)
        }

        fun update(fileName: String, config: Config) {
            if (!isExist(fileName)) return
            configMap[fileName] = config
        }

        fun getByName(fileName: String): Config {
            if (!isExist(fileName)) throw Exception("存在しないキーの代入")
            return configMap[fileName]!!
        }

        fun isExist(fileName: String): Boolean {
            return configMap.contains(fileName)
        }
    }
}