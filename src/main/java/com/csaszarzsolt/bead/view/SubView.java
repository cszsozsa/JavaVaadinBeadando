package com.csaszarzsolt.bead.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Sub View")
@Route()
public class SubView extends AbstractView {

    public SubView() {
        H1 h1 = new H1("SubPage");
        addMenuBar();
        add(h1);
    }
}
