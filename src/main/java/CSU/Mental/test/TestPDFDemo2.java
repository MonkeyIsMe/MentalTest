package CSU.Mental.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class TestPDFDemo2 {
	
	public static void main(String[] args) {
		// 创建文件
        Document document = new Document();
        // 建立一个书写器
        PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document,
			        new FileOutputStream("E://test2.pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 打开文件
        document.open();
        // 添加内容
        try {
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("______________________________________________________________________________________________________________________________________"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
			document.add(new Paragraph("Some content here Some content here Some content here Some content here Some content here"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // 设置属性
        // 标题
        document.addTitle("this is a title");
        // 作者
        document.addAuthor("Mr You");
        // 主题
        document.addSubject("this is subject");
        // 关键字
        document.addKeywords("Keywords");
        // 创建时间
        document.addCreationDate();
        // 应用程序
        document.addCreator("hd.com");

        // 关闭文档
        document.close();
        // 关闭书写器
        writer.close();
	}
}