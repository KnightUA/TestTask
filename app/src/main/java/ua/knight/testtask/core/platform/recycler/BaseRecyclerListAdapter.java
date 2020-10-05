package ua.knight.testtask.core.platform.recycler;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseRecyclerListAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements RecyclerCollectionAdapter<M> {

    protected final List<M> mList;
    protected OnItemClickListener<M> mOnItemClickListener;

    public BaseRecyclerListAdapter() {
        mList = new ArrayList<>();
    }

    public BaseRecyclerListAdapter(List<M> list) {
        mList = list;
    }

    public BaseRecyclerListAdapter(List<M> list, OnItemClickListener<M> onItemClickListener) {
        mList = list;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener<M> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public M getItem(int position) {
        return mList.get(position);
    }

    @Override
    public void clearAndAddAll(@Nullable Collection<M> data) {
        if (data == null) {
            if (getItemCount() == 0) notifyDataSetChanged();
            return;
        }
        mList.clear();

        for (M item : data) {
            addInternal(item);
        }

        notifyDataSetChanged();
    }

    @Override
    public void addAll(@Nullable Collection<M> data) {
        if (data == null) {
            return;
        }
        if (data.isEmpty()) {
            return;
        }
        for (M item : data) {
            addInternal(item);
        }

        int addedSize = data.size();
        int oldSize = mList.size() - addedSize;
        notifyItemRangeInserted(oldSize, addedSize);
    }

    @Override
    public void addItem(M item) {
        addInternal(item);

        notifyItemInserted(mList.size());
    }

    @Override
    public void addItem(int position, M item) {
        addInternal(position, item);

        notifyItemInserted(position);
    }

    @Override
    public void updateItem(M item) {
        // Swap the model
        int position = getItemPosition(item);
        if (position >= 0) {
            mList.remove(position);
            mList.add(position, item);
            notifyItemChanged(position);
        }
    }

    @Override
    public void removeItem(M item) {
        int position = getItemPosition(item);
        removeItem(position);
    }

    @Override
    public void removeItem(int position) {
        if (position >= 0) {
            mList.remove(position);
            notifyItemRemoved(position);
        }

        if (isEmpty()) clear();
    }

    @Override
    public void restoreItem(M item, int position) {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    @Override
    public Collection<M> getAll() {
        return mList;
    }

    @Override
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty() {
        return mList.isEmpty();
    }

    private int getItemPosition(M item) {
        for (int i = 0; i < mList.size(); i++) {
            M model = mList.get(i);
            if (model.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    private void addInternal(M item) {
        mList.add(item);
    }

    private void addInternal(int position, M item) {
        mList.add(position, item);
    }
}
