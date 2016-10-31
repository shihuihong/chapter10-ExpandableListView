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
	String[] type = new String[] { "�ҵĺ���", "��ѧͬѧ", "��������" };//��������ʾ������
	String[][] info = new String[][] { { "����", "����", "����" }, { "����", "��˹" },
			{ "����", "����", "����", "����" } };//����ÿһ������ݣ�ע�⣺ÿһ����ĸ������Բ�һ��
	int[] groupImgs = new int[] { R.drawable.g1, R.drawable.g2, R.drawable.g3};//���ͼ��
	int[][] imgIds = new int[][] {
			{ R.drawable.a1, R.drawable.a2, R.drawable.a3 },
			{ R.drawable.a4, R.drawable.a5, R.drawable.a6 },
			{ R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10 } };//ÿһ���ͼ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);//���ý��沼���ļ�		
		//��ȡ��չ�����б�
		ExpandableListView myExpandable = (ExpandableListView) findViewById(R.id.myExpandable);
		//����һ���Զ��������б�������������������������ʾ֮��Ĺ�ϵ
		ExpandableListAdapter myAdapter = new BaseExpandableListAdapter() {

			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				return true;
			}//�����Ƿ����ѡ��

			public boolean hasStableIds() {
				return false;
			}

			// �Լ������һ����ȡTextView�ķ���
			private TextView getTextView() {
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);// ���ÿ�Ⱥ͸߶�
				TextView textView = new TextView(MainActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL);// ����ˮƽ����
				textView.setTextSize(20);// �������ִ�СΪ20sp
				textView.setPadding(30, 0, 0, 0);
				textView.setTextColor(Color.BLACK);// �����ı���ɫ
				return textView;
			}//��ȡ�ı���ʾ���Զ����

			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				LinearLayout layout = new LinearLayout(MainActivity.this);// ���Բ���
				layout.setOrientation(LinearLayout.HORIZONTAL);// �������Բ��ַ���
				layout.setGravity(Gravity.CENTER_VERTICAL);//���ô�ֱ����
				ImageView groupImg=new ImageView(MainActivity.this);//����һ��ImageView
				groupImg.setImageResource(groupImgs[groupPosition]);//����ImageView��ͼƬ
				layout.addView(groupImg);//�����Բ��������ͼƬ
				TextView textView = getTextView();//�õ�һ��textView
				textView.setText(getGroup(groupPosition).toString());//����TextView����ʾ����
				layout.addView(textView);//�����Բ��������textView
				return layout;//�����������Բ��ֿؼ�
			}//��ȡ����ʾ�ؼ�

			public long getGroupId(int groupPosition) {

				return groupPosition;
			}//��ȡ���ID

			public int getGroupCount() {
				return type.length;
			}//��ȡ��ĸ���

			public Object getGroup(int groupPosition) {
				return type[groupPosition];
			}//��ȡ�Զ������

			public int getChildrenCount(int groupPosition) {
				return info[groupPosition].length;
			}//��ȡָ���������

			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				LinearLayout layout = new LinearLayout(MainActivity.this);// ���Բ���
				layout.setOrientation(LinearLayout.HORIZONTAL);// �������Բ��ַ���
				layout.setPadding(20, 0, 0, 0);//�������Բ��ֵ���߾�
				ImageView itemImage = new ImageView(MainActivity.this);//����ͼƬ��ͼ
				itemImage.setPadding(20, 0, 0, 0);//����ͼƬ����߾�
				itemImage
						.setImageResource(imgIds[groupPosition][childPosition]);
				layout.addView(itemImage);//�����Բ��������ͼƬ
				TextView textView = getTextView();//��ȡ�ı���ʾ��
				textView.setText(getChild(groupPosition, childPosition)
						.toString());//�����ı���ʾ�����ʾ����
				layout.addView(textView);//�����Բ���������ı���ʾ��
				return layout;//�������Բ���
			}//��ȡ������ʾ�ؼ�

			public long getChildId(int groupPosition, int childPosition) {
				return childPosition;
			}//��ȡ�����ID

			public Object getChild(int groupPosition, int childPosition) {
				return info[groupPosition][childPosition];
			}//��ȡָ������ָ����ŵ���
		};
		myExpandable.setAdapter(myAdapter);//������������չ�����б��������
	}
}