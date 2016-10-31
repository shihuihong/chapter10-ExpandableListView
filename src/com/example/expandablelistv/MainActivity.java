package com.example.expandablelistv;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	String[] type = new String[] { "我的好友", "大学同学", "亲戚朋友" };//定义组显示的文字
	String[][] info = new String[][] { { "张三", "张四", "张五" }, { "李四", "李斯" },
			{ "王五", "王六", "王二", "王三" } };//定义每一组的内容，注意：每一组项的个数可以不一致
	int[] groupImgs = new int[] { R.drawable.g1, R.drawable.g2, R.drawable.g3};//组的图标
	int[][] imgIds = new int[][] {
			{ R.drawable.a1, R.drawable.a2, R.drawable.a3 },
			{ R.drawable.a4, R.drawable.a5, R.drawable.a6 },
			{ R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10 } };//每一项的图标

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);//设置界面布局文件		
		//获取扩展下拉列表
		ExpandableListView myExpandable = (ExpandableListView) findViewById(R.id.myExpandable);
		//创建一个自定的下拉列表适配器，用于设置内容与显示之间的关系
		ExpandableListAdapter myAdapter = new BaseExpandableListAdapter() {

			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				return true;
			}//子项是否可以选择

			public boolean hasStableIds() {
				return false;
			}

			// 自己定义的一个获取TextView的方法
			private TextView getTextView() {
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);// 设置宽度和高度
				TextView textView = new TextView(MainActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL);// 文字水平居中
				textView.setTextSize(20);// 设置文字大小为20sp
				textView.setPadding(30, 0, 0, 0);
				textView.setTextColor(Color.BLACK);// 设置文本颜色
				return textView;
			}//获取文本显示框，自定义的

			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				LinearLayout layout = new LinearLayout(MainActivity.this);// 线性布局
				layout.setOrientation(LinearLayout.HORIZONTAL);// 设置线性布局方向
				layout.setGravity(Gravity.CENTER_VERTICAL);//设置垂直居中
				ImageView groupImg=new ImageView(MainActivity.this);//创建一个ImageView
				groupImg.setImageResource(groupImgs[groupPosition]);//设置ImageView的图片
				layout.addView(groupImg);//在线性布局中添加图片
				TextView textView = getTextView();//得到一个textView
				textView.setText(getGroup(groupPosition).toString());//设置TextView的显示内容
				layout.addView(textView);//在线性布局中添加textView
				return layout;//返回整个线性布局控件
			}//获取组显示控件

			public long getGroupId(int groupPosition) {

				return groupPosition;
			}//获取组的ID

			public int getGroupCount() {
				return type.length;
			}//获取组的个数

			public Object getGroup(int groupPosition) {
				return type[groupPosition];
			}//获取自定组对象

			public int getChildrenCount(int groupPosition) {
				return info[groupPosition].length;
			}//获取指定组的项数

			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				LinearLayout layout = new LinearLayout(MainActivity.this);// 线性布局
				layout.setOrientation(LinearLayout.HORIZONTAL);// 设置线性布局方向
				layout.setPadding(20, 0, 0, 0);//设置线性布局的左边距
				ImageView itemImage = new ImageView(MainActivity.this);//创建图片视图
				itemImage.setPadding(20, 0, 0, 0);//设置图片的左边距
				itemImage
						.setImageResource(imgIds[groupPosition][childPosition]);
				layout.addView(itemImage);//在线性布局中添加图片
				TextView textView = getTextView();//获取文本显示框
				textView.setText(getChild(groupPosition, childPosition)
						.toString());//设置文本显示框的显示内容
				layout.addView(textView);//在线性布局中添加文本显示框
				return layout;//返回线性布局
			}//获取自项显示控件

			public long getChildId(int groupPosition, int childPosition) {
				return childPosition;
			}//获取子项的ID

			public Object getChild(int groupPosition, int childPosition) {
				return info[groupPosition][childPosition];
			}//获取指定组中指定序号的项
		};
		myExpandable.setAdapter(myAdapter);//将适配器与扩展下拉列表关联起来
	}
}