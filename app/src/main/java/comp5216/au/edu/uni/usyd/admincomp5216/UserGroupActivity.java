package comp5216.au.edu.uni.usyd.admincomp5216;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class UserGroupActivity extends AppCompatActivity {

    TextView mTextViewUsername;
    EditText mEditTextUserGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_group);

        User user = (User) getIntent().getSerializableExtra("user");
        Log.d("User ", user.toString());

        mTextViewUsername = (TextView)findViewById(R.id.txtViewUserName);
        mEditTextUserGroup = (EditText)findViewById(R.id.editTextGroup);

        mTextViewUsername.setText(user.getName());
        mEditTextUserGroup.setText(user.getGroup());

    }
}
