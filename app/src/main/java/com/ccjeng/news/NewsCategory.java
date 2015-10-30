package com.ccjeng.news;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsCategory extends AppCompatActivity {

    private static final String TAG = "NewsCategory";

    @Bind(R.id.tool_bar)
    Toolbar toolbar;
    @Bind(R.id.listView)
    ListView listView;

    private int sourceNumber;
    private String tabName;
    private String[] category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(new IconicsDrawable(this)
                .icon(CommunityMaterial.Icon.cmd_keyboard_backspace)
                .color(Color.WHITE)
                .actionBarSize());

        //get intent values
        Bundle bunde = this.getIntent().getExtras();
        sourceNumber = Integer.parseInt(bunde.getString("SourceNum"));
        String CategoryName = bunde.getString("SourceName");
        tabName = bunde.getString("SourceTab");

        //set toolbar title
        getSupportActionBar().setTitle(CategoryName);
        showResult(tabName, sourceNumber);
        //Log.d(TAG, tabName +" " + CategoryName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showResult(String tabName, int sourceNumber) {

        if (tabName.equals("TW")) {
            switch (sourceNumber) {
                case 0:
                    category = getResources().getStringArray(R.array.newscatsYahoo);
                    break;
                case 1:
                    category = getResources().getStringArray(R.array.newscatsUDN);
                    break;
                case 2:
                    category = getResources().getStringArray(R.array.newscatsPCHome);
                    break;
                case 3:
                    category = getResources().getStringArray(R.array.newscatsChinaTimes);
                    break;
                case 4:
                    category = getResources().getStringArray(R.array.newscatsNOW);
                    break;
                case 5:
                    category = getResources().getStringArray(R.array.newscatsEngadget);
                    break;
                case 6:
                    category = getResources().getStringArray(R.array.newscatsBNext);
                    break;
                case 7:
                    category = getResources().getStringArray(R.array.newscatsEttoday);
                    break;
                case 8:
                    category = getResources().getStringArray(R.array.newscatsCNYes);
                    break;
                case 9:
                    category = getResources().getStringArray(R.array.newscatsNewsTalk);
                    break;
                case 10:
                    category = getResources().getStringArray(R.array.newscatsLibertyTimes);
                    break;
                case 11:
                    category = getResources().getStringArray(R.array.newscatsAppDaily);
                    break;
            }
        } else if (tabName.equals("HK")) {
            switch (sourceNumber) {
                case 0:
                    category = getResources().getStringArray(R.array.newscatsHKAppleDaily);
                    /*Category appCategory = new Category();
	        		category = appCategory.getCategory();*/
                    break;
                case 1:
                    category = getResources().getStringArray(R.array.newscatsHKOrientalDaily);
                    break;
                case 2:
                    category = getResources().getStringArray(R.array.newscatsHKYahoo);
                    break;
                case 3:
                    category = getResources().getStringArray(R.array.newscatsHKYahooStGlobal);
                    break;
                case 4:
                    category = getResources().getStringArray(R.array.newscatsHKMing);
                    break;
                case 5:
                    category = getResources().getStringArray(R.array.newscatsHKYahooMing);
                    break;
                case 6:
                    category = getResources().getStringArray(R.array.newscatsHKEJ);
                    break;
                case 7:
                    category = getResources().getStringArray(R.array.newscatsHKMetro);
                    break;
                case 8:
                    category = getResources().getStringArray(R.array.newscatsHKsun);
                    break;
                case 9:
                    category = getResources().getStringArray(R.array.newscatsHKam730);
                    break;
                case 10:
                    category = getResources().getStringArray(R.array.newscatsHKheadline);
                    break;
            }
        }

        if (category != null) {
            listView.setAdapter(new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, category));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    /*
                    dialog = ProgressDialog.show(NewsCategory.this, "", getString(R.string.loading), true, true);
                    new Thread() {
                        public void run() {
                            try {
                                goIntent(position, category[position]);
                            } catch(Exception e) {
                                e.printStackTrace();
                            } finally {
                                dialog.dismiss();
                            }
                        }
                    }.start();
                    */
                }
            });


        }
    }

}