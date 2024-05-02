package com.potato.nckuppp

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.router.Route


data class Foo(val id: Int, val name: String)


@Route("")
internal class MainView : VerticalLayout() {
    
    init {
        add(H1("Hello World"))
    }
}