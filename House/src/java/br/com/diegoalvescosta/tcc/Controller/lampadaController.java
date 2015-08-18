package br.com.diegoalvescosta.tcc.Controller;


import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import static java.awt.image.ImageObserver.ERROR;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author diego-dell
 */
@ManagedBean
@RequestScoped
public class lampadaController {

   
    public lampadaController() throws InterruptedException {
        
        
    }
    
    public void LigarQuarto() throws InterruptedException {
        iniciarConexao();
        Thread.sleep(1000);
        enviarDados(luzQuartoON);
        Thread.sleep(1000);
        serialPort.close();
    }
    
    public void DesligarQuarto() throws InterruptedException{
        iniciarConexao();
        Thread.sleep(1000);
        enviarDados(luzQuartoOFF);
        Thread.sleep(1000);
        serialPort.close();
    }
    
        
    
    
    //Envio de dados para arduino

    private static final String luzQuartoOFF = "2";
    private static final String luzQuartoON = "3";
    
    
    //Variaveis de Conexao
    private OutputStream output = null;
    SerialPort serialPort;
    private final String porta="/dev/ttyS81";
    private static final int timeOut = 2000; //2 segundos
    private static final int dataRate = 9600;
  
    
    
    public void iniciarConexao(){
        CommPortIdentifier portaId = null;
        Enumeration portaEnum = CommPortIdentifier.getPortIdentifiers();
        
        while(portaEnum.hasMoreElements()){
            CommPortIdentifier atualPortaId =(CommPortIdentifier) portaEnum.nextElement();
            if(porta.equals(atualPortaId.getName())){
                portaId=atualPortaId;
                break;
            }
        }
        if(portaId == null){
            System.out.println("Não se pode conectar a porta");
            System.exit(ERROR);
        }
        
        try{
            System.out.println("Conectou!");
            serialPort = (SerialPort) portaId.open(this.getClass().getName(), timeOut);
            //parametros da porta serial
            
            serialPort.setSerialPortParams(dataRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            output = serialPort.getOutputStream();
        }catch(Exception e){
            //mostrarErro(e.getMessage());
            System.exit(ERROR);
        }
    }
    
    
    private void enviarDados(String dados){
        
        try {            
            output.write(dados.getBytes());
            System.out.println("Enviou Dados!");
        } catch (IOException ex) {
            
        }
    }
}
