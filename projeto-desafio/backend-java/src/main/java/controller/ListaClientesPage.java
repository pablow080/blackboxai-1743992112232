package controller;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import service.IClienteService;
import model.Cliente;

public class ListaClientesPage extends BasePage {
    @SpringBean
    private IClienteService clienteService;

    public ListaClientesPage() {
        add(new ListView&lt;Cliente&gt;("clientes", clienteService.listarTodos()) {
            @Override
            protected void populateItem(ListItem&lt;Cliente&gt; item) {
                Cliente cliente = item.getModelObject();
                item.add(new Label("documento", cliente.getDocumento()));
                item.add(new Label("nome", cliente.getNome()));
            }
        });
    }
}
