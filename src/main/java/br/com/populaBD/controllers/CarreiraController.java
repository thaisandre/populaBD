package br.com.populaBD.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.populaBD.models.Carreira;
import br.com.populaBD.models.CategoriaCarreira;
import br.com.populaBD.models.Ferramenta;
import br.com.populaBD.repository.CarreiraRepository;
import br.com.populaBD.repository.FerramentaRepository;

@RequestMapping("/carreira")
@Controller
public class CarreiraController {
	
	@Autowired
	CarreiraRepository carreiraRepository;
	
	@Autowired
	FerramentaRepository ferramentaRepository;
	
	
	@RequestMapping("/popula")
	public void popula() throws IOException {
		File arquivo = new File("carreiras.xlsx");
		FileInputStream fis = new FileInputStream(arquivo);

		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		Iterator<Row> rowIterator = mySheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();
			Carreira carreira = new Carreira();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				carreira.setNome(cell.getStringCellValue());

				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				CategoriaCarreira categoria = CategoriaCarreira.valueOf(cell.getStringCellValue());
				carreira.setCategoria(categoria);
				
				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				carreira.setLink(cell.getStringCellValue());
				
				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				String s = cell.getStringCellValue();
				List<Ferramenta> ferramentas = new ArrayList<>();
				for(String str : s.split(",")) {
					System.out.println(str);
					Ferramenta f = ferramentaRepository.findByNome(str);
					System.out.println("ACHOU FERRAMENTA: " + f.toString());
					ferramentas.add(f);
					System.out.println("ferramentas: " + ferramentas.toString());
				}
				carreira.setFerramentas(ferramentas);
				//carreira.setFerramentas(ferramentas);
				//ferramenta.setDescricao(cell.getStringCellValue());
				
				

			}
			System.out.println("");
			carreiraRepository.save(carreira);
			//System.out.println("ferramenta: " + ferramenta.getNome() + " - " + ferramenta.getCategoria() + " - "
			//		+ ferramenta.getDescricao());
		}

		myWorkBook.close();
		fis.close();
	}
	
	@RequestMapping("/lista")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("carreira/form");
		List<Carreira> carreiras = (List<Carreira>) carreiraRepository.findAll();
		return mv.addObject("carreiras", carreiras);
	}
}
