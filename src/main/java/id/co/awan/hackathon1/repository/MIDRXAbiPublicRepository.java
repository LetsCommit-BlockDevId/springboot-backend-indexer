package id.co.awan.hackathon1.repository;

import id.co.awan.hackathon1.model.evm.ERC20Abi;
import id.co.awan.hackathon1.model.evm.LetsCommitAbi;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;


public class MIDRXAbiPublicRepository extends ERC20Abi implements AutoCloseable {

    protected MIDRXAbiPublicRepository(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    @Override
    public void close() throws Exception {
        super.web3j.close();
    }

    public static MIDRXAbiPublicRepository getInstance(String rpcUrl, String contractAddress) {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        ReadonlyTransactionManager transactionManager = new ReadonlyTransactionManager(web3j, "0x0000000000000000000000000000000000000000");
        DefaultGasProvider contractGasProvider = new DefaultGasProvider();
        return new MIDRXAbiPublicRepository(contractAddress, web3j, transactionManager, contractGasProvider);
    }

}
