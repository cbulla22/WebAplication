package co.edu.unbosque.xtreme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.xtreme.entity.DetalleVenta;
import co.edu.unbosque.xtreme.service.DetalleVentaService;

@RestController
@RequestMapping(path = "/testDetalleVenta")
public class DetalleVentaController {

		@Autowired
		DetalleVentaService detalleVentaService;
		
		@GetMapping("/ListaDetalleVenta")
		
		public List<DetalleVenta> getDetalleVenta(){
			
			return detalleVentaService.getDetalleVenta();
		}
		
		@PostMapping ("/guardarDetalleVenta")
		
		public void guardarDetalleVenta(@RequestBody DetalleVenta detalleVenta) {
			
			detalleVentaService.addDetalleVenta(detalleVenta);
			
		}
}
