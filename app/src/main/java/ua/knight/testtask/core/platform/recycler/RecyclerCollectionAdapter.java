package ua.knight.testtask.core.platform.recycler;

import android.view.View;

import java.util.Collection;

public interface RecyclerCollectionAdapter<M> {

    void clear();

    void setOnItemClickListener(OnItemClickListener<M> onItemClickListener);

    void clearAndAddAll(Collection<M> data);

    void addAll(Collection<M> data);

    void addItem(M item);

    void addItem(int position, M item);

    void updateItem(M item);

    void removeItem(M item);

    void removeItem(int position);

    void restoreItem(M item, int position);

    M getItem(int position);

    Collection<M> getAll();

    boolean isEmpty();

    interface OnItemClickListener<M> {
        void onItemClick(M item);
        void onItemClick(View view);
        void onItemClick(M item, View view);
    }

}
