package com.team.seacondlife.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.team.seacondlife.fragments.MainFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(//    @StringRes
    //    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_1, R.string.tab_text_2};
    private val mContext: Context, fm: FragmentManager?
) :
    FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

//        return PlaceholderFragment.newInstance(position + 1);

        //sustituimos el fragmento único por nuestros cuatro fragmentos, así, el método getItem devuelve el fragmento
        // que corresponde a la posición que se le pasa a la clase Fragment como argumento
        return when (position) {
            0 -> MainFragment()
            1 -> MainFragment()
            2 -> MainFragment()
            else -> MainFragment()
        }
    }

    //    @Nullable
    //    @Override
    //    public CharSequence getPageTitle(int position) {
    //        return mContext.getResources().getString(TAB_TITLES[position]);
    //    }
    override fun getCount(): Int {
        // Show 2 total pages.
        return 4
    }
}