package veljkojaksic.medkit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import veljkojaksic.medkit.R;
import veljkojaksic.medkit.databinding.ActivityCategoriesBinding;
import veljkojaksic.medkit.databinding.ActivityCategoryBinding;
import veljkojaksic.medkit.functionality.MyAdapter;
import veljkojaksic.medkit.functionality.SelectListener;

public class CategoryActivity extends AppCompatActivity implements SelectListener {
    
    private ActivityCategoryBinding binding;
    private MyAdapter myAdapter;
    private String categoryName;
    private List<String> drugNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        Intent intent = getIntent();

        categoryName = (String) intent.getExtras().get("CATEGORY_NAME");

        binding.category.setText(categoryName);

        showDrugsByCategory();
    }

    private void showDrugsByCategory() {
        binding.recyclerViewCategory.setHasFixedSize(true);
        binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        drugNames = new ArrayList<>();

        getDrugNames(drugNames);

        myAdapter = new MyAdapter(getApplicationContext(), drugNames, this);
        binding.recyclerViewCategory.setAdapter(myAdapter);
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
                            if (doc.getString("category").equals(categoryName)) {
                                String drugName = doc.getString("name");
                                drugNames.add(drugName);
                            }
                        }

                        myAdapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClicked(String name) {
        Intent intent = new Intent(getApplicationContext(), DrugInformationActivity.class);
        intent.putExtra("DRUG_NAME", name);
        startActivity(intent);
    }
}