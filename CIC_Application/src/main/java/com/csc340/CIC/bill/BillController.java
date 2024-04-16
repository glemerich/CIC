package com.csc340.CIC.bill;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/bill")
@Controller
public class BillController {

    @GetMapping("/all")
    public String getBills(Model model) {
        try {
            // URL for fetching bill data from the ProPublica Congress API
            // Create a URI from the string URL
            URI uri = URI.create("https://api.propublica.org/congress/v1/118/house/bills/introduced.json");

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
                JsonArray results = jsonElement.getAsJsonObject().getAsJsonArray("results");

                // Now results is a JsonArray containing the bills
                List<Bill> bills = new ArrayList<>();
                for (JsonElement resultElement : results) {
                    JsonObject resultObject = resultElement.getAsJsonObject();
                    JsonArray billsArray = resultObject.getAsJsonArray("bills");

                    // Now billsArray contains the bills for each result
                    for (JsonElement billElement : billsArray) {
                        JsonObject billObject = billElement.getAsJsonObject();
                        // Process each billObject here
                        // Example:
                        String title = billObject.get("title").getAsString();
                        String billId = billObject.get("bill_id").getAsString();
                        String billNumber = billObject.get("number").getAsString();
                        String billSlug = billObject.get("bill_slug").getAsString();
                        String introducedDate = billObject.get("introduced_date").getAsString();
                        String sponsorName = billObject.get("sponsor_name").getAsString();
                        String sponsorParty = billObject.get("sponsor_party").getAsString();
                        String sponsorState = billObject.get("sponsor_state").getAsString();
                        String sponsorId = billObject.get("sponsor_id").getAsString();
                        String shortTitle = billObject.get("short_title").getAsString();


                        // Construct and populate Bill object
                        Bill bill = new Bill();
                        bill.setTitle(title);
                        bill.setBillId(billId);
                        bill.setBillNumber(billNumber);
                        bill.setBillSlug(billSlug);
                        bill.setIntroducedDate(introducedDate);
                        bill.setSponsorName(sponsorName);
                        bill.setSponsorParty(sponsorParty);
                        bill.setSponsorState(sponsorState);
                        bill.setSponsorId(sponsorId);
                        bill.setShortTitle(shortTitle);
                        bills.add(bill);

                    }
                }
                // Add bills to model
                model.addAttribute("bills", bills);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bill/list-bills";
    }

    @GetMapping("/{billId}")
    public String getBillDetails(@PathVariable String billId, Model model) {
        try {
            // URL for fetching bill details from the ProPublica Congress API
            URI uri = URI.create("https://api.propublica.org/congress/v1/118/bills/" + billId + ".json");
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
    
                    // Construct and populate Bill object
                    Bill bill = new Bill();
                    bill.setTitle(resultObject.get("title").getAsString());
                    bill.setBillId(resultObject.get("bill_id").getAsString());
                    bill.setBillNumber(resultObject.get("number").getAsString());
                    bill.setBillSlug(resultObject.get("bill_slug").getAsString());
                    // String introducedDateString = resultObject.get("introduced_date").getAsString();
                    // bill.setIntroducedDate(new SimpleDateFormat("yyyy-MM-dd").parse(introducedDateString.substring(0, 10))); // Parse date string to Date object, excluding time    
                    bill.setSponsorName(resultObject.get("sponsor").getAsString());
                    bill.setSponsorParty(resultObject.get("sponsor_party").getAsString());
                    bill.setSponsorState(resultObject.get("sponsor_state").getAsString());
                    bill.setSponsorId(resultObject.get("sponsor_id").getAsString());
                    bill.setShortTitle(resultObject.get("short_title").getAsString());
                    bill.setBillUri(resultObject.get("congressdotgov_url").getAsString());
                    bill.setIntroducedDate(resultObject.get("introduced_date").getAsString());
    
                    // Set cosponsors information
                    JsonObject cosponsorsObject = resultObject.getAsJsonObject("cosponsors_by_party");
                    HashMap<String, Integer> cosponsorsByParty = new HashMap<>();
                    // Instantiate "D" and "R" values to zero
                    cosponsorsByParty.put("D", 0);
                    cosponsorsByParty.put("R", 0);
                    int totalCosponsors = 0;
    
                    if (cosponsorsObject != null) {
                        for (Map.Entry<String, JsonElement> entry : cosponsorsObject.entrySet()) {
                            String party = entry.getKey();
                            int count = entry.getValue().getAsInt();
                            cosponsorsByParty.put(party, count);
                            totalCosponsors += count; // Add individual cosponsors to total
                        }
                    }
                    bill.setCosponsorsByParty(cosponsorsByParty);
                    bill.setCosponsors(totalCosponsors);

                    // Parse actions
                    List<String> actions = new ArrayList<>();
                    JsonArray actionsArray = resultObject.getAsJsonArray("actions");
                    if (actionsArray != null) {
                        for (JsonElement actionElement : actionsArray) {
                            JsonObject actionObject = actionElement.getAsJsonObject();
                            String chamber = actionObject.get("chamber").getAsString();
                            String actionType = actionObject.get("action_type").getAsString();
                            String datetime = actionObject.get("datetime").getAsString();
                            String description = actionObject.get("description").getAsString();
                            String actionString = chamber + ": " + actionType + " - " + description + " (Date: " + datetime + ")";
                            actions.add(actionString);
                        }
                    }
                    bill.setActions(actions);

    
                    // Add bill details to the model
                    model.addAttribute("bill", bill);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bill/individual-bill"; // Adjust the template name based on your file structure
    }
    
}