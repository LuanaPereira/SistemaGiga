
package controle;

import dao.Dao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Pedidos;

@ManagedBean (name="pedidoGerenciar")
@ViewScoped
public class PedidoGerenciar implements Serializable{   

    private List<Pedidos> ped; 
    private Dao<Pedidos> dao;
    private List<Pedidos> atendidos = new ArrayList<>(); 
    private Pedidos novo;
    private Pedidos temp;
    private boolean mostraPopupNovo;
    
    public PedidoGerenciar(){
        dao = new Dao(Pedidos.class);
        novo = new Pedidos();
        ped = dao.listarTodos(); 
        mostraPopupNovo = false; 
    }

    
    public void atenderPedido(Pedidos u){
        u.setAtendido(true); 
        atendidos.add(u); 
        dao.alterar(u); 
        ped.remove(u); 
    }
    public void excluirPedido(Pedidos u){
        dao.excluir(u.getId());
        ped.remove(u); 
    }
    
    public void inserirPedido(){
        dao.inserir(novo);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage
        (null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Pedido cadastrado", null));
        ped = dao.listarTodos();
        novo = new Pedidos();
    }
    
    public void preparaEditarPedido(Pedidos u){
        temp = u; 
    }
    
    public void alterarPedido(){
        dao.alterar(temp);
    }
    
    public void abrirPopupNovo() {
        this.mostraPopupNovo = true;
    }
    
    public void fecharPopupNovo(){
        mostraPopupNovo = false;
    }
    
    public boolean isMostraPopupNovo() {
        return mostraPopupNovo;
    }

    public void setMostraPopupNovo(boolean mostraPopupNovo) {
        this.mostraPopupNovo = mostraPopupNovo;
    }
    
    public List<Pedidos> getPedidos() {
        return ped;
    }

    public void setUsuarios(List<Pedidos> ped) {
        this.ped = ped;
    }

    public Dao<Pedidos> getDao() {
        return dao;
    }

    public void setDao(Dao<Pedidos> dao) {
        this.dao = dao;
    }

    public Pedidos getNovo() {
        return novo;
    }

    public void setNovo(Pedidos novo) {
        this.novo = novo;
    }
    
    public Pedidos getTemp() {
        return temp;
    }

    public void setTemp(Pedidos temp) {
        this.temp = temp;
    }
    public List<Pedidos> getAtendidos() {
        return atendidos;
    }

    public void setAtendidos(List<Pedidos> atendidos) {
        this.atendidos = atendidos;
    }
}