package comp5216.au.edu.uni.usyd.admincomp5216;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEventActivity extends AppCompatActivity {

    EditText mEventName;
    EditText mEventLocation;
    EditText mEventType;
    EditText mEventDesc;

    Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        mEventName = (EditText) findViewById(R.id.editTextEventName);
        mEventLocation = (EditText) findViewById(R.id.editTextEventLocation);
        mEventType = (EditText) findViewById(R.id.editTextEventType);
        mEventDesc = (EditText) findViewById(R.id.editTextEventDesc);

        mSubmitButton = (Button) findViewById(R.id.btnSubmit);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEventName.getText().toString();
                String locaiton = mEventLocation.getText().toString();
                String desc = mEventDesc.getText().toString();
                String type = mEventType.getText().toString();

                if(title != null && !title.isEmpty()){
                    Event event = new Event(title, locaiton, type, desc);
                    FirebaseDatabase.getInstance().getReference().child("Events").push().setValue(event);

                    Intent intent = new Intent(AddEventActivity.this, EventsActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(AddEventActivity.this, "You need to give a title", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}
