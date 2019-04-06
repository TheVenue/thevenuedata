package presenters

import android.content.Context
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery
import views.MainActivity

class QueryDatabasePresenter(private val activity: MainActivity) {
    private val context: Context = activity.applicationContext
    var totalMemberCount: Int = 0
        private set
    var subscribedMemberCount: Int = 0
        private set
    var otherCount: Int = 0
        private set
    var wordOfMouthCount: Int = 0
        private set
    var facebookCount: Int = 0
        private set

    fun countTotalVsSubscribed() {
        totalMemberCount = 0
        subscribedMemberCount = 0
        val query = ParseQuery<ParseObject>("_User")
        query.findInBackground { objects, e ->
            if (e == null) {
                if (objects != null) {
                    for (obj in objects) {
                        totalMemberCount += 1
                        val tokenId = obj.getString("tokenId")
                        if (tokenId != null) {
                            if (tokenId != "") {
                                subscribedMemberCount += 1
                            }
                        }
                    }
                    activity.setTotalVsSubscribedTV()
                }
            } else
                makeToast(e.toString())
        }
    }

    fun countHowTheyFoundUs() {
        val query = ParseQuery<ParseObject>("How")
        query.findInBackground { objects, e ->
            if (e == null) {
                if (objects != null) {
                    for (`object` in objects) {
                        facebookCount = `object`.getInt("facebook")
                        wordOfMouthCount = `object`.getInt("word")
                        otherCount = `object`.getInt("other")
                    }
                    activity.setHowTheyFoundUsTV()
                }
            } else {
                makeToast(e.toString())
            }
        }
    }

    private fun makeToast(toString: String) {
        Toast.makeText(context, toString, Toast.LENGTH_LONG).show()
    }
}
