package org.acme;

import org.drools.core.process.instance.impl.WorkItemImpl;
import org.jbpm.process.workitem.core.TestWorkItemManager;
import org.jbpm.test.AbstractBaseTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MyConcatWorkItemHandlerTest extends AbstractBaseTest {

    @Test
    public void testHandler() {
        WorkItemImpl workItem = new WorkItemImpl();
        workItem.setParameter("FirstName", "John");
        workItem.setParameter("LastName", "Doe");

        TestWorkItemManager manager = new TestWorkItemManager();

        MyConcatWorkItemHandler handler = new MyConcatWorkItemHandler();
        handler.setLogThrownException(true);
        handler.executeWorkItem(workItem, manager);

        assertNotNull(manager.getResults());
        assertEquals(1, manager.getResults().size());
        assertEquals("John Doe", manager.getResults().get(0L).get("FullName"));
        assertTrue(manager.getResults().containsKey(workItem.getId()));
    }
}
