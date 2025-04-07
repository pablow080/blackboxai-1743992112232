package controller;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class BasePage extends WebPage {
    public BasePage() {
        add(new BookmarkablePageLink<>("listaClientesLink", ListaClientesPage.class));
        add(new BookmarkablePageLink<>("novoClienteLink", CadastroClientePage.class));
    }
}
