package hello;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import net.sf.dynamicreports.jasper.constant.JasperProperty;
import net.sf.dynamicreports.report.builder.column.ColumnBuilders;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.RichTextString;

//import net.sf.dynamicreports.examples.Templates;

//import javax.xml.transform.Templates;

@Component
public class RaportBuilder {


    public void buildExcelReport(OutputStream out) {

        //JasperXlsExporterBuilder xlsExporter = export.xlsExporter(out)
        JasperXlsExporterBuilder xlsExporter = export.xlsExporter("C:\\Users\\uvalkone\\tmp\\file.xls")
                .setDetectCellType(true)
                .setIgnorePageMargins(true)
                .setWhitePageBackground(false)
                .setRemoveEmptySpaceBetweenColumns(true);

        try {
            JasperReportBuilder jasperReportBuilder = report()
//                    .setColumnTitleStyle(Templates. .columnTitleStyle)
                    .addProperty(JasperProperty.EXPORT_XLS_FREEZE_ROW, "2")
                    .ignorePageWidth()
                    .ignorePagination()

                    .columns(
                            col.column("Item", "item", type.stringType()),
                            col.column("Description", "description", type.stringType()),
                            col.column("Quantity", "quantity", type.integerType()),
                            col.column("Unit price", "unitprice", type.bigDecimalType()))
//                    .title(cmp.text("Getting started; näin se käy!"))
//                    .pageFooter(cmp.pageXofY())
                    .setDataSource(createDataSource())
                    .toXls(xlsExporter);
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    public void buildPdfReport(OutputStream out) {

        ValueColumnBuilder col1 = col.column("Item", "item", type.stringType());
        //TextColumnBuilder col1 = col.column("Item", "item", (DRIDataType)type.stringType());

        try {
            JasperReportBuilder jasperReportBuilder = report().columns(
                    col1,
                    //col.column("Item", "item", type.stringType()),
                    col.column("Description", "description", type.stringType()),
                    col.column("Quantity", "quantity", type.integerType()),
                    col.column("Unit price", "unitprice", type.bigDecimalType()))
                    .title(cmp.text("Getting started; näin se käy!"))
                    .pageFooter(cmp.pageXofY())
                    .setDataSource(createDataSource());
                    //.show();

                    // .toPdf(out);
            String stop = "here";
        } catch (Exception e) {
        //} catch (DRException e) {
            e.printStackTrace();
        }
    }

    String description = "Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla, Tähän kuvausta sanatolkulla, kuvausta sanatolkulla";

    private JRDataSource createDataSource() {
        List<Data> data = new ArrayList<>();
        data.add(new Data("Notebook", "", 1, new BigDecimal(500)));
        data.add(new Data("DVD", "DVD", 5, new BigDecimal(30)));
        data.add(new Data("DVD", description, 1, new BigDecimal(28)));
        data.add(new Data("DVD", "DVD", 5, new BigDecimal(32)));
        data.add(new Data("Book", "DVD", 3, new BigDecimal(11)));
        data.add(new Data("Book", "DVD", 1, new BigDecimal(15)));
        data.add(new Data("Book", description, 5, new BigDecimal(10)));
        data.add(new Data("Book", "DVD", 8, new BigDecimal(9)));

        return new JRBeanCollectionDataSource(data);
    }

    public class Data {

        private String item;
        private String description;
        private Integer quantity;
        private BigDecimal unitprice;

        public Data(String item, String description, Integer quantity, BigDecimal unitprice) {
            this.item = item;
            this.description = description;
            this.quantity = quantity;
            this.unitprice = unitprice;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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


    public static void main(String[] args) {
        RaportBuilder raportBuilder = new RaportBuilder();
        raportBuilder.buildPdfReport(null);
        // raportBuilder.buildExcelReport(null);
    }
}
