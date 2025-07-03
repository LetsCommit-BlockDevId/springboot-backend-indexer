package id.co.awan.hackathon1.service;

import id.co.awan.hackathon1.repository.LetsCommitAbiPublicRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LetsCommitPubClientService {

    @Value("${web3j.rpc-url}")
    String rpcUrl;

    @Value("${web3j.contract.lets-commit}")
    String contractAddress;

    public String getProtocolAdmin() throws Exception {

        var instance = LetsCommitAbiPublicRepository
                .getInstance(rpcUrl, contractAddress);

        return instance.protocolAdmin()
                .send()
                .getValue();

    }

}
