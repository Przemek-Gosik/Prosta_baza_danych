package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements PhoneListAdapter.OnItemClickListener {
    private PhoneViewModel mPhoneViewModel;
    private PhoneListAdapter mAdapter;

    ItemTouchHelper.SimpleCallback callback=new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Phone phone = (Phone) mPhoneViewModel.getAllPhones().getValue().get(position);
            mPhoneViewModel.delete(phone);
            Toast.makeText(MainActivity.this, getText(R.string.elementRem), Toast.LENGTH_SHORT).show();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton floatingActionButton = findViewById(R.id.fabMain);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        mAdapter = new PhoneListAdapter(this,this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);
        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        mPhoneViewModel.getAllPhones().observe(this,phones->{
            mAdapter.setPhoneList(phones);
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.pierwsza_opcja){
            //this.deleteDatabase("telefony");
            mPhoneViewModel.deleteAll();
            Toast.makeText(this, getText(R.string.dbclear), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemCLickListener(int position) {
        Bundle bundle = new Bundle();
        Phone phone = (Phone) mPhoneViewModel.getAllPhones().getValue().get(position);
        bundle.putLong("phoneID",phone.getMKolumna());
        bundle.putString("manuFac",phone.getMKolumna2());
        bundle.putString("model",phone.getMKolumna3());
        bundle.putString("android",phone.getMKolumna4());
        bundle.putString("webPage",phone.getMKolumna5());
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}