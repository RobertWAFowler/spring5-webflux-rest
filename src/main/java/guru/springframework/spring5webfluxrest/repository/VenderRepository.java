package guru.springframework.spring5webfluxrest.repository;

import guru.springframework.spring5webfluxrest.domain.Vender;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VenderRepository extends ReactiveMongoRepository<Vender, String> {
}
