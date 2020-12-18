package ru.eugene.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eugene.backend.model.VoucherModel;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppService {
    private final AppRepository repository;

    List<VoucherModel> getAllVouchers() {
        return iterableToList(repository.findAll());
    }

    VoucherModel getVoucher(@RequestParam(name = "id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    void addVoucher(@RequestBody VoucherModel voucher) {
        repository.save(voucher);
    }

    void editVoucher(@RequestBody VoucherModel voucher) {
        VoucherModel foundModel = repository.findById(voucher.getId())
                .orElseThrow(() -> new NotFoundException());

        foundModel.setName(voucher.getName());
        foundModel.setDescription(voucher.getDescription());
        foundModel.setPrice(voucher.getPrice());
        foundModel.setDestinationCountry(voucher.getDestinationCountry());
        foundModel.setDestinationRegion(voucher.getDestinationRegion());
        repository.save(foundModel);
    }

    void deleteVoucher(@RequestParam(name = "id") Long id) {
        //Для того, чтобы если объекта нет, вылетела ошибка
        getVoucher(id);
        repository.deleteById(id);
    }

    private <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> result = new ArrayList();
        iterable.forEach(i -> result.add(i));
        return result;
    }
}
