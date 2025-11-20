package br.com.drwars.blockchain;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

public class OfertasContract extends Contract {
	private static String binary = "608060405234801561001057600080fd5b50610587806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c806346f81a87146100465780639507d39a1461005b578063f745630f14610085575b600080fd5b610059610054366004610224565b610098565b005b61006e610069366004610270565b610109565b60405161007c929190610289565b60405180910390f35b6100596100933660046102e6565b6101ba565b6040805160606020601f86018190040282018101835291810184815290918291908690869081908501838280828437600092018290525093855250505060209182018490528381529081905260409020815181906100f690826103d1565b5060208201518160010155905050505050565b60008181526020819052604081206001810154815460609392918291829061013090610348565b80601f016020809104026020016040519081016040528092919081815260200182805461015c90610348565b80156101a95780601f1061017e576101008083540402835291602001916101a9565b820191906000526020600020905b81548152906001019060200180831161018c57829003601f168201915b505050505091509250925050915091565b6000838152602081905260409020806101d4838583610491565b5050505050565b60008083601f8401126101ed57600080fd5b50813567ffffffffffffffff81111561020557600080fd5b60208301915083602082850101111561021d57600080fd5b9250929050565b60008060006040848603121561023957600080fd5b833567ffffffffffffffff81111561025057600080fd5b61025c868287016101db565b909790965060209590950135949350505050565b60006020828403121561028257600080fd5b5035919050565b604081526000835180604084015260005b818110156102b7576020818701810151606086840101520161029a565b818111156102c9576000606083860101525b50602083019390935250601f91909101601f191601606001919050565b6000806000604084860312156102fb57600080fd5b83359250602084013567ffffffffffffffff81111561031957600080fd5b610325868287016101db565b9497909650939450505050565b634e487b7160e01b600052604160045260246000fd5b600181811c9082168061035c57607f821691505b60208210810361037c57634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156103cc57600081815260208120601f850160051c810160208610156103a95750805b601f850160051c820191505b818110156103c8578281556001016103b5565b5050505b505050565b815167ffffffffffffffff8111156103eb576103eb610332565b6103ff816103f98454610348565b84610382565b602080601f831160018114610434576000841561041c5750858301515b600019600386901b1c1916600185901b1785556103c8565b600085815260208120601f198616915b8281101561046357888601518255948401946001909101908401610444565b50858210156104815787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b67ffffffffffffffff8311156104a9576104a9610332565b6104bd836104b78354610348565b83610382565b6000601f8411600181146104f157600085156104d95750838201355b600019600387901b1c1916600186901b1783556101d4565b600083815260209020601f19861690835b828110156105225786850135825560209485019460019092019101610502565b508682101561053f5760001960f88860031b161c19848701351681555b505060018560011b018355505050505056fea2646970667358221220f76259c15877c33700cee39b548dead0d8313bb3405f710d27dc7bb780ede1d864736f6c634300080f0033";
    
    public static OfertasContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new OfertasContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static OfertasContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new OfertasContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }	
	
    protected OfertasContract( 
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider gasProvider) {
        super(binary, contractAddress, web3j, credentials, gasProvider);
    }

    protected OfertasContract(
            String contractAddress,
            Web3j web3j,
            TransactionManager transactionManager,
            ContractGasProvider gasProvider) {
        super(binary, contractAddress, web3j, transactionManager, gasProvider);
    }

    
    public Oferta getOferta( BigInteger _id ) {
    	Oferta of = new Oferta();
    	try {
    		List<Type> res = get(_id).send( );
			byte[] decodedBytes = Base64.getDecoder().decode( ((Utf8String)res.get(0)).getValue() );
			String decodedString = new String(decodedBytes);
    		of.content = decodedString;
    		of.id = ((Uint256)res.get(1)).getValue();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return of;
    }
    
    
    private RemoteCall<List<Type>> get(BigInteger _id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
        		"get", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Uint(_id)), 
                Arrays.<TypeReference<?>>asList(  
                	new TypeReference<Utf8String>() {}, 
                	new TypeReference<Uint256>() {}   
                ));   
        return executeRemoteCallMultipleValueReturn(function);
    }
    
    public RemoteFunctionCall<TransactionReceipt> create(String _text, BigInteger _id) {
    	String encoded = Base64.getEncoder().encodeToString( _text.getBytes() );
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                "create", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(encoded), 
                new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }  
    
    public static class Oferta {
        public String content;
        public BigInteger id;
    }    

    
}
