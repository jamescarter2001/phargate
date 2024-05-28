package com.carter;

import com.carter.pharmacy.boots.model.BootsPharmacySearchResult;
import com.carter.util.http.RestClient;
import com.carter.util.http.RestClientFactory;
import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {
        RestClient restClient = RestClientFactory.newClient();
        BootsPharmacySearchResult result = restClient.get("https://www.boots.com/online/psc/search/store?from=0", BootsPharmacySearchResult.class);

        var app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .start(7070);
    }
}