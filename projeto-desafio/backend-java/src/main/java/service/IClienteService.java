package service;

import model.Cliente;
import java.util.List;

public interface IClienteService {
    Cliente salvar(Cliente cliente);
    Cliente buscarPorId(Long id);
    Cliente buscarPorDocumento(String documento);
    List&lt;Cliente&gt; listarTodos();
    void excluir(Long id);
    Cliente atualizar(Cliente cliente);
}
