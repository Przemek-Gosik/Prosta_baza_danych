package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private PhoneViewModel mPhoneViewModel;
    private boolean whichMode;

    long ID;
    String kol2,kol3,kol4 ,kol5;
    Button saveButton,cancelButton,webPageButton;
    EditText producent,model,wersjaAndroida,stronaWww;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        producent=findViewById(R.id.edycja1);
        model=findViewById(R.id.edycja2);
        wersjaAndroida=findViewById(R.id.edycja3);
        stronaWww=findViewById(R.id.edycja4);
        saveButton= findViewById(R.id.zapisz);
        cancelButton=findViewById(R.id.anuluj);
        webPageButton=findViewById(R.id.stronawww);


        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);
        Bundle bundle = getIntent().getExtras();

        if(bundle !=null){
            Toast.makeText(this, "EEEEE", Toast.LENGTH_SHORT).show();
            ID= bundle.getLong("phoneID");
            kol2 = bundle.getString("manuFac");
            kol3 = bundle.getString("model");
            kol4 = bundle.getString("android");
            kol5 = bundle.getString("webPage");
            producent.setText(kol2);
            model.setText(kol3);
            wersjaAndroida.setText(kol4);
            stronaWww.setText(kol5);
            setWhichMode(true);
        }else{
            setWhichMode(false);
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tekstValidation(producent) && tekstValidation(model) && tekstValidation(stronaWww) && tekstValidation(wersjaAndroida)) {
                    if(isWhichMode()){
                        Phone phone =(Phone) createNewPhone2(ID);
                        mPhoneViewModel.update(phone);

                    }else {

                        mPhoneViewModel.insert(createNewPhone());

                    }
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity2.this, getText(R.string.error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });
        webPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String adres = stronaWww.getText().toString();
                    if(!adres.startsWith("http://")){
                        adres = "http://"+adres;
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(adres));
                    startActivity(intent);
            }
        });
        producent.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b){
                if(!b){
                    if(!tekstValidation(producent)){
                        producent.setError(getText(R.string.manuError));
                    }
                }
            }
        });
        model.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if(!tekstValidation(model)){
                        model.setError(getText(R.string.modelError));
                    }
                }
            }
        });
        wersjaAndroida.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if(!tekstValidation(wersjaAndroida)){
                        wersjaAndroida.setError(getText(R.string.andError));
                    }
                }
            }
        });
        stronaWww.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if(!tekstValidation(stronaWww)){
                        stronaWww.setError(getText(R.string.webError));
                    }
                }
            }
        });



    }
    protected Phone createNewPhone(){
        String tekst1,tekst2,tekst3,tekst4;
        tekst1=producent.getText().toString();
        tekst2=model.getText().toString();
        tekst3=wersjaAndroida.getText().toString();
        tekst4=stronaWww.getText().toString();
        Phone phone=new Phone(tekst1,tekst2,tekst3,tekst4);
        return phone;
    }
    protected Phone createNewPhone2(long ID){
        String tekst1,tekst2,tekst3,tekst4;
        tekst1=producent.getText().toString();
        tekst2=model.getText().toString();
        tekst3=wersjaAndroida.getText().toString();
        tekst4=stronaWww.getText().toString();
        Phone phone=new Phone(ID,tekst1,tekst2,tekst3,tekst4);
        return phone;
    }

    protected boolean tekstValidation(EditText editText){
        if(editText.getText().toString().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public boolean isWhichMode() {
        return whichMode;
    }

    public void setWhichMode(boolean whichMode) {
        this.whichMode = whichMode;
    }

}