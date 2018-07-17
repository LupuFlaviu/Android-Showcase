package com.test.showcase.androidshowcase.articleList;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.test.showcase.androidshowcase.R;
import com.test.showcase.androidshowcase.databinding.ArticlesListActivityBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ArticlesListActivity extends DaggerAppCompatActivity {

    @Inject ViewModelProvider.Factory viewModelFactory;
    private ArticleListViewModel viewModel;
    private ArticlesListActivityBinding binding;
    private ArticlesListAdapter adapter;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.articles_list_activity);

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ArticleListViewModel.class);

        binding.setViewModel(viewModel);

        prepareRecyclerView();
        prepareSectionsSpinner();

        viewModel.articleList.observe(this, list -> adapter.replaceData(list));
    }

    private void prepareRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArticlesListAdapter(viewModel.articleList.getValue(), this::goToArticle);
        binding.recyclerView.setAdapter(adapter);
    }

    private void prepareSectionsSpinner() {

        ArrayAdapter<String> sectionsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, viewModel.articleSections);
        sectionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.articleSectionsSpinner.setAdapter(sectionsAdapter);
        binding.articleSectionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setArticleSectionPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void goToArticle(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == null) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.filterMenuItem:
                viewModel.toggleFilterZoneVisibility();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
