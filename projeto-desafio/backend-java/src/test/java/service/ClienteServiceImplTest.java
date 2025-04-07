package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.IClienteDAO;
import exception.ClienteExistenteException;
import exception.ClienteNaoEncontradoException;
import model.Cliente;

class ClienteServiceImplTest {

    @Mock
    private IClienteDAO clienteDAO;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setDocumento("12345678901");
    }

    // Testes anteriores mantidos...

    @Test
    void testBuscarPorIdExistente() throws ClienteNaoEncontradoException {
        when(clienteDAO.buscarPorId(1L)).thenReturn(Optional.of(cliente));
        
        Cliente result = clienteService.buscarPorId(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testBuscarPorIdNaoExistente() {
        when(clienteDAO.buscarPorId(anyLong())).thenReturn(Optional.empty());
        
        assertThrows(ClienteNaoEncontradoException.class, () -> {
            clienteService.buscarPorId(99L);
        });
    }

    @Test
    void testListarTodos() {
        List&lt;Cliente&gt; clientes = Arrays.asList(cliente);
        when(clienteDAO.listarTodos()).thenReturn(clientes);
        
        List&lt;Cliente&gt; result = clienteService.listarTodos();
        
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testExcluirClienteExistente() throws ClienteNaoEncontradoException {
        when(clienteDAO.buscarPorId(1L)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteDAO).excluir(1L);
        
        clienteService.excluir(1L);
        
        verify(clienteDAO, times(1)).excluir(1L);
    }

    @Test
    void testExcluirClienteNaoExistente() {
        when(clienteDAO.buscarPorId(anyLong())).thenReturn(Optional.empty());
        
        assertThrows(ClienteNaoEncontradoException.class, () -> {
            clienteService.excluir(99L);
        });
    }

    @Test
    void testAtualizarClienteExistente() throws ClienteNaoEncontradoException {
        when(clienteDAO.buscarPorId(1L)).thenReturn(Optional.of(cliente));
        when(clienteDAO.atualizar(any(Cliente.class))).thenReturn(cliente);
        
        Cliente result = clienteService.atualizar(cliente);
        
        assertNotNull(result);
        verify(clienteDAO, times(1)).atualizar(cliente);
    }

    @Test
    void testAtualizarClienteNaoExistente() {
        when(clienteDAO.buscarPorId(anyLong())).thenReturn(Optional.empty());
        
        assertThrows(ClienteNaoEncontradoException.class, () -> {
            clienteService.atualizar(cliente);
        });
    }
}
