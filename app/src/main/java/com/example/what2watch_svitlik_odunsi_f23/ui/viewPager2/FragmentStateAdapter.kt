package com.example.what2watch_svitlik_odunsi_f23.ui.viewPager2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.ui.home.HomeFragment
import com.example.what2watch_svitlik_odunsi_f23.ui.q1showmovie.q1Fragment
import com.example.what2watch_svitlik_odunsi_f23.ui.q2genre.q2Fragment
import com.example.what2watch_svitlik_odunsi_f23.ui.q3decade.q3Fragment
import com.example.what2watch_svitlik_odunsi_f23.ui.q4rating.q4Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class CollectionDemoFragment : Fragment() {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var demoCollectionAdapter: DemoCollectionAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Home"
                1 -> tab.text = "Show/Movie"
                2 -> tab.text = "Genre"
                3 -> tab.text = "Decade"
                4 -> tab.text = "Rating"
            }
        }.attach()
    }
}


class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 5


    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
            // Return the appropriate fragment for each position
            return when (position) {
                0 -> HomeFragment()
                1 -> q1Fragment()
                2 -> q2Fragment()
                3 -> q3Fragment()
                4 -> q4Fragment()
                else -> HomeFragment() // Handle unexpected position
            }
        }
    }

private const val ARG_OBJECT = "object"


// Instances of this class are fragments representing a single
// object in our collection.
class DemoObjectFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.activity_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val textView: TextView = view.findViewById(android.R.id.text1)
            textView.text = getInt(ARG_OBJECT).toString()
        }
    }
}
