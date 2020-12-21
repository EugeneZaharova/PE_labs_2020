package ru.eugene.backend;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eugene.backend.model.VoucherModel;

import java.util.UUID;

@Repository
public interface VoucherRepository extends CrudRepository<VoucherModel, UUID> {
}
