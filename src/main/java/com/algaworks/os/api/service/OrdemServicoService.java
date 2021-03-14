package com.algaworks.os.api.service;

import com.algaworks.os.api.exceptionhandler.EntidadeNaoEncontradaException;
import com.algaworks.os.api.exceptionhandler.NegocioException;
import com.algaworks.os.domain.model.Cliente;
import com.algaworks.os.domain.model.Comentario;
import com.algaworks.os.domain.model.OrdemServico;
import com.algaworks.os.domain.model.StatusOrdemServico;
import com.algaworks.os.domain.repository.ComentarioRepository;
import com.algaworks.os.domain.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final ClienteService clienteService;
    private final ComentarioRepository comentarioRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteService.buscaPorIdOuJogaBadRequestException(ordemServico.getCliente().getId());

        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        ordemServico.setCliente(cliente);

        return ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public OrdemServico buscaPorIdOuJogaUmaBadRequestException(Long id) {
        return ordemServicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
    }

    public void finalizar(Long ordemServicoId) {
        OrdemServico ordemServico = buscaPorIdOuJogaUmaBadRequestException(ordemServicoId);
        if(!StatusOrdemServico.ABERTA.equals(ordemServico.getStatus())) {
            throw new NegocioException("Ordem de serviço não pode ser finalizada");
        }
        ordemServico.setStatus(StatusOrdemServico.FINALIZADA);
        ordemServico.setDataFinalizacao(OffsetDateTime.now());

    }

    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = buscaPorIdOuJogaUmaBadRequestException(ordemServicoId);

        Comentario comentario = Comentario.builder()
                .dataEnvio(OffsetDateTime.now())
                .descricao(descricao)
                .ordemServico(ordemServico)
                .build();

        return comentarioRepository.save(comentario);
    }

}
