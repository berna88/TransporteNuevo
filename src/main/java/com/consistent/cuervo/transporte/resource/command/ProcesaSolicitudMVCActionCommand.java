package com.consistent.cuervo.transporte.resource.command;


import com.consistent.cuervo.transporte.constants.TrasportePortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mx.com.cuervo.rutas.transporte.model.Solicitud;
import mx.com.cuervo.rutas.transporte.service.SolicitudLocalService;



@Component(
		 property = {
		 "javax.portlet.name="+ TrasportePortletKeys.Trasporte,
		 "mvc.command.name=procesaSolicitud"
		 },
		 service = MVCActionCommand.class
		 )
public class ProcesaSolicitudMVCActionCommand extends BaseMVCActionCommand {
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		System.out.println("INSIDE PROCESA SOLICITUD");
		
		ThemeDisplay themeDisplay = 
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();
		String noEmpleado = (String) user.getExpandoBridge().getAttribute("No_Empleado");
		
		String mobile = ParamUtil.getString(actionRequest, "telefono","");
		String rutaId=ParamUtil.getString(actionRequest, "rutaElegida","");
		String paradaId=ParamUtil.getString(actionRequest, "paradaElegida","");
		System.out.println("ruta:" + rutaId);
		System.out.println("parada:" + paradaId);

		
	    ServiceContext serviceContext = ServiceContextFactory.getInstance(
	            Solicitud.class.getName(), actionRequest);

		_solicitudLocalService.addSolicitud(serviceContext.getUserId(),
				Long.valueOf(rutaId).longValue(), 
				Long.valueOf(paradaId).longValue(),
				Long.valueOf(noEmpleado).longValue(), 
				mobile, user.getEmailAddress(), false,
				serviceContext);
	}
	

	@Reference(unbind = "-")
	private SolicitudLocalService _solicitudLocalService;

}
