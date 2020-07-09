package devART.uca.capas.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.validation.Valid;

import devART.uca.capas.domain.*;
import devART.uca.capas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class CoordinadorController {
	@Autowired
	ExpedienteServiceImpl expedienteService;

	@Autowired
	MateriaService materiaService;

	@Autowired
	AlumnoxMateriaServiceImpl alumnoxMateriaService;

	private static DecimalFormat df = new DecimalFormat("0.00");

	@RequestMapping("/guardarExpediente")
	public ModelAndView guardarExpediente(@Valid @ModelAttribute Expediente expediente, BindingResult result) {

		ModelAndView mav = new ModelAndView();
		List<Expediente> expedientes = null;
		if(result.hasErrors()) {
			mav.setViewName("/Coordinador/AgregarExpediente");
		}else{
			try {
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ahora = LocalDate.now();
				LocalDate fechaNac = LocalDate.parse(expediente.getD_fnacimiento(), fmt);
				System.out.println("Fecha Nacimiento es  "+fechaNac);
				System.out.println("getD_fnacimiento() es  "+expediente.getD_fnacimiento());
				Period periodo = Period.between(fechaNac, ahora);
				if(periodo.getYears()>999){
					expediente.setS_edad(Integer.toString(999));
					expedienteService.insert(expediente);
				}else {
					expediente.setS_edad(Integer.toString(periodo.getYears()));
					expedienteService.insert(expediente);
				}

			}catch (Exception e) {
				e.printStackTrace();
			}
			expedientes = expedienteService.findAllExpe();
			mav.addObject("expedientes", expedientes);
			mav.addObject("message", "Estudiante Guardado!");
			mav.setViewName("/Coordinador/coordinador");

		}
		return mav;
	}

	@RequestMapping(value="/nuevaMateriaexpediente", method=RequestMethod.POST)
	public ModelAndView NuevoMateria(@RequestParam(value="codigo") Integer codigo) {
		System.out.println("Codigo final  es :"+codigo);
		AlumnoxMateria alumnoxmateria = new AlumnoxMateria();
		ModelAndView mav = new ModelAndView();
		List<Materia> materias = null;
		materias = materiaService.findAll();
		List<Expediente> expedientes = null;
		expedientes = expedienteService.filtrarPorID(codigo);
		mav.addObject("expedientes", expedientes);
		mav.addObject("alumnoxmateria", alumnoxmateria);
		mav.addObject("materias", materias);
		mav.setViewName("/Coordinador/AgregarMateria");
		return mav;
	}
	@RequestMapping(value="/guardarExpedientemateria", method=RequestMethod.POST)
	public ModelAndView guardarExpedientemateria(@Valid @ModelAttribute AlumnoxMateria alumnoxMateria, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {

			mav.setViewName("/Coordinador/AgregarMateria");
		}else{
			try {
				Float nota = Float.parseFloat(alumnoxMateria.getNota());
				if(nota>=6){
					alumnoxMateria.setEstado("Aprobado");
					System.out.println(alumnoxMateria.getEstado());
				}else{
					alumnoxMateria.setEstado("Reprobado");
					System.out.println(alumnoxMateria.getEstado());
				}
				alumnoxMateriaService.insert(alumnoxMateria);
//
			}catch (Exception e) {
				e.printStackTrace();
			}
			List<Expediente> expedientes = null;
			expedientes = expedienteService.findAllExpe();
			promediotodo(expedientes);
			aprobadasreprobadas(expedientes);
			mav.addObject("expedientes", expedientes);
			mav.setViewName("/Coordinador/coordinador");
		}
		return mav;
	}
	@RequestMapping("/NuevoExpediente")
	public ModelAndView NuevoExpediente() {
		ModelAndView mav = new ModelAndView();
		Expediente expediente=new Expediente();
		mav.addObject("expediente", expediente);
		mav.setViewName("/Coordinador/AgregarExpediente");
		return mav;
	}



	@RequestMapping(value="/buscarexpediente", method=RequestMethod.POST)
	public ModelAndView filtrar(@RequestParam(value="busqueda") String cadena,@RequestParam Long tipo)
	{
		ModelAndView mav = new ModelAndView();
		List<Expediente> expedientes = null;
		try {
			if (cadena==""){
				expedientes = expedienteService.findAllExpe();
				promediotodo(expedientes);
				aprobadasreprobadas(expedientes);
			}else {
				if (tipo ==1) {
					System.out.println("Nombre = "+cadena);
					expedientes = expedienteService.filtrarPorNombre(cadena);
					promediotodo(expedientes);
					aprobadasreprobadas(expedientes);
				}
				if (tipo ==2) {
					System.out.println("Apellido = "+cadena);
					expedientes = expedienteService.filtrarPorApellido(cadena);
					promediotodo(expedientes);
					aprobadasreprobadas(expedientes);
				}}

		}catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("expedientes", expedientes);
		mav.setViewName("/Coordinador/coordinador");

		return mav;
	}
	@RequestMapping(value="/editarexpedienteborrar")
	public ModelAndView editarBorrar(@RequestParam(value="id") String codigo)
	{
		ModelAndView mav = new ModelAndView();
		List<Expediente> expedientes = null;
		try {
			int codigoint = Integer.parseInt(codigo);
			expedienteService.delete(codigoint);

		}catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("expedientes", expedientes);
		mav.setViewName("/Coordinador/coordinador");

		return mav;
	}
	@RequestMapping(value="/editarexpedienteeditar")
	public ModelAndView editareditar(@RequestParam(value="id") String codigo)
	{
		ModelAndView mav = new ModelAndView();
		Expediente expediente=null;
		try {
			int codigoint = Integer.parseInt(codigo);
			expediente = expedienteService.filtrarUNO(codigoint);
			mav.addObject("expediente", expediente);
			mav.setViewName("/Coordinador/modificarExpediente");

		}catch (Exception e) {
			e.printStackTrace();
		}


		return mav;
	}

	@RequestMapping("/guardarExpedientemodificado")
	public ModelAndView guardarExpedientemodificado(@Valid @ModelAttribute Expediente expediente, BindingResult result) {

		ModelAndView mav = new ModelAndView();
		List<Expediente> expedientes = null;
		if(result.hasErrors()) {
			mav.setViewName("/Coordinador/modificarExpediente");

		}else{
			try {
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ahora = LocalDate.now();
				LocalDate fechaNac = LocalDate.parse(expediente.getD_fnacimiento(), fmt);
				System.out.println("Fecha NAch es  "+fechaNac);
				System.out.println("getD_fnacimiento() es  "+expediente.getD_fnacimiento());
				Period periodo = Period.between(fechaNac, ahora);
				if(periodo.getYears()>999){

					expediente.setS_edad(Integer.toString(999));
					expedienteService.insert(expediente);
					expedientes = expedienteService.findAllExpe();
				}else {
					expediente.setS_edad(Integer.toString(periodo.getYears()));
					expedienteService.insert(expediente);
					expedientes = expedienteService.findAllExpe();
				}

			}catch (Exception e) {
				e.printStackTrace();
			}
			mav.addObject("expedientes", expedientes);
			mav.addObject("message", "Estudiante Modificado!");
			mav.setViewName("/Coordinador/coordinador");
		}

		return mav;
	}

	@RequestMapping(value="/expediente", method=RequestMethod.POST)
	public ModelAndView mostrarExpediente(@RequestParam(value="id") String codigo)
	{
		ModelAndView mav = new ModelAndView();
		Expediente expediente=null;
		try {
			int codigoint = Integer.parseInt(codigo);
			expediente = expedienteService.filtrarUNO(codigoint);
			mav.addObject("expediente", expediente);
			mav.setViewName("/Coordinador/expediente");

		}catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value="/cursadas", method=RequestMethod.POST)
	public ModelAndView mostrarMaterias(@RequestParam(value="id") Integer codigo)
	{
		ModelAndView mav = new ModelAndView();
		List<AlumnoxMateria> alumnoxMaterias = null;
		AlumnoxMateria alumnoxmateria = new AlumnoxMateria();
		Expediente expediente=null;
		List<Materia> materias = null;
		try {
			//int codigoint = Integer.parseInt(codigo);
			alumnoxMaterias = alumnoxMateriaService.findOneEstudiante(codigo);
			mav.addObject("promedio","Su promedio es: "+df.format(promedio(alumnoxMaterias)));

//			alumnoxMaterias = alumnoxMateriaService.findAll();
			System.out.println("Codigo es :"+codigo);

		}catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("alumnoxmateria", alumnoxmateria);
		expediente = expedienteService.filtrarUNO(codigo);
		materias = materiaService.findAll();
		mav.addObject("materias", materias);
		mav.addObject("expediente", expediente);
		mav.addObject("alumnoxmaterias", alumnoxMaterias);
		mav.setViewName("/Coordinador/materiasCursadas");
		return mav;
	}

	public double promedio(List<AlumnoxMateria> alumnoxMaterias){
		double suma = 0;
		for (int i = 0; i < alumnoxMaterias.size(); i++) {
			double nota = Float.parseFloat(alumnoxMaterias.get(i).getNota());
			suma = suma + nota;
		}
		suma = suma/alumnoxMaterias.size();
		if(alumnoxMaterias.size()==0){
			return 0.0;
		}
		else{
			return suma;
		}
	};
	public void promediotodo(List<Expediente> expedientes){
		expedientes.forEach(e->{
			AtomicReference<Float> sumanotas = new AtomicReference<>((float) 0);
			e.getAlumnoxMaterias().forEach(a-> {
				if(a.getNota()!=""||a.getNota()!=null){
					float nota = Float.parseFloat(a.getNota());
					sumanotas.set(sumanotas.get() + nota);
				}
				else{
					float sumatotal;
					sumatotal = (float) 0.0;
				}
			});
			if(sumanotas!=null||e.getAlumnoxMaterias().size()!=0){
				double promedio;
				promedio = sumanotas.get() / e.getAlumnoxMaterias().size();
				if(promedio==Double.NaN){
//					System.out.println("No tienen promedio");
					e.setPromedio(0);
				}else{
//					System.out.println(promedio);
					double roundedDouble = Math.round(promedio * 100.0) / 100.0;
					e.setPromedio(roundedDouble);
				}
			}
		});
	};

	public void aprobadasreprobadas(List<Expediente> expedientes){
		expedientes.forEach(e->{
			AtomicInteger aprobadas= new AtomicInteger();
			AtomicInteger reprobadas= new AtomicInteger();
			e.getAlumnoxMaterias().forEach(a-> {
				float nota = Float.parseFloat(a.getNota());
				if(nota>=6){
					aprobadas.addAndGet( 1);

				}
				else
				{
					reprobadas.addAndGet(1);
				}
			});
//			System.out.println("Aprobadas: "+aprobadas);
//			System.out.println("Reprobadas: "+reprobadas);
			e.setAprobadas(aprobadas.get());
			e.setReprobadas(reprobadas.get());
		});
	}
}
//https://parzibyte.me/blog/2019/09/02/th-each-thymeleaf-recorrer-listas/

//https://stackoverflow.com/questions/24802681/org-springframework-validation-beanpropertybindingresult-exception