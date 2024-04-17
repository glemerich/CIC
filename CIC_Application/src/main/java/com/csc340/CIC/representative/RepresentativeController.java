package com.csc340.CIC.representative;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/representative")
@Controller
public class RepresentativeController {

    @GetMapping("/all")
    public String getAllRepresentatives(Model model) {
        try {
            // URL for fetching representative data from the ProPublica Congress API
            URI uri = URI.create("https://api.propublica.org/congress/v1/118/house/members.json");

            // Convert URI to URL
            URL url = uri.toURL();

            // Open HTTP connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-API-Key", "nK1MVaOhD4iNGxcfKHn8ooks0QvxBGJBvjNDPcv0");

            // Read response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                // Parse JSON response using Gson directly from the input stream
                JsonElement jsonElement = JsonParser.parseReader(reader);
                JsonObject responseObject = jsonElement.getAsJsonObject();
                JsonArray resultsArray = responseObject.getAsJsonArray("results");

                if (resultsArray != null) {
                    JsonObject resultObject = resultsArray.get(0).getAsJsonObject();
                    JsonArray membersArray = resultObject.getAsJsonArray("members");

                    // Now membersArray contains the representatives
                    List<Representative> representatives = new ArrayList<>();
                    
                    for (JsonElement memberElement : membersArray) {
                        JsonObject memberObject = memberElement.getAsJsonObject();
                        
                        // Construct and populate Representative object
                        Representative representative = new Representative();
                        representative.setId(memberObject.get("id").getAsString());
                        representative.setTitle(memberObject.get("title").getAsString());
                        representative.setFirstName(memberObject.get("first_name").getAsString());
                        representative.setLastName(memberObject.get("last_name").getAsString());
                        representative.setParty(memberObject.get("party").getAsString());
                        if (!memberObject.get("leadership_role").isJsonNull() ) {
                            representative.setLeadershipRole(memberObject.get("leadership_role").getAsString());
                        }
                        
                        JsonElement element = memberObject.get("next_election");
                        if (element != null && !element.isJsonNull() && !"null".equals(element.getAsString())) {
                            representative.setNextElection(memberObject.get("next_election").getAsString());
                        }
                        representative.setChamber(resultObject.get("chamber").getAsString());
                        representative.setUrl(memberObject.get("url").getAsString());
                        representative.setState(memberObject.get("state").getAsString());
                        representative.setDistrict(memberObject.get("district").getAsString());
                        // Add representative to the list
                        representatives.add(representative);
                    }       
                    // Add representatives to model
                    model.addAttribute("representatives", representatives);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "representative/list-representatives";
        
    }

    @GetMapping("/{representativeID}")
    public String getRepresentativeDetails(@PathVariable String representativeID, Model model) {
        try {
            // Construct the URL for fetching details of the representative with the given ID
            URI uri = URI.create("https://api.propublica.org/congress/v1/members/" + representativeID + ".json");

            // Convert URI to URL
            URL url = uri.toURL();

            // Open HTTP connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-API-Key", "nK1MVaOhD4iNGxcfKHn8ooks0QvxBGJBvjNDPcv0");

            // Read response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                // Parse JSON response using Gson directly from the input stream
                JsonElement jsonElement = JsonParser.parseReader(reader);
                JsonObject responseObject = jsonElement.getAsJsonObject();
                JsonArray resultsArray = responseObject.getAsJsonArray("results");

                if (resultsArray != null && resultsArray.size() > 0) {
                    JsonObject memberObject = resultsArray.get(0).getAsJsonObject();

                    // Find the role where the congress is 118
                    JsonArray rolesArray = memberObject.getAsJsonArray("roles");
                    JsonObject roles = null;
                    for (JsonElement roleElement : rolesArray) {
                        JsonObject roleObject = roleElement.getAsJsonObject();
                        if (roleObject.get("congress").getAsString().equals("118")) {
                            roles = roleObject;
                            break;
                        }
                    }

                    if (roles != null) {
                        // Construct and populate Representative object
                        Representative representative = new Representative();
                        representative.setId(memberObject.get("id").getAsString());
                        representative.setFirstName(memberObject.get("first_name").getAsString());
                        representative.setLastName(memberObject.get("last_name").getAsString());
                        representative.setParty(memberObject.get("current_party").getAsString());
                        representative.setState(roles.get("state").getAsString());
                        representative.setDistrict(roles.get("district").getAsString());
                        representative.setTitle(roles.get("title").getAsString());
                        representative.setChamber(roles.get("chamber").getAsString());
                        if (!roles.get("leadership_role").isJsonNull() ) {
                            representative.setLeadershipRole(roles.get("leadership_role").getAsString());
                        }
                        
                        JsonElement element = roles.get("next_election");
                        if (element != null && !element.isJsonNull() && !"null".equals(element.getAsString())) {
                            representative.setNextElection(roles.get("next_election").getAsString());
                        }
                        representative.setUrl(memberObject.get("url").getAsString());


                        // Add representative details to model
                        model.addAttribute("representative", representative);
                    } else {
                        // Handle the case where no role for congress 118 is found
                        model.addAttribute("errorMessage", "No data found for representative ID: " + representativeID + " in the 118th Congress");
                    }
                } else {
                    // Handle the case where no results are found for the representative ID
                    model.addAttribute("errorMessage", "No data found for representative ID: " + representativeID);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "representative/individual-representative";
    }


}
