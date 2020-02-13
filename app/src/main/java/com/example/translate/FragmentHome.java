package com.example.translate;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;


public class FragmentHome extends Fragment {
    private EditText inputText;
    private EditText outputText;
    private Button toFrenchButton;
    private Button toKiswahiliButton;
    private Button favouriteB;
    private Button historyB;
    public static SQLiteHelper sqLiteHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_home,container,false);
        inputText=view.findViewById(R.id.textToTranslate);
        outputText=view.findViewById(R.id.translatedText);
        toFrenchButton=view.findViewById(R.id.toFrench);
        toKiswahiliButton=view.findViewById(R.id.toKiswahili);
        favouriteB=view.findViewById(R.id.favourite);
//        creating sqlite helper object
        sqLiteHelper=new SQLiteHelper(getContext(),"LanguagesDB.sqlite",null,1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS history (sourceLanguage VARCHAR,targetLanguage VARCHAR)");
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS favourites (sourceLanguage VARCHAR,targetLanguage VARCHAR)");
        //creating swahili-french translator

        FirebaseTranslatorOptions opt =
                new FirebaseTranslatorOptions.Builder()
                        .setSourceLanguage(FirebaseTranslateLanguage.SW)
                        .setTargetLanguage(FirebaseTranslateLanguage.FR)
                        .build();
        final FirebaseTranslator SwahiliFrenchTranslator= FirebaseNaturalLanguage.getInstance().getTranslator(opt);
        FirebaseModelDownloadConditions cond=new FirebaseModelDownloadConditions.Builder()
                .requireWifi()
                .build();
        SwahiliFrenchTranslator.downloadModelIfNeeded(cond).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                swahili french  Model downloaded successfully
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                failed to download due to network problems or other errors in the device
           }
        });
//         creating FrenchSwahili translator
        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(FirebaseTranslateLanguage.FR)
                .setTargetLanguage(FirebaseTranslateLanguage.SW).build();
        final FirebaseTranslator FrenchSwahiliTranslator=FirebaseNaturalLanguage.getInstance().getTranslator(options);
        FirebaseModelDownloadConditions conditions=new FirebaseModelDownloadConditions.Builder()
                .requireWifi()
         .build();
        FrenchSwahiliTranslator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                 french swahili  model downloaded successfully
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                 model failed to download due to network problem or other errors in the device
            }
        });

//        setting onclickEvent listener for toFrenchButton
        toFrenchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=inputText.getText().toString();
                SwahiliFrenchTranslator.translate(s1).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String translatedTxt) {
//                         language translated successfully from kiswahili to french
                        outputText.setText(translatedTxt);
                        try {
                            sqLiteHelper.insertHistory(inputText.getText().toString(),translatedTxt.toString());
                            Toast.makeText(getContext(),"inserted into history",Toast.LENGTH_LONG).show();
                        }catch (Exception e){
                            Toast.makeText(getContext(),"failed to save to history\n"+e,Toast.LENGTH_LONG).show();
                            showErrorDialog();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                         error occured while translating the text
                        Toast.makeText(getContext(),"error occured\n"+e,Toast.LENGTH_LONG).show();
                    }
                });

            }
            private void showErrorDialog(){
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext(),R.style.AlertDialogTheme);
                View views=LayoutInflater.from(getContext()).inflate(R.layout.layout_error_dialog,
                        (ConstraintLayout)view.findViewById(R.id.layoutDialogContainer));
                builder.setView(views);
                ((TextView)views.findViewById(R.id.textTitle)).setText("Error ocurred");
                ((TextView)views.findViewById(R.id.textMessage)).setText("Cannot be translated");
                ((Button)views.findViewById(R.id.buttonAction)).setText(getResources().getString(R.string.okay));
                ((ImageView)views.findViewById(R.id.imageIcon)).setImageResource(R.drawable.error);
                final AlertDialog alertDialog=builder.create();
                views.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                if (alertDialog.getWindow() !=null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();

            }
        });
        //setting on Click listener for the toKiswahili Button
        toKiswahiliButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=inputText.getText().toString();
                FrenchSwahiliTranslator.translate(s1).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        //text translated successfully
                        outputText.setText(s);
                        try {
                            sqLiteHelper.insertHistory(inputText.getText().toString(),s.toString());
//
                            Toast.makeText(getContext(),"inserted into history",Toast.LENGTH_LONG).show();
                        }catch (Exception e){
                            Toast.makeText(getContext(),"failed to add into history\n"+e,Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // error occured while translatinng the text
                        Toast.makeText(getContext(),"error occurred\n"+e,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        favouriteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelper.insertFavourite(inputText.getText().toString(),outputText.getText().toString());
                    Toast.makeText(getContext(),"inserted into favourites",Toast.LENGTH_LONG).show();
                     showSuccessDialog();
                }catch (Exception e){
                    Toast.makeText(getContext(),"failed to add to favourites\n"+e,Toast.LENGTH_LONG).show();
                }
            }

            private void showSuccessDialog() {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext(),R.style.AlertDialogTheme);
                View views=LayoutInflater.from(getContext()).inflate(R.layout.layout_success_dialog,
                        (ConstraintLayout)view.findViewById(R.id.layoutDialogContainer));
                builder.setView(views);
                ((TextView)views.findViewById(R.id.textTitle)).setText(getResources().getString(R.string.success_title));
                ((TextView)views.findViewById(R.id.textMessage)).setText(getResources().getString(R.string.dummy_text));
                ((Button)views.findViewById(R.id.buttonAction)).setText(getResources().getString(R.string.okay));
                ((ImageView)views.findViewById(R.id.imageIcon)).setImageResource(R.drawable.success);
                final AlertDialog alertDialog=builder.create();
                views.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                if (alertDialog.getWindow() !=null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();

            }
        });
        return view;
    }




}


