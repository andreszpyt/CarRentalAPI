package com.car.infra.gateway;

import com.car.core.entities.Rental;
import com.car.core.gateway.RentalGateway;
import com.car.infra.mapper.RentalEntityMapper;
import com.car.infra.persistence.RentalEntity;
import com.car.infra.persistence.RentalRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RentalRepositoryGateway implements RentalGateway {
    private final RentalRepository repository;
    private final RentalEntityMapper mapper;

    public RentalRepositoryGateway(RentalRepository repository, RentalEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Rental createRental(Rental rental) {
        RentalEntity entity =  mapper.toEntity(rental);
        return mapper.toRental(repository.save(entity));
    }

    @Override
    public boolean hasConflictingRental(Long carId, LocalDateTime start, LocalDateTime end) {
        return repository.hasConflictingRental(carId, start, end);
    }
}
