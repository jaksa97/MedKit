package veljkojaksic.medkit.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import veljkojaksic.medkit.Activities.DrugInformationActivity;
import veljkojaksic.medkit.functionality.MyAdapter;
import veljkojaksic.medkit.functionality.SelectListener;
import veljkojaksic.medkit.R;
import veljkojaksic.medkit.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment implements SelectListener {

    private FragmentSearchBinding binding;
    private List<String> drugNames;
    private MyAdapter myAdapter;

    public SearchFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        showDrugs();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtering(newText);
                return false;
            }
        });

        return binding.getRoot();
    }

    private void filtering(String newText) {
        List<String> filteredDrugNames = new ArrayList<>();

        for (String name: drugNames)
        {
            if(name.toLowerCase().contains(newText.toLowerCase()))
            {
                filteredDrugNames.add(name);
            }
        }

        myAdapter.filterList(filteredDrugNames);
    }

    private void showDrugs() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));

        drugNames = new ArrayList<>();

        getDrugNames(drugNames);

        myAdapter = new MyAdapter(getActivity().getApplicationContext(), drugNames, this);
        binding.recyclerView.setAdapter(myAdapter);
    }

    private void getDrugNames(List<String> drugNames) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("drugs").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        drugNames.clear();

                        for (DocumentSnapshot doc : task.getResult())
                        {
                            String drugName = doc.getString("name");
                            drugNames.add(drugName);
                        }

                        myAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClicked(String name) {
        Intent intent = new Intent(getActivity().getApplicationContext(), DrugInformationActivity.class);
        intent.putExtra("DRUG_NAME", name);
        startActivity(intent);
    }
}