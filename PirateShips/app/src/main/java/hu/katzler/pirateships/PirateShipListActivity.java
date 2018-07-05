package hu.katzler.pirateships;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.katzler.pirateships.model.Ship;

/**
 * An activity representing a list of PirateShips. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PirateShipDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PirateShipListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private GetListAsyncTask getListAsyncTask;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.pirateship_list)
    RecyclerView recyclerView;
    private SimpleItemRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirateship_list);

        ButterKnife.bind(this, this);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.pirateship_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        //View recyclerView = findViewById(R.id.pirateship_list);
        //assert recyclerView != null;
        setupRecyclerView(recyclerView);

        getListAsyncTask = new GetListAsyncTask();
        getListAsyncTask.execute();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter = new SimpleItemRecyclerViewAdapter(this, new ArrayList<Ship>(), mTwoPane);
        recyclerView.setAdapter(adapter);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final PirateShipListActivity mParentActivity;
        private final List<Ship> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ship item = (Ship) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt(PirateShipDetailFragment.ARG_ITEM_ID, item.getId());
                    PirateShipDetailFragment fragment = new PirateShipDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.pirateship_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, PirateShipDetailActivity.class);
                    intent.putExtra(PirateShipDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(PirateShipListActivity parent,
                                      List<Ship> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pirateship_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.tvTitle.setText(mValues.get(position).getTitle());
            holder.tvPrice.setText(String.format("%d GOLD", mValues.get(position).getPrice()));

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public void update(List<Ship> ships) {
            mValues.clear();
            mValues.addAll(ships);
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView ivImage;
            final TextView tvTitle;
            final TextView tvPrice;

            ViewHolder(View view) {
                super(view);
                ivImage = (ImageView) view.findViewById(R.id.ivImage);
                tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            }
        }
    }


    class GetListAsyncTask extends AsyncTask<Void, Void, List<Ship>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //loading screen
        }

        @Override
        protected List<Ship> doInBackground(Void... voids) {
            List<Ship> list = App.get(PirateShipListActivity.this).getApplicationComponent().getPirateShipDownloader().downloadList();
            return list;
        }

        @Override
        protected void onPostExecute(List<Ship> ships) {
            super.onPostExecute(ships);
            Log.i(getClass().getSimpleName(), "ships: " + ships);
            adapter.update(ships);
        }
    }
}
