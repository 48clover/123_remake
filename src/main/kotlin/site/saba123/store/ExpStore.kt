package site.saba123.store

import site.saba123.model.Exp
import site.saba123.model.Job
import java.lang.Exception

class ExpStore {
    companion object {
        private val jobMap: MutableMap<String, Job> = mutableMapOf()

        fun add(name: String, job: Job) {
            if(isExist(name)) return
            jobMap[name] = job
        }

        fun delete(name: String) {
            if(!isExist(name)) return
            jobMap.remove(name)
        }

        fun update(name: String, job: Job) {
            if(!isExist(name)) return
            jobMap[name] = job
        }

        fun getByName(name: String): Job {
            if(!isExist(name)) throw Exception("存在しないキーの代入")
            return jobMap[name]!!
        }

        fun isExist(name: String): Boolean {
            return jobMap.contains(name)
        }
    }
}