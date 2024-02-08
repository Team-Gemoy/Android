package com.synrgy.wefly.data.api.json

import com.synrgy.wefly.data.api.json.list.PageAble

data class ContentResponse<T>(
    val content: ArrayList<T>,
    val pageable: PageAble,
)
