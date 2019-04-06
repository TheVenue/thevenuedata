package views

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.thevenuedata.R
import kotlinx.android.synthetic.main.activity_main.*
import presenters.QueryDatabasePresenter

class MainActivity : AppCompatActivity() {

    private var database: QueryDatabasePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = QueryDatabasePresenter(this)
        database!!.countTotalVsSubscribed()
        database!!.countHowTheyFoundUs()
    }

    fun setTotalVsSubscribedTV() {
        val output = ("How many members are there?\n${database!!.totalMemberCount}\n"
                + "\nHow many of them have subscribed?\n"
                + "${database!!.subscribedMemberCount}\n"
                + "\nWhat is the expected gross income in 30 days?\n"
                + "$${database!!.subscribedMemberCount * 5.09}\n"
                + "\nWhat is the total recurring expenses?\n $99.99\n")
        val primaryFont = Typeface.createFromAsset(assets, "fonts/EncodeSansExpanded-Thin.ttf")
        tv_members_info.typeface = primaryFont
        tv_members_info.text = output
    }

    fun setHowTheyFoundUsTV() {
        val output = ("The below data is how members found out about us.\n"
                + "\nFacebook: ${database!!.facebookCount}\n"
                + "Word of Mouth: ${database!!.wordOfMouthCount}\n"
                + "Other: ${database!!.otherCount}\n")
        val primaryFont = Typeface.createFromAsset(assets, "fonts/EncodeSansExpanded-Thin.ttf")
        tv_how_count.typeface = primaryFont
        tv_how_count.text = output
    }
}
