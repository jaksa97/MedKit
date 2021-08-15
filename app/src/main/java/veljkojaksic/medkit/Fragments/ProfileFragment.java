package veljkojaksic.medkit.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import veljkojaksic.medkit.R;
import veljkojaksic.medkit.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        binding.btnProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.changeProfilePicture.setVisibility(View.VISIBLE);
                binding.btnProfileSave.setVisibility(View.VISIBLE);
                binding.profileFullName.setEnabled(true);
                binding.profileEmail.setEnabled(true);
                binding.profileAddress.setEnabled(true);
                binding.profilePhoneNumber.setEnabled(true);
            }
        });

        binding.btnProfileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.changeProfilePicture.setVisibility(View.INVISIBLE);
                binding.btnProfileSave.setVisibility(View.INVISIBLE);
                binding.profileFullName.setEnabled(false);
                binding.profileEmail.setEnabled(false);
                binding.profileAddress.setEnabled(false);
                binding.profilePhoneNumber.setEnabled(false);
            }
        });

        return binding.getRoot();
    }
}