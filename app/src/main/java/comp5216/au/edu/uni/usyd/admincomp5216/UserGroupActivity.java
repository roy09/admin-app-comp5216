package comp5216.au.edu.uni.usyd.admincomp5216;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserGroupActivity extends AppCompatActivity {

    TextView mTextViewUsername;
    EditText mEditTextUserGroup;
    Button mSubmitButton;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_group);

        user = (User) getIntent().getSerializableExtra("user");
        Log.d("User ", user.toString());

        mTextViewUsername = (TextView)findViewById(R.id.txtViewUserName);
        mEditTextUserGroup = (EditText)findViewById(R.id.editTextGroup);
        mSubmitButton = (Button) findViewById(R.id.btnSubmit);

        mTextViewUsername.setText(user.getName());
        mEditTextUserGroup.setText(user.getGroup());

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mUserReference = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
                mUserReference.child("group").setValue(mEditTextUserGroup.getText().toString());

            }
        });

    }
}
