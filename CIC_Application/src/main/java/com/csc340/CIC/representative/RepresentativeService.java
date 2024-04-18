package com.csc340.CIC.representative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepresentativeService {

    @Autowired
    private final RepresentativeRepository representativeRepository;

    public RepresentativeService(RepresentativeRepository representativeRepository) {
        this.representativeRepository = representativeRepository;
    }

    public List<RepresentativeAPI> getAllRepresentatives() {
        try {
            URI uri = URI.create("https://api.propublica.org/congress/v1/118/house/members.json");
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-API-Key", "nK1MVaOhD4iNGxcfKHn8ooks0QvxBGJBvjNDPcv0");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                JsonElement jsonElement = JsonParser.parseReader(reader);
                JsonObject responseObject = jsonElement.getAsJsonObject();
                JsonArray resultsArray = responseObject.getAsJsonArray("results");

                if (resultsArray != null) {
                    JsonObject resultObject = resultsArray.get(0).getAsJsonObject();
                    JsonArray membersArray = resultObject.getAsJsonArray("members");

                    List<RepresentativeAPI> reps = new ArrayList<>();

                    for (JsonElement memberElement : membersArray) {
                        JsonObject memberObject = memberElement.getAsJsonObject();

                        // Construct and populate Representative object
                        RepresentativeAPI rep = new RepresentativeAPI();
                        rep.setId(memberObject.get("id").getAsString());
                        rep.setTitle(memberObject.get("title").getAsString());
                        rep.setFirstName(memberObject.get("first_name").getAsString());
                        rep.setLastName(memberObject.get("last_name").getAsString());
                        rep.setParty(memberObject.get("party").getAsString());
                        if (!memberObject.get("leadership_role").isJsonNull() ) {
                            rep.setLeadershipRole(memberObject.get("leadership_role").getAsString());
                        }
                        
                        JsonElement element = memberObject.get("next_election");
                        if (element != null && !element.isJsonNull() && !"null".equals(element.getAsString())) {
                            rep.setNextElection(memberObject.get("next_election").getAsString());
                        }
                        rep.setChamber(resultObject.get("chamber").getAsString());
                        rep.setUrl(memberObject.get("url").getAsString());
                        rep.setState(memberObject.get("state").getAsString());
                        rep.setDistrict(memberObject.get("district").getAsString());
                        // Add representative to the list
                        reps.add(rep);
                    }
                    return reps;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public RepresentativeDetails getRepresentativeDetails(String representativeID) {
        try {

            URI uri = URI.create("https://api.propublica.org/congress/v1/members/" + representativeID + ".json");
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-API-Key", "nK1MVaOhD4iNGxcfKHn8ooks0QvxBGJBvjNDPcv0");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                JsonElement jsonElement = JsonParser.parseReader(reader);
                JsonObject responseObject = jsonElement.getAsJsonObject();
                JsonArray resultsArray = responseObject.getAsJsonArray("results");

                if (resultsArray != null && resultsArray.size() > 0) {
                    JsonObject memberObject = resultsArray.get(0).getAsJsonObject();

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
                        RepresentativeDetails rep = new RepresentativeDetails();
                        rep.setId(memberObject.get("id").getAsString());
                        rep.setFirstName(memberObject.get("first_name").getAsString());
                        rep.setLastName(memberObject.get("last_name").getAsString());
                        rep.setParty(memberObject.get("current_party").getAsString());
                        rep.setState(roles.get("state").getAsString());
                        rep.setDistrict(roles.get("district").getAsString());
                        rep.setChamber(roles.get("chamber").getAsString());
                        if (!roles.get("leadership_role").isJsonNull() ) {
                            rep.setLeadershipRole(roles.get("leadership_role").getAsString());
                        }
                        
                        JsonElement element = roles.get("next_election");
                        if (element != null && !element.isJsonNull() && !"null".equals(element.getAsString())) {
                            rep.setNextElection(roles.get("next_election").getAsString());
                        }
                        rep.setUrl(memberObject.get("url").getAsString());

                        Optional<Representative> optionalRepresentative = getRepresentativeById(representativeID);
                        if (optionalRepresentative.isPresent()) {
                            Representative representative = optionalRepresentative.get();
                            if (representative.getBiography() != null && !representative.getBiography().isEmpty()) {
                                rep.setBiography(representative.getBiography());
                            } 
                        }

                        return rep;
                    } else {
                        throw new RuntimeException("No data found for representative ID: " + representativeID + " in the 118th Congress");
                    }
                } else {
                    throw new RuntimeException("No data found for representative ID: " + representativeID);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RepresentativeDetails updateBiography(String representativeID, String newBiography) {
            Representative representative = getRepresentativeById(representativeID)
            .orElseThrow(() -> new IllegalArgumentException("Representative not found with ID: " + representativeID));
            
            representative.setBiography(newBiography);
            representativeRepository.save(representative);
            RepresentativeDetails representativeDetails = new RepresentativeDetails();
            representativeDetails.setBiography(newBiography);
            return representativeDetails;
    }


    public Optional<Representative> getRepresentativeById(String id) {
        return representativeRepository.findById(id);
    }

    public Representative saveRepresentative(Representative representative) {
        return representativeRepository.save(representative);
    }

    public void deleteRepresentative(String id) {
        representativeRepository.deleteById(id);
    }
}
