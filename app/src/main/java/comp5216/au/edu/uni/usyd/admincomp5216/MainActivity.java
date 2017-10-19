package comp5216.au.edu.uni.usyd.admincomp5216;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN = 1; // to readSignIn Result
    private static final int RC_PHOTO_PICKER =  2; // to readPhotoPicker Result

    private ListView mMessageListView;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;

    private String mUsername;


    private FirebaseDatabase mFirebaseDatabase; // this references to the firebase db
    private DatabaseReference mMessagesDatabaseReference; // reference to particular section of db
    private DatabaseReference mUserDatabaseReference;
    private ChildEventListener mChildEventListener; // a listener to listen to a particular section of db
    private FirebaseStorage mFirebaseStorage; // reference to firebase storage

    private FirebaseAuth mFirebaseAUth; // reference to firebase auth

    private StorageReference mChatPhotoStorageReference;
    private FirebaseAuth.AuthStateListener mAuthStateListener; // reference to authstatelistener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAUth = FirebaseAuth.getInstance();


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    // user is logged in
                    Toast.makeText(MainActivity.this, "You're signed in", Toast.LENGTH_SHORT).show();
                } else {
                    // user is logged out, so initate login activity
                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                Toast.makeText(this, "Sign in successful", Toast.LENGTH_SHORT).show();
            } else if (requestCode == RESULT_CANCELED){
                Toast.makeText(this, "Sign in cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                // sign out
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        // during pause, we don't need the login state
        if (mAuthStateListener != null){
            mFirebaseAUth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        // when it resumes, we want the login state
        mFirebaseAUth.addAuthStateListener(mAuthStateListener);
    }



    private void onSignedOutCleanup(){
        mUsername = ANONYMOUS;
    }

    public void goEvents(View v){
        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);
    }

    public void goDeployChat(View v){
        Intent intent = new Intent(this, ChatDeployActivity.class);
        startActivity(intent);
    }



}

