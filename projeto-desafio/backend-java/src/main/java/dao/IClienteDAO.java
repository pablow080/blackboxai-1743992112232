package dao;

import java.util.List;
import java.util.Optional;
import model.Cliente;

public interface IClienteDAO {
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarPorId(Long id);
    Optional<Cliente> buscarPorDocumento(String documento);
    List<Cliente> listarTodos();
    void excluir(Long id);
    Cliente atualizar(Cliente cliente);
}