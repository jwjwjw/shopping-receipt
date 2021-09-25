package org.example.receipt.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.receipt.entity.ReceiptDTO;
import org.example.receipt.entity.RequestDTO;
import org.example.receipt.service.ReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Api(tags = "APIs")
@RestController
public class ReceiptController {

    private static Logger log = LoggerFactory.getLogger(ReceiptController.class);

    @Autowired
    ReceiptService receiptService;

    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @ApiOperation(value = "test", notes = "test")
    @GetMapping("/test")
    public String getTest() {
        return "SUCCESS";
    }

    @ApiOperation(value = "get receipt", notes = "get receipt")
    @PostMapping("/create")
    public String createReceipt(@RequestBody RequestDTO requestDTO) {

        log.info("controller");
        log.info(requestDTO.toString());
        ReceiptDTO receiptDTO = this.receiptService.getReceipt(requestDTO);
        System.out.println(receiptDTO.toString());
        return receiptDTO.toString();
    }

}
