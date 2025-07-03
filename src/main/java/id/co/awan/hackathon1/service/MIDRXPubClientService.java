package id.co.awan.hackathon1.service;

import id.co.awan.hackathon1.repository.MIDRXAbiPublicRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class MIDRXPubClientService {

    @Value("${web3j.rpc-url}")
    String rpcUrl;

    @Value("${web3j.contract.m-idrx}")
    String contractAddress;

    public BigInteger getTotalSupply() throws Exception {

        var instance = MIDRXAbiPublicRepository
                .getInstance(rpcUrl, contractAddress);

        return instance.totalSupply()
                .send()
                .getValue();
    }

}
