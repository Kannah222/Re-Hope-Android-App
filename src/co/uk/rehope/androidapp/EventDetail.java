package co.uk.rehope.androidapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventDetail extends Activity {

	private String eventTitle = "";
	private String eventContent = "";
	private String eventImageURL = "";
	private String eventCityURL = "";
	private String eventDate = "";
	private DrawableManager dm;
	private Typeface tf;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		tf = Typeface.createFromAsset(getAssets(), "fonts/ProximaNovaAlt-Reg.ttf");

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			eventTitle = extras.getString("eventTitle");
			eventContent = extras.getString("eventContent");
			eventImageURL = extras.getString("eventImageURL");
			eventCityURL = extras.getString("eventCityURL");
			eventDate = extras.getString("eventDate");
		}

		setTitle("Re:Hope - " + eventTitle);

		TextView title = (TextView) findViewById(R.id.detail_title);
		title.setTypeface(tf);
		title.setText(eventTitle);

		TextView content = (TextView) findViewById(R.id.detail_content);
		content.setTypeface(tf);
		content.setText(eventContent);

		SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = curFormater.parse(eventDate);
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM");
			String monthName = dateFormat.format(date);
			dateFormat = new SimpleDateFormat("dd");
			String dayName = dateFormat.format(date);
			TextView day = (TextView) findViewById(R.id.detail_day);
			day.setTypeface(tf);
			day.setText(dayName);
			TextView month = (TextView) findViewById(R.id.detail_month);
			month.setTypeface(tf);
			month.setText(monthName);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dm = new DrawableManager();
		ImageView header = (ImageView) findViewById(R.id.header);
		dm.fetchDrawableOnThread(eventImageURL, header);

		
		Button cityButton = (Button) findViewById(R.id.city_button);
		cityButton.setVisibility(View.GONE);
		/*// not currently using the city
		cityButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {

				Bundle bundle = new Bundle();
				bundle.putString("cityURL", eventCityURL);
				Intent mIntent = new Intent();
				mIntent.putExtras(bundle);
				setResult(RESULT_OK, mIntent);
				finish();

			}
		}); */

	}

}
