
package com.example.shoptrue.Utilities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import androidx.core.app.Fragment;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.shoptrue.ItemsDetail.ItemDetailsActivity;
import com.example.shoptrue.MainActivity;
import com.example.shoptrue.R;
import com.example.shoptrue.database.CartTable;
import com.example.shoptrue.database.DatabaseInstance;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

// ImageListFragment = zeigt Liste der Bilder im RecyclerView

// public static final String STRING_IMAGE_URI = "ImageUri"- String Konstante, die URI des Bildes von Aktivitäten zu übergeben
// mActivity - Referenz zur MainActivity, um auf Methoden der MainActivity zuzugreifen
// final = Konstant, kann nicht überschrieben werden, wenn über Intents () zugegriffen, um Fehler zu vermeiden
// static =  soll der ganzen Klasse gehören, unabhängig ob ein Objekt der Klasse erstellt wurde oder nicht


public class ImageListFragment extends Fragment {

    public static final String STRING_IMAGE_URI = "ImageUri";

    static DatabaseInstance instance;
    public static final String STRING_IMAGE_POSITION = "ImagePosition";
    private static MainActivity mActivity;

    // mActivity = (MainActivity) getActivity(); - Variable bekommt die Verbindung zur MainActivity

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        instance = DatabaseInstance.getInstance(mActivity);
    }

    // LayoutInflater - greift auf XML
    // RecyclerView - greift auf recyclerview_list und wird in der Variable rv gespeichert
    // false - da setupRecyclerView(rv) von Hand der RecyclerView zugeordnet ist

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.layout_recylerview_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    // RecylerView wird mit Daten gefüllt, damit im RasterLayout angezeigt
    // int [] items=null; - int - Array mit null, zeigt keine Daten enthalten und wird später von setupRecyclerView befüllt
    // items = ImageUrlUtils.getImageUrls(); - getImageUrls greift die Urls der KLasse ImageUrlUtils und speichert in items
    // StaggeredGridLayoutManager - dient als RasterLayout
    // recyclerView.setLayoutManager(layoutManager); - zuvor erstellte StaggeredGridLayoutManager wird RecycerlView zugewiesen
    // recyclerView.setAdapter - lädt die Daten der URLs in die recyclerView und zeigt die Bilder
    private void setupRecyclerView(RecyclerView recyclerView) {
        ArrayList<SingleItemModule> items=new ArrayList<SingleItemModule>();

            items = ImageUrlUtils.getImageUrls();

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(recyclerView, items));
    }

    // Adapterklasse - fügt Daten in die RecyclerView aus der mValues - Array
    // 1. Art des Adapter = zuständig für ein Single - Item (alle Bilder)
    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        // mValues = URLs der Bilder
        // mRecyclerView = Referenz zur RecyclerView
        private ArrayList<SingleItemModule> mValues;
        private RecyclerView mRecyclerView;

        // innere Klasse der ReyclerView
        // mView = Ansicht der Elemente
        // mImageView = Ansicht der Bilder
        // mLayoutItem = Layout der Elemente
        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView mImageView;
            public final LinearLayout mLayoutItem;

            public final TextView mName;

            public final TextView mDescription;

            public final TextView mPrice;

            // Aktualisiert die Views
            // Konstruktor der ViewHolder Klasse
            // findet image & layout_item byId
            public ViewHolder(View view) {
                super(view);
                mView = view;
                /* hier wird das View nochmal spezifiziert was gesucht wird */
                mImageView = view.findViewById(R.id.image1);
                mLayoutItem = (LinearLayout) view.findViewById(R.id.layout_item);
                mName = view.findViewById(R.id.item_name);
                mDescription = view.findViewById(R.id.description);
                mPrice = view.findViewById(R.id.price);

            }
        }

        // Konstruktor - damit Daten in die ReyclerView gegeben werden und Daten zu nutzen
        // weist mValues - Array die items  -Array zu
        public SimpleStringRecyclerViewAdapter(RecyclerView recyclerView, ArrayList<SingleItemModule> items) {
            mValues = items;
            mRecyclerView = recyclerView;
        }


        // Layout list-item wird abgerufen
        //parent - stellt einfach sicher, dass jedes Element entsprechend der Anordnung steht


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        // Zeigt die Bilder in der Liste
        // final ViewHolder holder - ViewHolder wird übergeben, der die Anzeige einzelner Elemente der RecyclerView enthält
        // final int position - Position bestimmt welches Bild angezeigt wird
        // Glide lädt die Bilder in MainActivity hoch ,indem die URL des Bildes bestimmt wird und fügt der ImageView hinzu

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {


            Glide.with(mActivity).load(mValues.get(position).image).into(holder.mImageView);
            holder.mName.setText(mValues.get(position).getName());
            holder.mPrice.setText(mValues.get(position).getPrice()+" €");
            holder.mDescription.setText(mValues.get(position).getDescription());
            holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {

            // Listener
                //  holder.mLayoutItem.setOnClickListener - wird dem Listener übergeben
                // Intent intent = new Intent(mActivity, ItemDetailsActivity.class) - start Intent der gesamten ImageListFragment in der MainActivity
                // mit URI & Position des Bildes

                @Override
                public void onClick(View v) {
                    instance.databaseDao().insertCartModel(new CartTable(mValues.get(position).image,mValues.get(position).name, mValues.get(position).description, mValues.get(position).price));
                    Intent intent = new Intent(mActivity, ItemDetailsActivity.class);
                    intent.putExtra(STRING_IMAGE_URI, mValues.get(position));
                    intent.putExtra(STRING_IMAGE_POSITION, position);
                    mActivity.startActivity(intent);

                }
            });

        }

       // herausforderungen: du weißt nicht, wie viele Produkte es gibt, wie es gescrollt werden soll
       // sobald die Liste in den Positionen überschritten wurde, wiederholt es sich
        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}
