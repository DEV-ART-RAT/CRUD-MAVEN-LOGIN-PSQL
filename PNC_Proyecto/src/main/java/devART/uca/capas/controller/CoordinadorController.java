package devART.uca.capas.controller;

import java.security.Principal;
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
import devART.uca.capas.utils.WebUtils;
import org.apache.tomcat.util.bcel.classfile.EnumElementValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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


	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String listado(Principal principal) {

		User auth = (User) ((Authentication) principal).getPrincipal();
		String rol = WebUtils.getRole(auth);
		//System.out.println(rol);

		if(rol.equals("ROLE_USER")){
			return "redirect:/userCoordinador";
		}

		if(rol.equals("ROLE_ADMIN")){
			return "redirect:/admin";
		}
		return "redirect:/";
	}
	@RequestMapping(value = "/userCoordinador", method = RequestMethod.GET)
	public ModelAndView listadoCoordinador(Principal principal,String mensaje) {
		if (mensaje==null){
			mensaje="";
		}
		ModelAndView mav = new ModelAndView();
		List<Expediente> expedientes = null;
		List<Expediente> expediente = null;
		List<AlumnoxMateria> alumnoxMaterias = null;
		try {
			expedientes = expedienteService.findAllExpe();
			promediotodo(expedientes);
			aprobadasreprobadas(expedientes);
		}catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("mensaje",mensaje);
		mav.addObject("expedientes", expedientes);
		mav.setViewName("/Coordinador/coordinador");

		return mav;
	}
	@RequestMapping(value = "/busquedaexpedientes", method = RequestMethod.GET)
	public ModelAndView listadobusqueda(Principal principal,@RequestParam(value="codigo") Integer codigo) {
		ModelAndView mav = new ModelAndView();
		List<Expediente> expedientes = null;
		List<Expediente> expediente = null;
		List<AlumnoxMateria> alumnoxMaterias = null;
		try {

			expedientes = expedienteService.findAllExpe();
			promediotodo(expedientes);
			aprobadasreprobadas(expedientes);
		}catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("expedientes", expedientes);
		mav.setViewName("/Coordinador/coordinador");

		return mav;
	}


	@RequestMapping("/guardarExpediente")
	public ModelAndView guardarExpediente(Principal principal,@Valid @ModelAttribute Expediente expediente, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		List<Expediente> expedientes = null;
		if(result.hasErrors()) {
			mav.setViewName("/Coordinador/AgregarExpediente");
			return mav;

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
			List<Expediente> expedientes1 = null;
			List<Expediente> expediente1 = null;
			List<AlumnoxMateria> alumnoxMaterias = null;
			try {
				expedientes1 = expedienteService.findAllExpe();
				promediotodo(expedientes1);
				aprobadasreprobadas(expedientes1);
			}catch (Exception e) {
				e.printStackTrace();
			}
			mav.addObject("mensaje", "Expediente Agregado con Exito!");
			mav.addObject("expedientes", expedientes1);
			mav.setViewName("/Coordinador/coordinador");
			return mav;
		}
	}

	@RequestMapping(value="/nuevaMateriaexpediente", method=RequestMethod.POST)
	public ModelAndView NuevoMateria(@RequestParam(value="codigo") Integer codigo) {
		System.out.println("Codigo final final final es :"+codigo);
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
	public ModelAndView guardarExpedientemateria(@RequestParam("ciclo") Long ciclo, @ModelAttribute("alumnoxmateria") @Valid AlumnoxMateria alumnoxMateria, BindingResult result) {
		ModelAndView mav = new ModelAndView();

		if(result.hasErrors()) {
			List<Materia> materias = materiaService.findAll();
			List<Expediente> expedientes = expedienteService.filtrarPorID(alumnoxMateria.getExpediente().getCodigo());
			mav.addObject("expedientes", expedientes);
			mav.addObject("materias", materias);
			mav.setViewName("/Coordinador/AgregarMateria");
			return mav;
		}else{
			try {
				if(ciclo==1){
					alumnoxMateria.setCiclo("01");
				}else if (ciclo==2){
					alumnoxMateria.setCiclo("02");
				}else if(ciclo==3){
					alumnoxMateria.setCiclo("03");
				}

				if(alumnoxMateria.getNota()>=6){
					alumnoxMateria.setEstado("Aprobado");
					System.out.println(alumnoxMateria.getEstado());
				}else{
					alumnoxMateria.setEstado("Reprobado");
					System.out.println(alumnoxMateria.getEstado());
				}
				alumnoxMateriaService.insert(alumnoxMateria);

			}catch (Exception e) {
				e.printStackTrace();
			}
			mav.addObject("mensaje","Agregado con exito!");
			AlumnoxMateria alumnoxmateria = new AlumnoxMateria();
			mav.addObject("alumnoxmateria", alumnoxmateria);

		}
		List<Materia> materias = materiaService.findAll();
		List<Expediente> expedientes = expedienteService.filtrarPorID(alumnoxMateria.getExpediente().getCodigo());
		mav.addObject("expedientes", expedientes);
		mav.addObject("materias", materias);
		mav.setViewName("/Coordinador/AgregarMateria");
		return mav;
	}
	@RequestMapping(value="/editarMateriaexpediente", method=RequestMethod.POST)
	public ModelAndView editarMateria(@RequestParam(value="codigo") Integer codigo) {
		System.out.println("Codigo final final final es :"+codigo);
		AlumnoxMateria alumnoxmateria = null;
		alumnoxmateria=alumnoxMateriaService.filtrarUNO(codigo);
		ModelAndView mav = new ModelAndView();
		List<Materia> materias = null;
		materias = materiaService.findAll();
		List<Expediente> expedientes = null;
		expedientes = expedienteService.filtrarPorID(alumnoxmateria.getExpediente().getCodigo());
		mav.addObject("expedientes", expedientes);
		mav.addObject("alumnoxmateria", alumnoxmateria);
		mav.addObject("materias", materias);
		mav.setViewName("/Coordinador/editarMateria");
		return mav;
	}
	@RequestMapping(value="/editarExpedientemateria", method=RequestMethod.POST)
	public ModelAndView editarExpedientemateria(@RequestParam("ciclo") Long ciclo, @ModelAttribute("alumnoxmateria") @Valid AlumnoxMateria alumnoxMateria, BindingResult result) {
		ModelAndView mav = new ModelAndView();

		if(result.hasErrors()) {
			List<Materia> materias = materiaService.findAll();
			List<Expediente> expedientes = expedienteService.filtrarPorID(alumnoxMateria.getExpediente().getCodigo());
			mav.addObject("expedientes", expedientes);
			mav.addObject("materias", materias);
			mav.setViewName("/Coordinador/editarMateria");
			return mav;
		}else{
			try {
				if(ciclo==1){
					alumnoxMateria.setCiclo("01");
				}else if (ciclo==2){
					alumnoxMateria.setCiclo("02");
				}else if(ciclo==3){
					alumnoxMateria.setCiclo("03");
				}

				if(alumnoxMateria.getNota()>=6){
					alumnoxMateria.setEstado("Aprobado");
					System.out.println(alumnoxMateria.getEstado());
				}else{
					alumnoxMateria.setEstado("Reprobado");
					System.out.println(alumnoxMateria.getEstado());
				}
				alumnoxMateriaService.insert(alumnoxMateria);

			}catch (Exception e) {
				e.printStackTrace();
			}
			mav.addObject("mensaje","Editado con exito!");
			AlumnoxMateria alumnoxmateria = new AlumnoxMateria();
			mav.addObject("alumnoxmateria", alumnoxmateria);

		}
		List<Materia> materias = materiaService.findAll();
		List<Expediente> expedientes = expedienteService.filtrarPorID(alumnoxMateria.getExpediente().getCodigo());
		mav.addObject("expedientes", expedientes);
		mav.addObject("materias", materias);
		mav.setViewName("/Coordinador/editarMateria");
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
	public ModelAndView filtrar(Principal principal,@RequestParam(value="busqueda") String cadena,@RequestParam Long tipo)
	{
		ModelAndView mav = new ModelAndView();
		List<Expediente> expedientes = null;
		try {
			if (cadena==""){
				return listadoCoordinador(principal,"");
			}else {
				if (tipo ==1) {
					expedientes = expedienteService.filtrarPorGeneral(cadena);
					promediotodo(expedientes);
					aprobadasreprobadas(expedientes);
				}
				if (tipo ==2) {
					expedientes = expedienteService.filtrarPorNombre(cadena);
					promediotodo(expedientes);
					aprobadasreprobadas(expedientes);
				}
				if (tipo ==3) {
					System.out.println("Apellido = "+cadena);
					expedientes = expedienteService.filtrarPorApellido(cadena);
					promediotodo(expedientes);
					aprobadasreprobadas(expedientes);
				}
				if (tipo ==4) {
					expedientes = expedienteService.filtrarPorCarne(cadena);
					promediotodo(expedientes);
					aprobadasreprobadas(expedientes);
				}if (tipo ==5) {
					expedientes = expedienteService.filtrarPorCentro(cadena);
					promediotodo(expedientes);
					aprobadasreprobadas(expedientes);
				}


			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("expedientes", expedientes);
		mav.setViewName("/Coordinador/listadoexpedientes");

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
	public ModelAndView guardarExpedientemodificado(Principal principal,@Valid @ModelAttribute Expediente expediente, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("/Coordinador/modificarExpediente");
			return mav;

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
			mav.addObject("mensaje","Modificado con exito");

			return mostrarExpediente(principal,String.valueOf(expediente.getCodigo()),"Modificado con exito");
		}
	}

	@RequestMapping(value="/expediente", method=RequestMethod.POST)
	public ModelAndView mostrarExpediente(Principal principal,@RequestParam(value="id") String codigo,String mensaje)
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
		mav.addObject("mensaje",mensaje);
		return mav;
	}

	@RequestMapping(value="/cursadas", method=RequestMethod.POST)
	public ModelAndView mostrarMaterias(@RequestParam(value="id") Integer codigo)
	{
		System.out.println("Su codigo es :"+codigo);
		ModelAndView mav = new ModelAndView();
		List<AlumnoxMateria> alumnoxMaterias = null;
		AlumnoxMateria alumnoxmateria = new AlumnoxMateria();
		Expediente expediente=null;
		List<Materia> materias = null;
		try {
			//int codigoint = Integer.parseInt(codigo);
			alumnoxMaterias = alumnoxMateriaService.findOneEstudiante(codigo);
			mav.addObject("promedio","Su promedio es: "+df.format(promedio(alumnoxMaterias)));
		}catch (Exception e) {
			e.printStackTrace();
		}
		if (codigo==null){
			mav.setViewName("/Coordinador/expediente");
			return mav;
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
			suma = suma + alumnoxMaterias.get(i).getNota();
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
//					System.out.println(a.getNota());
					sumanotas.set((float) (sumanotas.get() + a.getNota()));
				});
					if(sumanotas!=null||e.getAlumnoxMaterias().size()!=0)
					{
						double promedio;
						promedio = sumanotas.get() / e.getAlumnoxMaterias().size();
						if(promedio==Double.NaN){
							e.setPromedio(0);
						}else{
							double roundedDouble = Math.round(promedio * 100.0) / 100.0;
							e.setPromedio(roundedDouble);
						}
					}
					else{
						e.setPromedio(0);
					}
		});
	};

	public void aprobadasreprobadas(List<Expediente> expedientes){
		expedientes.forEach(e->{
			AtomicInteger aprobadas= new AtomicInteger();
			AtomicInteger reprobadas= new AtomicInteger();
			e.getAlumnoxMaterias().forEach(a-> {
				if(a.getNota()>=6){
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