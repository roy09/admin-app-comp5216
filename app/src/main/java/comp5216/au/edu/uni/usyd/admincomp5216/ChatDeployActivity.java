package comp5216.au.edu.uni.usyd.admincomp5216;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatDeployActivity extends AppCompatActivity {

    ListView mUsersListView;
    UserAdapter mUserAdapter;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mUserDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_deploy);

        mFirebaseDatabase = FirebaseDatabase.getInstance();


        mUsersListView = (ListView) findViewById(R.id.listview);

        final List<User> usersList = new ArrayList<>();
        mUserAdapter = new UserAdapter(this, R.layout.item_user, usersList);
        mUsersListView.setAdapter(mUserAdapter);

        mUserDatabaseReference = mFirebaseDatabase.getReference().child("Users");
        mUserDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot user : dataSnapshot.getChildren()) {
                    User userObject = user.getValue(User.class);
                    usersList.add(userObject);
                    mUserAdapter.notifyDataSetChanged();
                    Log.d("User", userObject.getName());

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mUsersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                User user = usersList.get(position);
                Log.d("USR", user.getName());

                Intent intent = new Intent(ChatDeployActivity.this, UserGroupActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });


    }
}
