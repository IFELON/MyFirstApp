package in.suriya.recyclerviewapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mcontext;
    private ArrayList<ExampleItem>mExampleList;

    public ExampleAdapter(Context context,ArrayList<ExampleItem>examplelist){
        mcontext = context;
        mExampleList =examplelist;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mcontext).inflate(R.layout.example_item,viewGroup,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {

        ExampleItem currentitem = mExampleList.get(i);

        String imageurl = currentitem.getmImageUrl();
        String CreatorName = currentitem.getmCreator();
        int likeCount = currentitem.getmLikes();

        exampleViewHolder.mTextViewCreator.setText(CreatorName);
        exampleViewHolder.mTextViewLikes.setText("Likes  :"+ likeCount);

        Picasso.with(mcontext).load(imageurl).fit().centerInside().into(exampleViewHolder.mImageview);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageview;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageview = itemView.findViewById(R.id.imge_View);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);
        }
    }
}
