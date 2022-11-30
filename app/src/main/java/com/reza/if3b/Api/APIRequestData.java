package com.reza.if3b.Api;

import com.reza.if3b.Model.QuotesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("quotes")
    Call<List<QuotesModel>> requestData() ;
}