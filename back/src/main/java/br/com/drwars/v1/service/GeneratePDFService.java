package br.com.drwars.v1.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;

import javax.annotation.PostConstruct;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.drwars.util.HeaderAndFooter;
import net.minidev.json.JSONObject;


@Service
public class GeneratePDFService {
	private String savePath = "/srv/calisto/pdf";
	
	@PostConstruct
	private void init() {
		new File( savePath ).mkdirs(); 
	}
	
	private static final int FONT_SIZE = 9;

	private Font boldFont   = new Font(Font.FontFamily.TIMES_ROMAN, FONT_SIZE, Font.BOLD);
	private Font italicFont = new Font(Font.FontFamily.TIMES_ROMAN, FONT_SIZE, Font.ITALIC);	
	private Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, FONT_SIZE, Font.NORMAL);	
	
	private String readFileModel() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader( this.savePath + "/model.txt"  ));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();
		return stringBuilder.toString();		
	}
	
	
	public ResponseEntity<InputStreamResource> generateContrato( JSONObject data ) {
		float left = 80;
		float right = 80;
		float top = 30;
		float bottom = 40;

		String modalidade = data.getAsString("modalidade");
		String poderCalorifico = data.getAsString("poderCalorifico");
		String pressaoFornecimento = data.getAsString("pressaoFornecimento");
		String vazaoFornecimento = data.getAsString("vazaoFornecimento");
		String poderCalorificoRef = data.getAsString("poderCalorificoRef");
		String poderCalorificoSup = data.getAsString("poderCalorificoSup");
		String vendedora = data.getAsString("vendedora");
		String compradora = data.getAsString("compradora");
		String dataInicio = data.getAsString("dataInicio");

		
		try {
			
			// String model = readFileModel();
			
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        
	        Document document = new Document(PageSize.A4, left, right, top, bottom);
	        PdfWriter writer = PdfWriter.getInstance( document, out );
	        document.open();

	        
	        // -------------------------  ONDE MUDAR -------------------------------------
	        
	        document.addCreator("DRWARS - Tecnologia");
	        document.addAuthor("Sistema de Comércio de Metano");
	        document.addTitle("Contrato");
	        document.addCreationDate();	        
	        
	        addParagraph( document, "CONTRATO DE COMPRA E VENDA DE GÁS NATURAL NA MODALIDADE " + modalidade, boldFont, Element.ALIGN_CENTER );
	        addParagraph( document, "CELEBRADO ENTRE "+vendedora+". E A "+compradora+", NA FORMA ABAIXO: ", boldFont, Element.ALIGN_CENTER );	        
	        document.add(new Paragraph(" "));
	        addParagraph( document, vendedora + " “VENDEDORA“; e ", normalFont, Element.ALIGN_LEFT );
	        document.add(new Paragraph(" "));
	        addParagraph( document, compradora + " “COMPRADORA“; e ", normalFont, Element.ALIGN_LEFT );
	        addParagraph( document, "Individualmente referidas como “PARTE” e conjuntamente como “PARTES”, ", normalFont, Element.ALIGN_LEFT );
	        document.add(new Paragraph(" "));
	        addParagraph( document, "CONSIDERANDO QUE:", boldFont, Element.ALIGN_LEFT );

	        
	        PdfPTable table = new PdfPTable(new float[]{1, 10});
	        writer.setPageEvent(new HeaderAndFooter( "", savePath ) );		
	        table.addCell( getCellKey("(i)") );
	        table.addCell( getCellVal("a VENDEDORA deseja vender e disponibilizar GÁS NATURALà COMPRADORAno PONTO DE ENTREGA DEFINIDO;") );
	        table.addCell( getCellKey("(ii)") );
	        table.addCell( getCellVal( "COMPRADORA deseja comprar o referido GÁS, nos termos e condições aqui estabelecidos; " ) );	        
	        table.addCell( getCellKey("(iii)") );
	        table.addCell( getCellVal( "a VENDEDORA deseja vender e disponibilizar GÁS NATURAL à COMPRADORA e a COMPRADORA deseja comprar o referido GÁS, nos termos e condições aqui estabelecidos; e ") );	        
	        table.addCell( getCellKey("(iv)") );
	        table.addCell( getCellVal( "a Agência Nacional do Petróleo, Gás Natural e Biocombustíveis (ANP) está em processo de revisão da metodologia de cálculo para alocação dos custos de transporte nas parcelas de transporte que deverão constar dos contratos de compra e venda de gás natural. ") );	        
	        document.add(table);
	        
	        addParagraph( document, "RESOLVEM celebrar o presente CONTRATO de Compra e Venda de Gás Natural (“CONTRATO”),que passa a ser regido integralmente pelas cláusulas e condições a seguir estabelecidas. ", normalFont, Element.ALIGN_JUSTIFIED );
	        document.add(new Paragraph(" "));
	        
	        addParagraph( document, "CLÁUSULA 1 – OBJETO ", boldFont, Element.ALIGN_LEFT );
	        document.add(new Paragraph(" "));
	        addParagraph( document, "1.1 O presente CONTRATO tem por objeto a venda e entrega pela VENDEDORA e a compra e recebimento pela COMPRADORA de GÁS, na MODALIDADE FIRME INFLEXÍVEL, segundo as condições estipuladas neste CONTRATO. ", normalFont, Element.ALIGN_JUSTIFIED );
	        
	        document.add(new Paragraph(" "));
	        addParagraph( document, "1.1.1 Informações a serem cumpridas na vigência do contrato", normalFont, Element.ALIGN_JUSTIFIED );
	        
	        PdfPTable table2 = new PdfPTable(new float[]{5, 2});
	        table2.addCell( getCellKey("• Pressão de Fornecimento: ") ); table2.addCell( getCellVal( pressaoFornecimento ) );
	        table2.addCell( getCellKey("• Vazão de Fornecimento: ") ); table2.addCell( getCellVal( vazaoFornecimento ) );
	        table2.addCell( getCellKey("• Poder Calorífico Referência: ") ); table2.addCell( getCellVal( poderCalorificoRef ) );
	        table2.addCell( getCellKey("• Poder Calorífico Superior: ") ); table2.addCell( getCellVal( poderCalorificoSup ) );
	        document.add(table2);
	      
	        document.add(new Paragraph(" "));
	        addParagraph( document, "CLÁUSULA 2 – VIGÊNCIA E INÍCIO DO FORNECIMENTO ", boldFont, Element.ALIGN_LEFT );
	        document.add(new Paragraph(" "));	        
	        
	        addParagraph( document, "2.1 O INÍCIO DO FORNECIMENTO ocorrerá a partir da DATA "+dataInicio+", para os PONTOS DE ENTREGA descritos no item 12.1, para todos os efeitos deste CONTRATO,desde que as condições precedentes abaixo descritas, referentes à VENDEDORA,sejam cumulativamente satisfeitas.", normalFont, Element.ALIGN_JUSTIFIED );	        
	        addParagraph( document, "2.1.1 A VENDEDORA se compromete a NOTIFICAR à COMPRADORA sobre a satisfação ou não das CONDIÇÕES PRECEDENTES.", normalFont, Element.ALIGN_JUSTIFIED );	        
	        addParagraph( document, "2.2 O CONTRATO terá vigência e eficácia a partir da data de INÍCIO DO FORNECIMENTO. ", normalFont, Element.ALIGN_JUSTIFIED );
	        addParagraph( document, "2.3 As PARTES poderão de comum acordo prorrogar o prazo de vigência deste, a partir do término definido no item 3.2, através de celebração de termo de aditamento ao presente CONTRATO. ", normalFont, Element.ALIGN_JUSTIFIED );
	        addParagraph( document, "2.4 As PARTES poderão renegociar uma nova data para INÍCIO DO FORNECIMENTO, de comum acordo, mediante a celebração de termo aditivo a este CONTRATO, para os PONTOS DE ENTREGA definidos.", normalFont, Element.ALIGN_JUSTIFIED );
	        addParagraph( document, "2.5 Durante a vigência do Contrato, a responsabilidade pela contratação da Saída no Sistema de Transporte poderá ser transferida para a Compradora, caso seja de interesse comum das PARTES. ", normalFont, Element.ALIGN_JUSTIFIED );
	        addParagraph( document, "2.6 As PARTES reconhecem que durante o período de contratação do transporte sob o regime extraordinário, a VENDEDORA se responsabilizará pela contratação do serviço de saída. Quando o serviço de transporte firme for disponibilizado, as PARTES se comprometem a manter entendimentos quanto à responsabilidade pela contratação do serviço de transporte de saída para a entrega do GÁS nos PONTOS DE ENTREGA objeto desse CONTRATO. ", normalFont, Element.ALIGN_JUSTIFIED );
	        addParagraph( document, "2.7 Após o término do prazo de vigência do CONTRATO, permanecerão válidas as regras referentes à recuperação do saldo de QPNR eventualmente remanescente, nos termos do item 5.1.1.5(b) por um período estabelecido. ", normalFont, Element.ALIGN_JUSTIFIED );
	        
	        document.newPage();
	        

	        addParagraph( document, "CLÁUSULA 3 – CONDIÇÕES DE ENTREGA ", boldFont, Element.ALIGN_LEFT );
	        document.add(new Paragraph(" "));	        
	        
	        addParagraph( document, "3.1 O GÁS será disponibilizado pela VENDEDORA à COMPRADORA nos PONTOS DE ENTREGA, atendendo às CONDIÇÕES DE ENTREGA que estão definidas nesta Cláusula e às especificações de QUALIDADE DO GÁS.  ", normalFont, Element.ALIGN_JUSTIFIED );
	        addParagraph( document, "3.2 As CONDIÇÕES DE ENTREGA em cada ESTAÇÃO DE ENTREGA serão apresentadas.", normalFont, Element.ALIGN_JUSTIFIED );
	        
	        PdfPTable table3 = new PdfPTable(new float[]{5, 2});
	        table3.addCell( getCellKey("• Pressão de Fornecimento: ") ); table.addCell( getCellVal( pressaoFornecimento ) );
	        document.add(table3);	        
	        
	        addParagraph( document, "3.3 Vazões de Fornecimento.", normalFont, Element.ALIGN_JUSTIFIED );
	        
	        PdfPTable table4 = new PdfPTable(new float[]{5, 2});
	        table4.addCell( getCellKey("• Vazão de Fornecimento: ") ); table.addCell( getCellVal( vazaoFornecimento ) );
	        document.add(table4);	        
	        
	        document.add(new Paragraph(" "));
	        addParagraph( document, "CLÁUSULA 4 – QUALIDADE DO GÁS  ", boldFont, Element.ALIGN_LEFT );
	        document.add(new Paragraph(" "));	        
	        
	        addParagraph( document, "4.1 A determinação do PODER CALORÍFICO SUPERIOR (PCS)nas CONDIÇÕES BASE, será efetuada por cálculo, conforme a norma ISO 6976:2016, ou a que venha a substituí-la em razão de disposição normativa superveniente. Poder Calorifico Superior: " + poderCalorifico , normalFont, Element.ALIGN_JUSTIFIED );

	        document.add(new Paragraph(" "));
	        addParagraph( document, "CLÁUSULA 5 – CONFORMIDADE DAS PARTES ", boldFont, Element.ALIGN_LEFT );
	        document.add(new Paragraph(" "));	
	        
	        addParagraph( document, "5.1. As PARTES expressam a sua concordância com o teor integral deste CONTRATO, obrigando-se a seu fiel e estrito cumprimento, em fé do que são firmadas em 02 (duas) vias de um mesmo teor e para um só efeito, na presença de 2 testemunhas. " , normalFont, Element.ALIGN_JUSTIFIED );
	        
	        // -----------------------------------------------------------------------------
	        
	        document.close();        
	        ByteArrayInputStream bis = new ByteArrayInputStream( out.toByteArray() );
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=contrato.pdf");	        
	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body( new InputStreamResource(bis) );		        
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}        
        
	}
	
	private void addParagraph( Document doc, String text, Font font, int align ) throws Exception {
        Paragraph l6 = new Paragraph(text, font);
        l6.setAlignment( align );	        	        
        doc.add(l6);		
	}
	
    private PdfPCell getCellVal(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, normalFont));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }

    private PdfPCell getCellKey(String key) {
        PdfPCell cell = new PdfPCell(new Phrase(key, normalFont));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }	
	

}
