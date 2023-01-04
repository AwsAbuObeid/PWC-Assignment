package com.pwc.task.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Objects;


@Controller
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return "redirect:findCity";
    }

    @RequestMapping(method = RequestMethod.GET,value = "findCity")
    public String findCity(@RequestParam(required=false,defaultValue = "Dubai") String city, Model model) throws IOException {
        String response="";
        String lng="55.297615";
        String lat="25.12148";
        try {
        WebClient client = WebClient.create();
        response = client.get()
                .uri("api.positionstack.com/v1/forward?access_key=646a0beecd57f07bab9d701eac4b30d8&query="+city)
                .retrieve().bodyToMono(String.class).block();
        assert !Objects.equals(response, "");
        }catch (Exception e) {
            model.addAttribute("error","Internal server error");
        }

        try {
            JsonObject JSON = JsonParser.parseString(response).getAsJsonObject();
            assert JSON.get("data").getAsJsonArray().get(0).getAsJsonObject().get("confidence").getAsInt()!=0;
             lng= JSON.get("data").getAsJsonArray().get(0).getAsJsonObject().get("longitude").getAsString();
             lat= JSON.get("data").getAsJsonArray().get(0).getAsJsonObject().get("latitude").getAsString();
        }catch (Exception e){
           model.addAttribute("error","unable to find city");
        }
        model.addAttribute("lng",lng);
        model.addAttribute("lat",lat);

        return "main";
    }
}
