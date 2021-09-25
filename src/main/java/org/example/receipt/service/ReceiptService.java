package org.example.receipt.service;

import org.example.receipt.entity.ReceiptDTO;
import org.example.receipt.entity.RequestDTO;

public interface ReceiptService {
    ReceiptDTO getReceipt(RequestDTO requestDTO);
}
