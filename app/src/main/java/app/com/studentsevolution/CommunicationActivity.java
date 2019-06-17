package app.com.studentsevolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class CommunicationActivity extends AppCompatActivity implements CategoryAdapter.OnCateListener {

    private final String TAG ="CommunicationActivity";

    RecyclerView comRecycle;
    ArrayList<String> comCategoryList;
    TextView title;
    CategoryAdapter adapter;
    DatabaseReference myref;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        progressBar = findViewById(R.id.comProgress);
        comRecycle = findViewById(R.id.comRecylce);
        title = findViewById(R.id.title);

        comRecycle.setLayoutManager(new LinearLayoutManager(this));
        comRecycle.setItemAnimator(new DefaultItemAnimator());

        showProgressBar();
        FirebaseApp.initializeApp(this);
        myref = FirebaseDatabase.getInstance().getReference().child("Communication");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comCategoryList = new ArrayList<>();
                comCategoryList.add((String) dataSnapshot.child("cat1").getValue());
                comCategoryList.add((String) dataSnapshot.child("cat2").getValue());
                comCategoryList.add((String) dataSnapshot.child("cat3").getValue());
                comCategoryList.add((String) dataSnapshot.child("cat4").getValue());
                comCategoryList.add((String) dataSnapshot.child("cat5").getValue());
                comCategoryList.add((String) dataSnapshot.child("cat6").getValue());
                adapter = new CategoryAdapter(comCategoryList, CommunicationActivity.this);
                comRecycle.setAdapter(adapter);
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
        Log.d(TAG,"cat "+position+" Clicked");
        if(position==0) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLWPirh4EWFpFIElSxplDlEhRDZHkBD-0n");
            intent.putExtra("videoName","Soft Skills");
            startActivity(intent);
        }
        else if(position==1) {
            Intent intent = new Intent(getBaseContext(), SpokenEnglishActivity.class);
            startActivity(intent);
        }
        else if(position==2) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLXIe9q-cTnIbhspG_5U_nhrhr0PGVe3le");
            intent.putExtra("videoName","Verbal & Non-Verbal Communication");
            startActivity(intent);
        }
        else if(position==3) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLXIe9q-cTnIbRhRgLN7Z9vgd-kK8xLJe2");
            intent.putExtra("videoName","Written Communication");
            startActivity(intent);
        }
        else if(position==4) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PL5bLw9Uguvv11fx6bS68yK5OeXAqLU3mW");
            intent.putExtra("videoName","Listening Skills");
            startActivity(intent);
        }
        else if(position==5) {
            Intent intent = new Intent(getBaseContext(), VideoActivity.class);
            intent.putExtra("playlistId","PLWc1yfTYfqNFfeyUbhfUXY1LJrBGiPeGL");
            intent.putExtra("videoName","Personality Development");
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"Wrong item Clicked!",Toast.LENGTH_SHORT).show();
        }
    }
}

