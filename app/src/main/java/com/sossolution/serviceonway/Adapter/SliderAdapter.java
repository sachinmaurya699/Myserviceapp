package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.sossolution.serviceonway.R;
import com.squareup.picasso.Picasso;

public class SliderAdapter  extends PagerAdapter{

   private Context context;
   private LayoutInflater inflater;
   private String[] urls;

    public SliderAdapter(Context context, String[] urls)
    {
        this.context=context;
        this.urls=urls;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View) object;
        container.removeView(view);


    }
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view=inflater.inflate(R.layout.slidernewimage,container,false);
        assert view != null;
        final ImageView imageView =view.findViewById(R.id.imageviewnew);
       // imageView.setImageResource(Integer.parseInt(String.valueOf(urls[position])));
        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        /*Glide.with(context)
                .load(urls[position])
                .resize(1000,400)
                .into(imageView);*/

        Picasso.get()
                .load(urls[position])
                .fit()
                .into(imageView);


      //  view.addView(view, 0);


        return view;
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
        super.restoreState(state, loader);

    }

    @Nullable
    @Override
    public Parcelable saveState()
    {
        return super.saveState();
    }
}
