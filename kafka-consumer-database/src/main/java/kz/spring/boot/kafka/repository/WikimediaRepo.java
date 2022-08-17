package kz.spring.boot.kafka.repository;

import kz.spring.boot.kafka.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaRepo  extends JpaRepository<WikimediaData, Long> {
}
