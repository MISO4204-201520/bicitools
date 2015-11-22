/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.variabilidad;

/**
 *
 * @author Taidy
 */

import com.bicitools.variabilidad.*;
import com.bicitools.variabilidad.AnotacionVariabilidadInfo.Priority;



@AnotacionVariabilidadInfo(
	priority = Priority.HIGH, 
	createdBy = "Bicitools",
        tags = {"sales","test" }
	
)
public class TestExample {

	@AnotacionVariabilidad(enabled = true)
	void testA() {
	  if (true)
		throw new RuntimeException("This test always failed");
	}

	@AnotacionVariabilidad(enabled = false)
	void testB() {
	  if (false)
		throw new RuntimeException("This test always passed");
	}

	@AnotacionVariabilidad(enabled = true)
	void testC() {
	  if (10 > 1) {
		// do nothing, this test always passed.
	  }
	}

}
