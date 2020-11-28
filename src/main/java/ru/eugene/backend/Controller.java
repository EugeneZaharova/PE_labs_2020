package ru.eugene.backend;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.eugene.backend.dto.Voucher;

import java.util.Collections;
import java.util.List;

@Api(tags = "Vouchers")
@RequestMapping("/vouchers")
@RestController
public class Controller {
    @ApiOperation("Get all vouchers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("list")
    List<Voucher> getAllVouchers() {
        return Collections.singletonList(generateVoucherStub());
    }

    @ApiOperation("Get voucher by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("get")
    Voucher getVoucher(@RequestParam(name = "id") Long id) {
        return generateVoucherStub();
    }

    @ApiOperation("Add voucher")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("add")
    void addVoucher(@RequestBody Voucher voucher) {

    }

    @ApiOperation("Edit voucher")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("edit")
    void editVoucher(@RequestBody Voucher voucher) {

    }

    @ApiOperation("Delete voucher")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("delete")
    void deleteVoucher(@RequestParam(name = "id") Long id) {

    }

    private Voucher generateVoucherStub() {
        return new Voucher(1L, "Trip to Sochi", "Desctiption", 50000L, "Russia", "Sochi");
    }
}
