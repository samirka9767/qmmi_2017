/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.w3c.dom.Document;

/**
 * @author xakus
 */
public class DocConvert {
    private String outHTMLCode = "";

    public File DocConvert(String filename,InputStream wordFileIS) throws IOException, ParserConfigurationException, TransformerException {
        File retFile = null;
        HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(wordFileIS);

        InlineImageWordToHtmlConverter wordToHtmlConverter = new InlineImageWordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        serializer.setOutputProperty(OutputKeys.ENCODING, "ANSI");
        serializer.setOutputProperty(OutputKeys.MEDIA_TYPE, "text/htlm");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        out.close();

        String result = new String(out.toByteArray());
       // System.out.println(result);
        outHTMLCode = result;
        String fName = filename.split("doc")[0];
        try {
            retFile = File.createTempFile("report_", ".html");
            BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(retFile)));
            out2.write(result);
            out2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retFile;

    }
}
