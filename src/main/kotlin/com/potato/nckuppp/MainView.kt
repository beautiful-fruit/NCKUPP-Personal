package com.potato.nckuppp

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.addColumnFor
import com.github.mvysny.kaributools.label
import com.potato.nckuppp.callAPI.*
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.page.BodySize
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.data.provider.ListDataProvider
import com.vaadin.flow.router.Route
import org.hibernate.id.insert.Binder

@Route("")
//@BodySize(width = "100vw", height = "100vh")
//@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
//internal class MainView: VerticalLayout() {
//    init {
//        val dept = "A9"
//        val params = mapOf("dept" to dept)
//        val response = getCourseResult(params)
//
//        add(h1("showing the data of $dept"))
//        add(grid(dataProvider = ListDataProvider(response?.data)) {
//            isAllRowsVisible = true
//            addColumnFor(Course::y).setHeader("year")
//            addColumnFor(Course::cn).setHeader("course name")
//        })
//
//    }
//}
class MainView: KComposite() {
    var dept = "A9"
    val params = mapOf("dept" to dept)
    val response = getCourseResult(params)
    var _dept: String = ""

    private val root = ui {
        verticalLayout {
            h1("showing the data of $dept")
            grid(dataProvider = ListDataProvider(response?.data)) {
                addColumnFor(Course::y).setHeader("year")
                addColumnFor(Course::cn).setHeader("course name")
            }
        }
    }
}
