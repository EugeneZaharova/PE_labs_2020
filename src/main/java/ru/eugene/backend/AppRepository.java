package ru.eugene.backend;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eugene.backend.model.VoucherModel;

@Repository
public interface AppRepository extends CrudRepository<VoucherModel, Long> {
}
