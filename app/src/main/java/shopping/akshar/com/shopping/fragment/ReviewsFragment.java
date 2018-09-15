package shopping.akshar.com.shopping.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shopping.akshar.com.shopping.R;
import shopping.akshar.com.shopping.adapter.Viewpageadaptor;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends Fragment {

   private TabLayout tab_review;
   private ViewPager Pager;
   private Viewpageadaptor viewpageadaptor;

    public ReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        tab_review = view.findViewById(R.id.tab_review);
        Pager = view.findViewById(R.id.Pager);
        viewpageadaptor = new Viewpageadaptor(getFragmentManager());

        viewpageadaptor.Addfragment(new AddReviewFragment(),"Add Review");

        viewpageadaptor.Addfragment(new ViewReviewFragment(),"View Review");

        Pager.setAdapter(viewpageadaptor);
        tab_review.setupWithViewPager(Pager);

        return view;
    }

}
