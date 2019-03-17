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

import views.MainActivity;

public class QueryDatabasePresenter {
    private MainActivity activity;
    private Context context;
    private int totalMemberCount;
    private int subscribedMemberCount;
    private int otherCount;
    private int wordOfMouthCount;
    private int facebookCount;

    public QueryDatabasePresenter(MainActivity activity) {
        this.activity = activity;
        context = activity.getApplicationContext();
    }

    public void countTotalVsSubscribed() {
        totalMemberCount = 0;
        subscribedMemberCount = 0;
        ParseQuery<ParseObject> query = new ParseQuery<>("_User");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects != null) {
                        for (ParseObject obj : objects) {
                            totalMemberCount += 1;
                            String tokenId = obj.getString("tokenId");
                            if (tokenId != null) {
                                if (!tokenId.equalsIgnoreCase("")) {
                                    subscribedMemberCount += 1;
                                }
                            }
                        }
                        activity.setTotalVsSubscribedTV();
                    }
                } else
                    makeToast(e.toString());
            }
        });
    }

    public void countHowTheyFoundUs() {
        ParseQuery<ParseObject> query = new ParseQuery<>("How");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects != null) {
                        for (ParseObject object : objects) {
                            facebookCount = object.getInt("facebook");
                            wordOfMouthCount = object.getInt("word");
                            otherCount = object.getInt("other");
                        }
                        activity.setHowTheyFoundUsTV();
                    }
                } else {
                    makeToast(e.toString());
                }
            }
        });
    }

    private void makeToast(String toString) {
        Toast.makeText(context, toString, Toast.LENGTH_LONG).show();
    }

    public int getTotalMemberCount() {
        return totalMemberCount;
    }

    public int getSubscribedMemberCount() {
        return subscribedMemberCount;
    }

    public int getOtherCount() {
        return otherCount;
    }

    public int getWordOfMouthCount() {
        return wordOfMouthCount;
    }

    public int getFacebookCount() {
        return facebookCount;
    }
}
