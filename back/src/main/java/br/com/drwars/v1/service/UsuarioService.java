package br.com.drwars.v1.service;

import br.com.drwars.converter.DozerConverter;
import br.com.drwars.entites.Empresa;
import br.com.drwars.entites.Perfil;
import br.com.drwars.entites.Usuario;
import br.com.drwars.exception.CampoInvalidoException;
import br.com.drwars.exception.InvalidoPermissaoException;
import br.com.drwars.exception.NotInsertException;
import br.com.drwars.exception.ResourceNotFoundException;
import br.com.drwars.repositories.EmpresaRepository;
import br.com.drwars.repositories.UsuarioRepository;
import br.com.drwars.sevice.UsuarioServices;
import br.com.drwars.sevice.EmailService;
import br.com.drwars.util.Constantes;
import br.com.drwars.util.PasswordUtil;
import br.com.drwars.util.UsuarioLogado;
import br.com.drwars.v1.vo.UsuarioEmpresaVO;
import br.com.drwars.v1.vo.UsuarioVO;
import br.com.drwars.v1.vo.security.UsuarioTrocaSenhaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoUsuarioListar;
import static br.com.drwars.util.security.ValidaPermissao.validaPermissaoUsuarioPersistir;

@Service
public class UsuarioService {

    private Logger logger = Logger.getLogger(UsuarioServices.class.getName());
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private EmailService emailService;

    @Autowired 
    private UsuarioService usuarioService;


    public Page<UsuarioVO> findUsuarioByNome(String nome, Pageable pageable) {
        logger.warning("Listar usuários");
        validaPermissaoUsuarioListar();
        Page<Usuario>  listaPage = null;
        if(nome==null || nome.isBlank()){
            listaPage= repository.findAll(pageable);
        }else {
            listaPage = repository.findByUsername(nome, pageable);
        }
        var listaVosPage = listaPage.map(p-> DozerConverter.parseObject(p,UsuarioVO.class));
        return listaVosPage;
    }

    public UsuarioVO findById(Long id) {
        logger.warning("Buscar usuário por id");
        validaPermissaoUsuarioListar();
        var model = (Usuario) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário  não  encontrado!"));
        if(model !=null)
            return DozerConverter.parseObject(model, UsuarioVO.class);
        else
            return   null;
    }
    
    public Usuario findUsuarioById(Long id) {
        logger.warning("Buscar usuário por id");
        var model = (Usuario) repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário  não  encontrado!"));
        if(model !=null)
            return model;
        else
            return   null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public UsuarioVO create(UsuarioVO vo) throws NotInsertException {
        logger.warning("Cadastrar usuário");
        validaPermissaoUsuarioPersistir();
        try {
        	var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
        	var model = new Usuario();
            preecherModel(vo, model);

            var user = this.repository.findByUsername(vo.getLogin());
            
            if(user!=null) {
            	throw new CampoInvalidoException("Já existe usuário cadastrado com esse nome");
            }
            
            var senha =PasswordUtil.gerarSenha();
            var  password = PasswordUtil.getSenhaCriptografada(senha);
            model.setPassword(password);
            model.setAccountNonLocked(true);
            model.setAccountNonExpired(true);
            model.setEnabled(true);
            model.setCredentialsNonExpired(true);
            
            model.setDataInclusao(LocalDateTime.now());
            model.setUsuarioInclusao(usuario);
            model.setDataAlteracao(LocalDateTime.now());
            model.setUsuarioAlteracao(usuario);
            model.setEmpresa(usuario.getEmpresa());
            
            repository.save(model);
            emailService.sendEmailPassword(model, senha);

            return new UsuarioVO(usuario);
        }catch (Exception e){
            logger.warning("ERROR  INCLUSAO "+e.getMessage());
            throw new NotInsertException("Erro  na inclusõo do usuário");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public UsuarioVO createUsuarioEmpresa(UsuarioEmpresaVO vo) throws NotInsertException, CampoInvalidoException {
        logger.warning("Cadastrar usuário");

        try {

            var existsEmpresa = empresaRepository.findEmpresaByCnpj(vo.getEmpresa().getCnpj());
            if(existsEmpresa!=null){
                throw new CampoInvalidoException("Esse  cnpj já está cadastrado!");
            }

            var user = this.repository.findByUsername(vo.getLogin());

            if(user!=null) {
                throw new CampoInvalidoException("Já existe usuário cadastrado com esse nome");
            }

            var userEmail = this.repository.findByEmail(vo.getEmail());

            if(userEmail!=null && !userEmail.isEmpty()) {
                throw new CampoInvalidoException("Já existe usuário cadastrado com esse e-mail.");
            }

            var empresa = this.empresaService.create(vo.getEmpresa());

            var model = new Usuario();
            model.setUserName(vo.getLogin());
            model.setFullName(vo.getFullName());
            model.setEmail(vo.getEmail());
            if(vo.getIdperfil()!=null) {
                var perfil = perfilService.findById(vo.getIdperfil()) ;
                model.setPerfil(DozerConverter.parseObject(perfil, Perfil.class));
            }
            model.setEmpresa(DozerConverter.parseObject(empresa, Empresa.class));


            var senha =vo.getSenha();
            var  password = PasswordUtil.getSenhaCriptografada(senha);
            model.setPassword(password);
            model.setAccountNonLocked(true);
            model.setAccountNonExpired(true);
            model.setEnabled(true);
            model.setCredentialsNonExpired(true);

            model.setDataInclusao(LocalDateTime.now());
            model.setUsuarioInclusao(null);
            model.setDataAlteracao(LocalDateTime.now());
            model.setUsuarioAlteracao(null);

            repository.save(model);
        //    emailService.sendEmailPassword(model, senha);

            return new UsuarioVO(model);
        }catch (CampoInvalidoException campoInvalidoException) {
            throw  new CampoInvalidoException(campoInvalidoException.getMessage());
        }catch (Exception e){
            logger.warning("ERROR  INCLUSAO "+e.getMessage());
            throw new NotInsertException("Erro  na inclusão do usuário");
        }
    }


    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public UsuarioVO update(UsuarioVO vo) throws NotInsertException,CampoInvalidoException {
        logger.warning("Alterar usuário");
        validaPermissaoUsuarioPersistir();
        try {
	        var model = (Usuario) repository.findById(vo.getId())
	                .orElseThrow(() -> new ResourceNotFoundException("Usuário  não  encontrado1"));
	        
	        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());
	        var user = this.repository.findByUsername(vo.getLogin());
	        if(user!=null && !user.getId().equals(usuario.getId())) {
	        	throw new CampoInvalidoException("Já existe usuário cadastrado com esse nome");
	        }

       		model.setDataAlteracao(LocalDateTime.now());
       		model.setUsuarioAlteracao(usuario);
	        preecherModel(vo, model);
	        repository.save(model);
            return DozerConverter.parseObject(model, UsuarioVO.class);
        
        }catch (Exception e){
            logger.warning("ERROR  ALTERACAO "+e.getMessage());
            throw new NotInsertException("Erro  na alteração do usuário");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void trocaSenha(UsuarioTrocaSenhaVO vo) throws NotInsertException,CampoInvalidoException {
        logger.warning("Trocar senha usuário");

        var usuario = usuarioService.findUsuarioById(UsuarioLogado.getUser().getId());

        if(!vo.getNovaSenha().equals(vo.getConfirmaSenha()))
            throw new CampoInvalidoException("Nova senha deve ser igual ao confirmar senha");

        var senhaAtualCriptografada  = PasswordUtil.getSenhaCriptografada(vo.getSenhaAtual());

        var novaSenhaCriptografada  = PasswordUtil.getSenhaCriptografada(vo.getNovaSenha());

        if(usuario!=null && usuario.getPassword().equals(senhaAtualCriptografada)) {
            throw new CampoInvalidoException("A senha atual está incorreta!");
        }

        usuario.setPassword(novaSenhaCriptografada);

        try {
            repository.save(usuario);

        }catch (Exception e){
            logger.warning("ERROR  ALTERACAO "+e.getMessage());
            throw new NotInsertException("Erro  na alteração do usuário");
        }

    }
    
    public Usuario findByUsername(String username) {
        logger.warning("Buscar usuário por username");
        var model = (Usuario) repository.findByUsername(username);
        return  model;
    }

    public List<Usuario> findByEmpresa(Long idEmpresa) {
        logger.warning("Listar usuários");
        List<Usuario>  listaPage = repository.findByEmpresa(idEmpresa);
        return listaPage;
    }


    private void preecherModel(UsuarioVO vo, Usuario model) {
        model.setUserName(vo.getLogin());
        model.setFullName(vo.getFullName());
        model.setEmail(vo.getEmail());
        if(vo.getIdperfil()!=null) {
            var perfil = perfilService.findById(vo.getIdperfil()) ;
            model.setPerfil(DozerConverter.parseObject(perfil, Perfil.class));
        }

        if(vo.getIdempresa()!=null) {
            var empresa = empresaService.findEmpresaById(vo.getIdempresa()) ;
            model.setEmpresa(empresa);
        }
    }


}
