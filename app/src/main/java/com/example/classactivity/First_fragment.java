package com.example.classactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class First_fragment extends Fragment {

    ArrayList<Pokemon> pokemons;
    Recyadapter adapter;
    Recyadapter recyadapter;
    RecyclerView recyclerView;
    public NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_fragment, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_poke);
        navController = Navigation.findNavController(getActivity(),R.id.host_fragment );
        GetdataService service = RetrofitInstance.getRetrofitInstance().create(GetdataService.class);

        Call<List<Pokemon>> call = service.getPokemons();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                System.out.println(response.body());
                initView(response.body());
            }
            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),"Something Went Wrong!!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initView(List<Pokemon> poklist){

        pokemons = (ArrayList<Pokemon>) poklist;
        adapter = new Recyadapter(pokemons,getActivity().getApplicationContext());
        @SuppressLint("WrongConstant") LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(onClickListener);
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Toast.makeText(getActivity().getApplicationContext(),pokemons.get(position).getName(),Toast.LENGTH_SHORT).show();
            Bundle data = new Bundle();
            data.putString("name",pokemons.get(position).getName());
            data.putString("type",pokemons.get(position).getType());
            data.putString("height",pokemons.get(position).getHeight());
            data.putString("weight",pokemons.get(position).getWeight());
            data.putString("ability",pokemons.get(position).getAbility());
            data.putString("description",pokemons.get(position).getDescription());
            data.putString("image_url",pokemons.get(position).getImage());


            navController.navigate(R.id.pokemoneDesc,data);

        }
    };

}
