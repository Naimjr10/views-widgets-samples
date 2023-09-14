/*
 * Copyright 2018 The Android Open Source Project
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
import androidx.viewpager2.integration.testapp.cards.CardViewAdapter
import androidx.viewpager2.integration.testapp.tag.CardViewActivity_TAG
import androidx.viewpager2.widget.ViewPager2

/**
 * Shows how to use [ViewPager2.setAdapter] with Views.
 *
 * @see CardFragmentActivity for an example of using {@link ViewPager2} with Fragments.
 */
open class CardViewActivity : BaseCardActivity() {

    init {
        Log.i(CardViewActivity_TAG, "objek dibuat")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager.adapter = CardViewAdapter()
    }
}
