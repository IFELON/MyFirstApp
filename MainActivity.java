package in.suriya.recyclerviewapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.textclassifier.TextLinks;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mrecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem>mExampleList;
    private RequestQueue mrequestqueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrecyclerView = findViewById(R.id.recycler_view);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mrequestqueue = Volley.newRequestQueue(this);
        ParseJson();
    }

    private void ParseJson() {
        String Url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject hits = jsonArray.getJSONObject(i);

                                String  CreatorName = hits.getString("user");
                                String  imageurl = hits.getString("webformatURL");
                                int likecount =hits.getInt("likes");

                                mExampleList.add(new ExampleItem(imageurl,CreatorName,likecount));
                            }
                            mExampleAdapter = new ExampleAdapter(MainActivity.this,mExampleList);
                            mrecyclerView.setAdapter(mExampleAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mrequestqueue.add(request);
    }
}
