package com.bookstore.bookstorecsdksibsapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bookstore.bookstorecsdksibsapplication.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        TextView tv = binding.sampleText;
        // Call the JNI method and set the result to the TextView
        tv.setText(getDataFromJNI());

        // Initialize book list and adapter
        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(this, R.layout.book_list_item, bookList);

        // Set up ListView with the adapter
        ListView listView = binding.listView;
        listView.setAdapter(bookAdapter);

        // Fetch and display books
        fetchAndDisplayBooks();
    }

    /**
     * A native method that is implemented by the 'application' native library,
     * which is packaged with this application.
     */
    public native String getDataFromJNI();

    // Method to fetch and display books
    private void fetchAndDisplayBooks() {
        // Replace this JSON string with your actual data-fetching mechanism
        String jsonString = getDataFromJNI();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String thumbnailUrl = jsonObject.getString("thumbnailUrl");

                // Create a Book object and add it to the list
                Book book = new Book(title, thumbnailUrl);
                bookList.add(book);
            }

            // Notify the adapter that the data set has changed
            bookAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
