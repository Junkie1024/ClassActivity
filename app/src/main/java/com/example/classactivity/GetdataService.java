package com.example.classactivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetdataService {

    @GET("Vy2abloQD")
    Call<List<Pokemon>> getPokemons();

}
