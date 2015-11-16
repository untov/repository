package hello;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

@Component
public class RaportBuilder {


    public void buildPdfReport(OutputStream out) {

        try {
            report().columns(
                    col.column("Item", "item", type.stringType()),
                    col.column("Quantity", "quantity", type.integerType()),
                    col.column("Unit price", "unitprice", type.bigDecimalType()))
                    .title(cmp.text("Getting started; näin se käy!"))
                    .pageFooter(cmp.pageXofY())
                    .setDataSource(createDataSource())
                    .toPdf(out);
        } catch (DRException e) {
            e.printStackTrace();
        }
    }


    private JRDataSource createDataSource() {
        List<Data> data = new ArrayList<>();
        data.add(new Data("Notebook", 1, new BigDecimal(500)));
        data.add(new Data("DVD", 5, new BigDecimal(30)));
        data.add(new Data("DVD", 1, new BigDecimal(28)));
        data.add(new Data("DVD", 5, new BigDecimal(32)));
        data.add(new Data("Book", 3, new BigDecimal(11)));
        data.add(new Data("Book", 1, new BigDecimal(15)));
        data.add(new Data("Book", 5, new BigDecimal(10)));
        data.add(new Data("Book", 8, new BigDecimal(9)));

        return new JRBeanCollectionDataSource(data);
    }

    public class Data {

        private String item;
        private Integer quantity;
        private BigDecimal unitprice;

        public Data(String item, Integer quantity, BigDecimal unitprice) {
            this.item = item;
            this.quantity = quantity;
            this.unitprice = unitprice;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getUnitprice() {
            return unitprice;
        }

        public void setUnitprice(BigDecimal unitprice) {
            this.unitprice = unitprice;
        }
    }

}
