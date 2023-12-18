package com.bookstore.bookstorecsdksibsapplication;

import static com.bookstore.bookstorecsdksibsapplication.R.layout.activity_book_detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_book_detail);

        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("selectedBook");

        updateUI(Objects.requireNonNull(book));
    }

    private void updateUI(Book book) {
        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(book.getTitle());

        TextView authorTextView = findViewById(R.id.authorTextView);
        authorTextView.setText(book.getAuthors().get(0));

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(book.getDescription());

        Button buyButton = createButton(book.getBuyLink());

        RelativeLayout layout = findViewById(R.id.parentLayout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.addRule(RelativeLayout.BELOW, R.id.descriptionTextView); // Adjust the rule as needed
        params.setMargins(0, 16, 0, 0); // Adjust margins as needed
        layout.addView(buyButton, params);
    }

    private Button createButton(final String buyLink) {
        Button button = new Button(this);
        button.setText("Buy");
        button.setOnClickListener(view -> openBuyLink(buyLink));
        return button;
    }

    private void openBuyLink(String buyLink) {
        // Implement the logic to open the buy link in a browser or any other appropriate action
        // Example using an implicit intent to open a web page:
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(buyLink));
        startActivity(intent);
    }

}
