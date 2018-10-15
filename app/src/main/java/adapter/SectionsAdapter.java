package adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import com.ilab.user.e_lora.R;
import com.ilab.user.e_lora.activities.fragments.Charts;
import com.ilab.user.e_lora.activities.fragments.Data;

public class SectionsAdapter extends FragmentPagerAdapter {
    Context context;

    public SectionsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new Data();
            case 1:
                return new Charts();
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        switch (position){
            case 0:
                return  context.getResources().getString(R.string.data_title);
            case 1:
                return context.getResources().getString(R.string.chart_title);
            default:
                Log.d("Beatrice", "LOST");
                return null;
        }

    }
}
