package edu.swpu.gps;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;







public class List_Made_Helper extends BaseAdapter {

    public List getLs5() {
        return ls5;
    }

    private List<Data_bean> data;//创建私有的Bean类的data
//    ls1,ls2,ls3,ls4,ls5,
    private List ls1,ls2,ls3,ls4,ls5,ls6,ls7,ls8,ls9;
    private Context context;





    public List_Made_Helper(List<Data_bean> data, List l1, List l2, List l3, List l4, List l5,List l6,List l7,List l8,List l9, Context context) {
        this.data = data;
        this.ls1=l1;
        this.ls2=l2;
        this.ls3=l3;
        this.ls4=l4;
        this.ls5=l5;
        this.ls6=l6;
        this.ls7=l7;
        this.ls8=l8;
        this.ls9=l9;

        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();//获取data的长度
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {//获取id
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){//防止view不停的新建，
            view = LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
        }


        TextView textView = view.findViewById(R.id.pusid);
        TextView textView2 = view.findViewById(R.id.pusname);
        TextView textView3 = view.findViewById(R.id.pdate);
        TextView textView4 = view.findViewById(R.id.pussay);
        TextView textView5 = view.findViewById(R.id.sr5);
//        Button xxxx=view.findViewById(R.id.xxxx);


        int max=ls1.size();
        if(i<max){

            String o1,o2,o3,o4,o5,o6;
            o1=String.valueOf(ls1.get(i));
            o2=String.valueOf(ls2.get(i));
            o3=String.valueOf(ls3.get(i));
            o4=String.valueOf(ls4.get(i));
            o5=String.valueOf(ls5.get(i));
            o6=String.valueOf(ls6.get(i));


            textView.setText(String.valueOf(ls1.get(i)));
            textView2.setText(String.valueOf(ls2.get(i)));
            textView3.setText(String.valueOf(ls3.get(i)));
            textView4.setText(String.valueOf(ls4.get(i)));
            textView5.setText(String.valueOf(ls5.get(i)));



            String rr= o6;


            ImageView img;

//            img=  findViewById(R.id.book_img);
//
//
//            ImageOptions imageOptions;
//            imageOptions = new ImageOptions.Builder()
//                    .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
//                    .setRadius(DensityUtil.dip2px(5))
//// 如果ImageView的大小不是定义为wrap_content, 不要crop.
//                    .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
//// 加载中或错误图片的ScaleType
////.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
//                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//                    .setLoadingDrawableId(R.mipmap.ic_launcher)
//                    .setFailureDrawableId(R.drawable.imgbad)
//                    .build();
////            x.image().bind(img, "https://5b0988e595225.cdn.sohucs.com/images/20170922/fe15d13a3e764a3bbaede340e47692ca.jpeg", imageOptions);//加载图片的控件，和加载网络图片的地址
//
//            x.image().bind(img, rr, imageOptions);//加载图片的控件，和加载网络图片的地址
//










//
//            xxxx.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    System.out.println(o6);
//
//
//
//
//
//
//
//                }
//            });



        }else {
            textView.setText("NULL");
            textView2.setText("NULL");
            textView3.setText("NULL");
            textView4.setText("NULL");
            textView5.setText("NULL");
        }

        return view;
    }
}

