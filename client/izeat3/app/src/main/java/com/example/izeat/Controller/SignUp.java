package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.izeat.R;
import com.example.izeat.Utils.ExampleActivity;
import com.example.izeat.Utils.MyAsyncTask;


import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity implements MyAsyncTask.Listeners {


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

    private int signUpResponse = -4;

    private boolean semaphoreLike;


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
                    startAsyncTask();

                }
                else
                    Toast.makeText(getApplicationContext(), "Renseigne toutes tes infos ça sera plus facile pour nous", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startAsyncTask() {
        new MyAsyncTask(this).execute();
    }

    @Override
    public void onPreExecute() {
        Toast.makeText(getApplicationContext(), "you're being signed up", Toast.LENGTH_LONG).show();
    }

    @Override
    public void doInBackground() {
        semaphoreLike = false;

        getUserSignUpAction(prenom.getText().toString(),
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

        while (!semaphoreLike) {
            //loop until getUserSignUpAction updated the response
        }

    }

    @Override
    public void onPostExecute(Long taskEnd) {

        //System.out.println("the signUpResponse is now : " + signUpResponse);
        if (signUpResponse == 0) {
            //System.out.println("in the if, signUpResponse = " + signUpResponse);


        }

        else {
            System.out.println("in the else ; signUpResponse = " + signUpResponse);
            //Toast.makeText(getApplicationContext(), "erreur lors du signup : code erreur = " + signUpResponse, Toast.LENGTH_LONG);

            // TODO : verify the response code with the server developers !! it may be wrong

            switch (signUpResponse) {
                case -2:
                    Toast.makeText(getApplicationContext(), "A user with the provided email already exists. No new user was added to the database. Please consider signing in instead.", Toast.LENGTH_LONG).show();
                    break;
                case -1:
                    Toast.makeText(getApplicationContext(), "A (server-sided) SQL error occurred while signing up and user was not added to the database.", Toast.LENGTH_LONG).show();

                    break;
                case -3:
                    Toast.makeText(getApplicationContext(), "adresse e-mail invalide", Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "An unknown error occurred while signing up.", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }


    private void getUserSignUpAction  (String firstName, String lastName, int age, char sex, int height, int weight, boolean isVegan, boolean isVegetarian, boolean isPalmOilOK, String password, String email, final Context context)
    {
        /*
         *  This method is used when a user wants to create an account on the database. Even though
         *  the HTTP request is a GET, it will add the user to the database when the method is used
         *  correctly.
         *
         *  Parameters:
         *      firstName: String representing the first name of the user. First letter should be uppercase.
         *      lastName: String representing the last name of the user. First letter should be uppercase.
         *      age: A positive integer giving the age of the user.
         *      sex: A character representing the gender of the user. 'f' -> female, 'h' -> male
         *      height: A positive integer giving the current height of the user in centimeters.
         *      weight: A positive integer giving the current weight of the user in kilograms.
         *      isVegan: A boolean: true -> User is vegan, false otherwise.
         *      isVegetarian: A boolean: true -> User is vegetarian, false otherwise.
         *      isPalmOilOK: A boolean: true -> User is ok with products containing palm oil, false otherwise.
         *      password: A string representing the password the user will use to retrieve its data.
         *      email: A string representing the email the user will use to login to retrieve its data.
         *      context: Give the current context of the activity (use getApplicationContext()).
         *  Info obtained inside the method:
         *      An integer which, according to its value, tells whether the sign up was successful are not.
         *  Example:
         *      getUserSignUp("Jean", "Dupont", 34, 'h', 178, 82, false, true, true, "mot_dE_PassE123", "jean.dupont@supermail.fr", getApplicationContext());
         *      -> Create an account for Jean Dupont.
         *  Notes:
         *      There is no verification made on the server at the moment apart from checking if the email
         *      is already in the database and contains '@'. It may be necessary to check things on client such as:
         *          - If isVegan = true and isVegetarian = false
         *          - firstName and lastName length < 20
         *          - Validity of each of the field used as parameters.
         */
        String vegan = isVegan ? "1" : "0"; // Convert the booleans to string.
        String vegetarian = isVegetarian ? "1" : "0";
        String palmOil = isPalmOilOK ? "1" : "0";
        // HTTPS is mandatory for requests containing user info.
        String url = "https://izeat.r2.enst.fr/ws/Izeat/webresources/user/signup/" + firstName + "/" + lastName + "/" + Integer.toString(age) + "/" + sex + "/" + Integer.toString(height) + "/" + Integer.toString(weight) + "/" + vegan + "/" + vegetarian + "/" + palmOil + "/" + password + "/" + email;

        RequestQueue queue = Volley.newRequestQueue(context);

        // ToDo: Change Request method to GET when the server will have modified the request method for sign up.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject response){
                        /***************************
                         *   BEGINNING OF ACTIONS   *
                         ***************************/
                        try {
                            int response_code = response.getInt("result");
                            signUpResponse = response_code;
                            //System.out.println("response code = " + response_code);
                            //System.out.println("signUpResponseAttribute : " + signUpResponse);
                            switch (response_code){
                                case 0:
                                    Log.e("TAG", "User was successfully added to the database on the server.");
                                    break;
                                case -2:
                                    Log.e("TAG", "A user with the provided email already exists. No new user was added to the database. Please consider signing in instead.");
                                    break;
                                case -1:
                                    Log.e("TAG", "A (server-sided) SQL error occurred while signing up and user was not added to the database.");
                                    break;
                                case -3:
                                    Log.e("TAG", "adresse e-mail invalide");
                                    break;
                                default:
                                    Log.e("TAG", "An unknown error occurred while signing up.");
                                    break;
                            }

                            semaphoreLike = true;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /***************************
                         *      END OF ACTIONS     *
                         ***************************/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error.toString());
                    }
                });
        queue.add(jsonObjectRequest);
    }

    private boolean signUpReady(){
        //This method is to avoid creating absurd users : we verify each field is filled in the signup screen
        int nomL = nom.length();
        System.out.println(nom.getText().toString());
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
                mailL >0 &&
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
