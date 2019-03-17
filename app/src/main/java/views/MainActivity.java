package views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.thevenuedata.R;

import presenters.QueryDatabasePresenter;

public class MainActivity extends AppCompatActivity {

    private QueryDatabasePresenter database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new QueryDatabasePresenter(this);
        database.countTotalVsSubscribed();
        database.countHowTheyFoundUs();
    }
    public void setTotalVsSubscribedTV() {
        TextView membersTV = findViewById(R.id.tv_total_vs_subscribed);
        String output = "There are currently " + database.getTotalMemberCount()
                + " members." + "\n" + database.getSubscribedMemberCount()
                + " of those have subscribed.";
        membersTV.setText(output);
    }

    public void setHowTheyFoundUsTV() {
        TextView howTV = findViewById(R.id.tv_how_count);
        String output = "The below data is how members found out about us.\n"
                + "Facebook: " + database.getFacebookCount() + "\n"
                + "Word of Mouth: " + database.getWordOfMouthCount() + "\n"
                + "Other: " + database.getOtherCount() + "\n";
        howTV.setText(output);
    }
}
