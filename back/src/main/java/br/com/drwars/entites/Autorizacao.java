package br.com.drwars.entites;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_autorizacoes")
public class Autorizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tb_autorizacoes_id_autorizacao_seq")
    @SequenceGenerator(name = "tb_autorizacoes_id_autorizacao_seq", sequenceName = "tb_autorizacoes_id_autorizacao_seq", allocationSize = 1)
    @Column(name = "id_autorizacao")
    private Long id;

    @Column(name = "dt_autorizacao")
    private LocalDateTime dataAutorizacao;

    @Column(name = "dt_validade")
    private LocalDateTime dataValidade;

    @ManyToOne
    @JoinColumn(name="id_empresa_autorizada",referencedColumnName = "id_empresa")
    private Empresa empresaAutorizada;

}
