package veljkojaksic.medkit.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import veljkojaksic.medkit.R;
import veljkojaksic.medkit.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/05/Take-care-of-your-body.-Its-the-only-place-you-have-to-live..jpg", ""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/05/A-healthy-outside-starts-from-the-inside..jpg", ""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Health-is-like-money-we-never-have-a-true-idea-of-its-value-until-we-lose-it.jpg", ""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/If-you-truly-treat-your-body-like-a-temple-it-will-serve-you-well-for-decades.-If-you-abuse-it-you-must-be-prepared-for-poor-health-and-a-lack-of-energy.jpg", ""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Healthy-citizens-are-the-greatest-asset-any-country-can-have.jpg", ""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Good-health-is-not-something-we-can-buy.-However-it-can-be-an-extremely-valuable-savings-account.jpg", ""));
        slideModels.add(new SlideModel("https://cdn.graciousquotes.com/wp-content/uploads/2020/03/Your-body-will-be-around-a-lot-longer-than-that-expensive-handbag.-Invest-in-yourself.jpg", ""));
        binding.imageSlider.setImageList(slideModels, true);

        return binding.getRoot();
    }
}