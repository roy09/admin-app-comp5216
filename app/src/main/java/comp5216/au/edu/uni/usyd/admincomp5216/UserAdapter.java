package comp5216.au.edu.uni.usyd.admincomp5216;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_user, parent, false);
        }

        TextView userName = (TextView) convertView.findViewById(R.id.txtViewName);
        TextView userGroup = (TextView) convertView.findViewById(R.id.txtViewGroup);

        User user = getItem(position);

        userName.setText(user.getName());
        userGroup.setText(user.getGroup());

        return convertView;
    }
}
