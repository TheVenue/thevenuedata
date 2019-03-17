package presenters;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thevenuedata.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class QueryDatabase {
    private Activity activity;
    private Context context;
    private int total = 0;
    private int subscribed = 0;

    public QueryDatabase(Activity activity) {
        this.activity = activity;
        context = activity.getApplicationContext();
    }

    public int[] getTotalVsSubscribedCount() {
        return queryDatabaseForTotalVsSubscribed();
    }

    private int[] queryDatabaseForTotalVsSubscribed() {
        int[] result = new int[2];
        total = 0;
        subscribed = 0;

        ParseQuery<ParseObject> query = new ParseQuery<>("User");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects != null) {
                        for (ParseObject obj : objects) {
                            String tokenId = obj.getString("tokenId");
                            if (tokenId != null && !tokenId.equalsIgnoreCase("")) {
                                subscribed++;
                            } else
                                total++;
                        }
                        makeToast(String.valueOf(objects.size()));
                    }
                } else
                    makeToast(e.toString());
            }
        });
        result[0] = total;
        result[1] = subscribed;
        return result;
    }

    public void setCountOnHowTheyFoundUs() {
        ParseQuery<ParseObject> query = new ParseQuery<>("How");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects != null) {
                        for (ParseObject object : objects) {
                            int facebookCount = object.getInt("facebook");
                            int wordOfMouthCount = object.getInt("word");
                            int otherCount = object.getInt("other");
                            setHowCount(facebookCount, wordOfMouthCount, otherCount);
                        }
                    }
                }
                else {
                    makeToast(e.toString());
                }
            }

            private void setHowCount(Integer...ints) {
                TextView howTV = activity.findViewById(R.id.tv_how_count);
                String output = "The below data is how members found out about us.\n"
                        + "Facebook: " + ints[0] + "\n"
                        + "Word of Mouth: " + ints[1] + "\n"
                        + "Other: " + ints[2] + "\n";
                howTV.setText(output);
            }
        });
    }

    private void makeToast(String toString) {
        Toast.makeText(context, toString, Toast.LENGTH_LONG).show();
    }
}
