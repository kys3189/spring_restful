package com.app.restful.service;

import com.app.restful.domain.CongestionData;
import com.app.restful.domain.CongestionResponse;
import com.app.restful.domain.PetTourDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenApiService {

    @Value("${api.base-url}")
    private String baseUrl;

    @Value("${api.service-key}")
    private String serviceKey;

    @Value("${api.area-based-list}")
    private String areaBasedList;

    private final ObjectMapper objectMapper;

    //    레거시
    public List<PetTourDTO> fetchData() throws IOException {

        List<PetTourDTO> petTourList = new ArrayList<PetTourDTO>();

//        URL
        String urlStr = UriComponentsBuilder.fromUriString(baseUrl + areaBasedList)
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "TestApp")
                .queryParam("_type", "json")
                .build().toString();

        URL url = new URL(urlStr);

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            result.append(line);
        }

        br.close();
        JsonNode jsonResponse = objectMapper.readTree(result.toString());

        if(jsonResponse.has("error")) {
            return null;
        }

        JsonNode itemsArray = jsonResponse.get("response").get("body").get("items").get("item");
        for(JsonNode item : itemsArray) {
            PetTourDTO petTour = new PetTourDTO();
            petTour.setAreacode(item.get("areacode").asText());
            petTour.setTitle(item.get("title").asText());
            petTour.setContentId(item.get("contentid").asText());
            petTour.setAddress(item.get("addr1").asText());
            petTour.setFirstImage1(item.get("firstimage").asText());
            petTour.setFirstImage1(item.get("firstimage2").asText());
            petTour.setTel(item.get("tel").asText());
            petTour.setZipcode(item.get("zipcode").asText());
            petTourList.add(petTour);
        }
        return petTourList;
    }

//    Rest Templates
    @Value("${api.base-url2}")
    private String baseUrl2;

    @Value("${api.congestion-20171231}")
    private String congestion2017311;

    public List<CongestionData> fetchData2() throws IOException, URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

//        URL
        String url = baseUrl2 + congestion2017311;
        String fullUrl = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("serviceKey", serviceKey)
                .queryParam("page", 1)
                .queryParam("perPage", 10)
                .queryParam("_type", "json")
                .build()
                .toString();

//        log.info("url : {}", fullUrl);

        URI uri = new URI(fullUrl);

        CongestionResponse response = restTemplate.getForObject(uri, CongestionResponse.class);
        log.info("response : {}", response);

        List<CongestionData> datas = response.getData();

        return datas;

    }

}
