package service;

import dao.IClienteDAO;
import model.Cliente;
import exception.ClienteExistenteException;
import exception.ClienteNaoEncontradoException;
import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements IClienteService {
    private final IClienteDAO clienteDAO;

    public ClienteServiceImpl(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public Cliente salvar(Cliente cliente) throws ClienteExistenteException {
        if (clienteDAO.buscarPorDocumento(cliente.getDocumento()).isPresent()) {
            throw new ClienteExistenteException("Documento já cadastrado: " + cliente.getDocumento());
        }
        return clienteDAO.salvar(cliente);
    }

    @Override
    public Cliente buscarPorDocumento(String documento) throws ClienteNaoEncontradoException {
        return clienteDAO.buscarPorDocumento(documento)
               .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com documento: " + documento));
    }

    @Override
    public Cliente buscarPorId(Long id) throws ClienteNaoEncontradoException {
        return clienteDAO.buscarPorId(id)
               .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com ID: " + id));
    }

    @Override
    public List&lt;Cliente&gt; listarTodos() {
        return clienteDAO.listarTodos();
    }

    @Override
    public void excluir(Long id) throws ClienteNaoEncontradoException {
        if (!clienteDAO.buscarPorId(id).isPresent()) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com ID: " + id);
        }
        clienteDAO.excluir(id);
    }

    @Override
    public Cliente atualizar(Cliente cliente) throws ClienteNaoEncontradoException {
        if (!clienteDAO.buscarPorId(cliente.getId()).isPresent()) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com ID: " + cliente.getId());
        }
        return clienteDAO.atualizar(cliente);
    }
}
