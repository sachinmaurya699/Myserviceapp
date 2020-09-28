package com.sossolution.serviceonway.Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData(){
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("This Terms and Condition is binding for the uses of Services and providing the service using \"SERVICE ON WAY\" (ServiceOnWay) platform.");
        cricket.add("With the use of our services, you give your consent to follow the terms and conditions listed by us on the various platforms (Webpage and Mobile Applications). The terms and conditions specified here follow to the website, application, services and all the related tools in the name of ServiceOnWay. You agree to follow the registration policy, cancellation policy, payment policy, privacy policy, refund policy and so on");
        cricket.add("ServiceOnWayholds its office at New Delhi, and we offer General Maintenance, Special Maintenance, General Check-up and Roadside Assistance Services for vehicles (Two Wheeler, Four Wheeler, and Heavy Vehicle). We tender services for Vehicles through our online platform. You can reach us using ServiceOnWay Mobile application, Webpage and/or through phone calls on contact details listed in the various platforms.");
        cricket.add("England");
        cricket.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketball = new ArrayList<String>();
        basketball.add("Payments are to be made in the form of INR only.");
        basketball.add("An advance payment made by the User(s) will be termed as un-refundable unless and until the mechanic fails to perform the assigned task.");
        basketball.add("User(s) must take the bills from the mechanic if the payment is made in cash.");
        basketball.add("ServiceOnWayfollows no cash policies, thus states that any payment made in cash is not accepted.");
        basketball.add("ServiceOnWayonly accepts payment through online mode that is net banking, card transfer, bank transfer, wallets and so on");
        basketball.add("ServiceOnWayholds the right to change, modify or alter the payment mode without any prior notice.");
        basketball.add("ServiceOnWayholds no responsibility for the payment made to the mechanic directly.");
        basketball.add("ServiceOnWaywill not continue to give the subsequent services unless the User(s) clears the past payments.");
        basketball.add("ServiceOnWaystates that no services will granted to the User(s) unless the service charges are paid.");
        basketball.add("If the customer under his own will tends to change the spare parts, oil, etc. as will cost him with the actual rate.");
        basketball.add("ServiceOnWaydoes not take the responsibility of the quality of services offered on the part of the mechanic without intimation to the ServiceOnWay.");
        basketball.add("ServiceOnWayis not responsible for any payment done to the third party.");
        basketball.add("Cancellation of the ongoing services will be liable for payment on the basis of actual work done so far.");
        basketball.add("Any payment or denial of payment or late payment by the User(s) will fall under the jurisdiction of Delhi court.");
        basketball.add("Payments are to be made in the form of INR only.");




        expandableListDetail.put("TERMS OF USE", cricket);
        expandableListDetail.put("PART A - TERMS APPLICABLE TO THE USER(S)/CUSTOMERS/MECHANICS", football);
        expandableListDetail.put("USER(S)/CUSTOMER-REGISTRATION", basketball);
        expandableListDetail.put("MECHANIC REGISTRATION", basketball);
        expandableListDetail.put("PROVISION OF SERVICES TO USER(S) /CUSTOMERS/MECHANICS", basketball);
        expandableListDetail.put("PROVISION OF PAYMENT PLATFORM TO THE User(S)/CUSTOMERS", basketball);
        expandableListDetail.put("SERVICEONWAY'S RESPONSIBILITY TOWARDS ITS USER(S)", basketball);
        expandableListDetail.put("PRIVACY AND COOKIES", basketball);
        expandableListDetail.put("INTELLECTUAL PROPERTY UNLESS OTHERWISE STATED", basketball);
        expandableListDetail.put("ADVERTISEMENTS", basketball);
        expandableListDetail.put("PART B - GENERAL TERMS", basketball);
        expandableListDetail.put("GENERAL", basketball);
        expandableListDetail.put("APPLICABLE LAW", basketball);
        return expandableListDetail;

    }
}
