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

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.integration.testapp.tag.PreviewPagesActivity_Adapter_TAG
import androidx.viewpager2.integration.testapp.tag.PreviewPagesActivity_TAG
import androidx.viewpager2.integration.testapp.tag.PreviewPagesActivity_ViewHolder_TAG
import androidx.viewpager2.widget.ViewPager2

class PreviewPagesActivity : FragmentActivity() {

    init {
        Log.i(PreviewPagesActivity_TAG, "objek dibuat")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(PreviewPagesActivity_TAG, "PreviewPagesActivity.onCreate()")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager2)
        findViewById<ViewPager2>(R.id.view_pager).apply {
            // Set offscreen page limit to at least 1, so adjacent pages are always laid out
            offscreenPageLimit = 1
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding = resources.getDimensionPixelOffset(R.dimen.halfPageMargin) +
                        resources.getDimensionPixelOffset(R.dimen.peekOffset)
                // setting padding on inner RecyclerView puts overscroll effect in the right place
                // TODO: expose in later versions not to rely on getChildAt(0) which might break
                setPadding(padding, 0, padding, 0)
                clipToPadding = false
            }
            adapter = Adapter()
        }
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_preview_pages, parent, false)
    ) {
        init {
            Log.i(PreviewPagesActivity_ViewHolder_TAG, "objek dibuat")
        }
    }

    class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        init {
            Log.i(PreviewPagesActivity_Adapter_TAG, "objek dibuat")
        }

        override fun getItemCount(): Int {
            Log.i(PreviewPagesActivity_Adapter_TAG,
                "PreviewPagesActivityAdapter.getItemCount()")

            return 10
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            Log.i(PreviewPagesActivity_Adapter_TAG,
                "PreviewPagesActivityAdapter.onCreateViewHolder()")

            return ViewHolder(parent)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            Log.i(PreviewPagesActivity_Adapter_TAG,
                "PreviewPagesActivityAdapter.onBindViewHolder()")

            holder.itemView.tag = position
        }
    }
}
