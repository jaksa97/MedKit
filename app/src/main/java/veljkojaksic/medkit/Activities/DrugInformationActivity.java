package veljkojaksic.medkit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import veljkojaksic.medkit.functionality.DrugModel;
import veljkojaksic.medkit.databinding.ActivityDrugInformationBinding;

public class DrugInformationActivity extends AppCompatActivity {

    private DrugModel drug;

    private ActivityDrugInformationBinding binding;
    private String drugName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDrugInformationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        Intent intent = getIntent();

        drugName = (String) intent.getExtras().get("DRUG_NAME");

        drug = new DrugModel();

        drug = findDrugInformation(drugName);

        Log.d("asd", drugName.toString() + "   " + drug.getManufacturer());

        binding.setDrug(drug);

    }

    private DrugModel findDrugInformation(String drugName)
    {
        DrugModel drugModel = new DrugModel();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("drugs").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (DocumentSnapshot doc : task.getResult())
                        {
                            if(doc.getString("name").equals(drugName))
                            {
                                drugModel.setManufacturer(doc.getString("manufacturer"));
                                drugModel.setType(doc.getString("type"));
                                drugModel.setDose(doc.getString("dose"));
                                drugModel.setWarning(doc.getString("warning"));
                                drugModel.setUsage(doc.getString("usage"));
                                drugModel.setPrice(doc.getString("price"));
                                drugModel.setSymptoms(null);
                                drugModel.setSideEffects(null);
                            }

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return drugModel;
    }
}