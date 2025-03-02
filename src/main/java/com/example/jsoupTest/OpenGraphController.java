package com.example.jsoupTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/og")
public class OpenGraphController {
    @Autowired
    private OpenGraphService openGraphService;

    @GetMapping("/fetch")
    public Map<String, String> fetchOgData(@RequestParam String url) {
        try {
            return openGraphService.getOGData(url);

        } catch (IOException e) {
//            ogData.put("error", e.getMessage());
            return Map.of("error", e.getMessage());
        }

    }

}
