package com.consistent.cuervo.transporte.resource.command;

import com.consistent.cuervo.transporte.constants.TrasportePortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mx.com.cuervo.rutas.transporte.model.Ruta;
import mx.com.cuervo.rutas.transporte.service.RutaLocalService;
import mx.com.cuervo.rutas.transporte.service.RutaLocalServiceUtil;


@Component(
		 property = {
		 "javax.portlet.name="+ TrasportePortletKeys.Trasporte,
		 "mvc.command.name=getRutas"
		 },
		 service = MVCResourceCommand.class
		 )
public class RutasMVCResourceCommand extends BaseMVCResourceCommand {

	/**
	 * variable para la muestra de Logs.
	 */
	private static Log _log = LogFactoryUtil.getLog(RutasMVCResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		_log.info("ServeResource ");
		resourceResponse.setCharacterEncoding("UTF-8");
		
		ThemeDisplay themeDisplay = 
				(ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		
		 List<Ruta> rutas = RutaLocalServiceUtil.getRutas(themeDisplay.getScopeGroupId(), 0,
				 RutaLocalServiceUtil.getRutasCount(themeDisplay.getScopeGroupId()));
		
		 JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			
		 for(Ruta ruta: rutas) {
			 _log.info("Capacidad: "+ ruta.getCapacidad());

			JSONObject json = JSONFactoryUtil.createJSONObject();
				 
			 json.put("nombre",ruta.getNombreRuta());
			 json.put("capacidad",ruta.getCapacidad());
			 json.put("disponibilidad",ruta.getDisponibilidad());
			 json.put("id",ruta.getRutaId());
			 jsonArray.put(json);
		 }
		try {
			
				resourceResponse.getWriter().write(jsonArray.toString());
		} catch (IOException e1) {
			_log.error(e1.getCause() + " = " + e1.getMessage());
			
		}

	}

	@Reference(unbind = "-")
	private RutaLocalService _rutaLocalService;

}
