package edu.swpu.gps;

import static android.os.SystemClock.sleep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import okhttp3.Callback;

public class MainActivity extends Activity {


//    private static final int REQUEST_READ_EXTERNAL_STORAGE = ;
    private Handler mHandler;
//    线程回调


    // 定义广播的Action
    // 定义广播的Action
    // 定义广播的Action
    // 定义广播的Action
    // 定义广播的Action
    public static final String MY_ACTION = "com.example.MY_ACTION";

//    下载文件
    private static final int PERMISSION_REQUEST_CODE = 100;
    private ProgressDialog progressDialog;


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 处理接收到的广播
            Log.d("MyService", "主线程收到：");
            String message = intent.getStringExtra("message");
            //在这里进行UI更新


            Log.d("主线程收到：", message);
//            String rr=intent.getStringExtra();
//            Log.d("MyService", rr);

        }
    };



    private static final String TAG = MainActivity.class.getSimpleName();
    private double latitude = 0.0;
    private double longitude = 0.0;
//    private TextView info;
    private LocationManager locationManager;

    public double getDistance(double lat1, double lon1, double lat2, double lon2) {

        float[] results=new float[1];

        Location.distanceBetween(lat1, lon1, lat2, lon2, results);

        return results[0];

    }








String url2= "https://ptgl.fujian.gov.cn:8088/masvod/public/2021/03/19/20210319_178498bcae9_r38.mp4";
    private TextView tv_result; // 声明一个文本视图对象
    private TextView tv_progress; // 声明一个文本视图对象
    private ImageView iv_result; // 声明一个图像视图对象




//    String URL_IMAGE="https://freepd.com/music/A%20Very%20Brady%20Special.mp3";
//
//    // 下载网络图片
//    private void downloadImage() {
//        tv_progress.setVisibility(View.GONE);
//        iv_result.setVisibility(View.VISIBLE);
//        OkHttpClient client = new OkHttpClient(); // 创建一个okhttp客户端对象
//        // 创建一个GET方式的请求结构
//        Request request = new Request.Builder().url(URL_IMAGE).build();
//        Call call = client.newCall(request); // 根据请求结构创建调用对象
//        // 加入HTTP请求队列。异步调用，并设置接口应答的回调方法
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) { // 请求失败
//                // 回到主线程操纵界面
//                runOnUiThread(() -> tv_result.setText("下载网络图片报错："+e.getMessage()));
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) { // 请求成功
//                InputStream is = response.body().byteStream();
//                // 从返回的输入流中解码获得位图数据
//                Bitmap bitmap = BitmapFactory.decodeStream(is);
//                String mediaType = response.body().contentType().toString();
//                long length = response.body().contentLength();
//                String desc = String.format("文件类型为%s，文件大小为%d", mediaType, length);
//                // 回到主线程操纵界面
//                runOnUiThread(() -> {
//                    tv_result.setText("下载网络图片返回："+desc);
//                    iv_result.setImageBitmap(bitmap);
//                });
//            }
//        });
//    }
//    String URL_APK="https://freepd.com/music/A%20Very%20Brady%20Special.mp3";
//
//    // 下载网络文件
//    private void downloadFile() {
//        tv_progress.setVisibility(View.VISIBLE);
//        iv_result.setVisibility(View.GONE);
//        OkHttpClient client = new OkHttpClient(); // 创建一个okhttp客户端对象
//        // 创建一个GET方式的请求结构
//        Request request = new Request.Builder().url(URL_APK).build();
//        Call call = client.newCall(request); // 根据请求结构创建调用对象
//        // 加入HTTP请求队列。异步调用，并设置接口应答的回调方法
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) { // 请求失败
//                // 回到主线程操纵界面
//                runOnUiThread(() -> tv_result.setText("下载网络文件报错："+e.getMessage()));
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) { // 请求成功
//                String mediaType = response.body().contentType().toString();
//                long length = response.body().contentLength();
//                String desc = String.format("文件类型为%s，文件大小为%d", mediaType, length);
//                // 回到主线程操纵界面
//                runOnUiThread(() -> tv_result.setText("下载网络文件返回："+desc));
//                String path = String.format("%s/%s.apk",
//                        getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString(),
//                        DateUtil.getNowDateTime());
//                // 下面从返回的输入流中读取字节数据并保存为本地文件
//                try (InputStream is = response.body().byteStream();
//                     FileOutputStream fos = new FileOutputStream(path)) {
//                    byte[] buf = new byte[100 * 1024];
//                    int sum=0, len=0;
//                    while ((len = is.read(buf)) != -1) {
//                        fos.write(buf, 0, len);
//                        sum += len;
//                        int progress = (int) (sum * 1.0f / length * 100);
//                        String detail = String.format("文件保存在%s。已下载%d%%", path, progress);
//                        // 回到主线程操纵界面
//                        runOnUiThread(() -> tv_progress.setText(detail));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

//注销广播接收
    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private List<Data_bean> data = new ArrayList<>();//ArrayList 类是一个可以动态修改的数组，与普通数组的区别就是它是没有固定大小的限制，我们可以添加或删除元素



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// 检查外部存储挂载状态
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 检查读取和写入动态权限
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            } else {
                // 如果没有，获取读取和写入动态权限
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }

        }
        
        
//        info = (TextView) findViewById(R.id.tv2);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String iid = getIntent().getStringExtra("key");
//        iid = "2935116713@qq.com";
//广播接收器



        //        // 注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(MainActivity.MY_ACTION);
        registerReceiver(receiver, filter);




        x.Ext.init(MainActivity.this.getApplication());
//        XUTILS

        mHandler = new Handler(Looper.getMainLooper());



        tv_result = findViewById(R.id.tx1);
        tv_progress = findViewById(R.id.tx2);
        iv_result = findViewById(R.id.im1);

//        findViewById(R.id.bt_im).setOnClickListener(v -> downloadImage());









//        findViewById(R.id.bt_mp).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String url="https://freepd.com/music/Amazing%20Grace.mp3";
//
//                Log.d("下载>>>>>>>>>", "onFailure:阿尔发顺丰分威风 " );
//
//
//
//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder().url(url).build();
//                client.newCall(request).enqueue(new okhttp3.Callback() {
//                    @Override
//                    public void onFailure(okhttp3.Call call, IOException e) {
//                        Log.d(TAG, "onFailure: " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(okhttp3.Call call, Response response) throws IOException {
//
//
//
//
//                        ResponseBody responseBody = response.body();
//                        ProgressResponseBody progressResponseBody = new ProgressResponseBody(responseBody, new ProgressListener() {
//                            @Override
//                            public void onProgress(long currentBytes, long contentLength, boolean done) {
//                                float percent = (float)currentBytes / (float)contentLength;
//                                Log.d(TAG, "progress: " + percent);
//
//                                Log.d("百分比>>>>>>>>>", "onFailure:阿尔发顺丰分威风 " );
//
//                            }   });
//
//
//
//
//                        String encodedUrl = URLEncoder.encode(url, "UTF-8");
//                        Log.d("UURLLL解码", encodedUrl);
//
//                    InputStream in = response.body().byteStream();
//                        // 获取文件名
//                        String fileName = url.substring(url.lastIndexOf("/") + 1);
//                        // 获取目录路径
//                        Log.d("下载>>>>>>>>>", "onFailure:阿尔发顺丰分威风 " );
//
//                        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
//
////                        String jsonStr = new String(fi, StandardCharsets.UTF_8);//把原始数据转为字符串
//
//                        String bd=fileName.replaceAll("%20"," ");
//
//                        File file = new File(path, bd);
//                        FileOutputStream out = new FileOutputStream(file);
//                        byte[] buffer = new byte[2048];
//                        int len = 0;
//                        while ((len = in.read(buffer)) != -1) {
//                            Log.d("下载>>>>>>>>>", len+"%%%%%%%");
//
//                            out.write(buffer, 0, len);
//                        }
//                        out.flush();
//                        out.close();
//                        in.close();
//                    }
//                });
//
//
////
////                client.newCall(request).enqueue(new Callback() {
////                    @Override
////                    public void onFailure(Call call, IOException e) {
////                        Log.d(TAG, "onFailure: " + e.getMessage());
////                    }
////
////                    @Override
////                    public void onResponse(Call call, Response response) throws IOException {
////                        InputStream in = response.body().byteStream();
////                        // 获取文件名
////                        String fileName = url.substring(url.lastIndexOf("/") + 1);
////                        // 获取目录路径
////                        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
////                        File file = new File(path, fileName);
////                        FileOutputStream out = new FileOutputStream(file);
////                        byte[] buffer = new byte[2048];
////                        int len = 0;
////                        while ((len = in.read(buffer)) != -1) {
////                            out.write(buffer, 0, len);
////                        }
////                        out.flush();
////                        out.close();
////                        in.close();
////                    }
////                });
//
//
//
//
//
//            }
//        });




//        // 检查写入外部存储的权限
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // 如果没有权限，则请求权限
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    REQUEST_WRITE_STORAGE);
//        } else {
//            // 如果已经有权限，则执行下载文件的操作
//            downloadFile();
//            Log.d("开始下载》》》》》》", "下》》》》》》》》》》》》");
//        }
















//        // 注册广播接收器
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(MainActivity.MY_ACTION);
//        registerReceiver(receiver, filter);






        Log.i("主线程：：Kathy", "Thread ID = " + Thread.currentThread().getId());
        Log.i("Kathy", "主线程，启动before StartService");

        //连续启动Service
        Intent intentOne = new Intent(this, TestOneService.class);
        startService(intentOne);
//        Intent intentTwo = new Intent(this, TestOneService.class);
//        startService(intentTwo);
//        Intent intentThree = new Intent(this, TestOneService.class);
//        startService(intentThree);





        //连续启动Service
        Intent intent3 = new Intent(this, TestOneService222.class);
        intent3.putExtra("yiyan", "顶针");
        startService(intent3);


        //连续启动Service
//        Intent intent4= new Intent(this, music_Play_Service.class);
//        intent4.putExtra("yiyan", "顶针");
//        startService(intent4);


        //连续启动Service
        Intent intent5= new Intent(this, MUSICSEVER2.class);
        intent5.putExtra("yiyan", "顶针");
        startService(intent5);
        
//        https://blog.51cto.com/u_14256/6434541
//        service启动教程








        //停止Service
//        Intent intentFour = new Intent(this, TestOneService.class);
//        stopService(intentFour);
//
//        //再次启动Service
//        Intent intentFive = new Intent(this, TestOneService.class);
//        startService(intentFive);

        Log.i("Kathy", "after StartService");
//        -----------------------------------
//                Android service 启动模式 安卓service启动方式
//        https://blog.51cto.com/u_14256/6434541
//








//        TextView po1 = findViewById(R.id.po1);
//        TextView po2 = findViewById(R.id.po2);
//        TextView jl = findViewById(R.id.jl);
//        Button btn = findViewById(R.id.button);
//        Button btn2 = findViewById(R.id.cgtx);
//        Button ts = findViewById(R.id.ts);


//        Button wt = findViewById(R.id.wt);
//        Button cgui =findViewById(R.id.cgui);

        TextView tx1=findViewById(R.id.tx1);

//        Button gtjson = findViewById(R.id.gtjson);






        // 步骤1：创建线程类 （继承自Thread类）
        class MyThread4 extends Thread{

            // 步骤2：复写run（），内容 = 定义线程行为
            @Override
            public void run(){




                //        #####################################
//生成列表
                Log.d("GGGGGGGGTTTTTT", "获取？？？^^^^^^^^^^^");


                String baseUrl="https://gitlab.com/michaelins.shi/music-list/-/raw/main/";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                HttpJson httpJson = retrofit.create(HttpJson.class);



                Call<ResponseBody> call = httpJson.getHaoMa();//这里Call里面的内容必须与HttpJson内的Call内的一致


//                client.newCall(request).enqueue(new okhttp3.Callback() {
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        try {

                            String jsonStr = new String(response.body().bytes(), StandardCharsets.UTF_8);//把原始数据转为字符串

                            String bd=jsonStr.replaceAll("%20"," ");
//                            Log.e("retrofit获取到的数据", bd);
//
//                            Log.e("retrofit获取到的数据", jsonStr);
//                            tx1.setText(bd);




                            try {
                                //获取assests文件夹下的内容
//                                InputStreamReader in = new InputStreamReader(getAssets().open("caipu.json"), "UTF-8");
//                                System.out.println("获取输入流");
//                                //读取文件的信息
//                                BufferedReader br = new BufferedReader(in);
                                String line = "";
//                                StringBuffer builder = new StringBuffer();
//                                while ((line = br.readLine()) != null) {
//                                    builder.append(line);
//                                }
                                //可以获取一个字符串对象
                                JSONObject obj = new JSONObject(bd);
                                //获取json数据
                                String object = obj.getString("schemaVersion");
                                System.out.println("获取的json数据college" + object);
                                //获取数组数据
                                JSONArray array = obj.getJSONArray("tracks");
                                //打印json数组的信息
//                                System.out.println("获取的json数据" + array);
//                                //获取数组中的对象信息
//                                System.out.println("获取的json数据" + array.get(0));
//                                System.out.println("获取的json数据" + array.get(1));
//
//                                System.out.println("有多大？？？" + array.length());



                                //获取数组中的对象中的信息
                                System.out.println("获取的json数据" + new JSONObject(array.get(0).toString()).getString("cover"));


                                String d1 = "id";
                                String d2 = "title";
                                String d3 = "artist";
                                String d4 = "year";
                                String d5 = "url";
                                String d6 ="cover";



                                List ls1 = new ArrayList();
                                List ls2 = new ArrayList();
                                List ls3 = new ArrayList();
                                List ls4 = new ArrayList();
                                List ls5 = new ArrayList();
                                List ls6 = new ArrayList();
//                                List ls7 = new ArrayList();
//                                List ls8 = new ArrayList();
//                                List ls9 = new ArrayList();

//
//                                d1 = new JSONObject(array.get(iii).toString()).getString("id");
//                                d2 = new JSONObject(array.get(iii).toString()).getString("itle");
//                                d3 = new JSONObject(array.get(iii).toString()).getString("artist");
//                                d4 = new JSONObject(array.get(iii).toString()).getString("year");
//                                d5 = new JSONObject(array.get(iii).toString()).getString("url");
//                                d6 = new JSONObject(array.get(iii).toString()).getString("cover");

                                int howlong = array.length();
                                System.out.println("好大嘛" + howlong);
                                int iii = 0;


                                while (iii < howlong) {
                                    d1 = new JSONObject(array.get(iii).toString()).getString("id");
                                    d2 = new JSONObject(array.get(iii).toString()).getString("title");
                                    d3 = new JSONObject(array.get(iii).toString()).getString("artist");
                                    d4 = new JSONObject(array.get(iii).toString()).getString("year");
                                    d5 = new JSONObject(array.get(iii).toString()).getString("url");
                                    d6 = new JSONObject(array.get(iii).toString()).getString("cover");


                                    ls1.add(d1);
                                    ls2.add(d2);
                                    ls3.add(d3);
                                    ls4.add(d4);
                                    ls5.add(d5);
                                    ls6.add(d6);

                                    iii++;

                                }

                                int er=ls2.size();
                                int uuu=0;

                                while (uuu<er){
                                    Log.d("获取音乐列表", String.valueOf(ls2.get(uuu)));

                                    uuu++;
                                }

                                int lo = er;

                                System.out.println("F多大DDDDDDDDDDD");
                                System.out.println(lo);
                                for (int i = 1; i <= lo; i++) {
                                    Data_bean bean = new Data_bean();
                                    bean.setName("第" + i + "个");
                                    data.add(bean);//通过add把数据加入到data里面
                                }


                                List<String> mpl=mp3_file_name_get_helper();
                                String[] ccp= mpl.toArray(new String[0]);
                                String uu="Burt's Requiem";

                                Log.d("333333333333333333333", String.valueOf(mpl));
                                Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains(uu)));
                                Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains("uu")));



                                ListView listView = findViewById(R.id.sclist2);//获取id，就是上面讲的屏幕里面的列表的id
                                listView.setAdapter(new List_Made_Helper22(data, ls1, ls2, ls3, ls4, ls5, ls6, ls6, ls6,ls6,"不知道哦",MainActivity.this));


                                List gx1=new ArrayList();
                                uuu=0;

                                String hc="";

//                                while (uuu<er){
//                                    Log.d("获取音乐列表", String.valueOf(ls6.get(uuu)));
//                                    hc= String.valueOf(ls6.get(uuu));
//                                    gx1.add("https://img0.baidu.com/it/u=2647999361,3725136645&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400");
//
//                                    uuu++;
//                                }

//                                Log.d("获取音乐列表", String.valueOf(ls2));


                            }catch (Exception e){
                                e.printStackTrace();
                            }


//                            jsonToObj(jsonStr);//这是对字符串数据解析具体数据方法
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


//
//                    @Override
//                    public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
//                        KLog.e("请求失败！", t.toString());
//                    }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("请求失败！", t.toString());

                    }

                });
//生成列表
//        #####################################






            }
        }

        // 步骤3：创建线程对象，即 实例化线程类
        MyThread4 mt4=new MyThread4();

        // 步骤4：通过 线程对象 控制线程的状态，如 运行、睡眠、挂起  / 停止
        // 此处采用 start（）开启线程

        mt4.start();


        // 步骤1：创建线程类 （继承自Thread类）




////        #####################################
////生成列表
//        Log.d("GGGGGGGGTTTTTT", "获取？？？^^^^^^^^^^^");
//
//
//        String baseUrl="https://gitlab.com/michaelins.shi/music-list/-/raw/main/";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        HttpJson httpJson = retrofit.create(HttpJson.class);
//
//
//
//        Call<ResponseBody> call = httpJson.getHaoMa();//这里Call里面的内容必须与HttpJson内的Call内的一致
//
//
////                client.newCall(request).enqueue(new okhttp3.Callback() {
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                try {
//
//                    String jsonStr = new String(response.body().bytes(), StandardCharsets.UTF_8);//把原始数据转为字符串
//
//                    String bd=jsonStr.replaceAll("%20"," ");
////                            Log.e("retrofit获取到的数据", bd);
////
////                            Log.e("retrofit获取到的数据", jsonStr);
////                            tx1.setText(bd);
//
//
//
//
//                    try {
//                        //获取assests文件夹下的内容
////                                InputStreamReader in = new InputStreamReader(getAssets().open("caipu.json"), "UTF-8");
////                                System.out.println("获取输入流");
////                                //读取文件的信息
////                                BufferedReader br = new BufferedReader(in);
//                        String line = "";
////                                StringBuffer builder = new StringBuffer();
////                                while ((line = br.readLine()) != null) {
////                                    builder.append(line);
////                                }
//                        //可以获取一个字符串对象
//                        JSONObject obj = new JSONObject(bd);
//                        //获取json数据
//                        String object = obj.getString("schemaVersion");
//                        System.out.println("获取的json数据college" + object);
//                        //获取数组数据
//                        JSONArray array = obj.getJSONArray("tracks");
//                        //打印json数组的信息
////                                System.out.println("获取的json数据" + array);
////                                //获取数组中的对象信息
////                                System.out.println("获取的json数据" + array.get(0));
////                                System.out.println("获取的json数据" + array.get(1));
////
////                                System.out.println("有多大？？？" + array.length());
//
//
//
//                        //获取数组中的对象中的信息
//                         System.out.println("获取的json数据" + new JSONObject(array.get(0).toString()).getString("cover"));
//
//
//                        String d1 = "id";
//                        String d2 = "title";
//                        String d3 = "artist";
//                        String d4 = "year";
//                        String d5 = "url";
//                        String d6 ="cover";
//
//
//
//                        List ls1 = new ArrayList();
//                        List ls2 = new ArrayList();
//                        List ls3 = new ArrayList();
//                        List ls4 = new ArrayList();
//                        List ls5 = new ArrayList();
//                        List ls6 = new ArrayList();
////                                List ls7 = new ArrayList();
////                                List ls8 = new ArrayList();
////                                List ls9 = new ArrayList();
//
////
////                                d1 = new JSONObject(array.get(iii).toString()).getString("id");
////                                d2 = new JSONObject(array.get(iii).toString()).getString("itle");
////                                d3 = new JSONObject(array.get(iii).toString()).getString("artist");
////                                d4 = new JSONObject(array.get(iii).toString()).getString("year");
////                                d5 = new JSONObject(array.get(iii).toString()).getString("url");
////                                d6 = new JSONObject(array.get(iii).toString()).getString("cover");
//
//                        int howlong = array.length();
//                        System.out.println("好大嘛" + howlong);
//                        int iii = 0;
//
//
//                        while (iii < howlong) {
//                            d1 = new JSONObject(array.get(iii).toString()).getString("id");
//                            d2 = new JSONObject(array.get(iii).toString()).getString("title");
//                            d3 = new JSONObject(array.get(iii).toString()).getString("artist");
//                            d4 = new JSONObject(array.get(iii).toString()).getString("year");
//                            d5 = new JSONObject(array.get(iii).toString()).getString("url");
//                            d6 = new JSONObject(array.get(iii).toString()).getString("cover");
//
//
//                            ls1.add(d1);
//                            ls2.add(d2);
//                            ls3.add(d3);
//                            ls4.add(d4);
//                            ls5.add(d5);
//                            ls6.add(d6);
//
//                            iii++;
//
//                        }
//
//                        int er=ls2.size();
//                        int uuu=0;
//
//                        while (uuu<er){
//                            Log.d("获取音乐列表", String.valueOf(ls2.get(uuu)));
//
//                            uuu++;
//                        }
//
//                        int lo = er;
//
//                        System.out.println("F多大DDDDDDDDDDD");
//                        System.out.println(lo);
//                        for (int i = 1; i <= lo; i++) {
//                            Data_bean bean = new Data_bean();
//                            bean.setName("第" + i + "个");
//                            data.add(bean);//通过add把数据加入到data里面
//                        }
//
//
//                        List<String> mpl=mp3_file_name_get_helper();
//                        String[] ccp= mpl.toArray(new String[0]);
//                        String uu="Burt's Requiem";
//
//                        Log.d("333333333333333333333", String.valueOf(mpl));
//                        Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains(uu)));
//                        Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains("uu")));
//
//
//
//                        ListView listView = findViewById(R.id.sclist2);//获取id，就是上面讲的屏幕里面的列表的id
//                        listView.setAdapter(new List_Made_Helper22(data, ls1, ls2, ls3, ls4, ls5, ls6, ls6, ls6,ls6,"不知道哦",MainActivity.this));
//
//                        List gx1=new ArrayList();
//                        uuu=0;
//
//                        String hc="";
//
//                        while (uuu<er){
//                            Log.d("获取音乐列表", String.valueOf(ls6.get(uuu)));
//                            hc= String.valueOf(ls6.get(uuu));
//                            gx1.add("https://img0.baidu.com/it/u=2647999361,3725136645&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400");
//
//                            uuu++;
//                        }
//
////                                Log.d("获取音乐列表", String.valueOf(ls2));
//
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//
////                            jsonToObj(jsonStr);//这是对字符串数据解析具体数据方法
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
////
////                    @Override
////                    public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
////                        KLog.e("请求失败！", t.toString());
////                    }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("请求失败！", t.toString());
//
//            }
//
//        });
////生成列表
////        #####################################

//        view = LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);

//        List<String> mpl=mp3_file_name_get_helper();
//        String[] ccp= mpl.toArray(new String[0]);
//        String uu="Burt's Requiem";
//
//        Log.d("333333333333333333333", String.valueOf(mpl));
//        Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains(uu)));
//        Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains("uu")));
//
//
//        listView

//        mpfile=mp3_file_get_helper();
        ListView lst=findViewById(R.id.sclist2);
//        String ss= String.valueOf(lst.getAdapter());
        int list_lon=lst.getChildCount();
        View childView = lst.getChildAt(0);
//        TextView textView3 = childView.findViewById(R.id.dow);
//
//       textView3.setText("你是猪猪");

//        Log.d("CCCCCCCCCCCCCC", childView.getTransitionName());

        //        View childView = lst.getChildAt(i);
//            // 对子View进行操作
//            TextView textView2 = childView.findViewById(R.id.pusname);
//            String ee= String.valueOf(textView2.getText());


//        // 步骤1：创建线程类 （继承自Thread类）
//        class MyThread extends Thread{
//
//            // 步骤2：复写run（），内容 = 定义线程行为
//            @Override
//            public void run(){
//
////                new_mp3_list();
//
////        跟新本地文件
//            }
//        }
//
//        // 步骤3：创建线程对象，即 实例化线程类
//        MyThread mt3=new MyThread( );
//
//        // 步骤4：通过 线程对象 控制线程的状态，如 运行、睡眠、挂起  / 停止
//        // 此处采用 start（）开启线程
//        mt3.start();


        new_mp3_list();




//        gtjson.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Log.d("GGGGGGGGTTTTTT", "获取？？？^^^^^^^^^^^");
//
//
//                String baseUrl="https://gitlab.com/michaelins.shi/music-list/-/raw/main/";
//
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(baseUrl)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                HttpJson httpJson = retrofit.create(HttpJson.class);
//
//
//
//                Call<ResponseBody> call = httpJson.getHaoMa();//这里Call里面的内容必须与HttpJson内的Call内的一致
//
//
////                client.newCall(request).enqueue(new okhttp3.Callback() {
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                        try {
//
//                            String jsonStr = new String(response.body().bytes(), StandardCharsets.UTF_8);//把原始数据转为字符串
//
//                            String bd=jsonStr.replaceAll("%20"," ");
////                            Log.e("retrofit获取到的数据", bd);
////
////                            Log.e("retrofit获取到的数据", jsonStr);
////                            tx1.setText(bd);
//
//
//
//
//                            try {
//                                //获取assests文件夹下的内容
////                                InputStreamReader in = new InputStreamReader(getAssets().open("caipu.json"), "UTF-8");
////                                System.out.println("获取输入流");
////                                //读取文件的信息
////                                BufferedReader br = new BufferedReader(in);
//                                String line = "";
////                                StringBuffer builder = new StringBuffer();
////                                while ((line = br.readLine()) != null) {
////                                    builder.append(line);
////                                }
//                                //可以获取一个字符串对象
//                                JSONObject obj = new JSONObject(bd);
//                                //获取json数据
//                                String object = obj.getString("schemaVersion");
//                                System.out.println("获取的json数据college" + object);
//                                //获取数组数据
//                                JSONArray array = obj.getJSONArray("tracks");
//                                //打印json数组的信息
////                                System.out.println("获取的json数据" + array);
////                                //获取数组中的对象信息
////                                System.out.println("获取的json数据" + array.get(0));
////                                System.out.println("获取的json数据" + array.get(1));
////
////                                System.out.println("有多大？？？" + array.length());
//
//
//
//                                //获取数组中的对象中的信息
////                                System.out.println("获取的json数据" + new JSONObject(array.get(0).toString()).getString("id"));
////                                System.out.println("获取的json数据" + new JSONObject(array.get(0).toString()).getString("title"));
////                                System.out.println("获取的json数据" + new JSONObject(array.get(0).toString()).getString("artist"));
////                                System.out.println("获取的json数据" + new JSONObject(array.get(0).toString()).getString("year"));
////                                System.out.println("获取的json数据" + new JSONObject(array.get(0).toString()).getString("url"));
////                                System.out.println("获取的json数据" + new JSONObject(array.get(0).toString()).getString("cover"));
//
//
//                                String d1 = "id";
//                                String d2 = "title";
//                                String d3 = "artist";
//                                String d4 = "year";
//                                String d5 = "url";
//                                String d6 ="cover";
//
//
//
//                                List ls1 = new ArrayList();
//                                List ls2 = new ArrayList();
//                                List ls3 = new ArrayList();
//                                List ls4 = new ArrayList();
//                                List ls5 = new ArrayList();
//                                List ls6 = new ArrayList();
////                                List ls7 = new ArrayList();
////                                List ls8 = new ArrayList();
////                                List ls9 = new ArrayList();
//
////
////                                d1 = new JSONObject(array.get(iii).toString()).getString("id");
////                                d2 = new JSONObject(array.get(iii).toString()).getString("itle");
////                                d3 = new JSONObject(array.get(iii).toString()).getString("artist");
////                                d4 = new JSONObject(array.get(iii).toString()).getString("year");
////                                d5 = new JSONObject(array.get(iii).toString()).getString("url");
////                                d6 = new JSONObject(array.get(iii).toString()).getString("cover");
//
//                                int howlong = array.length();
//                                System.out.println("好大嘛" + howlong);
//                                int iii = 0;
//
//
//                                while (iii < howlong) {
//                                    d1 = new JSONObject(array.get(iii).toString()).getString("id");
//                                    d2 = new JSONObject(array.get(iii).toString()).getString("title");
//                                    d3 = new JSONObject(array.get(iii).toString()).getString("artist");
//                                    d4 = new JSONObject(array.get(iii).toString()).getString("year");
//                                    d5 = new JSONObject(array.get(iii).toString()).getString("url");
//                                    d6 = new JSONObject(array.get(iii).toString()).getString("cover");
//
//
//                                    ls1.add(d1);
//                                    ls2.add(d2);
//                                    ls3.add(d3);
//                                    ls4.add(d4);
//                                    ls5.add(d5);
//                                    ls6.add(d6);
//
//                                    iii++;
//
//                                }
//
//                                int er=ls2.size();
//                                int uuu=0;
//
//                                while (uuu<er){
//                                    Log.d("获取音乐列表", String.valueOf(ls2.get(uuu)));
//
//                                    uuu++;
//                                }
//
//                                int lo = er;
//
//                                System.out.println("F多大DDDDDDDDDDD");
//                                System.out.println(lo);
//                                for (int i = 1; i <= lo; i++) {
//                                    Data_bean bean = new Data_bean();
//                                    bean.setName("第" + i + "个");
//                                    data.add(bean);//通过add把数据加入到data里面
//                                }
//
//
//
//
//                                List<String> mpl=mp3_file_name_get_helper();
//                                String[] ccp= mpl.toArray(new String[0]);
////                                String uu="Burt's Requiem";
//
////                                Log.d("333333333333333333333", String.valueOf(mpl));
////                                Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains(uu)));
////                                Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains("uu")));
//
//                                ListView listView = findViewById(R.id.sclist2);//获取id，就是上面讲的屏幕里面的列表的id
//                                listView.setAdapter(new List_Made_Helper22(data, ls1, ls2, ls3, ls4, ls5, ls6, ls6, ls6,ls6,"不知道哦",MainActivity.this));
//
//                                List gx1=new ArrayList();
//                                uuu=0;
//
//                                String hc="";
//
//                                while (uuu<er){
//                                    Log.d("获取音乐列表", String.valueOf(ls6.get(uuu)));
//                                    hc= String.valueOf(ls6.get(uuu));
//                                    gx1.add("https://img0.baidu.com/it/u=2647999361,3725136645&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400");
//
//                                    uuu++;
//                                }
//
//
//
//
//
//
//
//
//
////                                Log.d("获取音乐列表", String.valueOf(ls2));
//
//
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//
//
//
//
//
//
////                            jsonToObj(jsonStr);//这是对字符串数据解析具体数据方法
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//
////
////                    @Override
////                    public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
////                        KLog.e("请求失败！", t.toString());
////                    }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.e("请求失败！", t.toString());
//
//                    }
//
//
//
//
//
//                });
//
//
//
////                call.enqueue(new Callback<ResponseBody>() {
////                    @Override
////                    public void onResponse(@Nullable Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
////                        try {
////
////                            String jsonStr = new String(response.body().bytes());//把原始数据转为字符串
////                            KLog.e("retrofit获取到的数据", jsonStr);
////
////                            jsonToObj(jsonStr);//这是对字符串数据解析具体数据方法
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                        }
////
////                    }
////
////                    @Override
////                    public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
////                        KLog.e("请求失败！", t.toString());
////                    }
////                });
//
//
//
//
//
//
//
//            }
//
//
//
//
//
//        });





//
//        cgui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("WWWWWW", "岂懂？？？^^^^^^^^^^^");
//
//
//
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        int bb=0;
//                        // Message和Handler均可获得msg
//                        // Message msg = handler.obtainMessage();
//
//                        while (true){
//                            bb++;
//                            String ss= String.valueOf(bb)+"你是猪猪";
//
//                            tx1.setText(ss);
//                            Log.d("WWWWWW", "^^^^^^^^^^^"+bb);
//                            sleep(500);
//
//                        }
//
//
//                    }
//                }).start();
//
//
//
//            }
//        });




        // 步骤1：创建线程类 （继承自Thread类）
        class MyThread3 extends Thread{

            // 步骤2：复写run（），内容 = 定义线程行为
            @Override
            public void run(){


                Log.d("我是线程！！！！！！！！！", "FKKKKKKKKKKK");

                OkHttpClient client = new OkHttpClient();


//                private OkHttpClient client;
//
//
//                    client = new OkHttpClient();
//                https://blog.csdn.net/q1210249579/article/details/110771175
                    // 创建一个请求对象
                    Request request = new Request.Builder()
                            .url("https://gitlab.com/michaelins.shi/music-list/-/raw/main/music-list.json")
                            .build();

                    // 发送请求并获取响应
                    try {
                        Response response = client.newCall(request).execute();
                        String responseData = response.body().string();
                        Log.d("OkHttp", responseData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://gitlab.com/michaelins.shi/music-list/-/raw/main/") // 设置基本URL
                        .addConverterFactory(GsonConverterFactory.create()) // 指定JSON数据的转换器
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<ApiService> call = apiService.getWeather();
                Log.d("QQQQQQQQQQQQQQQQ", "AAAAAAAAAAAAAAAAA");

                call.enqueue(new Callback<ApiService>() {
                    @Override
                    public void onResponse(Call<ApiService> call, retrofit2.Response<ApiService> response) {
//                        Log.d("RESPON",response.body());
                        Log.d("QQQQQQ互殴还有ilQQ", "AAAAAAAAA一股右路i‘0有’哦iu哦iuuAAAAAAAA");
                        Log.d("RESPON",response.message().toString());
                        Log.d("RESPON", String.valueOf(response.code()));
                        Log.d("RESPON",response.toString());

                        // 步骤1：创建线程类 （继承自Thread类）
                        class MyThread2 extends Thread{

                            // 步骤2：复写run（），内容 = 定义线程行为
                            @Override
                            public void run(){
                                Log.d("QQQQQQ互殴还有ilQQ", "AAAEEEEEEEEEEEEEEEEEEAAAAAAAA");

                            }
                        }

                        // 步骤3：创建线程对象，即 实例化线程类
                        MyThread2 mt=new MyThread2();

                        // 步骤4：通过 线程对象 控制线程的状态，如 运行、睡眠、挂起  / 停止
                        // 此处采用 start（）开启线程
                        mt.start();


                    }

                    @Override
                    public void onFailure(Call<ApiService> call, Throwable t) {
                        Log.d("错误？？？？", String.valueOf(t));

                    }


                });
                Log.d("RRRRRRRRRRRRRRRRRR",getClassLoader().toString());











            }
        }

        // 步骤3：创建线程对象，即 实例化线程类
        MyThread3 mt3=new MyThread3();

        // 步骤4：通过 线程对象 控制线程的状态，如 运行、睡眠、挂起  / 停止
        // 此处采用 start（）开启线程
//        mt.start();


//        private static final int REQUEST_WRITE_STORAGE = 112;
        final int REQUEST_WRITE_STORAGE = 112;


//        wt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("保存？？？","efkffp[df");
//
//
//
//
//
//
//
//                // 检查写入外部存储的权限
//                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    // 如果没有权限，则请求权限
//                    ActivityCompat.requestPermissions(MainActivity.this,
//                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                            REQUEST_WRITE_STORAGE);
//                } else {
//                    // 如果已经有权限，则执行写入文件的操作
//                    writeToFile();
//                }
//            }
//
//            private void writeToFile() {
//                String filename = "music/example.txt";
//                String content = "Hello, this is some text content to be written to the file.让我写入！！！！";
//
//                File file = new File(Environment.getExternalStorageDirectory(), filename);
//
//                try {
//                    FileOutputStream fos = new FileOutputStream(file);
//                    fos.write(content.getBytes());
//                    fos.close();
//                    Toast.makeText(MainActivity.this, "文件写入成功", Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "文件写入失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//
//
////                        writeToFile();
//
////            @Override
////            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////                if (requestCode == REQUEST_WRITE_STORAGE) {
////                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                        // 如果用户授予了写入外部存储的权限，则执行写入文件的操作
////                        writeToFile();
////                    } else {
////                        Toast.makeText(MainActivity.this, "权限被拒绝，无法写入文件", Toast.LENGTH_SHORT).show();
////                    }
////                }
////
////
////
////
////            }
////
////            void onRequestPermissionsResult(requestCode, permissions, grantResults);
//        });






//        ts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("WWWWWWWWWWW", "啊啊啊啊");
//
//
//
//                mt3.start();
//
//
////                HttpDownloader downloader = new HttpDownloader();
////                String urlStr = "https://ltbook.top/lp/xymm/img/Img1.jpg";
////                downloader.download(urlStr);
////                https://freepd.com/music/A%20Very%20Brady%20Special.mp3
//
////                String urlStr = "https://ltbook.top/lp/xymm/img/Img1.jpg";
//
//
//
//
//
//
//
//
//            }
//        });

//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 创建Intent对象
//                Intent intent = new Intent("com.example.MY_ACTION");
//                // 添加需要传递的数据
//                intent.putExtra("message", "子线程你再骂！");
//                // 发送广播
//                sendBroadcast(intent);
//
////                Log.d("主线程发送：", "你发送广播");
//
//
//            }
//        });


//        btn.setOnClickListener(new View.OnClickListener() {
//            int sw = 0;
//            double j1,j2,w1,w2;
//            @Override
//            public void onClick(View view) {
//                Log.d("AAAAAAAAAAAAAAAA", "你干嘛，哎哟");
//
//                toggleGPS();
//                Log.d("AAAAAAAAAAAAAAAA", "你干嘛，哎哟");
//                Log.d("AAAAAAAAAAAAAAAA", "纬度：" + latitude + "\n" + "经度：" + longitude);
//
//                getLocation();
//                info.setText("纬度：" + latitude + "\n" + "经度：" + longitude);
//
//
//                if(sw==0){
//                    sw=1;
//                    po1.setText("纬度：" + latitude + "\n" + "经度：" + longitude);
//                    j1=longitude;
//                    w1=latitude;
//                }else{
//                    if(sw==1){
//                        sw=1;
//                        po2.setText("纬度：" + latitude + "\n" + "经度：" + longitude);
//                        j2=longitude;
//                        w2=latitude;
//
//                        Log.d("AAAAAAAAAAAAAAAA", "你干嘛，哎哟"+getDistance(w1, j1,w2,j2));
//                        double ss =getDistance(w1, j1,w2,j2);
////                        double jjj=ss[0];
//                        jl.setText(new String(String.valueOf(ss)));
//
//
//
//
//
//
//                    }
//                }
//
//
//
//
//            }
//        });



        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            getLocation();
            //gps已打开
        } else {
            toggleGPS();
            new Handler() {
            }.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getLocation();
                }
            }, 2000);

        }
    }



//
//    private static final int REQUEST_WRITE_STORAGE = 112;
//    private static final String BASE_URL = "https://freepd.com/music/A%20Very%20Brady%20Special.mp3/"; // 替换为实际的下载链接
//    private static final String FILE_NAME = "example.mp3"; // 文件保存的名称
////    下载mp3
//
//    private void downloadFile() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .build();
//        Log.d("开始下载》》》》》》", "下》》》》》》》》》》》》");
//
//        FileDownloadService service = retrofit.create(FileDownloadService.class);
//        Call<ResponseBody> call = service.downloadFileWithDynamicUrlSync("download/");
//        Log.d("开始下载》》》》》》", "下》》》》》》》》》》》》");
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            void onResponse(Call<ResponseBody> call, Response response) {
//                if (response.isSuccessful()) {
//                    boolean success = writeResponseBodyToDisk(response.body());
//                    Log.d("开始下载》》》》》》", "哎哟你干嘛");
//
//                    if (success) {
//                        Toast.makeText(MainActivity.this, "文件下载成功并保存到音乐文件夹", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(MainActivity.this, "文件保存失败", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(MainActivity.this, "文件下载失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                Log.d("开始下载》》》》》》《《《《《《《《《", "哎哟你干嘛");
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "文件下载失败", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private boolean writeResponseBodyToDisk(ResponseBody body) {
//        try {
//            File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
//            File file = new File(musicDir, FILE_NAME);
//
//            InputStream inputStream = null;
//            OutputStream outputStream = null;
//
//            try {
//                byte[] fileReader = new byte[4096];
//                long fileSize = body.contentLength();
//                long fileSizeDownloaded = 0;
//
//                inputStream = body.byteStream();
//                outputStream = new FileOutputStream(file);
//
//                while (true) {
//                    int read = inputStream.read(fileReader);
//                    if (read == -1) {
//                        break;
//                    }
//                    outputStream.write(fileReader, 0, read);
//                    fileSizeDownloaded += read;
//                }
//
//                outputStream.flush();
//                return true;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            } finally {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_WRITE_STORAGE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // 如果用户授予了写入外部存储的权限，则执行下载文件的操作
//                downloadFile();
//            } else {
//                Toast.makeText(this, "权限被拒绝，无法下载文件", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    interface FileDownloadService {
//        @Streaming
//        @GET
//        Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
//    }













    private void toggleGPS() {
        Intent gpsIntent = new Intent();
        gpsIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        gpsIntent.addCategory("android.intent.category.ALTERNATIVE");
        gpsIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(this, 0, gpsIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
            Location location1 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location1 != null) {
                latitude = location1.getLatitude(); // 经度
                longitude = location1.getLongitude(); // 纬度
            }
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        }
//        info.setText("纬度：" + latitude + "\n" + "经度：" + longitude);

        System.out.println("AAAAAAAAAAAAAAAAAAAAA");
    }

    LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, provider);
        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, provider);
        }

        // 当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Log.e("Map", "Location changed : Lat: " + location.getLatitude() + " Lng: " + location.getLongitude());
                latitude = location.getLatitude(); // 经度
                longitude = location.getLongitude(); // 纬度
            }
        }
    };

 /*
 *
 * 打开和关闭gps第二种方法
 * private void openGPSSettings() {
 //获取GPS现在的状态（打开或是关闭状态）
 boolean gpsEnabled = Settings.Secure.isLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER);
 if (gpsEnabled) {
 //关闭GPS
 Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, false);
 } else {
 //打开GPS
 Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, true);
 }
 }*/

//
public  class down_PROSS extends AsyncTask<Void, Integer, Void>{
    ProgressBar psb;
    public void ready(ProgressBar pp){
        psb=pp;
    }
    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 0; i < 100; i++) {
            //如果已经设置成cancel，则终止掉当前的进程
//                                    if(mTask.isCancelled()){
//                                        break;
//
//                                    }
            publishProgress(i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
//                                if(mTask.isCancelled()){
//                                    return;
//
//                                }
        psb.setProgress(values[0]);
    }
}





    class List_Made_Helper22 extends BaseAdapter {

        public List getLs5() {
            return ls5;
        }

        private List<Data_bean> data;//创建私有的Bean类的data
        //    ls1,ls2,ls3,ls4,ls5,
        private List ls1,ls2,ls3,ls4,ls5,ls6,ls7,ls8,ls9;
        private  String[] ccp;
        private Context context;

        String nid=null;




        public List_Made_Helper22(List<Data_bean> data, List l1, List l2, List l3, List l4, List l5,List l6,List l7,List l8,List l9,String sid, Context context) {
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
            this.nid=sid;
//            this.ccp=pp;

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
//            Button xxxx=view.findViewById(R.id.xxxx);
//                        <Button
//            android:id="@+id/xxxx"
//            android:layout_width="wrap_content"
//            android:layout_height="wrap_content"
//            android:text="查看详细信息"
//            app:layout_constraintEnd_toEndOf="parent"
//            app:layout_constraintTop_toTopOf="parent" />


            Button  dow= view.findViewById(R.id.dow);

            ImageView img= view.findViewById(R.id.book_img);

//            SeekBar sb3 = view.findViewById(R.id.xzjdt);
//            下载进度滑块
            ProgressBar dow_prs=view.findViewById(R.id.dow_prs);


            int max=ls1.size();
            if(i<max){

                String rr= String.valueOf(ls6.get(i));

                String o1,o2,o3,o4,o5,o6,o7,o8,o9,pid;
                o1=String.valueOf(ls1.get(i));
                o2=String.valueOf(ls2.get(i));
                o3=String.valueOf(ls3.get(i));
                o4=String.valueOf(ls4.get(i));
                o5=String.valueOf(ls5.get(i));
                o6=String.valueOf(ls6.get(i));
                o7=String.valueOf(ls7.get(i));
                o8=String.valueOf(ls8.get(i));
                o9=String.valueOf(ls9.get(i));
                pid=this.nid;

                textView.setText(String.valueOf(ls1.get(i)));
                textView2.setText(String.valueOf(ls2.get(i)));
                textView3.setText(String.valueOf(ls3.get(i)));
                textView4.setText(String.valueOf(ls4.get(i)));
                textView5.setText(String.valueOf(ls5.get(i)));





//                ImageView img=  findViewById(R.id.book_img);

                ImageOptions imageOptions;
                imageOptions = new ImageOptions.Builder()
                        .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                        .setRadius(DensityUtil.dip2px(5))
// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                        .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
// 加载中或错误图片的ScaleType
//.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                        .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                        .setLoadingDrawableId(R.mipmap.ic_launcher)
                        .setFailureDrawableId(R.drawable.imgbad)
                        .build();
//            x.image().bind(img, "https://5b0988e595225.cdn.sohucs.com/images/20170922/fe15d13a3e764a3bbaede340e47692ca.jpeg", imageOptions);//加载图片的控件，和加载网络图片的地址

                x.image().bind(img, rr, imageOptions);//加载图片的控件，和加载网络图片的地址

//                Log.d("WWWWWWWWWWTTTTTTTTTTTImG", rr);
//                Log.d("RRRRRRRRRRRRRRRRRRRRRRRR", String.valueOf(img));
//                Log.d("RRRRRRRRRRRRRRRRRRRRRRRR", String.valueOf(textView3));





//                List<String> mpl=mp3_file_name_get_helper();
//                String[] ccp= mpl.toArray(new String[0]);
//                String uu="Burt's Requiem";
//
//                Log.d("333333333333333333333", String.valueOf(mpl));
//                Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains(uu)));
//                Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains("uu")));

//                if(Arrays.asList(ccp).contains(dow.getText())){
//                    Log.d("333333333333333333333", "存在！！！！");
//                    dow.setText("PLAY!!");
//                }else {
//
//                }

//                if(i==1){
//                    dow.setText("你是猪猪");
//                }

                dow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("我是LIST", o2);

//                        ListView mdlist=findViewById(R.id.sclist2);
                        List<String> mpl=mp3_file_name_get_helper();
        String[] ccp= mpl.toArray(new String[0]);
        String uu="Burt's Requiem";


        if(Arrays.asList(ccp).contains(o2)){
// 创建Toast实例
            Toast toast = Toast.makeText(getApplicationContext(), "文件已经存在！！！", Toast.LENGTH_SHORT);
// 显示Toast
            toast.show();

            Intent intent = new Intent();
            intent.setClass(MainActivity.this,musicplayer3.class);



            intent.putExtra("key",pid);
            intent.putExtra("kouwei",o1);
            intent.putExtra("name",o2);
            intent.putExtra("shicai",o3);
            intent.putExtra("caiputx",o4);
            intent.putExtra("ziliao",o5);
            intent.putExtra("id",o6);
            intent.putExtra("imgurl",o7);
            intent.putExtra("baike",o8);
            intent.putExtra("caixi",o9);

            System.out.println(o7);

            System.out.println("地址？？？？？？？");



            intent.putExtra("key","NSZZ");

            startActivity(intent);
//            finish();


        }else {

            dow_prs.setVisibility(View.VISIBLE);
            down_controler(dow_prs,o5,"bzd",1);
//            最后一个参数等于1会下载，其他不下载

        }

//
//        Log.d("333333333333333333333", String.valueOf(mpl));
//        Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains(uu)));
//        Log.d("333333333333333333333", String.valueOf(Arrays.asList(ccp).contains("uu")));
//
//                        List<String> ty=mp3_file_get_helper();
//
//                        Log.d("MMMMMMMMMMMPPPPPPPPPPP3333", String.valueOf(ty));






                    }
                });


//                xxxx.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        System.out.println("注意！！！！！！！！！！");
//                        System.out.println(o1);
//                        System.out.println(o2);
//                        System.out.println(o3);
//                        System.out.println(o4);
//                        System.out.println(o5);
//                        System.out.println(o6);
//                        System.out.println(o7);
//                        System.out.println(o8);
//                        System.out.println(o9);
//
//                        Intent intent = new Intent();
//                        intent.setClass(MainActivity.this,musicplayer3.class);
//
//
//
//                        intent.putExtra("key",pid);
//                        intent.putExtra("kouwei",o1);
//                        intent.putExtra("name",o2);
//                        intent.putExtra("shicai",o3);
//                        intent.putExtra("caiputx",o4);
//                        intent.putExtra("ziliao",o5);
//                        intent.putExtra("id",o6);
//                        intent.putExtra("imgurl",o7);
//                        intent.putExtra("baike",o8);
//                        intent.putExtra("caixi",o9);
//
//                        System.out.println(o7);
//
//                        System.out.println("地址？？？？？？？");
//
//
//
//                        intent.putExtra("key","NSZZ");
//
//                        startActivity(intent);
//                        finish();
//
//
//
//
//
//                    }
//                });



            }else {
                textView.setText("NULL");
                textView2.setText("NULL");
                textView3.setText("NULL");
                textView4.setText("NULL");
                textView5.setText("NULL");
            }

            if(i==19){
                new_mp3_list();
//                等列表更新完成，查询是否已经存在文件


            }

            return view;
        }
    }



    public  List<String> mp3_file_name_get_helper(){
        final int PERMISSION_REQUEST_CODE = 1;
        ListView listView;
        List<String> mp3Files;
        mp3Files = new ArrayList<>();

        // 检查并请求读取存储权限
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        } else {
//            getMp3Files();

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

            File directory = new File(path);
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".mp3")) {
                        String fn=file.getName();
                        String[] parts = fn.split("\\.");
                        mp3Files.add(parts[0]);
                    }
                    if (file.isFile() && file.getName().endsWith(".m4a")) {
//                        mp3Files.add(file.getName());
                        String fn=file.getName();
                        String[] parts = fn.split("\\.");
                        mp3Files.add(parts[0]);
                    }
                }
            }

//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mp3Files);
//        listView.setAdapter(adapter);
            return mp3Files;

        }



        return mp3Files;






    }
    public  List<String> mp3_file_get_helper(){
         final int PERMISSION_REQUEST_CODE = 1;
        ListView listView;
         List<String> mp3Files;
        mp3Files = new ArrayList<>();

        // 检查并请求读取存储权限
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        } else {
//            getMp3Files();

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

            File directory = new File(path);
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".mp3")) {
                        mp3Files.add(file.getName());
                    }
                    if (file.isFile() && file.getName().endsWith(".m4a")) {
                        mp3Files.add(file.getName());
                    }
                }
            }

//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mp3Files);
//        listView.setAdapter(adapter);
            return mp3Files;

        }




//        private void getMp3Files() {
//            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
//
//            File directory = new File(path);
//            File[] files = directory.listFiles();
//
//            if (files != null) {
//                for (File file : files) {
//                    if (file.isFile() && file.getName().endsWith(".mp3")) {
//                        mp3Files.add(file.getName());
//                    }
//                }
//            }
//
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mp3Files);
////        listView.setAdapter(adapter);
//        }

        return mp3Files;






        }


       public void new_mp3_list(){

        List<String> rr=new ArrayList<>();
        List<String> mpfile=new ArrayList<>();

//           String[] arr = mpfile.toArray(new String[0]);
//           String target = "apple";
//           List<String> list = Arrays.asList(arr);
//        List<String> mplist = Array;

           mpfile=mp3_file_get_helper();
        ListView lst=findViewById(R.id.sclist2);
//        String ss= String.valueOf(lst.getAdapter());
           for (int i = 0; i < lst.getChildCount(); i++) {
               View childView = lst.getChildAt(i);
               // 对子View进行操作
               TextView textView2 = childView.findViewById(R.id.pusname);
               String ee= String.valueOf(textView2.getText());


               rr.add(String.valueOf(textView2.getText()));

               for(int b=0;b<mpfile.size();b++){


                   String str =mpfile.get(b);
                   String[] parts = str.split("\\.");

                   Log.d("NNNNNNNNNNN", Arrays.toString( parts)+"----"+ee);

                   if( parts[0].equals(ee)){
                       Log.d("iiiiiiiiiiiisssssssss", "文件存在");
                       Log.d("iiiiiiiiiiiisssssssss", Arrays.toString( parts));

//                       TextView textView2 = childView.findViewById(R.id.pusname);
                       Button cgbt=childView.findViewById(R.id.dow);
                       cgbt.setText("播放");
                   }


               }
           }
        Log.d("aaaaaaaaaaaaaa", String.valueOf(rr));

           Log.d("aaaaaaaaaaaaaa", String.valueOf(mpfile));






        }

        public void down_controler(ProgressBar scb,String url,String save,int start) {
//            url="https://freepd.com/music/Amazing%20Grace.mp3";


//            ResponseBody responseBody = response.body();
            //总长度
//            long length = responseBody.contentLength();


            if(start==1){


//    scb.setProgress((int) 50);

    Log.d("下载>>>>>>>>>", "onFailure:阿尔发顺丰分威风 ");


    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url).build();



    client.newCall(request).enqueue(new okhttp3.Callback() {
        @Override
        public void onFailure(okhttp3.Call call, IOException e) {
            Log.d(TAG, "onFailure: " + e.getMessage());
        }

        @Override
        public void onResponse(okhttp3.Call call, Response response) throws IOException {


            ResponseBody responseBody = response.body();


//            ResponseBody responseBody = response.body();

            long length = responseBody.contentLength();
            Log.d("长度！！！", String.valueOf(length));


            ProgressResponseBody progressResponseBody = new ProgressResponseBody(responseBody, new ProgressListener() {
                @Override
                public void onProgress(long currentBytes, long contentLength, boolean done) {
                    float percent = (float) currentBytes / (float) contentLength;
                    Log.d(TAG, "progress: " + percent);
                    scb.setProgress((int) percent);


                    Log.d("百分比>>>>>>>>>", "onFailure:阿尔发顺丰分威风 ");

                }
            });


            String encodedUrl = URLEncoder.encode(url, "UTF-8");
            Log.d("UURLLL解码", encodedUrl);

            InputStream in = response.body().byteStream();


            // 获取文件名
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            // 获取目录路径
            Log.d("下载>>>>>>>>>", "onFailure:阿尔发顺丰分威风 ");

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

//                        String jsonStr = new String(fi, StandardCharsets.UTF_8);//把原始数据转为字符串

            String bd = fileName.replaceAll("%20", " ");

            File file = new File(path, bd);
            FileOutputStream out = new FileOutputStream(file);



            int numread=0;

            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {

                Log.d("下载>>>>>>>>>", len + "%%%%%%%");
                numread+=len;
                scb.setProgress((int) (100*numread/length));



                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
            in.close();

            scb.setVisibility(View.INVISIBLE);

//            new_mp3_list();

            // 通过Handler将结果发送到主线程
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // 在主线程中更新UI
                    new_mp3_list();
                }});


        }
    });



}else {

//    scb.setProgress((int) 100);


}
        }





    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getMp3Files();
            } else {
                // 没有获取到权限，可以给出相应提示或者处理逻辑
            }
        }



    }







}