package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.izeat.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class add extends AppCompatActivity {

    private static final int RETURN_TAKE_PICTURE=1;
    private ImageButton btnTakePicture;
    private ImageView imgAffichePhoto;
    private String photoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initActivity();
    }

    @SuppressLint("WrongViewCast")
    private void initActivity() {
        //reccupération des objets graphiques
        btnTakePicture=(ImageButton) findViewById(R.id.btnTakePicture);
        //imgAffichePhoto=(ImageView)findViewById(R.id.imgAffichePhoto);

        //methode pour gérer les évènements
        createOnClickBtnTakePicture();

    }

    private void createOnClickBtnTakePicture(){
         btnTakePicture.setOnClickListener(new Button.OnClickListener() {
             @Override
             public void onClick(View v) {
                 takePicture();

             }
         });
    }

    //accès appareil photo, photo et memorise dans un fichier temporaire
    private void takePicture(){
        // créer un intent pour ouvrir une fenetre pour prendre la photo
        Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        // test pour controller que l'intent peut etre gérer

        if(intent.resolveActivity(getPackageManager())!=null){
            // créer un nom de fichier unique
            String time = new SimpleDateFormat(  "yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile= File.createTempFile("photo"+time, ".jpg", photoDir);
                //enregistrer le chemin complet
                photoPath=photoFile.getAbsolutePath();
                //créer URI
                Uri photoUri= FileProvider.getUriForFile(add.this,
                        add.this.getApplicationContext().getPackageName() + ".provider",
                        photoFile);

                //transfert Uri vers l'intent pour enegistrement photo dans fichier temporaire
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

                //ouvrir l'activité pa rapport à l'intent
                startActivityForResult(intent,RETURN_TAKE_PICTURE );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}

        // retour de l'appel de l'appareil photo
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data)  {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode==RETURN_TAKE_PICTURE && resultCode==RESULT_OK){
                //reccupérer image
                Bitmap image = BitmapFactory.decodeFile(photoPath);
                //afficher image
                imgAffichePhoto.setImageBitmap(image);
        }


    }

}
