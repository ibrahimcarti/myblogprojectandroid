    package com.example.finalexam;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import android.app.ProgressDialog;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Toast;

    import com.example.finalexam.databinding.ActivityMainBinding;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.firebase.auth.AuthResult;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.UserProfileChangeRequest;
    import com.google.firebase.firestore.FirebaseFirestore;
    import com.google.firebase.firestore.auth.User;

    public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;
        private ProgressDialog progressDialog;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding=ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            binding.goToLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            });

            binding.createAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name= binding.name.getText().toString();
                    String number= binding.number.getText().toString();
                    String email= binding.email.getText().toString();
                    String password= binding.password.getText().toString();
                    if(!name.isEmpty()|| !number.isEmpty()|| !email.isEmpty() || !password.isEmpty()){
                    progressDialog =new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle("Creating");
                    progressDialog.setMessage("Account");
                    progressDialog.show();



                    FirebaseAuth
                            .getInstance()
                            .createUserWithEmailAndPassword(email.trim(),password.trim())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                                    FirebaseAuth.getInstance().getCurrentUser()
                                            .updateProfile(userProfileChangeRequest);
                                    new MySharedPreferences(MainActivity.this).setMyData(number);

                                    UserModel userModel=new UserModel();
                                    userModel.setUserName(name);
                                    userModel.setUserNumber(number);
                                    userModel.setUserMail(email);

                                    FirebaseFirestore
                                            .getInstance()
                                            .collection("Users")
                                            .document(FirebaseAuth.getInstance().getUid())
                                            .set(userModel);

                                    reset();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.cancel();
                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                        Toast.makeText(getApplicationContext(), "Lütfen boş alan bırakmayın!", Toast.LENGTH_SHORT).show();

                    }
                }

            });
        }

        private void reset() {

            progressDialog.cancel();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this,"Account Created Please Login!",Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
        }
    }