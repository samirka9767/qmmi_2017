package utils;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class InlineImageWordToHtmlConverter extends WordToHtmlConverter {

    public InlineImageWordToHtmlConverter(Document document) {
        super(document);
    }

    @Override
    protected void processImageWithoutPicturesManager(Element currentBlock,
        boolean inlined, Picture picture)
    {
        int width= picture.getHorizontalScalingFactor();
        int height= picture.getVerticalScalingFactor();

        Element imgNode = currentBlock.getOwnerDocument().createElement("img");
        StringBuilder sb = new StringBuilder();
        sb.append(Base64.encodeBase64String(picture.getRawContent()));
        sb.insert(0, "data:"+picture.getMimeType()+";base64,");
        imgNode.setAttribute("src", sb.toString());
        imgNode.setAttribute("width", ""+width);
        imgNode.setAttribute("heght", ""+height);
        currentBlock.appendChild(imgNode);
    }

}