package org.intermine.web.struts;

/*
 * Copyright (C) 2002-2010 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.intermine.web.logic.template.TemplateBuildState;
import org.intermine.web.logic.session.SessionMethods;

/**
 * Action invoked when user submits general template settings form.
 *
 * @author Thomas Riley
 */
public class TemplateSettingsAction extends InterMineAction
{
    /**
     * Edit template constraint properties.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return an ActionForward object defining where control goes next
     * @exception Exception if the application business logic throws
     *  an exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        TemplateSettingsForm tsf = (TemplateSettingsForm) form;

        ActionErrors errors = tsf.validate(mapping, request);
        saveErrors(request, (ActionMessages) errors);

        TemplateBuildState tbs = SessionMethods.getTemplateBuildState(session);

        tbs.setDescription(tsf.getDescription());
        tbs.setName(tsf.getName());
        tbs.setTitle(tsf.getTitle());
        tbs.setComment(tsf.getComment());

        return new ForwardParameters(mapping.findForward("query")).forward();
    }
}
