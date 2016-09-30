package com.games.cyko.storelocator.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.games.cyko.storelocator.R;
import com.games.cyko.storelocator.controller.StoreAdapter;
import com.games.cyko.storelocator.controller.UserSessionManager;
import com.games.cyko.storelocator.models.DBHelper;
import com.games.cyko.storelocator.models.StoreItems;

import java.util.List;


public class StoreFragment extends Fragment {

    /*** check the current user session ***/
    UserSessionManager session;

    private RecyclerView mapView;
    private StoreAdapter store_controller;
    private Button add_store;
    private List listData;
    private EditText add_store_name;
    private Button reset;
    private DBHelper store_db ;

    public StoreFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_store, container, false);
        setHasOptionsMenu(true);
        store_db = new DBHelper(v.getContext());

        //get session
        session = new UserSessionManager(v.getContext());
        //check if the a use exists
        if(session.checkLogin()){
            //finish();
            getActivity().finish();
        }

        listData = store_db.getListData();

        mapView = (RecyclerView)v.findViewById(R.id.store_list);
        mapView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        store_controller = new StoreAdapter(listData, v.getContext());
        mapView.setAdapter(store_controller);

        add_store_name = (EditText)v.findViewById(R.id.txt_add_store_name);
        reset = (Button)v.findViewById(R.id.btn_reset_list);
        //handle the delete events, pass in the method handle
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(mapView);

        //adding the store into the list view
        add_store = (Button) v.findViewById(R.id.btn_add_store);
        add_store.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addStore();
            }
        });


        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                store_db.deleteAllStores();
                listData.clear();
                store_controller.notifyDataSetChanged();
            }
        });

        return v;
    }

    private ItemTouchHelper.Callback createHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                moveStore(viewHolder.getAdapterPosition(),target.getAdapterPosition());

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deleteStore(viewHolder.getAdapterPosition());
            }
        };

        return simpleCallback;
    }

    private void addStore(){
        if(!add_store_name.getText().toString().isEmpty()){
            StoreItems item  = store_db.getRandomListItem(add_store_name.getText().toString());
            listData.add(item);
            store_controller.notifyItemInserted(listData.indexOf(item));
            add_store_name.setText("");
        }else{
            Toast.makeText(getContext(),"please add store name", Toast.LENGTH_SHORT).show();
        }

    }

    private void moveStore(int oldPos, int newPos){
        StoreItems item = (StoreItems) listData.get(oldPos);
        listData.remove(oldPos);
        listData.add(newPos, item);
        store_controller.notifyItemMoved(oldPos, newPos);
    }

    private void deleteStore(final int position){
        StoreItems item = (StoreItems) listData.get(position);

        Toast.makeText(getContext(),"" + item.getAd_hoc_id(),Toast.LENGTH_LONG).show();
        store_db.deleteStore(item.getAd_hoc_id());
        listData.remove(position);
        store_controller.notifyItemRemoved(position);
    }
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.store, menu);
        super.onCreateOptionsMenu(menu,inflater);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                session.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
