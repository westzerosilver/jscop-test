package com.example.jsoupTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

    @GetMapping("/fetch")
    public Map<String, String> fetchOgData(@RequestParam String url) {
        Map<String, String> ogData = new HashMap<>();
        try {
            Document doc = Jsoup.connect(url)
//                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36")
//                    .referrer("https://www.google.com/")  // 구글 검색을 통해 들어온 것처럼 속임
                    .timeout(10000)
                    .get();

            // Open Graph 태그 추출
            ogData.put("title", getMetaTag(doc, "og:title"));
            ogData.put("description", getMetaTag(doc, "og:description"));
            ogData.put("image", getMetaTag(doc, "og:image"));
            ogData.put("url", getMetaTag(doc, "og:url"));

        } catch (IOException e) {
            ogData.put("error", e.getMessage());
        }
        return ogData;
    }

    private String getMetaTag(Document doc, String property) {
        Element tag = doc.select("meta[property=" + property + "]").first();
        return tag != null ? tag.attr("content") : "";
    }
}
