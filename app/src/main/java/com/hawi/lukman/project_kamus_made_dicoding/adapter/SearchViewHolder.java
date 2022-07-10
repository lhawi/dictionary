package com.hawi.lukman.project_kamus_made_dicoding.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hawi.lukman.project_kamus_made_dicoding.DetailActivity;
import com.hawi.lukman.project_kamus_made_dicoding.R;
import com.hawi.lukman.project_kamus_made_dicoding.model.KamusModel;

class SearchViewHolder extends RecyclerView.ViewHolder {

    TextView tvWord, tvTranslate;

    public SearchViewHolder(View itemView) {
        super(itemView);

        tvWord = itemView.findViewById(R.id.tvWord);
        tvTranslate = itemView.findViewById(R.id.tvTranslate);
    }

    public void bind(final KamusModel kamusModel) {
        tvWord.setText(kamusModel.getKata());
        tvTranslate.setText(kamusModel.getDeskripsi());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.ITEM_VOCABULARY, kamusModel.getKata());
                intent.putExtra(DetailActivity.ITEM_ARTI, kamusModel.getDeskripsi());
                intent.putExtra(DetailActivity.ITEM_CATEGORY, kamusModel.getCategory());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
