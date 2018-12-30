package shopping.akshar.com.shopping.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.adapter.Gradiadaptor;
import shopping.akshar.com.shopping.pojo.Graidmodel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraidFragment extends Fragment {

    RecyclerView graid;
    private List<Graidmodel> graidmodels;
    private String Local_image = "http://192.168.0.103/shopping/imageList.php";

    public GraidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graid, container, false);
        graid = view.findViewById(R.id.graid);
        graidmodels = new ArrayList<>();
        dashboard();
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        graid.setLayoutManager(manager);

        return view;
    }

    private void dashboard() {

        JsonArrayRequest request = new JsonArrayRequest(Local_image, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i=0; i<response.length() ; i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        Graidmodel graidmodel = new Graidmodel();
                        graidmodel.setUrl(jsonObject.getString("url"));
                        graidmodel.setName(jsonObject.getString("name"));
                        graidmodels.add(graidmodel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Gradiadaptor gradiadaptor = new Gradiadaptor(getActivity(),graidmodels);
                graid.setAdapter(gradiadaptor);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

}
