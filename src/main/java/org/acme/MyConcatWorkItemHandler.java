package org.acme;

import org.jbpm.process.workitem.core.AbstractLogOrThrowWorkItemHandler;
import org.jbpm.process.workitem.core.util.RequiredParameterValidator;
import org.jbpm.process.workitem.core.util.Wid;
import org.jbpm.process.workitem.core.util.WidMavenDepends;
import org.jbpm.process.workitem.core.util.WidParameter;
import org.jbpm.process.workitem.core.util.WidResult;
import org.jbpm.process.workitem.core.util.service.WidAction;
import org.jbpm.process.workitem.core.util.service.WidAuth;
import org.jbpm.process.workitem.core.util.service.WidService;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;

import java.util.HashMap;
import java.util.Map;

@Wid(widfile = "MyConcatDefinitions.wid", name = "MyConcatDefinitions",
        displayName = "MyConcatDefinitions",
        defaultHandler = "mvel: new org.acme.MyConcatWorkItemHandler()",
        documentation = "myconcatworkitem/index.html",
        category = "myconcatworkitem",
        icon = "MyConcatDefinitions.png",
        parameters = {
                @WidParameter(name = "FirstName"),
                @WidParameter(name = "LastName")
        },
        results = {
                @WidResult(name = "FullName")
        },
        mavenDepends = {
                @WidMavenDepends(group = "org.acme", artifact = "myconcatworkitem", version = "1.0.0-SNAPSHOT")
        },
        serviceInfo = @WidService(category = "myconcatworkitem", description = "${description}",
                keywords = "",
                action = @WidAction(title = "Sample Title"),
                authinfo = @WidAuth(required = true, params = {"FirstName", "LastName"},
                        paramsdescription = {"First name", "Last name"},
                        referencesite = "referenceSiteURL")
        )
)
public class MyConcatWorkItemHandler extends AbstractLogOrThrowWorkItemHandler {

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        try {
            RequiredParameterValidator.validate(this.getClass(), workItem);

            String firstName = (String) workItem.getParameter("FirstName");
            String lastName = (String) workItem.getParameter("LastName");

            String fullName = firstName + " " + lastName;

            Map<String, Object> results = new HashMap<String, Object>();
            results.put("FullName", fullName);

            manager.completeWorkItem(workItem.getId(), results);
        } catch (Throwable cause) {
            handleException(cause);
        }
    }

    @Override
    public void abortWorkItem(WorkItem workItem,
                              WorkItemManager manager) {
    }
}
