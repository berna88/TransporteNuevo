package com.consistent.cuervo.transporte.resource.command;

import com.consistent.cuervo.transporte.constants.TrasportePortletKeys;
import com.consistent.cuervo.transporte.portlet.TrasportePortlet;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
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

import mx.com.cuervo.rutas.transporte.model.Parada;
import mx.com.cuervo.rutas.transporte.model.Ruta;
import mx.com.cuervo.rutas.transporte.service.ParadaLocalService;
import mx.com.cuervo.rutas.transporte.service.RutaLocalService;
import mx.com.cuervo.transporte.fileentries.service.api.FileEntriesCuervoTransporteService;

@Component(
		 property = {
		 "javax.portlet.name="+ TrasportePortletKeys.Trasporte,
		 "mvc.command.name=getParadas"
		 },
		 service = MVCResourceCommand.class
		 )
public class ParadasMVCResourceCommand extends BaseMVCResourceCommand {
	
	private static Log _log = LogFactoryUtil.getLog(ParadasMVCResourceCommand.class);
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
	
	//	System.out.println("INSIDE GetParadas");
		
		ThemeDisplay themeDisplay = 
				(ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		 JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			
		String rutaId=resourceRequest.getParameter("rutaId");
		List <Parada> paradas = _paradaLocalService.getParadas(
				themeDisplay.getScopeGroupId(), Long.valueOf(rutaId));
			
		Ruta ruta = _rutaLocalService.getRuta(Long.valueOf(rutaId));
		_log.info(" antes paradas: ");
		for(Parada parada: paradas) {
			JSONObject json = JSONFactoryUtil.createJSONObject();
		
			if(parada.getNombreArchivo().equals("N/A") ||parada.getNombreArchivo().equals("no aplica"))
			{
				json.put("urlImage", "");
					
			}else {
				long imageMapaIds = 0;
				imageMapaIds = _fileEntriesCuervoTransporteService.getFileId(parada.getNombreCarpeta(),
						"map.png", themeDisplay);
				
				if(imageMapaIds != 0) {
					_log.info("Existe el mapa");
					DLFileEntry imageFEMap = null;
					imageFEMap = DLFileEntryLocalServiceUtil.getFileEntry(imageMapaIds);
					imageFEMap.getLatestFileVersion(true);
					String urlMapa = _fileEntriesCuervoTransporteService.getUrlFile(themeDisplay, imageFEMap);
					json.put("urlMapa", urlMapa);
				}else {
					_log.info("No existe el mapa");
				}
				if(!parada.getNombreArchivo().isEmpty()) {
					long imageId = _fileEntriesCuervoTransporteService.getFileId(parada.getNombreCarpeta(),
							parada.getNombreArchivo(), themeDisplay);
					DLFileEntry imageFE = null;
					imageFE = DLFileEntryLocalServiceUtil.getFileEntry(imageId);
					imageFE.getLatestFileVersion(true);
					String urlImage = _fileEntriesCuervoTransporteService.getUrlFile(themeDisplay, imageFE);
					json.put("urlImage", urlImage);
				}
			}
			
			json.put("descripcionParada", parada.getDescripcion());
			json.put("nombreRuta", ruta.getNombreRuta());
			json.put("nombreParada",parada.getNombreParada());
			json.put("horarioParada", parada.getHorario());
			jsonArray.put(json);
				
			}
		try {
			
	//		System.out.println(jsonArray.toString());
			resourceResponse.getWriter().write(jsonArray.toString());
		} catch (IOException e1) {
			System.out.println(e1.getCause() + " = " + e1.getMessage());
			
		}
	}

	@Reference(unbind="-")
	private ParadaLocalService _paradaLocalService;

	@Reference(unbind="-")
	private RutaLocalService _rutaLocalService;

	@Reference(unbind="-")
	FileEntriesCuervoTransporteService _fileEntriesCuervoTransporteService;
}
