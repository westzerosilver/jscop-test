package com.example.jsoupTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenGraphService {


    public Map<String, String> getOGData(String url) throws IOException {
        Map<String, String> ogData = new HashMap<>();

        Document doc = Jsoup.connect(url)
                .timeout(10000)
                .get();

        // Open Graph 태그 추출
        ogData.put("title", getMetaTag(doc, "og:title"));
        ogData.put("description", getMetaTag(doc, "og:description"));
        ogData.put("image", getMetaTag(doc, "og:image"));
        ogData.put("url", getMetaTag(doc, "og:url"));

        return ogData;
    }


    private String getMetaTag(Document doc, String property) {
        Element tag = doc.select("meta[property=" + property + "]").first();
        return tag != null ? tag.attr("content") : "";
    }
}
