package io.permit.sdk;

import com.google.common.net.MediaType;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.permit.sdk.enforcement.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

class Car {
    public String brand = null;
    public int    doors = 0;
}

class Fleet {
    public ArrayList<Car> cars = new ArrayList<>();
    public Car bestCar = null;
    public int    size = 0;
}

public class PermitClient {
    public void serializeJson() {
        Gson gson = new Gson();
//        System.out.println(gson.toJson(1));            // ==> 1
//        System.out.println(gson.toJson("abcd"));       // ==> "abcd"
//        System.out.println(gson.toJson(10L)); // ==> 10

        // deserialization
        String json = "{\"brand\":\"Jeep\", \"doors\": 3}";
        Car car = gson.fromJson(json, Car.class);
        System.out.println(car.brand);
        System.out.println(car.doors);

        // serialization
        User user = User.fromString("asaf");
        System.out.println(gson.toJson(user));

        User user2 = new User.Builder("asaf").withEmail("asaf@permit.io").build();
        System.out.println(gson.toJson(user2));

        System.out.println(gson.toJson("treasure:3".split(":")));
        System.out.println(gson.toJson("treasure".split(":")));


        Car car2 = new Car();
        car2.doors = 5;
        Fleet fleet = new Fleet();
        fleet.cars.add(car);
        fleet.cars.add(car2);
        fleet.bestCar = car2;
        fleet.size = 2;
        String jsonout = gson.toJson(fleet);
        System.out.println(jsonout);

//        int one = gson.fromJson("1", int.class);
//        Integer one1 = gson.fromJson("1", Integer.class);
//        Long one2 = gson.fromJson("1", Long.class);
//        Boolean false3 = gson.fromJson("false", Boolean.class);
//        String str = gson.fromJson("\"abc\"", String.class);
    }

     public void invokePost() throws URISyntaxException, IOException {
         OkHttpClient client = new OkHttpClient();

         //1. Create JSON Request for sending in the POST method
//         Car car = new Car();
//         Gson gson = new Gson();
//         car.brand = "Rover";
//         car.doors = 5;
//         String requestBody = gson.toJson(car);
//
//         //2. Create Request Body
//         RequestBody body = RequestBody.create(
//                 requestBody,
//                 MediaType.parse("application/json"));
//
//

         //3. Create HTTP request
         Request request = new Request.Builder()
                 .url("http://localhost:8000/v1/roles")
                 .get()
                 .addHeader("Content-Type", "application/json")
                 .addHeader("Authorization", "Bearer PJUKkuwiJkKxbIoC4o4cguWxB_2gX6MyATYKc2OCM")
                 .build();

         //4. Synchronous call to the REST API
         Response response = client.newCall(request).execute();
         System.out.println(response.body().string());
     }
}
