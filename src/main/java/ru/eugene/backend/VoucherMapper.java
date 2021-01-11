package ru.eugene.backend;

import ru.eugene.backend.dto.Voucher;
import ru.eugene.backend.model.VoucherModel;

import java.util.ArrayList;
import java.util.List;

public class VoucherMapper {
    public static Voucher fromModel(VoucherModel model) {
        return new Voucher(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getPrice(),
                model.getDestinationCountry(),
                model.getDestinationRegion()
        );
    }

    public static VoucherModel toModel(Voucher voucher) {
        return new VoucherModel(
                voucher.getId(),
                voucher.getName(),
                voucher.getDescription(),
                voucher.getPrice(),
                voucher.getDestinationCountry(),
                voucher.getDestinationRegion()
        );
    }

    public static List<Voucher> fromModel(List<VoucherModel> voucherModels) {
        List<Voucher> result = new ArrayList<>();
        voucherModels.forEach(m -> result.add(fromModel(m)));
        return result;
    }

    public static List<VoucherModel> toModel(List<Voucher> vouchers) {
        List<VoucherModel> result = new ArrayList<>();
        vouchers.forEach(v -> result.add(toModel(v)));
        return result;
    }
}
