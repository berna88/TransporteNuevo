package com.consistent.cuervo.transporte.resource.command;

import com.consistent.cuervo.transporte.constants.TrasportePortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mx.com.cuervo.rutas.transporte.model.Parada;
import mx.com.cuervo.rutas.transporte.service.ParadaLocalService;


@Component(
		 property = {
		 "javax.portlet.name="+ TrasportePortletKeys.Trasporte,
		 "mvc.command.name=getParadasSolicitud"
		 },
		 service = MVCResourceCommand.class
		 )
public class ParadasSolicitudMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			 {
		
		ThemeDisplay themeDisplay = 
				(ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		 JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			
		String rutaId=resourceRequest.getParameter("rutaId");

		List <Parada> paradas = _paradaLocalService.getParadas(
				themeDisplay.getScopeGroupId(), Long.valueOf(rutaId));
		
		for(Parada parada: paradas) {
			
			JSONObject json = JSONFactoryUtil.createJSONObject();
			json.put("nombreParada", parada.getNombreParada());
			json.put("idParada",parada.getParadaId());
			
			jsonArray.put(json);
		}
			try {
				resourceResponse.getWriter().write(jsonArray.toString());
			} catch (Exception e1) {
				System.out.println(e1.getCause() + " = " + e1.getMessage());
				}
		
	}

	@Reference(unbind="-")
	private ParadaLocalService _paradaLocalService;

}
