package me.marosi.applist;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    AppListAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AppListAdapter(getInstalledApps());
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.save)
    public void saveClicked() {

    }

    private List<AppData> getInstalledApps() {
        List<AppData> appDataList = new ArrayList<>();
        PackageManager pm = getPackageManager();

        List<ApplicationInfo> packages = pm.getInstalledApplications(0);
        for (ApplicationInfo appInfo : packages) {
            if (isUserApp(appInfo) && !getPackageName().equals(appInfo.packageName))
                appDataList.add(new AppData(pm, appInfo));
        }

        Collections.sort(appDataList, new Comparator<AppData>() {
            @Override
            public int compare(AppData app1, AppData app2) {
                return app1.getName().compareTo(app2.getName());
            }
        });

        return appDataList;
    }

    static boolean isUserApp(ApplicationInfo appInfo) {
        int mask = ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;
        return (appInfo.flags & mask) == 0;
    }

//    private void saveJson(Object object, Type type, String directory, String fileName) {
//
//        File file = new File(getApplicationContext().getDir(directory, Context.MODE_PRIVATE),
//                fileName);
//        OutputStream outputStream = null;
//        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting()
//                .create();
//        try {
//            outputStream = new FileOutputStream(file);
//            BufferedWriter bufferedWriter;
//            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,
//                        StandardCharsets.UTF_8));
//            } else {
//                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//            }
//
//            gson.toJson(object, type, bufferedWriter);
//            bufferedWriter.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            if (DEBUG) Log.e(saveJson, "saveUserData, FileNotFoundException e: '" + e + "'");
//        } catch (IOException e) {
//            e.printStackTrace();
//            if (DEBUG) Log.e(saveJson, "saveUserData, IOException e: '" + e + "'");
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.flush();
//                    outputStream.close();
//                } catch (IOException e) {
//                    if (DEBUG) Log.e(saveJson, "saveUserData, finally, e: '" + e + "'");
//                }
//            }
//        }
//
//    }
//
//
//    private Object loadJson(Type type,  String directory, String fileName) {
//        Object jsonData = null;
//
//        File file = new File(getApplicationContext().getDir(directory, Context.MODE_PRIVATE),
//                fileName);
//        InputStream inputStream = null;
//        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting()
//                .create();
//        try {
//            inputStream = new FileInputStream(file);
//            InputStreamReader streamReader;
//            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                streamReader = new InputStreamReader(inputStream,
//                        StandardCharsets.UTF_8);
//            } else {
//                streamReader = new InputStreamReader(inputStream, "UTF-8");
//            }
//
//            jsonData = gson.fromJson(streamReader, type);
//            streamReader.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            if (DEBUG) Log.e(TAG, "loadJson, FileNotFoundException e: '" + e + "'");
//        } catch (IOException e) {
//            e.printStackTrace();
//            if (DEBUG) Log.e(TAG, "loadJson, IOException e: '" + e + "'");
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    if (DEBUG) Log.e(TAG, "loadJson, finally, e: '" + e + "'");
//                }
//            }
//        }
//        return jsonData;
//    }
}
