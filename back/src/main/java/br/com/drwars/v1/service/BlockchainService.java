package br.com.drwars.v1.service;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock.Block;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import br.com.drwars.blockchain.OfertasContract;
import br.com.drwars.entites.Oferta;
import br.com.drwars.repositories.OfertaRepository;
import br.com.drwars.v1.vo.oferta.OfertaVO;

@Service
public class BlockchainService {
	private Logger logger = Logger.getLogger(BlockchainService.class.getName());
	private Web3j web3;
	private String walletsFolder = "/wallets";
	private Credentials credentials;
	private OfertasContract contract;
	private String contractAddress = "0xF12b5dd4EAD5F743C6BaA640B0216200e89B60Da";
	private ContractGasProvider gasProvider; 
	
	@Value("${rpc.endpoint}") String rpcEndpoint;
	
	@Autowired private OfertaRepository ofertaRepository;
	
	
	@PostConstruct
	private void init() {
		new File( walletsFolder ).mkdirs();
		logger.info("init blockchain access at " + rpcEndpoint);
		try {
			web3 = Web3j.build( new HttpService( rpcEndpoint ) );
			getVersion();
			credentials = getFromPk("8f2a55949038a9610f50fb23b5883af3b4ecb3c3bb792cbcefbd1542c692be63");
			loadContract();
			
			//Oferta of = getOferta( 1L );
			//System.out.println( of.content );
			//System.out.println( of.id.longValue() );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public br.com.drwars.blockchain.OfertasContract.Oferta getOferta( Long id ) {
		return contract.getOferta( new BigInteger( id.toString() ) );
	}
	
	
	public void createOferta( OfertaVO oferta ) throws Exception {
		Block bl = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
		BigInteger gasLimit = bl.getGasLimit();
		
		//oferta.setEmpresaPrivada( null );
		//oferta.setListaNegociacaoRodada( null );
		
		logger.info("Iniciando gravação da oferta " + oferta.getId() + " na blockchain...");
		String of = new org.json.JSONObject(oferta).toString();
		logger.info("  > Bytes     : " + of.length() );
		logger.info("  > Gas Limit : " + gasLimit );
		contract.create( of, new BigInteger( oferta.getId().toString() ) ).sendAsync().thenAccept( transactionReceipt -> {
			logger.info("Transação concluída no bloco " + transactionReceipt.getBlockNumber().toString() );
			String txHash = transactionReceipt.getTransactionHash();
			logger.info("  > Hash      : " + txHash );
			
			try {
				Optional<Oferta> ofEnt = ofertaRepository.findById( oferta.getId() );
				if( ofEnt.isPresent() ) {
					Oferta ofe = ofEnt.get();
					ofe.setTxBlockchainHash( txHash );
					ofertaRepository.save( ofe );
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}).exceptionally( transactionReceipt  -> {
			logger.info("Transação falhou: " + transactionReceipt.getMessage() );
			return null;
		});
	}
	
	private void loadContract() {
		try {
			BigInteger gasPrice = Convert.toWei("1", Convert.Unit.GWEI).toBigInteger();
			BigInteger gasLimit = BigInteger.valueOf(4700000);
			gasProvider = new StaticGasProvider(gasPrice, gasLimit);
			contract = OfertasContract.load( contractAddress, web3, credentials, gasProvider );
		} catch( Exception e) {
			e.printStackTrace();
		}
	}	
	
	private Credentials getFromPk( String pk ) {
		Credentials credentials = Credentials.create(pk);
		logger.info("PK Address : " + credentials.getAddress() );
		return credentials;
	}	
	
	
	private void getVersion() throws Exception {
		Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
		String clientVersion = web3ClientVersion.getWeb3ClientVersion();
		logger.info( "Client node ID " + clientVersion );
	}
	
	public String transfer( String toAddress, double amount) throws Exception {
		TransactionReceipt transactionReceipt = Transfer.sendFunds( web3, credentials, toAddress, BigDecimal.valueOf( amount ), Convert.Unit.ETHER).send();
		String tx = transactionReceipt.getTransactionHash();
		logger.info( "Transfer transaction: " + tx );
		return tx;
	}
	
	public void balance(String address) throws Exception {
		EthGetBalance ethGetBalance = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get();
		logger.info("Balance: " + Convert.fromWei( ethGetBalance.getBalance().toString() , Unit.ETHER ) );	
	}		
	
}
