package packTests;

import java.io.File;

import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.OdfContentDom;
import org.odftoolkit.odfdom.dom.element.office.OfficeDocumentContentElement;
import org.w3c.dom.NamedNodeMap;
public class LectorODT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "./PDF2EPUB/Origin/ODF/Oda Nobuna.odt";
		 OdfTextDocument elem = null;
		try {
			elem = OdfTextDocument.loadDocument("./PDF2EPUB/Origin/ODF/Oda Nobuna.odt");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 OdfContentDom content = null;
		try {
			content = elem.getContentDom();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 OfficeDocumentContentElement node =content.getRootElement();
		 System.out.println(node.getTextContent());

	        }

}
