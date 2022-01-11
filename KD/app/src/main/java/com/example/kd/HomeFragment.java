package com.example.kd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ProgressDialog loading;
    LinearLayout gone,recview;
    String query;
    SearchView searchView;
    SearchAdapter adapter;
    RecyclerView searchedProducts;
    ArrayList<PhoneModel> datalist=new ArrayList<>();

    String phone=null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        loading = new ProgressDialog(getContext());
        loading.setCancelable(true);
        loading.setMessage("Loading...");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);

//        Button search = (Button) view.findViewById(R.id.search_button);
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), Product.class);
//                startActivity(i);
//            }
//        });
        recview=view.findViewById(R.id.recview);
        searchedProducts = view.findViewById(R.id.searched_products);
        searchedProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        gone = view.findViewById(R.id.to_be_gone);
        gone.setVisibility(View.VISIBLE);
        searchView = view.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals("")) {
                    searchedProducts.setVisibility(View.GONE);
                    recview.setVisibility(View.GONE);
                    gone.setVisibility(View.VISIBLE);
                } else {
                    recview.setVisibility(View.VISIBLE);
                    searchedProducts.setVisibility(View.VISIBLE);
                    gone.setVisibility(View.GONE);
                }
                query = s;
                return false;
            }
        });
        adapter = new SearchAdapter(datalist,getContext());
        searchedProducts.setAdapter(adapter);


        MaterialButton search = (MaterialButton) view.findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getdatafromfirebase();

            }
        });



        return view;}
        public void getdatafromfirebase (){
            String [] brands={"Alcatel","Apple","Calme","Samsung","GFive","HTC","Huawei","Infinix","Itel",
                    "LG","Motorola","Nokia","OnePlus","Oppo","QMobile","Realme","Sony","Tecno","Telenor","Vivo","Xiaomi","Other"};
            FirebaseDatabase db= FirebaseDatabase.getInstance();
            for(int i=0;i<brands.length;++i) {
                DatabaseReference ref = db.getReference().child("Mobile").child("BrandName").child(brands[i]);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot snap : snapshot.getChildren()) {
                            Log.d("TAG", "snapshot  : ");

                            //GenericTypeIndicator<HashMap<String,Object>> t = new GenericTypeIndicator<HashMap<String, Object>>() {
                            //    };
                            //HashMap<String,Object> hash = snapshot.getValue(t);
                            //for(Map.Entry element: hash.entrySet()) {
                            String a = (String) snap.getKey().toString();
                            //Log.d("Usman", ""+a);
                            if (a.toLowerCase().contains(query.toLowerCase())) {
                                Log.d("Search result", "" + a);
                                PhoneModel obj = new PhoneModel();
                                //  DataSnapshot snap2 = (DataSnapshot) snap.getChildren();
                                //   obj.setName(snap.getValue(String.class));
                                obj.setName(a);
                                obj.setBrand(snapshot.getKey().toString());
                                DatabaseReference imgref = FirebaseDatabase.getInstance().getReference("Mobile").child("BrandName").child(brands[1]).child(a);
                                imgref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot imgshot) {
                                        Boolean img = false;
                                        for (DataSnapshot imgsnap : imgshot.getChildren()) {
                                            if (imgsnap.child("Image").getValue(String.class) != null && img == false) {
                                                obj.setImage(imgsnap.child("Image").getValue(String.class));
                                                img = true;
                                                // Log.d("TAG", "onDataChange: " +imgsnap.child("Image").getValue(String.class));
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                // obj.setId(snap.getKey());
                                //if(snap2.hasChild("Image")){
                                //  Log.d("Usman", " yes image");
                                //}

                                //obj.setImage(snap2.child("Image").getValue(String.class));
                                //obj.setPrice(snap.child("Price").getValue(String.class));
                                //obj.setLink(snap.child("VendorLink").getValue(String.class));
                                //String price = snap2.child("Price").getValue(String.class);
                                //if(price!=null){
                                // String[] pricee=price.split("Rs");
                                //if(pricee.length>=2){
                                //obj.setPrice1(Integer.parseInt(pricee[1].replaceAll("[\\D]","")));
                                //}}

                                datalist.add(obj);
                                adapter.notifyDataSetChanged();
                            }
                            //}

//                //Log.d("Usman", ""+a);
                        }
                    }

                    //
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }}

}
