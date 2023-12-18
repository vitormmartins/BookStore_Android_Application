package com.bookstore.bookstorecsdksibsapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Book> data;

    public BookAdapter(Context context, int layoutResourceId, ArrayList<Book> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        BookHolder holder;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new BookHolder();
            holder.titleTextView = row.findViewById(R.id.titleTextView);
            holder.thumbnailImageView = row.findViewById(R.id.thumbnailImageView);

            row.setTag(holder);
        } else {
            holder = (BookHolder) row.getTag();
        }


        Book book = data.get(position);

        holder.titleTextView.setText(book.getTitle());

        Picasso.get().load(book.getThumbnailUrl()).into(holder.thumbnailImageView);

        assert convertView != null;
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BookDetailActivity.class);
            intent.putExtra("selectedBook", (CharSequence) getItem(position));
            getContext().startActivity(intent);
        });

        return convertView;
    }

    static class BookHolder {
        TextView titleTextView;
        ImageView thumbnailImageView;
    }
}

