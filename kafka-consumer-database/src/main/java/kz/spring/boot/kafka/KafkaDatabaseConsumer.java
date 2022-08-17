package kz.spring.boot.kafka;

import kz.spring.boot.kafka.entity.WikimediaData;
import kz.spring.boot.kafka.repository.WikimediaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private final WikimediaRepo wikimediaRepo;

    @Autowired
    public KafkaDatabaseConsumer(WikimediaRepo wikimediaRepo) {
        this.wikimediaRepo = wikimediaRepo;
    }

    @KafkaListener(topics = "wikimedia-recentchange", groupId = "myGroup")
    private void consume(String eventMessage){
        LOGGER.info(String.format("Message recived -> %s", eventMessage));
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikimediaRepo.save(wikimediaData);
    }
}
