

package com.example.valiutos;

import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.valiutos.AsyncDataLoader;
        import com.example.valiutos.Constants;

        import java.io.IOException;
        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvItems;
    private TextView tvStatus;
    private ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvItems = findViewById(R.id. lv_output);
        this.tvStatus = findViewById(R.id. tv_text);

        this.listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        this.lvItems.setAdapter(this.listAdapter);
    }

    public void get_output_api(View view) {
        this.tvStatus.setText(R.string.loading_data);
        getDataByAsyncTask();
        Toast.makeText(this, R.string.msg_using_async_task, Toast.LENGTH_LONG).show();
    }

    public void getDataByAsyncTask(){
        AsyncDataLoader dataLoader =
                new AsyncDataLoader() {
                    @Override
                    public void onPostExecute(String result) {
                        tvStatus.setText(getString(R.string.data_loaded) + result);
                    }
                };
        dataLoader.execute(Constants.METEOLT_API_URL);
    }
}