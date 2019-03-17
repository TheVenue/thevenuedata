package views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.thevenuedata.R;

import presenters.QueryDatabase;

public class MainActivity extends AppCompatActivity {

    private TextView membersTV;
    private QueryDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        membersTV = findViewById(R.id.members);
        database = new QueryDatabase(this);

        setMembersTv();
        database.setCountOnHowTheyFoundUs();
    }

    private void setMembersTv() {
        int[] membersTotalVsSubscribed = database.getTotalVsSubscribedCount();
        membersTV.setText("The total number of accounts are: " + membersTotalVsSubscribed[0]
                        + "\nThe total number of paid subscribers are: " + membersTotalVsSubscribed[1]);
    }
}
