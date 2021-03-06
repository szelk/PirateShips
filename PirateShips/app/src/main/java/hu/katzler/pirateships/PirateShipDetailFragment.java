package hu.katzler.pirateships;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.katzler.pirateships.model.Ship;

/**
 * A fragment representing a single Ship detail screen.
 * This fragment is either contained in a {@link PirateShipListActivity}
 * in two-pane mode (on tablets) or a {@link PirateShipDetailActivity}
 * on handsets.
 */
public class PirateShipDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Ship ship;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PirateShipDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            int id = getArguments().getInt(ARG_ITEM_ID);
            ship = App.get(getActivity()).getApplicationComponent().getPirateShipDownloader().getShip(id);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(ship.getTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pirateship_detail, container, false);
        TextView tvDetail =  (TextView) rootView.findViewById(R.id.tvDetail);
        TextView tvPrice = (TextView) rootView.findViewById(R.id.tvPrice);

        if (ship != null) {
            tvDetail.setText(ship.getDescription());
            tvPrice.setText(String.format("%d GOLD", ship.getPrice()));
        }

        return rootView;
    }
}
