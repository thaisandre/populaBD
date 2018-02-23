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
import br.com.populaBD.models.Curso;
import br.com.populaBD.models.Ferramenta;
import br.com.populaBD.models.TipoCurso;
import br.com.populaBD.repository.CarreiraRepository;
import br.com.populaBD.repository.CursoRepository;
import br.com.populaBD.repository.FerramentaRepository;

@RequestMapping("/curso")
@Controller
public class CursoController {
	
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private CarreiraRepository carreiraRepository;
	
	@Autowired
	private FerramentaRepository ferramentaRepository;
	
	
	@RequestMapping("/popula")
	public void popula() throws IOException {
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
				curso.setTipo(tipo);
				
				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				String sc = cell.getStringCellValue();
				List<Carreira> carreiras = new ArrayList<>();
				for(String str : sc.split(",")) {
					System.out.println(str);
					Carreira c = carreiraRepository.findByNome(str);
					System.out.println("ACHOU CARREIRA: " + c.toString());
					carreiras.add(c);
					System.out.println("carreiras: " + carreiras.toString());
				}
				curso.setCarreiras(carreiras);;
				
				cell = cellIterator.next();
				System.out.println(cell.getStringCellValue());
				String sf = cell.getStringCellValue();
				List<Ferramenta> ferramentas = new ArrayList<>();
				for(String str : sf.split(",")) {
					System.out.println(str);
					Ferramenta f = ferramentaRepository.findByNome(str);
					System.out.println("ACHOU FERRAMENTA: " + f.toString());
					ferramentas.add(f);
					System.out.println("ferramentas: " + ferramentas.toString());
				}
				
				curso.setFerramentas(ferramentas);
				//carreira.setFerramentas(ferramentas);
				//ferramenta.setDescricao(cell.getStringCellValue());	
			}
			System.out.println("");
			cursoRepository.save(curso);
			//System.out.println("ferramenta: " + ferramenta.getNome() + " - " + ferramenta.getCategoria() + " - "
			//		+ ferramenta.getDescricao());
		}

		myWorkBook.close();
		fis.close();
	}
	
	@RequestMapping("/lista")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("curso/form");
		List<Curso> cursos = (List<Curso>) cursoRepository.findAll();
		return mv.addObject("cursos", cursos);
	}
}
