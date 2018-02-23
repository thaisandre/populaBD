package br.com.populaBD.testes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.populaBD.models.Curso;
import br.com.populaBD.models.TipoCurso;

public class PopulaCurso {
	
	public static void main(String[] args) throws IOException {
		File arquivo = new File("cursos.xlsx");
		FileInputStream fis = new FileInputStream(arquivo);

		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		Iterator<Row> rowIterator = mySheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();
			Curso curso = new Curso();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				curso.setNome(cell.getStringCellValue());
				
				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				curso.setNomeAbreviado(cell.getStringCellValue());
				
				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				curso.setLink(cell.getStringCellValue());
				
				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				TipoCurso tipo = TipoCurso.valueOf(cell.getStringCellValue());
				//carreira.setCategoria(categoria);
				
				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				String c = cell.getStringCellValue();
				for(String carreira_str : c.split(",")){
					System.out.println(carreira_str);
					//procura carreira e seta
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
