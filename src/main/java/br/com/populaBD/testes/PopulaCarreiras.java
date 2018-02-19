package br.com.populaBD.testes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.populaBD.models.CategoriaCarreira;

public class PopulaCarreiras {
	
	public static void main(String[] args) throws IOException {
		File arquivo = new File("carreiras.xlsx");
		FileInputStream fis = new FileInputStream(arquivo);

		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		Iterator<Row> rowIterator = mySheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();
			//Carreira carreira = new Carreira();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				//carreira.setNome(cell.getStringCellValue());

				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				CategoriaCarreira categoria = CategoriaCarreira.valueOf(cell.getStringCellValue());
				//carreira.setCategoria(categoria);
				
				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				//carreira.setLink(cell.getStringCellValue());
				
				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				String s = cell.getStringCellValue();
				for(String str : s.split(",")) {
					System.out.println(str);
				}
				//carreira.setFerramentas(ferramentas);
				//ferramenta.setDescricao(cell.getStringCellValue());
				
				

			}
			System.out.println("");
			//System.out.println("ferramenta: " + ferramenta.getNome() + " - " + ferramenta.getCategoria() + " - "
			//		+ ferramenta.getDescricao());
		}

		myWorkBook.close();
		fis.close();
	}
}
