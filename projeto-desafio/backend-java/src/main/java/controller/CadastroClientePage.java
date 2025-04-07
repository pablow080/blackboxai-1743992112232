package controller;

import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import service.IClienteService;
import model.Cliente;
import model.Cliente.TipoPessoa;

public class CadastroClientePage extends BasePage {
    @SpringBean
    private IClienteService clienteService;

    public CadastroClientePage() {
        Form&lt;Cliente&gt; form = new Form&lt;&gt;("form", Model.of(new Cliente()));
        
        // Campo para seleção do tipo de pessoa
        RadioGroup&lt;TipoPessoa&gt; tipoPessoa = new RadioGroup&lt;&gt;("tipoPessoa");
        tipoPessoa.add(new Radio&lt;&gt;("fisica", Model.of(TipoPessoa.FISICA)));
        tipoPessoa.add(new Radio&lt;&gt;("juridica", Model.of(TipoPessoa.JURIDICA)));
        form.add(tipoPessoa);

        // Campos para pessoa física
        form.add(new TextField&lt;&gt;("nome").setRequired(false));
        form.add(new TextField&lt;&gt;("documento")
            .add(StringValidator.exactLength(11))); // CPF
        
        // Campos para pessoa jurídica
        form.add(new TextField&lt;&gt;("razaoSocial").setRequired(false));
        form.add(new TextField&lt;&gt;("documentoJuridico")
            .add(StringValidator.exactLength(14))); // CNPJ

        form.add(new Button("salvar") {
            @Override
            public void onSubmit() {
                Cliente cliente = form.getModelObject();
                clienteService.salvar(cliente);
                setResponsePage(ListaClientesPage.class);
            }
        });
        
        add(form);
    }
}
