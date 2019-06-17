package app.com.studentsevolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TechnicalActivity extends AppCompatActivity implements CategoryAdapter.OnCateListener {

    RecyclerView TechRecycle;
    ArrayList<String> TechCategoryList;
    TextView title;
    CategoryAdapter adapter;
    DatabaseReference myref;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical);

        progressBar = findViewById(R.id.techProgress);

        TechRecycle = findViewById(R.id.TechRecylce);
        title = findViewById(R.id.title);

        TechRecycle.setLayoutManager(new LinearLayoutManager(this));
        TechRecycle.setItemAnimator(new DefaultItemAnimator());

        showProgressBar();
        FirebaseApp.initializeApp(this);
        myref = FirebaseDatabase.getInstance().getReference().child("Technical");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TechCategoryList = new ArrayList<>();
                TechCategoryList.add((String) dataSnapshot.child("cat1").getValue());
                TechCategoryList.add((String) dataSnapshot.child("cat2").getValue());
                TechCategoryList.add((String) dataSnapshot.child("cat3").getValue());
                TechCategoryList.add((String) dataSnapshot.child("cat4").getValue());
                TechCategoryList.add((String) dataSnapshot.child("cat5").getValue());
                TechCategoryList.add((String) dataSnapshot.child("cat6").getValue());
                TechCategoryList.add((String) dataSnapshot.child("cat7").getValue());
                TechCategoryList.add((String) dataSnapshot.child("cat8").getValue());
                TechCategoryList.add((String) dataSnapshot.child("cat9").getValue());
                TechCategoryList.add((String) dataSnapshot.child("cat10").getValue());
                adapter = new CategoryAdapter(TechCategoryList, TechnicalActivity.this);
                TechRecycle.setAdapter(adapter);
                hideProgressBar();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                System.out.println("Failed to read value.");

            }
        });
        
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void OnCateClick(int position) {
        if(position==0) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLfVsf4Bjg79CZ5kHTiQHcm-l2q8j06ofd");
            intent.putExtra("videoName","C programming");
            startActivity(intent);
        }
        else if(position==1) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLfVsf4Bjg79Cu5MYkyJ-u4SyQmMhFeC1C");
            intent.putExtra("videoName","C++ programming");
            startActivity(intent);
        }
        else if(position==2) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLd3UqWTnYXOmx_J1774ukG_rvrpyWczm0");
            intent.putExtra("videoName","Java programming");
            startActivity(intent);
        }
        else if(position==3) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLd3UqWTnYXOmzcSdWIh-EggqAtCXvJxzu");
            intent.putExtra("videoName","Python programming");
            startActivity(intent);
        }
        else if(position==4) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PL6cactdCCnTLkQah9GKzsJmiLbegy4dEk");
            intent.putExtra("videoName","Web Development HTML/CSS/JS");
            startActivity(intent);
        }
        else if(position==5) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLFgUdubu2ofjuWm14mwzddzKTo5gqYvB3");
            intent.putExtra("videoName","PHP & MySQL");
            startActivity(intent);
        }
        else if(position==6) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLd3UqWTnYXOk6NjwymS0DIaZH4GnLGf1p");
            intent.putExtra("videoName","Java Advance");
            startActivity(intent);
        }
        else if(position==7) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLd3UqWTnYXOkzPunQOObl4m_7i6aOIoQD");
            intent.putExtra("videoName","Python Advance");
            startActivity(intent);
        }
        else if(position==8) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLd3UqWTnYXOmiyL-GfyUwSt4agV20-h3-");
            intent.putExtra("videoName","Web Development Django");
            startActivity(intent);
        }
        else if(position==9) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PL3pGy4HtqwD02GVgM96-V0sq4_DSinqvf");
            intent.putExtra("videoName","Data Structure & Algorithms");
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Wrong item clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}
