package site.saba123.store

import site.saba123.model.Permission

class PermissionStore {
    companion object {
        private val permissionMap: MutableMap<String, Permission> = mutableMapOf()
        fun add(name: String, permission: Permission) {
            if (isExist(name)) return
            permissionMap[name] = permission
        }

        fun delete(name: String) {
            if (!isExist(name)) return
            permissionMap.remove(name)
        }

        fun update(name: String, permission: Permission) {
            if (!isExist(name)) return
            permissionMap[name] = permission

        }

        fun getByName(name: String): Permission {
            if (isExist(name)) throw Exception("存在しないキーの代入")
            return permissionMap[name]!!

        }

        fun isExist(name: String): Boolean {
            return permissionMap.contains(name)
        }
    }
}