package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.izeat.R;
import com.example.izeat.Utils.ExampleActivity;
import com.example.izeat.Utils.SignUpException;

public class SignUp extends AppCompatActivity {


    private EditText nom;
    private EditText prenom;
    private EditText mail;
    private EditText password;
    private EditText age;
    private EditText weight;
    private EditText height;
    private char sex;

    private boolean[] userPreferences = new boolean[3];
    //userPref[0] represents if the user is Vegan, userPref[1] represents Vegetarian, userPref[2] is his position about palmOil

    private Button suivant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.poids);
        height = findViewById(R.id.taille);
        userPreferences[2] = true; //default value of isPalmOilOk must be true

        suivant = findViewById(R.id.btn_suivant);
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signUpReady()) {
                    int signUpResponseCode = 0;

                    try {
                        ExampleActivity.getUserSignUpAction(prenom.getText().toString(),
                                nom.getText().toString(),
                                Integer.parseInt(age.getText().toString()),
                                sex,
                                Integer.parseInt(height.getText().toString()),
                                Integer.parseInt(weight.getText().toString()),
                                userPreferences[0],
                                userPreferences[1],
                                userPreferences[2],
                                password.getText().toString(),
                                mail.getText().toString(),
                                getApplicationContext());
                    }

                    catch (SignUpException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                        signUpResponseCode = e.getExceptionCode();
                        e.printStackTrace();
                    }

                    if (signUpResponseCode == 0) {
                        Intent recipeListIntent = new Intent(SignUp.this, RecipesListActivity.class);
                        startActivity(recipeListIntent);
                    }

                    else
                        System.out.println("erreur lors du signup : code erreur = " + signUpResponseCode);
                }
                else
                    Toast.makeText(getApplicationContext(), "Renseigne toutes tes infos ça sera plus facile pour nous", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean signUpReady(){
        //This method is to avoid creating absurd users : we verify each field is filled in the signup screen
        int nomL = nom.length();
        int prenomL = prenom.length();
        int mailL = mail.length();
        int passwordL = password.length();
        int ageL = age.length();
        int weightL = weight.length();
        int heightL = height.length();
        boolean allRadioGroupChecked =  (((RadioGroup)findViewById(R.id.sex_group)).getCheckedRadioButtonId() != -1) &&
                                        (((RadioGroup)findViewById(R.id.diet_group)).getCheckedRadioButtonId() != -1) &&
                                        (((RadioGroup)findViewById(R.id.palm_oil_group)).getCheckedRadioButtonId() != -1);

        if (nomL < 21 &&
                nomL > 0 &&
                prenomL < 21 &&
                prenomL >0 &&
                mailL < 21 &&
                mailL >0 &&
                passwordL < 21 &&
                passwordL >0 &&
                ageL < 21 &&
                ageL >0 &&
                weightL <21 &&
                weightL >0 &&
                heightL <21 &&
                heightL >0 &&
                allRadioGroupChecked)
            return true;
        else
            return false;
    }

    public void onRadioButtonClickedSex(View view) { //Handling the selection of a gender
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.femme:
                if (checked)
                    sex = 'f';
                    break;
            case R.id.homme:
                if (checked)
                    sex = 'h';
                    break;
        }
    }

    public void onRadioButtonClickedPalmOil(View view) { //Handling the selection of a gender
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.oui:
                if (checked)
                    userPreferences[2] = false;
                break;
            case R.id.non:
                if (checked)
                    userPreferences[2] = true;
                break;
        }
    }

    public void onRadioButtonClickedDiet(View view) { //Handling the selection of a gender
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.none:
                if (checked) {
                    userPreferences[0] = false;
                    userPreferences[1] = false;
                }
                break;
            case R.id.vegan:
                if (checked)
                {
                    userPreferences[0] = true;
                    userPreferences[1] = true;
                }
                break;
            case R.id.vegetarien:
                if (checked)
                {
                    userPreferences[0] = false;
                    userPreferences[1] = true;
                }
                break;
        }
    }
}
