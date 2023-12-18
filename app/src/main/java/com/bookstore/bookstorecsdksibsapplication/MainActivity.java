package com.bookstore.bookstorecsdksibsapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bookstore.bookstorecsdksibsapplication.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String CA_BUNDLE;

    // Used to load the 'application' library on application startup.
    static {
        System.loadLibrary("application");
    }

    private ActivityMainBinding binding;
    private BookAdapter bookAdapter;
    private ArrayList<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize book list and adapter
        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(this, R.layout.book_list_item, bookList);

        // Set up ListView with the adapter
        ListView listView = binding.listView;
        listView.setAdapter(bookAdapter);

        CurlHelper.copyCaBundleFromAssetsToInternalStorage(this);
        CA_BUNDLE = CurlHelper.getCaBundlePath(this);


        // Execute AsyncTask to fetch data from JNI
        new FetchDataAsyncTask().execute();
    }

    /**
     * A native method that is implemented by the 'application' native library,
     * which is packaged with this application.
     */
    public native String getDataFromJNI(String caBundlePath);


    // AsyncTask to fetch and display books
    private class FetchDataAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // Call the JNI method in the background thread
            return getDataFromJNI(CA_BUNDLE);
        }

        @Override
        protected void onPostExecute(String jsonString) {
            try {
                JSONObject object = new JSONObject(jsonString);
                JSONArray jsonArray  = object.getJSONArray("items");

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String title = jsonObject.getJSONObject("volumeInfo").getString("title");
                    String thumbnailUrl = jsonObject.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("smallThumbnail");

                    // Create a Book object and add it to the list
                    Book book = new Book(title, thumbnailUrl);
                    bookList.add(book);
                }

                bookAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
