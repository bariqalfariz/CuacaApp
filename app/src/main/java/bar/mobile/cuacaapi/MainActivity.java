package bar.mobile.cuacaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import bar.mobile.cuacaapi.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromApi();
    }

    private void getDataFromApi() {
        ApiService.endpoint().getData().enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if (response.isSuccessful()){
                    MainModel data = response.body();
                    tv.setText(data.getDaily().getWeathercode().get(1).toString());
                    Log.d(TAG, data.toString());
                }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }
}