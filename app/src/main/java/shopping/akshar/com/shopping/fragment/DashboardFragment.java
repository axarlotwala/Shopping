package shopping.akshar.com.shopping.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.adapter.Dashboard_adaptor;
import shopping.akshar.com.shopping.pojo.Dashboardmodel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    TextView brand_label;
    RecyclerView brand_dashboard;
    List<Dashboardmodel> dashboardmodels;
    String Dashboard_url = "http://192.168.0.103/shopping/brand.php";



    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        brand_label = view.findViewById(R.id.brand_label);
        brand_dashboard = view.findViewById(R.id.brand_dashboard);
        dashboardmodels = new ArrayList<>();
        view_banner();
        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
        brand_dashboard.setLayoutManager(manager);

        return view;
    }

    private void view_banner() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Dashboard_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i=0;i<response.length();i++){

                    try {
                        jsonObject = response.getJSONObject(i);
                        Dashboardmodel dashboardmodel = new Dashboardmodel();
                        dashboardmodel.setName(jsonObject.getString("brand_name"));
                        dashboardmodel.setImage(jsonObject.getString("brand_image"));

                        dashboardmodels.add(dashboardmodel);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Dashboard_adaptor dashboard_adaptor = new Dashboard_adaptor(getActivity(),dashboardmodels);
                brand_dashboard.setAdapter(dashboard_adaptor);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

}
