package ru.eugene.backend;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.eugene.backend.dto.Voucher;

import java.util.List;
import java.util.UUID;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class VoucherControllerTest {
    @Autowired
    VoucherController voucherController;

    @Test
    public void testGetAllVouchers() {
        List<Voucher> vouchers = voucherController.getAllVouchers();
        Assertions.assertNotEquals(0, vouchers.size());
    }

    @Test
    public void testGetVoucherById() {
        voucherController.getVoucher(UUID.randomUUID());
    }

    @Test
    public void testAddVoucher() {
        voucherController.addVoucher(ObjectGenerator.generateVoucher());
    }

    @Test
    public void testEditVoucher() {
        voucherController.editVoucher(ObjectGenerator.generateVoucher());
    }

    @Test
    public void testDeleteVoucher() {
        voucherController.deleteVoucher(UUID.randomUUID());
    }
}
