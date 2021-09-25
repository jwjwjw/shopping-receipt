import org.example.receipt.controller.ReceiptController;
import org.example.receipt.entity.ProductDTO;
import org.example.receipt.entity.ReceiptDTO;
import org.example.receipt.entity.RequestDTO;
import org.example.receipt.service.ReceiptService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ReceiptControllerTest {


    private ReceiptController controller;

    @Mock
    ReceiptService receiptService;

    @Before
    public void before() {
        controller = new ReceiptController();
        controller.setReceiptService(receiptService);
    }

    @Test
    public void testCreateItem() {

        RequestDTO request = new RequestDTO();
        request.setLocation("CA");

        ProductDTO product = new ProductDTO();
        product.setProductName("book");
        product.setPrice(new BigDecimal("10.00"));
        product.setQuantity(10);

        request.setProductList(Collections.singletonList(product));

        when(this.receiptService.getReceipt(request)).thenReturn(new ReceiptDTO());

        String response = this.controller.createReceipt(request);
        Assert.assertNotNull(response);
    }


    @Test
    public void testGetAll() {

        RequestDTO request = new RequestDTO();
        request.setLocation("NY");

        ProductDTO product = new ProductDTO();
        product.setProductName("shirt");
        product.setPrice(new BigDecimal("13.99"));
        product.setQuantity(2);

        request.setProductList(Collections.singletonList(product));

        when(this.receiptService.getReceipt(request)).thenReturn(new ReceiptDTO());

        String response = this.controller.createReceipt(request);
        Assert.assertNotNull(response);
    }

}
