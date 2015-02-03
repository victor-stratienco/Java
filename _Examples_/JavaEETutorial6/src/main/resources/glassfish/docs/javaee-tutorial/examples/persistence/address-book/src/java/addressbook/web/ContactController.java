/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package addressbook.web;

import addressbook.entity.Contact;
import addressbook.web.util.JsfUtil;
import addressbook.web.util.PaginationHelper;
import addressbook.ejb.ContactFacade;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;


@ManagedBean(name = "contactController")
@SessionScoped
public class ContactController {
    @EJB
    private addressbook.ejb.ContactFacade ejbFacade;
    private Contact current;
    private DataModel items = null;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ContactController() {
    }

    public Contact getSelected() {
        if (current == null) {
            current = new Contact();
            selectedItemIndex = -1;
        }

        return current;
    }

    private ContactFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                        @Override
                        public int getItemsCount() {
                            return getFacade()
                                       .count();
                        }

                        @Override
                        public DataModel createPageDataModel() {
                            return new ListDataModel(
                                    getFacade().findRange(
                                            new int[] {
                                                getPageFirstItem(),
                                                getPageFirstItem()
                                                + getPageSize()
                                            }));
                        }
                    };
        }

        return pagination;
    }

    public String prepareList() {
        recreateModel();

        return "List";
    }

    public String prepareView() {
        current = (Contact) getItems()
                                .getRowData();
        selectedItemIndex = pagination.getPageFirstItem()
            + getItems()
                  .getRowIndex();

        return "View";
    }

    public String prepareCreate() {
        current = new Contact();
        selectedItemIndex = -1;

        return "Create";
    }

    public String create() {
        try {
            getFacade()
                .create(current);
            JsfUtil.addSuccessMessage(
                    ResourceBundle.getBundle("/Bundle").getString(
                            "ContactCreated"));

            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(
                    e,
                    ResourceBundle.getBundle("/Bundle").getString(
                            "PersistenceErrorOccured"));

            return null;
        }
    }

    public String prepareEdit() {
        current = (Contact) getItems()
                                .getRowData();
        selectedItemIndex = pagination.getPageFirstItem()
            + getItems()
                  .getRowIndex();

        return "Edit";
    }

    public String update() {
        try {
            getFacade()
                .edit(current);
            JsfUtil.addSuccessMessage(
                    ResourceBundle.getBundle("/Bundle").getString(
                            "ContactUpdated"));

            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(
                    e,
                    ResourceBundle.getBundle("/Bundle").getString(
                            "PersistenceErrorOccured"));

            return null;
        }
    }

    public String destroy() {
        current = (Contact) getItems()
                                .getRowData();
        selectedItemIndex = pagination.getPageFirstItem()
            + getItems()
                  .getRowIndex();
        performDestroy();
        recreateModel();

        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();

        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();

            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade()
                .remove(current);
            JsfUtil.addSuccessMessage(
                    ResourceBundle.getBundle("/Bundle").getString(
                            "ContactDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(
                    e,
                    ResourceBundle.getBundle("/Bundle").getString(
                            "PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade()
                        .count();

        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;

            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }

        if (selectedItemIndex >= 0) {
            current = getFacade()
                          .findRange(
                        new int[] { selectedItemIndex, selectedItemIndex + 1 })
                          .get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination()
                        .createPageDataModel();
        }

        return items;
    }

    private void recreateModel() {
        items = null;
    }

    public String next() {
        getPagination()
            .nextPage();
        recreateModel();

        return "List";
    }

    public String previous() {
        getPagination()
            .previousPage();
        recreateModel();

        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(
            ejbFacade.findAll(),
            false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(
            ejbFacade.findAll(),
            true);
    }

    @FacesConverter(forClass = Contact.class)
    public static class ContactControllerConverter implements Converter {
        public Object getAsObject(
            FacesContext facesContext,
            UIComponent component,
            String value) {
            if ((value == null) || (value.length() == 0)) {
                return null;
            }

            ContactController controller = (ContactController) facesContext.getApplication()
                                                                           .getELResolver()
                                                                           .getValue(
                        facesContext.getELContext(),
                        null,
                        "contactController");

            return controller.ejbFacade.find(getKey(value));
        }

        Long getKey(String value) {
            Long key;
            key = Long.valueOf(value);

            return key;
        }

        String getStringKey(Long value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);

            return sb.toString();
        }

        public String getAsString(
            FacesContext facesContext,
            UIComponent component,
            Object object) {
            if (object == null) {
                return null;
            }

            if (object instanceof Contact) {
                Contact o = (Contact) object;

                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException(
                        "object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: "
                        + ContactController.class.getName());
            }
        }
    }
}