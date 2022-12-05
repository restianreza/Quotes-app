package com.reza.if3b.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.reza.if3b.Adapter.AdapterQuotes;
import com.reza.if3b.Api.APIRequestData;
import com.reza.if3b.Api.RetroServer;
import com.reza.if3b.Model.QuotesModel;
import com.reza.if3b.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvQuotes;
    private ProgressBar pbQuotes;
    private List<QuotesModel> listQuotes;
    private AdapterQuotes adQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvQuotes = findViewById(R.id.rv_qoutes);
        pbQuotes = findViewById(R.id.pb_qoutes);

        rvQuotes.setLayoutManager(new LinearLayoutManager(this));
        retrieveQuote();
    }

    private void retrieveQuote(){
        pbQuotes.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<List<QuotesModel>> retrieveProcess = ARD.requestData();

        retrieveProcess.enqueue(new Callback<List<QuotesModel>>() {
            @Override
            public void onResponse(Call<List<QuotesModel>> call, Response<List<QuotesModel>> response) {
                listQuotes = response.body();
                adQuotes = new AdapterQuotes(listQuotes, MainActivity.this);
                rvQuotes.setAdapter(adQuotes);
                pbQuotes.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<QuotesModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Mengubungi Server", Toast.LENGTH_SHORT).show();
                pbQuotes.setVisibility(View.GONE);
            }
        });
    }
}