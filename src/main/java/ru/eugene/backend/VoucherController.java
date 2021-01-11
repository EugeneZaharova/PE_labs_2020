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
import java.util.UUID;

@Api(tags = "Vouchers")
@RequestMapping("/vouchers")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VoucherController {
    private final VoucherService service;

    @ApiOperation("Get all vouchers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("list")
    List<Voucher> getAllVouchers() {
        return VoucherMapper.fromModel(service.getAllVouchers());
    }

    @ApiOperation("Get voucher by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("get")
    Voucher getVoucher(@RequestParam(name = "id") UUID id) {
        return VoucherMapper.fromModel(service.getVoucher(id));
    }

    @ApiOperation("Add voucher")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("add")
    void addVoucher(@RequestBody Voucher voucher) {
        service.addVoucher(VoucherMapper.toModel(voucher));
    }

    @ApiOperation("Edit voucher")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("edit")
    void editVoucher(@RequestBody Voucher voucher) {
        service.editVoucher(VoucherMapper.toModel(voucher));
    }

    @ApiOperation("Delete voucher")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("delete")
    void deleteVoucher(@RequestParam(name = "id") UUID id) {
        service.deleteVoucher(id);
    }

}
