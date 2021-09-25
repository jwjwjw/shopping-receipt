import org.example.receipt.entity.ProductDTO;
import org.example.receipt.entity.ReceiptDTO;
import org.example.receipt.entity.RequestDTO;
import org.example.receipt.service.ReceiptServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RunWith(MockitoJUnitRunner.class)
public class ReceiptServiceTest {

    private ReceiptServiceImpl service = new ReceiptServiceImpl();

    @Before
    public void before() {
        Map<String, List<String>> category = new ConcurrentHashMap<>();

        category.put("food", Arrays.asList("potato chips,bread,chicken".split(",")));
        category.put("cloth", Arrays.asList("shirt,shoe,pants".split(",")));
        category.put("stationery", Arrays.asList("book,pencil,pen".split(",")));
        this.service.setCategory(category);
        this.service.setTaxCA("0.0975");
        this.service.setTaxNY("0.08875");
    }


    @Test
    public void testUseCase1() {
        RequestDTO request = new RequestDTO();
        request.setLocation("CA");

        List<ProductDTO> products = new ArrayList<>();
        ProductDTO product = new ProductDTO();
        product.setProductName("book");
        product.setPrice(new BigDecimal("17.99"));
        product.setQuantity(1);
        products.add(product);

        ProductDTO product2 = new ProductDTO();
        product2.setProductName("potato chips");
        product2.setPrice(new BigDecimal("3.99"));
        product2.setQuantity(1);
        products.add(product2);

        request.setProductList(products);

        ReceiptDTO receiptDTO = this.service.getReceipt(request);
        Assert.assertEquals("21.98", receiptDTO.getSubtotal());
        Assert.assertEquals("1.80", receiptDTO.getTax());
        Assert.assertEquals("23.78", receiptDTO.getTotal());
    }

    @Test
    public void testCreateItems2() {
        RequestDTO request = new RequestDTO();
        request.setLocation("NY");

        List<ProductDTO> products = new ArrayList<>();
        ProductDTO product = new ProductDTO();
        product.setProductName("book");
        product.setPrice(new BigDecimal("17.99"));
        product.setQuantity(1);
        products.add(product);

        ProductDTO product2 = new ProductDTO();
        product2.setProductName("pencil");
        product2.setPrice(new BigDecimal("2.99"));
        product2.setQuantity(3);
        products.add(product2);

        request.setProductList(products);

        ReceiptDTO receiptDTO = this.service.getReceipt(request);
        Assert.assertEquals("26.96", receiptDTO.getSubtotal());
        Assert.assertEquals("2.40", receiptDTO.getTax());
        Assert.assertEquals("29.36", receiptDTO.getTotal());
    }


    @Test
    public void testCreateItems3() {
        RequestDTO request = new RequestDTO();
        request.setLocation("NY");

        List<ProductDTO> products = new ArrayList<>();

        ProductDTO product = new ProductDTO();
        product.setProductName("pencil");
        product.setPrice(new BigDecimal("2.99"));
        product.setQuantity(2);
        products.add(product);

        ProductDTO product2 = new ProductDTO();
        product2.setProductName("shirt");
        product2.setPrice(new BigDecimal("29.99"));
        product2.setQuantity(1);
        products.add(product2);

        request.setProductList(products);

        ReceiptDTO receiptDTO = this.service.getReceipt(request);
        Assert.assertEquals("35.97", receiptDTO.getSubtotal());
        Assert.assertEquals("0.55", receiptDTO.getTax());
        Assert.assertEquals("36.52", receiptDTO.getTotal());
    }


    @Test(expected=IllegalArgumentException.class)
    public void testParameter() {
        RequestDTO request = new RequestDTO();

        ProductDTO product = new ProductDTO();
        product.setProductName("book");
        product.setPrice(new BigDecimal(10.00));
        product.setQuantity(10);

        request.setProductList(Collections.singletonList(product));

        this.service.getReceipt(request);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testParameter2() {
        RequestDTO request = new RequestDTO();
        request.setLocation("CA");

        ProductDTO product = new ProductDTO();
        product.setPrice(new BigDecimal(10.00));
        product.setQuantity(10);

        request.setProductList(Collections.singletonList(product));

        this.service.getReceipt(request);
    }

}
