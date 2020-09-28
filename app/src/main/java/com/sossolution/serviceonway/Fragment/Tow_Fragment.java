package com.sossolution.serviceonway.Fragment;

import androidx.fragment.app.Fragment;

public class Tow_Fragment extends Fragment {
   /* TextView textView;
    Fragment frag1;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    Towdapter towdapter;
    List<Newmodel> datalisttwo;
    GridLayout gridLayout;
*/

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tow_, container, false);
        //textView=view.findViewById(R.id.two);

        String Car="car";
        String Honda="honda";

        hitapimodel(Car,Honda);
        //recyclerview add

        recyclerView=view.findViewById(R.id.recyclerviewtwo);
       // datalisttwo = new ArrayList<>();
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,GridLayoutManager.VERTICAL,false));
        gridLayout = view.findViewById(R.id.gridview1);


        // textView.setText(getArguments().getString("msg"));
        return view;
    }*/

  /*  private void hitapimodel(String Car,String Honda)
    {

        String url="https://serviceonway.com/Get_All_Model_Details?veh="+Car+"&maker="+Honda;

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        String model=jsonObject.getString("model");

                       datalisttwo.add(new Newmodel(model));

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getActivity(),e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                towdapter=new Towdapter(getContext(), (ArrayList<Newmodel>) datalisttwo);
                recyclerView.setAdapter(towdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<String, String>();
                return hashMap;

            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);





    }*/

   /* public static Tow_Fragment newInstance(String text) {

        Tow_Fragment tow_fragment= new Tow_Fragment();
        Bundle b = new Bundle();
        b.putString("msg", text);
        tow_fragment.setArguments(b);
        return tow_fragment;
    }*/

    /*@Override
    public void fragmentJump(Storedata mItemSelected1){

        Tow_Fragment  mFragment = new Tow_Fragment();
        Bundle mBundle = new Bundle();
        mBundle.putParcelable("item_selected_key", (Parcelable) mItemSelected1);
        mFragment.setArguments(mBundle);
        //switchContent(R.id.frag1, mFragment);
        ((Allcontent_Activity)getActivity()).switchContent(frag1, mFragment);
    }*/

    /*public void  towfragment(Storedata storedata)
    {
       Tow_Fragment tow_fragment= new Tow_Fragment();
       Bundle bundle= new Bundle();
       bundle.putParcelable("item key", (Parcelable) storedata);
       tow_fragment.setArguments(bundle);
        //switchContent(R.id., mFragment);
    }
*/
}
