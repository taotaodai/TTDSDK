package com.ttd.sdk.utils.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 万能{@link ViewGroup}适配器
 *
 * @param <T>
 * @author wt 2018-03-24
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    private int mItemLayoutId;


    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public void setData(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        if (mDatas != null) {
            return mDatas.get(position);
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView, parent);

        convert(viewHolder, getItem(position), position, parent);
        return viewHolder.getConvertView();

    }

    /**
     * 为每个item添加数据、事件等
     * @param helper ViewHolder
     * @param item 一条数据
     * @param position
     * @param parent
     */
    protected abstract void convert(ViewHolder helper, T item, int position, ViewGroup parent);

    protected ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
    }

    /**
     * 控件管理器。
     * 主要实现对控件的复用，提高{@link ViewGroup}性能
     */
    public static class ViewHolder {
        private final SparseArray<View> mViews;
        private View mConvertView;
        private int position;

        private ViewHolder(Context context, ViewGroup parent, int layoutId,
                           int position) {
            this.mViews = new SparseArray<>();
            this.position = position;
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            //setTag
            mConvertView.setTag(this);

        }

        /**
         * @return
         * @deprecated 返回的下标有时候不正确，可以在重写{@link CommonAdapter}的convert时从参数中获得。
         */
        @Deprecated
        public int getPosition() {
            return position;
        }


        /**
         * 拿到一个ViewHolder对象
         *
         * @param context
         * @param convertView
         * @param parent
         * @param layoutId
         * @param position
         * @return
         */
        private static ViewHolder get(Context context, View convertView,
                                      ViewGroup parent, int layoutId, int position) {

            if (convertView == null) {
                return new ViewHolder(context, parent, layoutId, position);
            }
            return (ViewHolder) convertView.getTag();
        }


        /**
         * 通过控件的Id获取对应的控件，如果没有则加入views集合
         *
         * @param viewId
         * @return
         */
        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int viewId) {

            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * @return {@link ViewGroup }对应下标的视图
         */
        public View getConvertView() {
            return mConvertView;
        }

    }

}

