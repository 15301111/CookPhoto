package com.example.morphtin.dishes.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morphtin.dishes.R;

import java.util.List;
import java.util.Map;

public class DynamicsAdapter extends BaseAdapter {
    private List<Map<String, Object>> dataList;
    private Context context;
    private int resource;

    /**
     * 有参构造
     *
     * @param context
     *            界面
     * @param dataList
     *            数据
     * @param resource
     *            列表项资源文件
     */
    public DynamicsAdapter(Context context, List<Map<String, Object>> dataList,
                           int resource) {
        this.context = context;
        this.dataList = dataList;
        this.resource = resource;

    }

    @Override
    public int getCount() {

        return dataList.size();
    }

    @Override
    public Object getItem(int index) {

        return dataList.get(index);
    }

    @Override
    public long getItemId(int index) {

        return index;
    }

    @Override
    public View getView(int index, View view, ViewGroup arg2) {
        // 声明内部类
        Util util = null;
        // 中间变量
        final int flag = index;
        /**
         * 根据listView工作原理，列表项的个数只创建屏幕第一次显示的个数。
         * 之后就不会再创建列表项xml文件的对象，以及xml内部的组件，优化内存，性能效率
         */
        if (view == null) {
            util = new Util();
            // 给xml布局文件创建java对象
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
            // 指向布局文件内部组件
            util.contentTextView = (TextView) view
                    .findViewById(R.id.item_content);
            util.dateTextView = (TextView) view.findViewById(R.id.item_date);
            util.titleTextView = (TextView) view.findViewById(R.id.item_title);
            util.imageView = (ImageView) view.findViewById(R.id.item_img);
            util.infoButton = (Button) view.findViewById(R.id.button_info);
            util.deleteButton = (Button) view.findViewById(R.id.button_delete);
            // 增加额外变量
            view.setTag(util);
        } else {
            util = (Util) view.getTag();
        }
        // 获取数据显示在各组件
        Map<String, Object> map = dataList.get(index);
        util.contentTextView.setText((String) map.get("content"));
        util.dateTextView.setText((String) map.get("date"));
        util.titleTextView.setText((String) map.get("title"));
        util.imageView.setImageResource((Integer) map.get("img"));

        // 删除按钮，添加点击事件
        util.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dataList.remove(flag);
                notifyDataSetChanged();
                Map<String, Object> map = dataList.get(flag);
                String str = "已删除\n标题：" + map.get("title") + "\n内容："
                        + map.get("content") + "\n日期：" + map.get("date");
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            }
        });
        // 详情按钮，添加点击事件
        util.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Map<String, Object> map = dataList.get(flag);
                String str = "标题：" + map.get("title") + "\n内容："
                        + map.get("content") + "\n日期：" + map.get("date");
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}

/**
 * 内部类，用于辅助适配
 *
 * @author qiangzi
 *
 */
class Util {
    ImageView imageView;
    TextView contentTextView, dateTextView, titleTextView;
    Button infoButton, deleteButton;

}
