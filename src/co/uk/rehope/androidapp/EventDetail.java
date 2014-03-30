package co.uk.rehope.androidapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.Typefaces;

public class EventDetail extends Activity {

	private String eventTitle = "";
	private String eventContent = "";
	private String eventImageURL = "";
	private String eventCityURL = "";
	private String eventDate = "";
	private DrawableManager dm;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);

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
		title.setTypeface(Typefaces.get(this, "fonts/ProximaNovaAlt-Reg.ttf"));
		title.setText(eventTitle);

		TextView content = (TextView) findViewById(R.id.detail_content);
		content.setTypeface(Typefaces.get(this, "fonts/ProximaNovaAlt-Reg.ttf"));
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
			day.setTypeface(Typefaces.get(this, "fonts/ProximaNovaAlt-Reg.ttf"));
			day.setText(dayName);
			TextView month = (TextView) findViewById(R.id.detail_month);
			month.setTypeface(Typefaces.get(this, "fonts/ProximaNovaAlt-Reg.ttf"));
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
