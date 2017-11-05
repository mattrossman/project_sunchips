package com.example.andrew.tutorial.CarManager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.andrew.tutorial.DisplayCarActivity;
import com.example.andrew.tutorial.R;
import com.example.andrew.tutorial.Vehicle;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matt on 11/4/2017.
 */

public class RequestTask {
    /* A RequestTask runs a unique set of commands and updates necessary parts of the app UI */


    /* mActivity holds a reference to the main app context so you can externally find and manipulate
    View elements such as text boxes
     */
    protected Activity mActivity;

    public RequestTask(Activity activity) {
        mActivity = activity;
    }

    public void handler(JSONObject obj) throws JSONException { }

    public void run(String response) {
        try {
            System.out.println("Response: "+response);
            JSONObject obj = parseXML(response);
            handler(obj);
        }
        catch (JSONException je){
            System.out.println("Problem handling the response");
            System.out.println(je.toString());
        }
    }

    protected void sendRequest(String url) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(mActivity);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        RequestTask.this.run(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                System.out.println("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private static JSONObject parseXML(String xmlString) {
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
            return xmlJSONObj;
        } catch (JSONException je) {
            System.out.println("Problem parsing the XML");
            System.out.println(je.toString());
            return null;
        }
    }
}

class PriceRequest extends RequestTask {
    private String grade;
    public PriceRequest(Activity activity, String grade) {
        super(activity);
        this.grade = grade;
    }

    @Override
    public void handler(JSONObject obj) throws JSONException {
        TextView tvPrice = (TextView) mActivity.findViewById(R.id.textView);
        JSONObject prices = obj.getJSONObject("fuelPrices");
        String price = Double.toString(prices.getDouble(grade));
        tvPrice.setText(price);
    }

    public void request() {
        sendRequest("http://www.fueleconomy.gov/ws/rest/fuelprices");
    }
}

class YearsRequest extends RequestTask {
    public YearsRequest(Activity activity) {
        super(activity);
    }

    @Override
    public void handler(JSONObject yearsObj) throws JSONException {
        if (yearsObj.get("menuItems").getClass()==JSONObject.class) {
            JSONArray jaYears = yearsObj.getJSONObject("menuItems").getJSONArray("menuItem");
            String[] years = new String[jaYears.length()];
            for (int i = 0; i < jaYears.length(); ++i)
                years[i] = Integer.toString(jaYears.getJSONObject(i).getInt("value"));

            Dropdown d = new Dropdown(mActivity, mActivity.findViewById(R.id.yearsSpinner));
            d.updateOptions(years);
        }
    }

    public void request() {
        sendRequest("http://www.fueleconomy.gov/ws/rest/vehicle/menu/year");
    }
}

class MakesRequest extends RequestTask {

    private String year;

    public MakesRequest(Activity activity, String year) {
        super(activity);
        this.year = year;
    }

    @Override
    public void handler(JSONObject makesObj) throws JSONException {
        if (makesObj.get("menuItems").getClass()==JSONObject.class) {
            JSONArray jaMakes = makesObj.getJSONObject("menuItems").getJSONArray("menuItem");
            String[] makes = new String[jaMakes.length()];
            for (int i = 0; i < jaMakes.length(); ++i)
                makes[i] = jaMakes.getJSONObject(i).getString("value");
            Dropdown d = new Dropdown(mActivity, mActivity.findViewById(R.id.makesSpinner));
            d.updateOptions(makes);
        }
    }


    public void request() {
        sendRequest("http://www.fueleconomy.gov/ws/rest/vehicle/menu/make?year="+year);
    }
}

class ModelsRequest extends RequestTask {

    private String year, make;

    public ModelsRequest(Activity activity, String year, String make) {
        super(activity);
        this.year = year;
        this.make = make;
    }

    @Override
    public void handler(JSONObject modelsObj) throws JSONException {
        if (modelsObj.get("menuItems").getClass()==JSONObject.class) {
            JSONArray modelArray = modelsObj.getJSONObject("menuItems").getJSONArray("menuItem");
            String[] models = new String[modelArray.length()];
            for (int i = 0; i < modelArray.length(); ++i)
                models[i] = modelArray.getJSONObject(i).getString("value");
            Dropdown d = new Dropdown(mActivity, mActivity.findViewById(R.id.modelsSpinner));
            d.updateOptions(models);
        }
    }

    public void request() {
        sendRequest("http://www.fueleconomy.gov/ws/rest/vehicle/menu/model?year="+year+"&make="+make);
    }
}

class OptionsRequest extends RequestTask {

    private String year, make, model;

    public OptionsRequest(Activity activity, String year, String make, String model) {
        super(activity);
        this.year = year;
        this.make = make;
        this.model = model;
    }

    @Override
    public void handler(JSONObject optionsObj) throws JSONException {
        Map<String, String> options = getMap(optionsObj);
        Dropdown d = new Dropdown(mActivity, mActivity.findViewById(R.id.optionsSpinner));
        d.updateOptions(options.keySet().toArray(new String[options.size()]));
    }

    public void request() {
        System.out.println("Requesting options for a "+year+" "+make+" "+model);
        sendRequest("http://www.fueleconomy.gov/ws/rest/vehicle/menu/options?year="+year+"&make="+make+"&model="+model);
    }

    protected static Map<String, String> getMap(JSONObject obj) throws JSONException {
        if (obj.get("menuItems").getClass()==JSONObject.class) {
            HashMap<String, String> options = new HashMap<String, String>();
            obj = obj.getJSONObject("menuItems");
            if (obj.get("menuItem").getClass()==JSONArray.class) {
                JSONArray ja = obj.getJSONArray("menuItem");
                for (int i=0; i<ja.length(); ++i)
                    options.put(ja.getJSONObject(i).getString("text"),
                            Integer.toString(ja.getJSONObject(i).getInt("value")));
            }
            else if (obj.get("menuItem").getClass()==JSONObject.class) {
                JSONObject item = obj.getJSONObject("menuItem");
                options.put(item.getString("text"), Integer.toString(item.getInt("value")));
            }
            return options;
        }
        return null;
    }
}


class IDRequest extends OptionsRequest {

    private String year, make, model, option;

    public IDRequest(Activity activity, String year, String make, String model, String option) {
        super(activity, year, make, model);
        this.option = option;
    }

    @Override
    public void handler(JSONObject obj) throws JSONException {
        Map<String, String> options = getMap(obj);
        String id = options.get(option);

        new MPGRequest(mActivity, id).request();
    }
}

class MPGRequest extends RequestTask {

    private String id;

    public MPGRequest(Activity activity, String id) {
        super(activity);
        this.id = id;
    }

    public void handler(JSONObject obj) throws JSONException {
        obj = obj.getJSONObject("vehicle");
        double mpg = obj.getDouble("comb08");
        Vehicle toAdd = new Vehicle();
        toAdd.setYear(Integer.parseInt(AddCarActivity.year));
        toAdd.setMake(AddCarActivity.make);
        toAdd.setModel(AddCarActivity.model);
        toAdd.setOption(AddCarActivity.option);

        new Garage(mActivity).saveVehicle(toAdd);
        System.out.println("The MPG is "+Double.toString(mpg));
    }

    public void request() {
        sendRequest("http://www.fueleconomy.gov/ws/rest/vehicle/"+id);
    }
}