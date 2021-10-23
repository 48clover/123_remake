package site.saba123.model

class Rank {
    companion object {
        fun format(rank: Int): String {
            return when(rank) {
                0 -> {"観光"}
                1 -> {"住民"}
                2 -> {"信任"}
                3 -> {"管理"}
                4 -> {"主"}
                5 -> {"パイ"}
                else -> {""}
            }
        }
    }
}