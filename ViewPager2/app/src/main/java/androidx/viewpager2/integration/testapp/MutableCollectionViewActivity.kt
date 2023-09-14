/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.viewpager2.integration.testapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.integration.testapp.tag.ClickRegistry_TAG
import androidx.viewpager2.integration.testapp.tag.MutableCollectionViewActivity_TAG
import androidx.viewpager2.integration.testapp.tag.PageViewHolder_TAG
import androidx.viewpager2.widget.ViewPager2

/**
 * Shows how to use [RecyclerView.Adapter.notifyDataSetChanged] with [ViewPager2]. Here [ViewPager2]
 * represents pages as [View]s.
 */
class MutableCollectionViewActivity : MutableCollectionBaseActivity() {

    init{
        Log.i(MutableCollectionViewActivity_TAG, "objek dibuat")
    }

    override fun createViewPagerAdapter(): RecyclerView.Adapter<*> {
        Log.i(MutableCollectionViewActivity_TAG,
            "MutableCollectionViewActivity.createViewPagerAdapter()")

        val items = items // avoids resolving the ViewModel multiple times
        val clickRegistry: ClickRegistry by viewModels()
        return object : RecyclerView.Adapter<PageViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, type: Int) = PageViewHolder(parent)
            override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
                val itemId = holder.itemId
                val clickHandler = { clickRegistry.registerClick(itemId) }
                val clickCountProvider = { clickRegistry.getClickCount(itemId) }
                holder.bind(items.getItemById(itemId), clickHandler, clickCountProvider)
            }

            override fun getItemCount(): Int = items.size
            override fun getItemId(position: Int): Long = items.itemId(position)
        }.apply { setHasStableIds(true) }
    }
}

class PageViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_mutable_collection, parent, false)
    ) {

    init{
        Log.i(PageViewHolder_TAG, "objek dibuat")
    }

    private val textViewItemId: TextView = itemView.findViewById(R.id.textViewItemText)
    private val textViewCount: TextView = itemView.findViewById(R.id.textViewCount)
    private val buttonCountIncrease: Button = itemView.findViewById(R.id.buttonCountIncrease)

    fun bind(itemText: String, registerClick: () -> Unit, getClickCount: () -> Int) {
        Log.i(PageViewHolder_TAG, "PageViewHolder.bind()")

        textViewItemId.text = itemText
        val updateClickText = { textViewCount.text = "${getClickCount()}" }
        updateClickText()

        buttonCountIncrease.setOnClickListener {
            registerClick()
            updateClickText()
        }
    }
}

/**
 * Stores click counts for items. Items are identified by an id.
 */
class ClickRegistry : ViewModel() {

    init{
        Log.i(ClickRegistry_TAG, "objek dibuat")
    }

    private val clickCount = mutableMapOf<Long, Int>()
    fun getClickCount(itemId: Long): Int {
        Log.i(ClickRegistry_TAG, "ClickRegistry.getClickCount()")

        return clickCount[itemId] ?: 0
    }
    fun registerClick(itemId: Long) {
        Log.i(ClickRegistry_TAG, "ClickRegistry.registerClick()")

        clickCount.set(itemId, 1 + getClickCount(itemId))
    }
}
