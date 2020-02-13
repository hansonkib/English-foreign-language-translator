package com.example.translate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
//import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
//import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
//import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
//import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

public class MainActivity extends AppCompatActivity {
Button tButton,bfs;
EditText inText,outText,frend,kiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //language translotor codes english german object
        // Create an English-German translator:
//        FirebaseTranslatorOptions options =
//                new FirebaseTranslatorOptions.Builder()
//                        .setSourceLanguage(FirebaseTranslateLanguage.SW)
//                        .setTargetLanguage(FirebaseTranslateLanguage.FR)
//                        .build();
//        final FirebaseTranslator englishGermanTranslator =
//                FirebaseNaturalLanguage.getInstance().getTranslator(options);
//
//        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder()
//                .requireCharging() //requireWifi()
//                .build();
//        englishGermanTranslator.downloadModelIfNeeded(conditions)
//                .addOnSuccessListener(
//                        new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void v) {
                                // Model downloaded successfully. Okay to start translating.
                                // (Set a flag, unhide the translation UI, etc.)
//                            }
//                        })
//                .addOnFailureListener(
//                        new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
                                // Model couldn’t be downloaded or other internal error.
                                // ...
//                            }
//                        });
//code for from france to kiswahili

//        FirebaseTranslatorOptions opt =
//                new FirebaseTranslatorOptions.Builder()
//                        .setSourceLanguage(FirebaseTranslateLanguage.FR)
//                        .setTargetLanguage(FirebaseTranslateLanguage.SW)
//                        .build();
//        final FirebaseTranslator FranceSwahiliTranslator =
//                FirebaseNaturalLanguage.getInstance().getTranslator(opt);
//
//        FirebaseModelDownloadConditions cond = new FirebaseModelDownloadConditions.Builder()
//                .requireCharging() //.requireWifi()
//                .build();
//        FranceSwahiliTranslator.downloadModelIfNeeded(cond)
//                .addOnSuccessListener(
//                        new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void v) {
                                // Model downloaded successfully. Okay to start translating.
                                // (Set a flag, unhide the translation UI, etc.)
//                            }
//                        })
//                .addOnFailureListener(
//                        new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
                                // Model couldn’t be downloaded or other internal error.
                                // ...
//                            }
//                        });





        tButton=findViewById(R.id.translate);
        inText=findViewById(R.id.origText);
        outText=findViewById(R.id.FinText);
        frend=findViewById(R.id.frenchs);
        kiswa=findViewById(R.id.kis);
        bfs=findViewById(R.id.translatekf);
//        action event for button to translate from france to kiswahili
//        bfs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String intext=frend.getText().toString();
//                FranceSwahiliTranslator.translate(intext).addOnSuccessListener(new OnSuccessListener<String>() {
//                    @Override
//                    public void onSuccess(@NonNull String translatedtexts) {
//                       translation succeeded
//                        kiswa.setText(translatedtexts);
//                    }
//                })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                error occurred here
//                                Toast.makeText(getApplicationContext(),"error occurred in kis",Toast.LENGTH_LONG).show();
//                            }
//                        });
//            }
//        });
//        tButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text=inText.getText().toString();
//                englishGermanTranslator.translate(text)
//                        .addOnSuccessListener(
//                                new OnSuccessListener<String>() {
//                                    @Override
//                                    public void onSuccess(@NonNull String translatedText) {
                                        // Translation successful.
//                                        outText.setText(translatedText);
//                                    }
//                                })
//                        .addOnFailureListener(
//                                new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
                                        // Error.
                                        // ...
//                                        Toast.makeText(getApplicationContext(),"error occurred",Toast.LENGTH_LONG).show();
//                                    }
//                                });
//            }
//        });

    }
}


