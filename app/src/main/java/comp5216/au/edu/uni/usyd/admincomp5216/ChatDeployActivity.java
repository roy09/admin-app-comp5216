package comp5216.au.edu.uni.usyd.admincomp5216;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChatDeployActivity extends AppCompatActivity {

    ListView mUsersListView;
    UserAdapter mUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_deploy);

        mUsersListView = (ListView) findViewById(R.id.listview);

        List<User> usersList = new ArrayList<>();
        usersList.add(new User("Jay", "image", "status", "thumb_image"));


        mUserAdapter = new UserAdapter(this, R.layout.item_user, usersList);
        mUsersListView.setAdapter(mUserAdapter);


    }
}
