package veljkojaksic.medkit.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import veljkojaksic.medkit.Activities.MainActivity;
import veljkojaksic.medkit.Activities.PictureActivity;
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

        showUserData();

        binding.btnProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attributesProperties(View.VISIBLE, true);
            }
        });

        binding.btnProfileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attributesProperties(View.INVISIBLE, false);
                updateUserInformation(binding.profileFullName.getText().toString(), binding.profileEmail.getText().toString(), binding.profileAddress.getText().toString(), binding.profilePhoneNumber.getText().toString());
            }
        });

        binding.changeProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PictureActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }

    private void updateUserInformation(String name, String email, String address, String phoneNumber) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(FirebaseAuth.getInstance().getUid())
                .update("FullName", name, "Email", email, "Address", address, "PhoneNumber", phoneNumber)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getActivity().getApplicationContext(), "Information updated!", Toast.LENGTH_SHORT).show();
                        } else
                        {
                            Toast.makeText(getActivity().getApplicationContext(), "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void attributesProperties(int visible, boolean b) {
        binding.changeProfilePicture.setVisibility(visible);
        binding.btnProfileSave.setVisibility(visible);
        binding.profileFullName.setEnabled(b);
        binding.profileEmail.setEnabled(b);
        binding.profileAddress.setEnabled(b);
        binding.profilePhoneNumber.setEnabled(b);
    }

    private void showUserData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult() != null)
                    {
                        binding.profileFullName.setText(task.getResult().getString("FullName"));
                        binding.profileEmail.setText(task.getResult().getString("Email"));
                        binding.profileAddress.setText(task.getResult().getString("Address"));
                        binding.profilePhoneNumber.setText(task.getResult().getString("PhoneNumber"));
                    }
        });


        //ovo radi samo  treba da se resize-uje slika prilikom prikaza

        /*StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("profilePictures/" + FirebaseAuth.getInstance().getUid().toString());

        try {
            final File image = File.createTempFile(FirebaseAuth.getInstance().getUid().toString(),"jpeg");
            storageReference.getFile(image)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
                            binding.profilePicture.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity().getApplicationContext(), "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}