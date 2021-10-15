package veljkojaksic.medkit.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import veljkojaksic.medkit.R;
import veljkojaksic.medkit.databinding.ActivityCategoriesBinding;
import veljkojaksic.medkit.databinding.ActivityMainBinding;
import veljkojaksic.medkit.functionality.MyAdapter;
import veljkojaksic.medkit.functionality.SelectListener;

public class CategoriesActivity extends AppCompatActivity implements SelectListener {

    private ActivityCategoriesBinding binding;
    private MyAdapter myAdapter;
    private List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        showCategories();
    }

    private void showCategories() {
        binding.recyclerViewCategories.setHasFixedSize(true);
        binding.recyclerViewCategories.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        categories = new ArrayList<>();
        categories.add("Analgesics");
        categories.add("Antibiotics");
        categories.add("Antidepressants");
        categories.add("Sedatives");
        categories.add("Probiotics");

        myAdapter = new MyAdapter(getApplicationContext(), categories, this);
        binding.recyclerViewCategories.setAdapter(myAdapter);
    }

    @Override
    public void onItemClicked(String name) {
        Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
        intent.putExtra("CATEGORY_NAME", name);
        startActivity(intent);
    }
}