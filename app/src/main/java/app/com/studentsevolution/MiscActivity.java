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

public class MiscActivity extends AppCompatActivity implements CategoryAdapter.OnCateListener {

    RecyclerView MiscRecycle;
    ArrayList<String> MiscCategoryList;
    TextView title;
    CategoryAdapter adapter;
    DatabaseReference myref;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misc);

        progressBar= findViewById(R.id.miscProgress);

        MiscRecycle = findViewById(R.id.MiscRecylce);
        title = findViewById(R.id.title);

        MiscRecycle.setLayoutManager(new LinearLayoutManager(this));
        MiscRecycle.setItemAnimator(new DefaultItemAnimator());

        showProgressBar();
        FirebaseApp.initializeApp(this);
        myref = FirebaseDatabase.getInstance().getReference().child("Misc");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MiscCategoryList = new ArrayList<>();
                MiscCategoryList.add((String) dataSnapshot.child("cat1").getValue());
                MiscCategoryList.add((String) dataSnapshot.child("cat2").getValue());
                MiscCategoryList.add((String) dataSnapshot.child("cat3").getValue());
                MiscCategoryList.add((String) dataSnapshot.child("cat4").getValue());
                MiscCategoryList.add((String) dataSnapshot.child("cat5").getValue());
                MiscCategoryList.add((String) dataSnapshot.child("cat6").getValue());
                MiscCategoryList.add((String) dataSnapshot.child("cat7").getValue());
                adapter = new CategoryAdapter(MiscCategoryList, MiscActivity.this);
                MiscRecycle.setAdapter(adapter);
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
            intent.putExtra("playlistId","PLJwZDT12ePodJJAhY9yHjpG08h_Hf5MNb");
            intent.putExtra("videoName","Group Discussion");
            startActivity(intent);
        }
        else if(position==1) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLnHpoGzKoRE6FevmjwSC6sYpTj3EuugVr");
            intent.putExtra("videoName","Personal Interview");
            startActivity(intent);
        }
        else if(position==2) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLUKRqQ8cSB-DCDuEl5vYogIMH9ph-7OP7");
            intent.putExtra("videoName"," English Motivational Videos");
            startActivity(intent);
        }
        else if(position==3) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLy6cehdZn4Ka5m1cy-xT6afwVaJwBzm6P");
            intent.putExtra("videoName","English Stories");
            startActivity(intent);
        }
        else if(position==4) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PL2Pb6OtEXTfgWXx5kqjQL9-N7pjl_1k_p");
            intent.putExtra("videoName","Current Affairs");
            startActivity(intent);
        }
        else if(position==5) {
            Intent intent = new Intent(getBaseContext(), InterviewActivity.class);
            startActivity(intent);
        }
        else if(position==6) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLOXPTf9C9_jIff-QxN2TtcHeGSZCxb-6-");
            intent.putExtra("videoName","Hindi Motivational Videos");
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Wrong item clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}
