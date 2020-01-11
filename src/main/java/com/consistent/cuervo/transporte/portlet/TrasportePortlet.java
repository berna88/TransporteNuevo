package com.consistent.cuervo.transporte.portlet;

import com.consistent.cuervo.transporte.constants.TrasportePortletKeys;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jonathan Cruz S�nchez
 */
@Component(
		
	configurationPid = "com.consistent.cuervo.transporte.configuracion.ConfigurationTransporte",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TrasportePortletKeys.Trasporte,
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TrasportePortlet extends MVCPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();
		String noEmpleado = (String) user.getExpandoBridge().getAttribute("No_Empleado");
		String mobile = (String) user.getExpandoBridge().getAttribute("mobile");
		
		renderRequest.setAttribute("noEmpleado", noEmpleado);
		renderRequest.setAttribute("mobile", mobile);
		
		super.render(renderRequest, renderResponse);
	}
}