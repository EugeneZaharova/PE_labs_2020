package ru.eugene.backend;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.eugene.backend.dto.Voucher;
import ru.eugene.backend.model.VoucherModel;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Vouchers")
@RequestMapping("/vouchers")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppController {
    private final AppService service;

    @ApiOperation("Get all vouchers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("list")
    List<Voucher> getAllVouchers() {
        return fromModel(service.getAllVouchers());
    }

    @ApiOperation("Get voucher by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("get")
    Voucher getVoucher(@RequestParam(name = "id") Long id) {
        return fromModel(service.getVoucher(id));
    }

    @ApiOperation("Add voucher")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("add")
    void addVoucher(@RequestBody Voucher voucher) {
        service.addVoucher(toModel(voucher));
    }

    @ApiOperation("Edit voucher")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("edit")
    void editVoucher(@RequestBody Voucher voucher) {
        service.editVoucher(toModel(voucher));
    }

    @ApiOperation("Delete voucher")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("delete")
    void deleteVoucher(@RequestParam(name = "id") Long id) {
        service.deleteVoucher(id);
    }

    private Voucher fromModel(VoucherModel model) {
        return new Voucher(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getPrice(),
                model.getDestinationCountry(),
                model.getDestinationRegion()
        );
    }

    private VoucherModel toModel(Voucher voucher) {
        return new VoucherModel(
                voucher.getId(),
                voucher.getName(),
                voucher.getDescription(),
                voucher.getPrice(),
                voucher.getDestinationCountry(),
                voucher.getDestinationRegion()
        );
    }

    private List<Voucher> fromModel(List<VoucherModel> voucherModels) {
        List<Voucher> result = new ArrayList<>();
        voucherModels.forEach(m -> result.add(fromModel(m)));
        return result;
    }

    private List<VoucherModel> toModel(List<Voucher> vouchers) {
        List<VoucherModel> result = new ArrayList<>();
        vouchers.forEach(v -> result.add(toModel(v)));
        return result;
    }
}
