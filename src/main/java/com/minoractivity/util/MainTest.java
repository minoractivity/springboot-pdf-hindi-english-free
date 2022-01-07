package com.minoractivity.util;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import static org.thymeleaf.templatemode.TemplateMode.HTML;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;

//import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;



public class MainTest {
	private static final String OUTPUT_FILE = "testMinor.pdf";
    private static final String UTF_8 = "UTF-8";
public static void main(String[] args) {
	MainTest mainTest=new MainTest();
	try {
		mainTest.generatePdf();
		mainTest.convertToXhtml("");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void generatePdf() throws Exception {

    
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(HTML);
    templateResolver.setCharacterEncoding(UTF_8);

    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);

    
    Data data = exampleDataForJohnDoe();

    Context context = new Context();
    context.setVariable("data", data);

    
    String renderedHtmlContent = templateEngine.process("template", context);
    String xHtml = convertToXhtml(renderedHtmlContent);

    ITextRenderer renderer = new ITextRenderer();
    renderer.getFontResolver().addFont("Code39.ttf", IDENTITY_H, EMBEDDED);

  
    String baseUrl = FileSystems
                            .getDefault()
                            .getPath("src", "test", "resources")
                            .toUri()
                            .toURL()
                            .toString();
    renderer.setDocumentFromString(xHtml, baseUrl);
    renderer.layout();

    // And finally, we create the PDF:
    OutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
    renderer.createPDF(outputStream);
    outputStream.close();
}

private static Data exampleDataForJohnDoe() {
    Data data = new Data();
    data.setFirstname("इसे  आजमाएं अभिव्यक्ति  की आजादी ");
    data.setLastname("/कृष्णा कुमार द्विवेदी ");
    data.setStreet("श्री  किशोर ,डी जी - ८४  पानी की टंकी के पास ");
    data.setZipCode("12345");
    data.setCity("भोपाल ");
    return data;
}

static class Data {
    private String firstname;
    private String lastname;
    private String street;
    private String zipCode;
    private String city;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

private String convertToXhtml(String html) throws UnsupportedEncodingException {
    Tidy tidy = new Tidy();
    tidy.setInputEncoding(UTF_8);
    tidy.setOutputEncoding(UTF_8);
    tidy.setXHTML(true);
    ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    tidy.parseDOM(inputStream, outputStream);
    return outputStream.toString(UTF_8);
}
//new Hindi code
public static String generateHindiReport( String path) throws Exception {


    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

    templateResolver.setPrefix("templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setCharacterEncoding("UTF-8");
    templateResolver.setTemplateMode("HTML5");

    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);

    Context context = new Context();

    context.setVariable("title", "gfgf");
    context.setVariable("todaysDate", "08/12/2022");

    String reportHtml = "template";

    String outputReportName = "";

  
    Data data = exampleDataForJohnDoe();
    context.setVariable("data", data);

   String renderedHtmlContent = templateEngine.process(reportHtml, context);


   System.out.print("HTML Value Is >>>>"+renderedHtmlContent);
    
    return renderedHtmlContent;

}

}
