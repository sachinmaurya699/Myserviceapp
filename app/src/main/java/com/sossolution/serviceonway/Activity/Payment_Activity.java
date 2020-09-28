package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.sossolution.serviceonway.R;

import org.json.JSONObject;

public class Payment_Activity extends AppCompatActivity implements PaymentResultWithDataListener {

    private Button buttonConfirmOrder;
    private EditText editTextPayment;
    private static final String TAG = Payment_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_);
        //checkout.setKeyID("<YOUR_KEY_ID>");
        buttonConfirmOrder = findViewById(R.id.buttonConfirmOrder);
        editTextPayment = findViewById(R.id.editTextPayment);
        listeners();
    }

    private void listeners() {


        buttonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPayment();

            }
        });
    }

    private void startPayment()
    {

        final Activity activity = this;
        final Checkout chekout= new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "My product payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
            options.put("currency", "INR");
            String payment = editTextPayment.getText().toString();
            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);
            JSONObject preFill = new JSONObject();
            preFill.put("email", "sachinmaurya");
            preFill.put("contact", "8601502970");
            options.put("prefill", preFill);

            chekout.open(activity,options);

        } catch (Exception e)
        {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /*@Override
    public void onPaymentSuccess(String razorpayPaymentID)
    {

        Toast.makeText(this, "Payment successfully done! " + razorpayPaymentID, Toast.LENGTH_SHORT).show();


    }*/


    /*@Override
    public void onPaymentError(int code, String response)
    {

        try {
            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }
    }
*/
    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

    }
}
