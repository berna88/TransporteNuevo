package com.consistent.cuervo.transporte.resource.command;

import com.consistent.cuervo.transporte.constants.TrasportePortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;

import java.io.IOException;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mx.com.cuervo.rutas.transporte.model.Ruta;
import mx.com.cuervo.rutas.transporte.service.RutaLocalService;


@Component(
		 property = {
		 "javax.portlet.name="+ TrasportePortletKeys.Trasporte,
		 "mvc.command.name=getCapacidadDisponibilidad"
		 },
		 service = MVCResourceCommand.class
		 )
public class CapacidadDisponibilidadMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
	//	System.out.println("INSIDE DISPONIBILIDAD");
		String rutaId=resourceRequest.getParameter("rutaId");
		Ruta ruta = _rutaLocalService.getRuta(Long.parseLong(rutaId));
		JSONObject json = JSONFactoryUtil.createJSONObject();
		json.put("capacidad", ruta.getCapacidad());
		json.put("disponibilidad", ruta.getDisponibilidad());

		try {
			
	//		System.out.println(json.toString());
			resourceResponse.getWriter().write(json.toString());
		} catch (IOException e1) {
			System.out.println(e1.getCause() + " = " + e1.getMessage());
			
		}
	}

	@Reference(unbind = "-")
	private RutaLocalService _rutaLocalService;

}
