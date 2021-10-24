package site.saba123.store

import site.saba123.model.Status

class StatusStore {
    companion object {
        private val statusMap: MutableMap<String, Status> = mutableMapOf()
        fun add(name: String, status: Status) {
            if(isExist(name)) return
            statusMap[name] = status
        }

        fun delete(name: String) {
            if(!isExist(name)) return
            statusMap.remove(name)
        }

        fun update(name: String, status: Status) {
            if(!isExist(name)) return
            statusMap[name] = status
        }

        fun getByName(name: String): Status {
            if(!isExist(name)) throw Exception("存在しないキーの代入")
            return statusMap[name]!!
        }

        fun isExist(name: String): Boolean {
            return statusMap.contains(name)
        }
    }
}