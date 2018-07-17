package com.test.showcase.androidshowcase.articleList;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.showcase.androidshowcase.R;
import com.test.showcase.androidshowcase.databinding.ArticleListItemBinding;
import com.test.showcase.data.model.ArticlePreviewModel;

import java.util.List;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ArticleItemViewHolder> {

    private List<ArticlePreviewModel> list;
    private IArticleListItemClicked callback;

    ArticlesListAdapter(List<ArticlePreviewModel> list, IArticleListItemClicked callback) {
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ArticleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ArticleListItemBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.article_list_item, parent, false);

        return new ArticleItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleItemViewHolder holder, int position) {
        ArticlePreviewModel model = list.get(position);
        ArticleListItemViewModel itemViewModel = new ArticleListItemViewModel(model);

        holder.bindViewModel(itemViewModel, callback);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void replaceData(List<ArticlePreviewModel> list) {
        this.list = list;

        //in more complex scenarios this is not desired. DiffUtil will be needed
        notifyDataSetChanged();
    }

    class ArticleItemViewHolder extends RecyclerView.ViewHolder{
        private ArticleListItemBinding binding;

        ArticleItemViewHolder(ArticleListItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bindViewModel(ArticleListItemViewModel viewModel, IArticleListItemClicked callback) {
            binding.setViewModel(viewModel);

            binding.cardView.setOnClickListener(v -> callback.onArticleClicked(viewModel.url));
        }
    }
}
