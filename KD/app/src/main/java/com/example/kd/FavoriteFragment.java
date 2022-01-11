package com.example.kd;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//import com.google.gson.Gson;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    RecyclerView recyclerViewFavoriteProduct;
    ArrayList<FavoriteModel> favoriteProducts = new ArrayList<>();

    FavoriteAdapter adapter;
    //ProgressDialog loading;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userEmail = user.getEmail();
    String[] user1=userEmail.split("@");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);



        recyclerViewFavoriteProduct = view.findViewById(R.id.favoritesRecyclerView);

        //    loading = new ProgressDialog(getContext());
        //    loading.setCancelable(true);
        //    loading.setMessage("Loading...");
        //    loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //    loading.show();

//        datalist = new ArrayList<>();
//        adapter = new SearchAdapter(datalist, getContext());
        adapter= new FavoriteAdapter(favoriteProducts,getContext());
        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(getContext());
        recyclerViewFavoriteProduct.setLayoutManager(layoutManagerProduct);
        recyclerViewFavoriteProduct.setAdapter(adapter);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference().child("Favourites").child(user1[0]);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    FavoriteModel obj= new FavoriteModel();
                    obj.setProductName(snap.child("productName").getValue(String.class));
                    obj.setBrand(snap.child("brand").getValue(String.class));
                    obj.setImage(snap.child("image").getValue(String.class));
                    favoriteProducts.add(obj);
                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        recyclerViewFavoriteProduct.setAdapter(new FavoriteAdapter(initDataTopCategory()));
        return view;
    }



//        db = FirebaseFirestore.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String userEmail = user.getEmail();
//
//
//        CollectionReference doc = db.collection("Users").document(userEmail).collection("Favourites");
//        doc.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                        for (DocumentSnapshot d : list) {
//                            SearchModel obj = new SearchModel();
//
//                            obj.setName(d.getString("name"));
//                            obj.setPrice(d.getString("price"));
//                            obj.setImage(d.getString("image"));
//                            obj.setData(d.getString("data"));
//                            obj.setLink(d.getString("link"));
//
//                            datalist.add(obj);
//                        }
//                        adapter.notifyDataSetChanged();
//
//                        if (loading.isShowing())
//                            loading.dismiss();
//                    }
//                });

}
