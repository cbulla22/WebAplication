package co.edu.unbosque.xtreme.webcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.unbosque.xtreme.controller.UsuarioController;
import co.edu.unbosque.xtreme.entity.Usuario;




@Controller

public class LoginWebControlador {
	
	private Usuario usuario2;
	private Usuario usuario1;
	@Autowired
	private UsuarioController usuarioController;

	@GetMapping("/login")
	public String LoginForm(Model model) {
		
		model.addAttribute("usuario", new Usuario());		
		
		return "login";
	}
	
	@GetMapping("/salir")
	public String salir(Model model) {
		VentasWebControlador.cantidadProductos.clear();
		VentasWebControlador.productosCompra.clear();
		VentasWebControlador.listaIva.clear();
		VentasWebControlador.listaTotal.clear();
		VentasWebControlador.clientePrincipal.setCedulaCliente(null);
		VentasWebControlador.clientePrincipal.setNombreCliente("");
		model.addAttribute("usuario", new Usuario());		
		
		return "login";
	}
	
	
	

	
	@PostMapping("/login")
	public String LoginSubmit( Usuario usuario, Model model, RedirectAttributes redirAttrs) {
		
		
	
		List<Usuario> usuarios = usuarioController.getUsuarios();	
		
		for (int i = 0; i < usuarios.size(); i++) {

			usuario2 = (Usuario) model.getAttribute("usuario");
			if (usuarios.get(i).getUsr().equals(usuario.getUsr()) && usuarios.get(i).getPasswordUsuario().equals(usuario.getPasswordUsuario())){
				model.addAttribute("login", usuario);	
				
				if (usuario2.getUsr().equals(usuarios.get(i).getUsr())) {
					
					usuario1 = new Usuario (usuarios.get(i).getCedulaUsuario(),usuarios.get(i).getEmailUsuario()
							,usuarios.get(i).getNombreUsuario(),usuarios.get(i).getPasswordUsuario(),usuarios.get(i).getUsr());
					model.addAttribute("usuario2", usuario);	
				}
				return "redirect:/index";
			}			
			else if(usuario.getUsr().isEmpty()) {
				redirAttrs.addFlashAttribute("vacio","Completa los campos");
				
			}else if(usuarios.get(i).getUsr().equals(usuario.getUsr())) {
				
				
				if( usuarios.get(i).getPasswordUsuario()!=usuario.getPasswordUsuario()) {
				redirAttrs.addFlashAttribute("password","Contraseña incorrecta");
				}
			}else {
				redirAttrs.addFlashAttribute("error","Usuario no existe");
			}

		}
		String mensaje1 = " no encontrad";
			return "redirect:/login";
	}
	
	@GetMapping("/index")
	public String prueba(Model model) {
		if (usuario1 != null) {
		model.addAttribute("usuario2", usuario1);	
		return "index";
		}
		return "redirect:/login";
	}





	public Usuario getUsuario() {
		return usuario1;
	}





	public void setUsuario(Usuario usuario) {
		this.usuario1 = usuario;
	}





	
	

}
