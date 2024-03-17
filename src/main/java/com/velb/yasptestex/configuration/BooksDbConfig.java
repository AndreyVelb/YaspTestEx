package com.velb.yasptestex.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.logging.Logger;
import java.util.zip.ZipInputStream;

@Component
@RequiredArgsConstructor
public class BooksDbConfig {

    @Value("${kaggle.dataset}")
    private String kaggleDatasetUrl;

    @Value("${kaggle.username}")
    private String kaggleUsername;

    @Value("${kaggle.key}")
    private String kaggleKey;

    @Value("${kaggle.file-path}")
    private String kaggleFilePath;

    private static final Logger log = Logger.getLogger(String.valueOf(BooksDbConfig.class));


    private final Connection connection;


    @EventListener(value = ApplicationStartedEvent.class)
    public void downloadAndSaveFile() {

        String authStr = kaggleUsername + ":" + kaggleKey;

        String base64AuthStr = Base64.getEncoder().encodeToString(authStr.getBytes());

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.execute(
                kaggleDatasetUrl,
                HttpMethod.GET,
                clientHttpRequest -> clientHttpRequest.getHeaders().set(
                        "Authorization",
                        "Basic " + base64AuthStr),
                clientHttpResponse -> {
                    try (ZipInputStream zipInputStream = new ZipInputStream(clientHttpResponse.getBody())) {
                        while ((zipInputStream.getNextEntry()) != null) {
                            try (FileOutputStream fout = new FileOutputStream(kaggleFilePath);) {
                                for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
                                    fout.write(c);
                                }
                                fout.flush();
                                fout.close();
                                zipInputStream.closeEntry();
                            }
                        }
                    }

                    return new File(kaggleFilePath);
                });
    }

    @EventListener(value = ApplicationStartedEvent.class)
    public void saveFileAndPrepareDb() {
        try {
            Statement statement = connection.createStatement();
            statement.execute(SqlInitQuery.CREATE_TABLE);
            statement.execute(SqlInitQuery.ADD_COLUMN_PUBLICATION_DATE_MOD);
            statement.execute(SqlInitQuery.ADD_COLUMN_RATING_MOD);
            statement.execute(SqlInitQuery.ADD_COLUMN_NUMBER_OF_VOTERS);
            statement.execute(SqlInitQuery.ADD_COLUMN_NUM_PAGES_MOD);
            statement.execute(SqlInitQuery.CREATE_ALIAS_FOR_PARSE_DATE);
            statement.execute(SqlInitQuery.CREATE_ALIAS_FOR_PARSE_NUMERIC);
            statement.execute(SqlInitQuery.UPDATE_COLUMN_PUBLICATION_DATE_MOD);
            statement.execute(SqlInitQuery.UPDATE_COLUMN_RATING_MOD);
            statement.execute(SqlInitQuery.UPDATE_COLUMN_NUMBER_OF_VOTERS);
            statement.execute(SqlInitQuery.UPDATE_COLUMN_NUM_PAGES_MOD);
            statement.execute(SqlInitQuery.DROP_COLUMN_PUBLICATION_DATE);
            statement.execute(SqlInitQuery.DROP_COLUMN_NUM_PAGES);
            statement.execute(SqlInitQuery.DROP_COLUMN_RATING_SCORE);
            statement.execute(SqlInitQuery.DROP_COLUMN_NUM_RATINGS);
            log.info("THE DATABASE IS READY FOR USE");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
